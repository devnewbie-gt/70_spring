package com.spring.biz.view.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

//@Controller
public class GetBoardListController {
	
	// 매개변수를 Spring framework로부터 주입 받음(boardDAO, mav)
	//@RequestMapping("/getBoardList.do")
	public ModelAndView getBoardList(BoardDAO boardDAO, ModelAndView mav) {
		List<BoardVO> boardList = boardDAO.getBoardList();
		
		mav.addObject("boardList", boardList);
		mav.setViewName("getBoardList.jsp");
		
		return mav;
	}
	
	@Deprecated
	public String handleRequest() {
		
		// 1. 게시글 목록 조회(select)
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList();
		
		// 2. 검색 결과를 session에 저장
		//request.getSession().setAttribute("boardList", boardList);
		
		// 3. ModelAndView 형식으로 데이터 저장 및 뷰 이름 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList); // 뷰에서 사용할 데이터 저장
		mav.setViewName("getBoardList.jsp"); // ViewResolver를 사용하지 않음
		
		return null;
	}
	
}
