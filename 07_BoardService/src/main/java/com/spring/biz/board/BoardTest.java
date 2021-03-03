package com.spring.biz.board;

import com.spring.biz.board.impl.BoardDAO;

public class BoardTest {
	public static void main(String[] args) {
		BoardVO vo = new BoardVO();
		vo.setTitle("테스트");
		vo.setWriter("테스터");
		vo.setContent("테스트 중");
		
		BoardDAO dao = new BoardDAO();
		dao.insertBoard(vo);
		
		vo.setSeq(1);
		BoardVO vo2 = dao.getBoard(vo);
		System.out.println("vo2 : " + vo2);
	}
}
