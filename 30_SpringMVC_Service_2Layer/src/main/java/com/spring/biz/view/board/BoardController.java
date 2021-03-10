package com.spring.biz.view.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

@Controller
@SessionAttributes("board") // board라는 이름의 Model이 있으면 session에 저장
public class BoardController {
	@Autowired
	private BoardService boardService;	// DI, 의존 주입 <-- BoardServiceImpl(@Service("boardService"))
	
	public BoardController() {
		System.out.println("---->>>> BoardController() 객체 생성");
		System.out.println(">> boardService : " + boardService);
	}
	
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
	public String getBoardList(BoardVO boardVO, Model model) {
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
		
		List<BoardVO> boardList = boardService.getBoardList(boardVO);
		
		model.addAttribute("boardList", boardList);
		
		return "getBoardList.jsp";
	}
	
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO boardVO, Model model) {
		model.addAttribute("board", boardService.getBoard(boardVO));
		
		return "getBoard.jsp";
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo) {
		System.out.println(">>> 게시글 입력 - insertBoarD()");
		boardService.insertBoard(vo);
		
		return "redirect:getBoardList.do";
	}
	
	@RequestMapping("/updateBoard.do")
	// 인수(파라미터)에 설정한 @ModelAttribute("board") : 속성 명 board가 있으면 사용
	// 없으면 새로 생성하여 적용
	public String updateBoard(@ModelAttribute("board")BoardVO boardVO) {
		System.out.println("> board : " + boardVO);
		boardService.updateBoard(boardVO);
		
		return "getBoardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO boardVO) {
		boardService.deleteBoard(boardVO);
		
		return "getBoardList.do";
	}
	
}
