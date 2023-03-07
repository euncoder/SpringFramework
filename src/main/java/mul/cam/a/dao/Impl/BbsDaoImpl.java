package mul.cam.a.dao.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.BbsDao;
import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

@Repository
public class BbsDaoImpl implements BbsDao {

	@Autowired // 싱글톤으로 하나만 연결
	SqlSession session;
	
	String ns = "Bbs."; // ns로 접근

	@Override
	public List<BbsDto> bbslist(BbsParam bbs) {
		return session.selectList(ns + "bbslist" , bbs);
	}

	@Override
	public int getAllBbs(BbsParam bbs) {
		return session.selectOne(ns + "getAllBbs", bbs);
	}
	
	@Override
	public int writeBbs(BbsDto dto) {
		return session.insert(ns + "writeBbs" ,dto);
	}

	@Override
	public BbsDto getBbsInfo(int seq) {
		return session.selectOne(ns + "getBbsInfo" , seq);
	}

	@Override
	public List<BbsComment> commentList(int seq) {
		return session.selectList(ns + "commentList" , seq);
	}

	@Override
	public int commentWrite(BbsComment dto) {
		return session.insert(ns + "commentWrite" , dto);
	}

	@Override
	public int updateBbs(BbsDto dto) {
		return session.update(ns + "updateBbs" ,dto );
	}

	@Override
	public int answerAf(BbsDto dto) {
			   session.update(ns + "answerBbsUpdate" ,dto);
		return session.insert(ns + "answerBbsInsert" ,dto);
	}
	
}
