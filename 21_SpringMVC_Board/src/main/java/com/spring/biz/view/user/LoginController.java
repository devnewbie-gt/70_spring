package com.spring.biz.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.biz.user.UserVO;
import com.spring.biz.user.impl.UserDAO;
import com.spring.biz.view.controller.Controller;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 사용자 입력 데이터 확인(추출)
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// 2. DB 연동 작업(id, password 유무 확인)
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);
		
		// 3. 화면 내비게이션(화면 이동)
		// 로그인 성공 : 게시글 보여주기 - getBoardList.jsp
		// 로그인 실패 : 로그인 화면으로 이동 - login.jsp
		String returnStr = "";
		if(user != null){ // 사용자 정보가 존재하는 경우
			System.out.println("> 로그인 성공");
			request.getSession().setAttribute("id", user.getId());
			//response.sendRedirect("getBoardList.do");
			
			// ViewResolver를 이용하지 않는 경우에는 .do를 붙임
			returnStr = "getBoardList.do";
		} else {
			System.out.println("> 로그인 실패");
			//response.sendRedirect("login.jsp");
			
			// ViewResolver를 이용하는 경우 .jsp를 붙이지 않음
			// 경로 및 확장자는 ViewResolver를 통해 추가 구성
			returnStr = "login";
		}
		
		return returnStr;
	}
	
}
