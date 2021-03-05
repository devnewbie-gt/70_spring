<%@page import="com.spring.biz.user.impl.UserDAO"%>
<%@page import="com.spring.biz.user.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 파라미터 값 id, password를 받아서 DB에 존재하는지 확인하고 로그인 처리 
	- DB에 존재하면 로그인 성공 : getBoardList.jsp
	- DB에 존재하지 않으면 로그인 실패 : login.jsp
--%>
<%
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
	if(user != null){ // 사용자 정보가 존재하는 경우
		System.out.println("> 로그인 성공");
		response.sendRedirect("getBoardList.jsp");
	} else {
		System.out.println("> 로그인 실패");
		response.sendRedirect("login.jsp");
	}
	
%>