package com.spring.biz.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;
import com.spring.biz.view.controller.Controller;

public class DeleteBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//1. 전달받은 파라미터 값 추출(확인)
		//request.setCharacterEncoding("UTF-8");
		String seq = request.getParameter("seq");
		
		// 2. 업무 처리 - DB 데이터 연동 작업(게시글 수정)
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.deleteBoard(vo);
		
		// 3. 화면 내비게이션(목록 페이지로)
		//response.sendRedirect("getBoardList.do");
		
		return "getBoardList.do";
	}
	
}
