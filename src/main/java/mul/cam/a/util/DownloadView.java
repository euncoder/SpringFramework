package mul.cam.a.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;
									//띄어주는view 다운로드 버튼작동하게 만들기

import mul.cam.a.service.PdsService;
public class DownloadView extends  AbstractView {
			//다운받아서 띄어진window작업해주는 window를 나오게하는..;
	@Autowired
	PdsService service; //service에 접근가능
	
	@Override //생성 
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("DownloadView renderMergedOutputModel");
		//다운로드받을때 가는거
		
		File downloadFile = (File)model.get("downloadFile") ; //getattribute랑 비슷허다; 경로와 함께 넘어온 파일
		String filename = (String)model.get("filename"); //원본 파일명
		int seq = (Integer)model.get("seq");//다운로드 카운트 증가
		
		response.setContentType(this.getContentType());
		response.setContentLength((int)downloadFile.length());
		
		//한글파일명 정상적으로 나오게 하기
		filename = URLEncoder.encode(filename, "utf-8");
		
		
		// 다운로드창에 실제로 나오게 하는거								다운로드 받을때 원본파일로 바꿔주는것
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
							//2진수 형태로 받게하는것
		response.setHeader("Content-Transfer-Encoding", "binary;");
							//Content-Length 파일 길이값
		response.setHeader("Content-Length", "" + downloadFile.length()); //filename길이값
							//저장을 하겠느냐 (no-cache 안하겠다고 설정함)
		response.setHeader("Pragma", "no-cache;"); 
							//기한두기 필요없으면 -1
		response.setHeader("Expires", "-1;");
		
		OutputStream os = response.getOutputStream(); //생성해주는 작업
		FileInputStream fis = new FileInputStream(downloadFile);//파일안에
		
		//위에 객체2개 생성한것을 data 복사한다/ 실제 데이터를 기입하는부분
		FileCopyUtils.copy(fis, os);
		
		//down load count증가
		
		
		if(fis !=null) {
			fis.close(); //null이 아니면 파일닫아주기
		}
		
		
		
	}
	
	
	
}
