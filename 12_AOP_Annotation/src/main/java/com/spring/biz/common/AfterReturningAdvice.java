package com.spring.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.spring.biz.user.UserVO;

@Service
@Aspect
public class AfterReturningAdvice {
	
	@Pointcut("execution(* com.spring.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.spring.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	// returnObj : 추가 바인드변수를 사용 시에는 스프링 설정 파일에 추가 작성
	// 명시적으로 스프링 프레임워크에 매개변수 값을 받을 변수 명을 알림
	@AfterReturning(pointcut = "getPointcut()", returning = "returnObj")
	public void afterReturnLog(JoinPoint jp, Object returnObj) {
		// 클라이언트가 호출한 메소드의 이름 반환
		String methodName = jp.getSignature().getName();
		
		// 리턴 받은 객체를 확인하고 필요한 작업 처리
		if(returnObj instanceof UserVO) {
			UserVO vo = (UserVO)returnObj;
			if("admin".equalsIgnoreCase(vo.getRole())) {
				System.out.println(vo.getName() + " 로그인(Admin)");
			} else {
				System.out.println(vo.getName() + " 로그인");
			}
		}
		System.out.println("[사후 처리]" + methodName + "() 메소드, "
				+ "리턴 값 : " + returnObj);
	}
	
}
