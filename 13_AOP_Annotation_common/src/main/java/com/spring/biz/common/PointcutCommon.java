package com.spring.biz.common;

import org.aspectj.lang.annotation.Pointcut;


public class PointcutCommon {
	
	@Pointcut("execution(* com.spring.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.spring.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
}
