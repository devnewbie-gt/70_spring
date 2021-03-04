package com.spring.biz.common;

import org.aspectj.lang.JoinPoint;

public class AfterThrowingAdvice {
//	public void exceptionLog() {
//		System.out.println("[후처리-AfterThrowingAdvice]"
//				+ " 비즈니스 로직 수행 중 예외 발생 로그");
//	}
	
	// JoinPoint 이외의 매개변수 exceptObj 사용
	public void exceptionLog(JoinPoint jp, Exception exceptObj) {
		String methodName = jp.getSignature().getName();
		
		if(exceptObj instanceof IllegalArgumentException) {
			System.out.println(">>> 부적합한 값이 전달되었습니다");
		} else if(exceptObj instanceof NumberFormatException) {
			System.out.println(">>> 숫자 형식이 아닙니다");
		} else if(exceptObj instanceof Exception) {
			System.out.println(">>> 예외가 발생하였습니다");
		}
		
		System.out.println("[예외 발생] " + methodName + "() 메소드 "
				+ "- 실행 중 예외 발생, 메시지 : " + exceptObj.getMessage());
	}
}
