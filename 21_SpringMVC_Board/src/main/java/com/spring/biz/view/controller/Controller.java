package com.spring.biz.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 업무를 처리하는 Controller를 표준화
public interface Controller {
	String handleRequest(HttpServletRequest request, 
			HttpServletResponse response);
}
