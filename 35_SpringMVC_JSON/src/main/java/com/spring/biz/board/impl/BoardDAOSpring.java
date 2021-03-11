package com.spring.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.biz.board.BoardVO;

// 스프링 JDBC JdbcTemplate을 주입받아서(DI) 처리
@Repository
public class BoardDAOSpring {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	// 생성자를 이용한 의존성 주입을 위한 객체 선언
//	private final JdbcTemplate jdbcTemplate;
	
	public BoardDAOSpring() {
		System.out.println(">> BoardDAOSpring() 객체 생성");
	}
	
	/* 생성자를 이용한 의존성 주입 방법
	@Autowired
	public BoardDAOSpring(JdbcTemplate jdbcTemplate) {
		System.out.println(">> BoardDAOSpring() 객체 생성");
		this.jdbcTemplate = jdbcTemplate;
	}*/
	
	// SQL문
	private final String BOARD_INSERT
		= "insert into board (seq, title, writer, content) "
		+ "values((select nvl(max(SEQ), 0) + 1 from board), ?, ?, ?) ";
				// 서브 쿼리를 이용하여 max + 1 로 받음
	
	private final String BOARD_UPDATE
		= "update board set title = ?, content = ? where seq = ? ";
	
	private final String BOARD_DELETE
		= "delete from board where seq = ? ";
	
	private final String BOARD_GET
		= "select * from board where seq = ? ";
	
	private final String BOARD_LIST
		= "select * from board order by seq desc ";
	
	// TITLE(제목) 값으로 검색
	private final String BOARD_LIST_T
		= "select * from board where title like '%'||?||'%' order by seq desc ";
		
	// CONTENT(내용) 값으로 검색
	private final String BOARD_LIST_C
		= "select * from board where content like '%'||?||'%' order by seq desc ";
	
	// 글 입력
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC(jdbcTemplate)로 insertBoard() 실행");
		
		Object args[] = {vo.getTitle(), vo.getWriter(), vo.getContent()};
		// Object형 배열을 통해 전달하는 방식
		//super.getJdbcTemplate().update(BOARD_INSERT, args);
		
		// vo의 getter를 사용할 수도 있음
		jdbcTemplate.update(BOARD_INSERT, 
				vo.getTitle(), vo.getWriter(), vo.getContent());
	}
		
	// 게시글 1개 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC(jdbcTemplate)로 getBoard() 실행");
		BoardVO board = null;
		
		Object args[] = {vo.getSeq()};
										// .queryForObject : 단일 데이터 반환
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC(jdbcTemplate)로 updateBoard() 실행");
		
		jdbcTemplate.update(BOARD_UPDATE, 
				vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC(jdbcTemplate)로 deleteBoard() 실행");
		
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}
	
	// 게시글 전체 조회
	public List<BoardVO> getBoardList() {
		System.out.println("===> Spring JDBC(jdbcTemplate)로 getBoardList() 실행");
									// .query : 데이터를 List형태로 반환
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}

	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Spring JDBC(jdbcTemplate)로 getBoardList() 실행");
		String sql = "";
		if(vo.getSearchCondition() == null) {
			vo.setSearchCondition("TITLE");
		} 
		if(vo.getSearchKeyword() == null) {
			vo.setSearchKeyword("");
		}
		
		if(vo.getSearchCondition().equals("TITLE")) {
			sql = BOARD_LIST_T;
		} 
		if(vo.getSearchCondition().equals("CONTENT")){
			sql = BOARD_LIST_C;
		}
		
		Object args[] = {vo.getSearchKeyword()};
		
		return jdbcTemplate.query(sql, args, new BoardRowMapper());
	}
	
}
