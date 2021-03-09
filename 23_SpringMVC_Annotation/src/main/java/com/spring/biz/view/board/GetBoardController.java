package com.spring.biz.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

//@Controller
public class GetBoardController {
	
	//@RequestMapping("/getBoard.do")
	public ModelAndView getBoard(BoardDAO boardDAO, BoardVO boardVO, ModelAndView mav) {
		mav.addObject("board", boardDAO.getBoard(boardVO));
		mav.setViewName("getBoard.jsp");
		
		return mav;
	}
	
}
