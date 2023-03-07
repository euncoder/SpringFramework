package mul.cam.a.dao;

import java.util.List;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;
				//함수 모아넣기
public interface BbsDao {
		
	//글과 페이지넘기기
	List<BbsDto> bbslist(BbsParam bbs); //BbsParam 넣어준값 담기
	//글의 총수
	int getAllBbs(BbsParam bbs);
	//글작성
	int writeBbs(BbsDto dto);
	//글보기
	BbsDto getBbsInfo(int seq);
	//반환타입	   이름	   입력값
	
	//게시글 수정
	int updateBbs(BbsDto dto);
	
	//답글answerAf.do
	int answerAf(BbsDto dto);
	
	
	//댓글보기
	List<BbsComment> commentList (int seq);  
	//댓글쓰기
	int commentWrite(BbsComment dto);
	
}
