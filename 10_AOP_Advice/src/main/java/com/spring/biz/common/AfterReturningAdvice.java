package com.spring.biz.common;

public class AfterReturningAdvice {
	public void afterReturnLog() {
		System.out.println("[후처리-AfterReturningAdvice.afterReturnLog]"
				+ " 비즈니스 로직 수행 후 로그(정상 실행 시)"); // 예외 발생 시 수행되지 않음
	}
}
