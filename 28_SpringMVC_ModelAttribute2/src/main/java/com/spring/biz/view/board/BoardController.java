package com.spring.biz.view.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

@Controller
public class BoardController {
	
	// 메소드에 선언된 @ModelAttribute : 리턴 된 데이터를 View에 전달
	// @ModelAttribute 선언 된 메소드는 @RequestMapping 메소드 보다 먼저 실행
	// View에 전달될 때 설정된 명칭(ex/ "conditionMap")으로 전달
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		System.out.println("--->> @ModelAttribute - Map searchConditionMap()");
		
		// key : 제목, value : TITLE
		// key : 애옹, value : CONTENT
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		
		return conditionMap;
	}
	
	
	// 리턴 타입 ModelAndView -> String
	// 전달하는 데이터 타입 ModelAndView -> Model
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO boardVO, BoardDAO boardDAO, Model model) {
		System.out.println("> condition : " + boardVO.getSearchCondition());
		System.out.println("> keyword : " + boardVO.getSearchKeyword());
		
		// 검색 조건 값 확인
		// 검색 조건이 없을 때 : 기본 값 설정
		if(boardVO.getSearchCondition() == null) {
			boardVO.setSearchCondition("TITLE");
		} 
		if(boardVO.getSearchKeyword() == null) {
			boardVO.setSearchKeyword("");
		}
		
		List<BoardVO> boardList = boardDAO.getBoardList(boardVO);
		
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
