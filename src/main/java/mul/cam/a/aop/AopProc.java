package mul.cam.a.aop;

import java.lang.reflect.Member;
import java.security.Signature;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mul.cam.a.dto.MemberDto;

@Aspect
public class AopProc {
			/*
			 * 	AOP(Aspect Oriented Programing)관점지향
			 * 목적 : 감시자
			 
			 */
			//@Around("within(mul.cam.a.controller.*) or within(mul.cam.a.dao.*.*)")
			  @Around("within(mul.cam.a.controller.*) ")
		  
		 public Object loggerAop(ProceedingJoinPoint Joinpoint)throws Throwable {
			
				  /* 평상시에 꺼두기
			// session check     Request필요한경우,(취득)얻어오기
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			//넘어온 Request객체가 null이 아닐때(넘어온 데이터(요청)이 있을때,)
			if(request != null) {
				HttpSession session = request.getSession();
				MemberDto login = (MemberDto)session.getAttribute("login");
				if(login == null) {
					return "redirect:/sessionOut.do"; //sessionOut.do로 가라
						  //controller에서 controller로 이동시 == sendredirect
				}
			}
				   */
			
			 //logger
			 String signatureStr = Joinpoint.getSignature().toShortString();
			 
			 try {
				 Object obj = Joinpoint.proceed(); //실행시
				 System.out.println("AOP log:" + signatureStr + "메소드 실행" + new Date());
				 
				 return obj;
			 }finally {
				 System.out.println("실행후:" + System.currentTimeMillis());
			 
			 
		 }
	}
	
	
}
