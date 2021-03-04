package com.spring.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class AroundAdvice {
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		// 핵심 메소드 실행 전 처리
		System.out.println("[Around Before]"
				+ " 비즈니스 로직 실행 전 처리");
		
		Object returnObj = pjp.proceed(); // 실행할 메소드 동작 시키기
		
		// 핵심 메소드 실행 후 처리
		// 상단에서 예외 발생 시 실행되지 않음
		System.out.println("[Around After]"
				+ " 비즈니스 로직 실행 후 처리");
		
		return returnObj;
	}
}
