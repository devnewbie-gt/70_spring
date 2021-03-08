<%@page import="com.spring.biz.board.impl.BoardDAO"%>
<%@page import="com.spring.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 전달받은 데이터(파라미터) 값을 이용하여 DB 데이터 수정 후 페이지 이동(목록 페이지로) --%>
<%
	// 1. 전달받은 파라미터 값 추출(확인)
	request.setCharacterEncoding("UTF-8");
	String seq = request.getParameter("seq");
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	
	// 2. 업무 처리 - DB 데이터 연동 작업(게시글 수정)
	BoardVO vo = new BoardVO();
	vo.setSeq(Integer.parseInt(seq));
	vo.setTitle(title);
	vo.setWriter(writer);
	vo.setContent(content);
	
	BoardDAO boardDAO = new BoardDAO();
	boardDAO.updateBoard(vo);
	
	// 3. 화면 내비게이션(목록 페이지로)
	response.sendRedirect("getBoardList.jsp");
%>