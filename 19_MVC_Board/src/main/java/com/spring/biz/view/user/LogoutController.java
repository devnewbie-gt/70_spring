package com.spring.biz.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.biz.view.controller.Controller;

public class LogoutController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// 1. 세션 초기화(세션 객체 종료)
		request.getSession().invalidate();
		
		// 2. 화면 내비게이션(로그인 페이지)
		//response.sendRedirect("login.jsp");
		
		return "login";
	}
	
}
