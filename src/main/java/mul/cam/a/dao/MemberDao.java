package mul.cam.a.dao;

import java.util.List;

import mul.cam.a.dto.MemberDto;

public interface MemberDao {
		
	List<MemberDto> allMember();
	
	int idcheck(String id);
	
	int addmember(MemberDto dto);
	
	MemberDto login(MemberDto dto);
}
