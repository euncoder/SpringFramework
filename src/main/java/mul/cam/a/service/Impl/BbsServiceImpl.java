package mul.cam.a.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.cam.a.dao.BbsDao;
import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;
import mul.cam.a.service.BbsService;

@Service
public class BbsServiceImpl implements BbsService {
		
	@Autowired
	BbsDao dao;

	@Override
	public List<BbsDto> bbslist(BbsParam bbs) {
		// TODO Auto-generated method stub
		return dao.bbslist(bbs);
	}

	@Override
	public int getAllBbs(BbsParam bbs) {
		// TODO Auto-generated method stub
		return dao.getAllBbs(bbs);
	}

	@Override
	public boolean writeBbs(BbsDto dto) {
		int count = dao.writeBbs(dto); // writeBbs불러와서 count적용
		return count>0?true:false;
	}

	@Override
	public BbsDto getBbsInfo(int seq) {
		return dao.getBbsInfo(seq);
	}

	@Override
	public List<BbsComment> commentList(int seq) {
		return dao.commentList(seq);
	}

	@Override
	public boolean commentWrite(BbsComment dto) {
		int count = dao.commentWrite(dto); //댓글작성이 되믄 count올라감
		return count>0?true:false;
	}
	//게시글 수정
	@Override
	public boolean updateBbs(BbsDto dto) {
		int count = dao.updateBbs(dto);
		return count>0?true:false;
	}

	@Override
	public boolean answerAf(BbsDto dto) {
		int count = dao.answerAf(dto);
		return count>0?true:false;
	}
	
}
