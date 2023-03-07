package mul.cam.a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;
import mul.cam.a.service.BbsService;

@Controller
public class BbsController {
          		//게시판 관리Controller
	@Autowired
	BbsService service;
	
	@GetMapping(value = "bbslist.do")
	public String bbslit(BbsParam param, Model model) {
		
		//글의 시작과 끝 
		int pn = param.getPageNumber(); //pageNumber넘어오게  0 1 2 3 4
		int start = 1 + (pn * 10); //한페이지에 10개씩 1 11
		int end =( pn + 1 ) * 10;  // 10 20
		
		param.setStart(start);
		param.setEnd(end);
		
		List<BbsDto> list = service.bbslist(param);
		
		//page 총 개수 구하기
		int len = service.getAllBbs(param);
		//몇 page까지 있는지
		int pageBbs = len / 10; // ex) 25/10 -> 몫은 2
		if((len % 10) > 0) {
			pageBbs = pageBbs + 1; //page총수 준비
		}
		
		if(param.getChoice() == null || param.getChoice().equals("")
			|| param.getSearch() == null || param.getSearch().equals("")){
				param.setChoice("검색");
				param.setSearch("");
				
			
		}
			
		model.addAttribute("bbslist",list); //게시판list보내기
		model.addAttribute("pageBbs",pageBbs); //총 page수
		model.addAttribute("pageNumber",param.getPageNumber()); //현재page
		model.addAttribute("choice",param.getChoice());			//검색 카테고리
		model.addAttribute("search",param.getSearch());			//검색어
		
		return "bbslist";
		
	}
		//글쓰기창 만들기
		//method=get
		@GetMapping(value = "bbswrite.do")
		public String bbswrite() {
			return "bbswrite";
		}
		
		//글작성버튼 적용하기
		@PostMapping(value = "bbswriteAf.do")//model로 짐싸기
		public String bbswriteAf(BbsDto dto, Model model) {
			boolean write = service.writeBbs(dto);
			String msg = ""; //초기값잡기
			if(write) {
				msg = "BBS_ADD_OK";
			} else {
				msg = "BBS_ADD_NG";
			}				//짐싸는 이름 
			model.addAttribute("bbswrite",msg);	//BBS_ADD_OK가 뜰때 bbslist.do로 넘어가게 하기
			
			return "message"; //message.jsp로 넘어감
			
			
		}
		//게시글보기 Bbsdetail로 넘어갓!
		@GetMapping(value = "bbsdetail.do") 
		public String bbsdetail(Model model, int seq) {
		   BbsDto dto = service.getBbsInfo(seq); //dto정보담기
		   model.addAttribute("bbsdto",dto); //BbsDto짐싸기
		   
		   return "bbsdetail";
		}
		
		//게시글 수정하기 전글을 보여주게 만들기 (만든다음 bbsupdate.jsp만들기)
		@GetMapping(value = "bbsupdate.do")
		public String bbsupdate(Model model,int seq) {
			BbsDto dto = service.getBbsInfo(seq); //getBbsInfo한번 더 불러주기
			model.addAttribute("bbsdto",dto);
			
			return "bbsupdate";
		}
		//게시글 수정 
		@GetMapping(value = "bbsupdateAf.do")//jsp에서 받아오는거
		public String bbsupdateAf(Model model,BbsDto dto) {
			boolean isS = service.updateBbs(dto);
			String bbsupdateAf = "";
			if(isS) {
				bbsupdateAf = "BBS_UPDATE_OK";
			}else {
				bbsupdateAf = "BBS_UPDATE_NG";
			}
			model.addAttribute("bbsupdate",bbsupdateAf);//message.jsp로 짐싸주기
			
			return "message";//보내주는장소
		}
		//게시글 답글
		@GetMapping(value = "answer.do")
		public String answer(Model model,int seq) {
			 BbsDto dto = service.getBbsInfo(seq); // getBbsInfo원글제목/내용/작성자 등 불러오기
			 model.addAttribute("dto",dto);
			 
			 return "answer";
		}
		//게시글 작성완료 버튼 적용하기
		@PostMapping(value = "answerAf.do")
		public String answerAf(Model model, BbsDto dto){
			boolean answer = service.answerAf(dto);
			String ans = "";
			if(answer) {
				ans = "BBS_ANSWER_OK";
			}else {
				ans = "BBS_ANSWER_NG";
			}
			model.addAttribute("answer",answer);
			
			return "message";
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//댓글보기 
		@ResponseBody //**필수**요거 있어야 ajax요청이랑 list return됨!!
		@GetMapping(value = "commentList.do")
		public List<BbsComment> commentList(int seq){
			List<BbsComment> list = service.commentList(seq); 
									//list에다 BbsComment 담기
			return list;
		}
		
		//댓글쓰기
		@PostMapping(value = "commentWriteAf.do")
		public String commentWriteAf(BbsComment dto) {
			boolean isS = service.commentWrite(dto);
			if(isS) {
				System.out.println("댓글작성에 성공했습니다");
			}else {
				System.out.println("댓글작성에 실패했습니다");
			}
			
			return "redirect:/bbsdetail.do?seq=" + dto.getSeq(); //BbsComment에있는 seq 가지고 bbsdetail.do로 가기
		}
}
