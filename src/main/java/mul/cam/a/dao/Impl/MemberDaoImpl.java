package mul.cam.a.dao.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.MemberDao;
import mul.cam.a.dto.MemberDto;

@Repository // == 저장소 // MemberDao 상속받기
public class MemberDaoImpl implements MemberDao {
	// MyBatis접근(생성)
	@Autowired // @Autowired은 class를 자동 생성해줌 ex) SqlSession session = new 객체를 생성
	SqlSession session;
	
	String ns = "Member.";
	
	@Override // 필수
	public List<MemberDto> allMember() {

		return session.selectList("Member." + "allMember"); // data가져오기
	}
	
	@Override
	public int idcheck(String id) {
		return session.selectOne(ns + "idcheck", id); //id count값 가져오기
	}
	
	@Override
	public int addmember(MemberDto dto) {
		return session.insert(ns + "addmember", dto);
	}

	@Override
	public MemberDto login(MemberDto dto) {
		return session.selectOne(ns + "login", dto);
	}
}
