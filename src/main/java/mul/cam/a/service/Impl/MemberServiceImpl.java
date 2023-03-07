package mul.cam.a.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.cam.a.dao.MemberDao;
import mul.cam.a.dto.MemberDto;
import mul.cam.a.service.MemberService;

@Service   							// MemberService상속받기
public class MemberServiceImpl implements MemberService{
		//Dao 접근(생성)
	
	@Autowired
	MemberDao dao;
	

	@Override
	public List <MemberDto> allMember() {
		return dao.allMember();
	}


	@Override
	public boolean idcheck(String id) {
		int count = dao.idcheck(id);
		return count>0?true:false; //id가 있으면 NO 없으면 YES(count값 가져오기)
	}


	@Override
	public boolean addMember(MemberDto dto) {
		int count = dao.addmember(dto);
		return count>0?true:false;
	}


	@Override
	public MemberDto login(MemberDto dto) {
		return dao.login(dto);
	}
	
	
}
