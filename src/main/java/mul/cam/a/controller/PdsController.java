package mul.cam.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mul.cam.a.dto.PdsDto;
import mul.cam.a.service.PdsService;
import mul.cam.a.util.PdsUtil;

@Controller   //자료실만들고 파일다운받는기능
public class PdsController {
	
	@Autowired
	PdsService service;
	
	@RequestMapping(value = "pdslist.do",method = RequestMethod.GET)
	public String pdslist(Model model) {
		List<PdsDto> list = service.pdslist();
		model.addAttribute("pdslist",list);
		
		return "pdslist";
	}
	
	@GetMapping(value = "pdswrite.do")
	public String pdswrite() {
		return "pdswrite";
	}
	
	//파일업로드
	@PostMapping(value = "pdsupload.do")
						   //PdsDto
	public String pdsupload(PdsDto dto, 		
							//파일업로드할때 파일 바이트로 날라오는거		//업로드 false(확인안하겠다)
							@RequestParam(value = "fileload" ,required = false)
							MultipartFile fileload,
							//파일경로얻기
							HttpServletRequest req) {
		
		//filename취득
		String filename = fileload.getOriginalFilename(); //원본의 파일명
		
		dto.setFilename(filename); //원본파일명설정(DB에 넣기위한 작업)
		
		//upload경로 설정하기 HttpServletRequest req
		//server 업로드만들기
		String fupload = req.getServletContext().getRealPath("/upload"); //upload파일 장소지정해줌
		//이 윗줄이 파일 업로드 경로를 지정하는곳
		
		//folder
		//String fupload = "c:\\temp"; //파일업로드 주소 지정
		
		System.out.println("fupload:" + fupload);// >> 출력하면
		
		//file명을 충돌되지 않는 명칭(Date)으로 변경
		String newfilename = PdsUtil.getNewFileName(filename); //util에 작성한 함수
		
		dto.setNewfilename(newfilename); //변경된 file명으로 저장됨 (DB에 말고)
		
//		File file = new File(newfilename + "/" + newfilename); //file업로드 경로 + + "/" + newfilename 충돌되지 않게하기 위해
		File file = new File(fupload + "/" + newfilename); //file업로드 경로 + + "/" + newfilename 충돌되지 않게하기 위해
		
		try {
			//실제로 파일생성 + 기입 = 업로드
			//File생성 file만들면서  fileload로 날라온.getBytes를 file로기입하라
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			
			System.out.println(dto.toString());
			
			//DB에 저장
			service.uploadPds(dto);
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return "redirect:/pdslist.do";
		
	}
	
	// 파일 다운로드 하기
	@PostMapping(value = "filedownLoad.do") //들어오는값 3개를 download view로 보내기
	public String filedownLoad(int seq, String filename, String newfilename, Model model, 
			HttpServletRequest req){
		//경로 취득
		String fupload = req.getServletContext().getRealPath("/upload");
		//폴더
		//String fupload = "c:\\temp";
		
		
		//다운로드 받을 파일 객체로 넘겨
		File downloadFile = new File(fupload + "/" + newfilename); //경로와 함께 실제 넘긴 파일을 넘겨
		
		//3개 넘기고 위에 downloadFile넘기고 downloadView짐싸서 넘기기
		model.addAttribute("downloadFile",downloadFile); //(file) 다운로드 하는 파일명 보내기(실제 업로드 되어있는 파일명)
		model.addAttribute("filename",filename); //(String) 원 파일명
		model.addAttribute("seq",seq); //(int)download count증가 시키기 위해seq를 넣어둠
		return "DownloadView"; //downloadView로 가라
	}
	
	
	//update
	
	
}
