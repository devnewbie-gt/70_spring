package com.spring.biz.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;
import com.spring.biz.view.controller.Controller;

public class GetBoardListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 게시글 목록 조회(select)
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList();
		
		// 2. 검색 결과를 session에 저장
		request.getSession().setAttribute("boardList", boardList);
		
		// 3. 화면 이동
		//response.sendRedirect("getBoardList.jsp");
		
		return "getBoardList";
	}
	
}
