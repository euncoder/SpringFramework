package mul.cam.a.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mul.cam.a.dto.PdsDto;


public interface PdsDao {
	List<PdsDto> pdslist();
	
	int uploadPds(PdsDto dto);
	
	

}
