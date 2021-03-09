package com.spring.biz.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;


public class GetBoardListController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 게시글 목록 조회(select)
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList();
		
		// 2. 검색 결과를 session에 저장
		//request.getSession().setAttribute("boardList", boardList);
		
		// 3. ModelAndView 형식으로 데이터 저장 및 뷰 이름 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList); // 뷰에서 사용할 데이터 저장
		mav.setViewName("getBoardList"); // ViewResolver를 사용하기 때문에 .jsp를 명시하지 않음(설정된 suffix(.jsp)가 ViewResolver를 경유할 때 붙음)
		
		return mav;
	}
	
}
