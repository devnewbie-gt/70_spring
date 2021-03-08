package com.spring.biz.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.SessionScope;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;
import com.spring.biz.user.UserVO;
import com.spring.biz.user.impl.UserDAO;
import com.spring.biz.view.user.LoginController;

// Servlet 3.1
//@WebServlet("*.do")	// .do로 끝나는 모든 요청(request) 처리
//web.xml 설정 방식으로 변경
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	
	public DispatcherServlet() {
		System.out.println(">> DispatcherServlet 실행~~");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println(">> init() 실행~~~");
		super.init();
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./"); // 위치 경로
		viewResolver.setSuffix(".jsp"); // 파일 타입(확장 명)
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 처리 작업 진행
		System.out.println(">> DispatcherServlet.process() : *.do 요청에 대한 처리");
		
		// 1. 클라이언트에서 어떤 작업을 요청했는지 확인
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println("path : " + path);
		
		// 2. 클라이언트의 요청 형태에 따른 작업 처리
		Controller ctrl = handlerMapping.getController(path);
		System.out.println(">> ctrl : " + ctrl);
		
		// 3. 검색된 컨트롤러 실행(컨트롤러 handleRequest 메소드 호출)
		String viewName = ctrl.handleRequest(request, response);
		System.out.println(">> viewName : " + viewName);
		
		// 4. 이동할 view 이름을 확인하고 응답 페이지 작성
		String view = null; // 최종적으로 요청 처리를 수행할 URL 값
		if(viewName.contains(".do")) {
			// .do가 붙는 경우 그대로 사용
			view = viewName;
		} else {
			// .do가 없는 경우, viewResolver에서 prefix, suffix를 붙인 path를 리턴 받음
			view = viewResolver.getView(viewName);
		}
		System.out.println(">> view : " + view);
		
		// 5. 재요청 처리
		response.sendRedirect(view);
		
	}

}
