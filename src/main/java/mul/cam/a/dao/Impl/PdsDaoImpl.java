package mul.cam.a.dao.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.PdsDao;
import mul.cam.a.dto.PdsDto;

@Repository
public class PdsDaoImpl implements PdsDao{
	
	@Autowired
	SqlSessionTemplate session;
	
	String ns = "Pds.";

	@Override
	public List<PdsDto> pdslist() {
		return session.selectList(ns + "pdslist"); //값1개
	}

	@Override
	public int uploadPds(PdsDto dto) {
		return session.insert(ns + "uploadPds", dto);
		//콤마로 넘긴는거야
		//
	}
	
	
	
}
