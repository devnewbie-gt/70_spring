package com.spring.biz.common;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service	// 객체(인스턴스) 생성
@Aspect		// 포인트컷 + 어드바이스 연결
public class BeforeAdvice {
	
	// 포인트컷 작성 : 명칭은 메소드 명 사용
	// 메소드 형식으로 작성(조인포인트 자체가 일반 메소드에 해당)
	@Pointcut("execution(* com.spring.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Before("allPointcut()")	// 포인트컷 메소드 명 작성
	public void beforeLog(JoinPoint jp) {	// 어드바이스 메소드
		String methodName = jp.getSignature().getName(); // 실행 될 메소드 명
		Object args[] = jp.getArgs();
		System.out.println("args : " + Arrays.toString(args));
		
		try {
			System.out.println("[사전 처리]" + methodName + "() 메소드"
					+ ", args 정보 : " + args[0] + " - 비즈니스 로직 수행 전 로그");
		} catch (Exception e) {
			System.out.println("[사전 처리]" + methodName + "() 메소드"
					+ ", args 정보 : 없음 - 비즈니스 로직 수행 전 로그");
		}
		
	}

}
