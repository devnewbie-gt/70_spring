package com.spring.biz.view.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

@Controller
public class IntegratedBoardController {
	
	@RequestMapping("/getBoardList.do")
	public ModelAndView getBoardList(BoardDAO boardDAO, ModelAndView mav) {
		List<BoardVO> boardList = boardDAO.getBoardList();
		mav.addObject("boardList", boardList);
		mav.setViewName("getBoardList.jsp");
		
		return mav;
	}
	
	@RequestMapping("/getBoard.do")
	public ModelAndView getBoard(BoardDAO boardDAO, BoardVO boardVO, ModelAndView mav) {
		mav.addObject("board", boardDAO.getBoard(boardVO));
		mav.setViewName("getBoard.jsp");
		
		return mav;
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println(">>> 게시글 입력 - insertBoarD()");
		boardDAO.insertBoard(vo);
		
		return "redirect:getBoardList.do";
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardDAO boardDAO, BoardVO boardVO) {
		boardDAO.updateBoard(boardVO);
		
		return "getBoardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardDAO boardDAO, BoardVO boardVO) {
		boardDAO.deleteBoard(boardVO);
		
		return "getBoardList.do";
	}
	
}
