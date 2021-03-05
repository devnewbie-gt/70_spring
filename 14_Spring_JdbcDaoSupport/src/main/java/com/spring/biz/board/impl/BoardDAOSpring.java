package com.spring.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spring.biz.board.BoardVO;
import com.spring.biz.common.JDBCUtil;

// spring-jdbc의 JdbcDaoSupport 상속 확장 방식으로 구현
@Repository("boardDAOSpring")
public class BoardDAOSpring extends JdbcDaoSupport {
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
	
	public BoardDAOSpring() {
		System.out.println(">> BoardDAOSpring() 객체 생성");
	}
	
	// JdbcDaoSupport에 DataSource 객체(인스턴스) 전달
	@Autowired	// 파라미터 타입과 동일한 객체를 주입하면서 메소드를 실행. 컨테이너 구동 전에 수행(Autowired)
	public void setSuperDataSource(DataSource dataSource) {
		System.out.println(">>>> BoardDAOSpring.setSuperDataSource() 실행");
		System.out.println(">>>> dataSource : " + dataSource);
		super.setDataSource(dataSource);
	}
	
	// 글 입력
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 insertBoard() 실행");
		
		Object args[] = {vo.getTitle(), vo.getWriter(), vo.getContent()};
		// Object형 배열을 통해 전달하는 방식
		//super.getJdbcTemplate().update(BOARD_INSERT, args);
		
		// vo의 getter를 사용할 수도 있음
		super.getJdbcTemplate().update(BOARD_INSERT, 
				vo.getTitle(), vo.getWriter(), vo.getContent());
	}
	
	// 게시글 1개 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoard() 실행");
		BoardVO board = null;
		
		Object args[] = {vo.getSeq()};
										// .queryForObject : 단일 데이터 반환
		return super.getJdbcTemplate().queryForObject(BOARD_GET, args, new BoardRowMapper());
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 updateBoard() 실행");
		
		super.getJdbcTemplate().update(BOARD_UPDATE, 
				vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 deleteBoard() 실행");
		
		super.getJdbcTemplate().update(BOARD_DELETE, vo.getSeq());
	}
	
	// 게시글 전체 조회
	public List<BoardVO> getBoardList() {
		System.out.println("===> Spring JDBC로 getBoardList() 실행");
									// .query : 데이터를 List형태로 반환
		return super.getJdbcTemplate().query(BOARD_LIST, new BoardRowMapper());
	}
	
}
