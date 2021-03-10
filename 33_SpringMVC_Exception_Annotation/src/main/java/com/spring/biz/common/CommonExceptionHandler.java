package com.spring.biz.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// @ControllerAdvice : 객체 생성 + 적용 범위 설정
// com.spring.biz.view 패키지 포함 하위 패키지에서 발생하는 예외 처리 클래스
@ControllerAdvice("com.spring.biz.view") // 설정한 경로에서 발생하는 오류를 받아서 처리
public class CommonExceptionHandler {
	
	// @ExceptionHandler : 해당 예외 발생 시 메소드 실행
	// 구체적인 오류에 대한 처리가 가능
	@ExceptionHandler(Exception.class) // Exception.class 발생 시 실행
	public ModelAndView handleException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/error.jsp");
		return mav;
	}
	
	@ExceptionHandler(IllegalArgumentException.class) // Exception.class 발생 시 실행
	public ModelAndView handleException(IllegalArgumentException e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/illegalArgError.jsp");
		return mav;
	}
	
	@ExceptionHandler(NullPointerException.class) // Exception.class 발생 시 실행
	public ModelAndView handleException(NullPointerException e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/nullPointerError.jsp");
		return mav;
	}
	
}
