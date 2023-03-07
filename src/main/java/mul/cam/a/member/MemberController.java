package mul.cam.a.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mul.cam.a.dto.MemberDto;
import mul.cam.a.service.MemberService;
					//로그인,회원정보 관리하는Controller
@Controller
public class MemberController {
	
	// service 접근(생성)
	@Autowired
	MemberService service;
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
		System.out.println("MemberController login" + new Date());
		
		return "login";
	}
	
	@RequestMapping(value = "regi.do", method = RequestMethod.GET)
	public String regi() {
		System.out.println("MemberController regi" + new Date());
		
		return "regi";
	}
	
	@ResponseBody
	@RequestMapping(value = "idcheck.do", method = RequestMethod.POST)
	public String idcheck(String id) {
		System.out.println("MemberController regi" + new Date());
		boolean isS = service.idcheck(id); // 서비스에 접근해서 true/false가져오기
		if(isS == true) { //id가 있음 중복체크
			return "NO";	 
			
		}
		return "YES"; //id가 없음
	}
	@ResponseBody
	@RequestMapping(value = "regiAf.do", method = RequestMethod.POST)
	public String regiAf(Model model, MemberDto dto) {
		System.out.println("MemberController regiAf" + new Date());
		
		boolean isS = service.addMember(dto);
		String message = "";
		if(isS) {
			message = "MEMBER_ADD_YES";
		}else {
			message = "MEMBER_ADD_NO";
		}
		model.addAttribute("message",message);
		return "message";
	}
	
	@RequestMapping(value = "loginAf.do", method = RequestMethod.POST)
	public String login(HttpServletRequest req, Model model,MemberDto dto) {
		System.out.println("MemberController login" + new Date());
		
		MemberDto mem =service.login(dto);
		String msg = "";
		if(mem !=null) { //login 성공
			req.getSession().setAttribute("login",mem); //세션에 로그인 데이터 저장
			// req.getSession().setMaxInactiveInterval(60 * 2);
			msg = "LOGIN_OK";
		}else { //login 실패
			msg = "LOGIN_FAIL";
		}
		model.addAttribute("login",msg); //model안에 속성을 추가하라
		
		return "message";
	}
	
	@RequestMapping(value = "sessionOut.do", method = RequestMethod.POST)
	public String sessionOut(Model model) {
		String sessionOut = "로 그 인 해 됴 요";
		
		model.addAttribute("sessionOut",sessionOut);
		return "message";
		
	}
}










