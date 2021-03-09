package com.spring.biz.view.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

@Controller
public class BoardController {
	
	// 리턴 타입 ModelAndView -> String
	// 전달하는 데이터 타입 ModelAndView -> Model
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardDAO boardDAO, Model model) {
		List<BoardVO> boardList = boardDAO.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "getBoardList.jsp";
	}
	
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardDAO boardDAO, BoardVO boardVO, Model model) {
		model.addAttribute("board", boardDAO.getBoard(boardVO));
		
		return "getBoard.jsp";
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
