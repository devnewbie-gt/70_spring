package com.spring.biz.board;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {
	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동
		GenericXmlApplicationContext container 
			= new GenericXmlApplicationContext("applicationContext_after-throwing.xml");
		
		System.out.println("---- 컨테이너 구동 후 ----");
		// 2. 스프링 컨테이너 사용
		BoardService boardService = (BoardService)container.getBean("boardService"); // bean의 이름. @Service의 이름
		
		// 데이터 입력
		BoardVO vo = new BoardVO();
		vo.setTitle("테스트-spring");
		vo.setWriter("테스터");
		vo.setContent("테스트 중-spring");
		boardService.insertBoard(vo);
		
		// 전체 데이터 조회
		List<BoardVO> list = boardService.getBoardList();
		for (BoardVO board : list) {
			System.out.println("board : " + board.toString());
		}
		
		// 3. 스프링 컨테이너 종료
		
	}
}
