package com.spring.biz.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;
import com.spring.biz.user.UserVO;
import com.spring.biz.user.impl.UserDAO;

// Servlet 3.1
//@WebServlet("*.do")	// .do로 끝나는 모든 요청(request) 처리
//web.xml 설정 방식으로 변경
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		if(path.equals("/login.do")) {
			System.out.println(">>> 로그인 처리");
			//request.getRequestDispatcher("login_proc.jsp").forward(request, response);
			
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
				request.getSession().setAttribute("id", user.getId());
				response.sendRedirect("getBoardList.do");
			} else {
				System.out.println("> 로그인 실패");
				response.sendRedirect("login.jsp");
			}
			
		}
		else if(path.equals("/logout_proc.do")) {
			System.out.println(">>> 로그아웃 처리");
			// 1. 세션 초기화(세션 객체 종료)
			request.getSession().invalidate();
			
			// 2. 화면 내비게이션(로그인 페이지)
			response.sendRedirect("login.jsp");
		}
		else if(path.equals("/getBoardList.do")) {
			System.out.println(">>> 게시글 전체 목록 보여주기");
			// 1. 게시글 목록 조회(select)
			BoardDAO boardDAO = new BoardDAO();
			List<BoardVO> boardList = boardDAO.getBoardList();
			
			// 2. 검색 결과를 session에 저장
			request.getSession().setAttribute("boardList", boardList);
			
			// 3. 화면 이동
			response.sendRedirect("getBoardList.jsp");
			
		}
		else if(path.equals("/getBoard.do")) {
			System.out.println(">>> 게시글 상세 보기");
		}
		else if(path.equals("/insertBoard.do")) {
			System.out.println(">>> 게시글 입력 처리");
		}
		else if(path.equals("/updateBoard.do")) {
			System.out.println(">>> 게시글 수정 처리");
		}
		else if(path.equals("/deleteBoard.do")) {
			System.out.println(">>> 게시글 삭제 처리");
		}
	}

}
