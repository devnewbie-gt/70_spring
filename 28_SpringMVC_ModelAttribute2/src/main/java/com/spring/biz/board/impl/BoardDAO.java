package com.spring.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.biz.board.BoardVO;
import com.spring.biz.common.JDBCUtil;

// @Repository : DB 연동 작업을 처리하는 클래스에 설정(xxxDAO, xxxDao)
//		-@Component를 상속받아 기능이 확장된 어노테이션
@Repository("boardDAO")
public class BoardDAO {
	// JDBC 관련 변수(필드)
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
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
	
	public BoardDAO() {
		System.out.println(">> BoardDAO() 객체 생성");
	}
	
	// 글 입력
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard() 실행");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT);
			
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt);
		}
		
	}
	
	// 게시글 1개 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JDBC로 getBoard() 실행");
		BoardVO board = null;
		
		try {
			conn = JDBCUtil.getConnection();
			if(conn == null) return null;
			
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, vo.getSeq());
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, rs);
		}
		
		return board;
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 실행");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getSeq());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt);
		}
		
	}
	
	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC로 deleteBoard() 실행");
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			
			stmt.setInt(1, vo.getSeq());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt);
		}
		
	}
	
	// 게시글 전체 조회
	public List<BoardVO> getBoardList() {
		System.out.println("===> JDBC로 getBoardList() 실행");
		List<BoardVO> list = null;
		
		conn = JDBCUtil.getConnection();
		if(conn == null) return null;
		
		try {
			
			stmt = conn.prepareStatement(BOARD_LIST);
			rs = stmt.executeQuery();
			
			list = new ArrayList<BoardVO>();
			
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
				
				list.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, rs);
		}
		
		return list;
	}

	public List<BoardVO> getBoardList(String condition, String keyword) {
		System.out.println("===> JDBC로 getBoardList() 실행");
		List<BoardVO> list = null;
		
		conn = JDBCUtil.getConnection();
		if(conn == null) return null;
		
		try {
			if(condition.equals("TITLE")) {
				stmt = conn.prepareStatement(BOARD_LIST_T);
			} else {
				stmt = conn.prepareStatement(BOARD_LIST_C);
			}
			stmt.setString(1, keyword);
			rs = stmt.executeQuery();
			
			list = new ArrayList<BoardVO>();
			
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
				
				list.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, rs);
		}
		
		
		return list;
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JDBC로 getBoardList() 실행");
		List<BoardVO> list = null;
		
		conn = JDBCUtil.getConnection();
		if(conn == null) return null;
		
		try {
			if(vo.getSearchCondition().equals("TITLE")) {
				stmt = conn.prepareStatement(BOARD_LIST_T);
			} else {
				stmt = conn.prepareStatement(BOARD_LIST_C);
			}
			stmt.setString(1, vo.getSearchKeyword());
			rs = stmt.executeQuery();
			
			list = new ArrayList<BoardVO>();
			
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
				
				list.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, rs);
		}
		
		
		return list;
	}
	
}
