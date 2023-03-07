package mul.cam.a.service;
import java.util.List;

import mul.cam.a.dto.MemberDto;
public interface MemberService {
		
	List<MemberDto> allMember();
	
	boolean idcheck(String id); //받아서 편집
	
	boolean addMember(MemberDto dto);
	
	MemberDto login(MemberDto dto);
}
