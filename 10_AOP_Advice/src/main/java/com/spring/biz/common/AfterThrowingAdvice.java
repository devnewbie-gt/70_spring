package com.spring.biz.common;

public class AfterThrowingAdvice {
	public void exceptionLog() {
		System.out.println("[후처리-AfterThrowingAdvice]"
				+ " 비즈니스 로직 수행 중 예외 발생 로그");
	}
}
