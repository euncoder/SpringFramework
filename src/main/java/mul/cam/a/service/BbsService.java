package mul.cam.a.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

@Service
public interface BbsService {

	
		
		List<BbsDto> bbslist(BbsParam bbs);
		
		int getAllBbs(BbsParam bbs); //getAllBbs에 resultType있어서 int가능
		
		boolean writeBbs(BbsDto dto); 
		
		BbsDto getBbsInfo(int seq);
		
		//게시글 수정
		boolean updateBbs(BbsDto dto);
		
		boolean answerAf(BbsDto dto);
		
		List<BbsComment> commentList (int seq); 
		
		boolean commentWrite(BbsComment dto); //commentWrite에 insert라 resultType없어서 int..애바boolean ㄱㄱ
}
