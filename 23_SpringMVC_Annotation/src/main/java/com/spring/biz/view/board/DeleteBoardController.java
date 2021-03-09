package com.spring.biz.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

//@Controller
public class DeleteBoardController {
	
	//@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardDAO boardDAO, BoardVO boardVO) {
		boardDAO.deleteBoard(boardVO);
		
		return "getBoardList.do";
	}
	
}
