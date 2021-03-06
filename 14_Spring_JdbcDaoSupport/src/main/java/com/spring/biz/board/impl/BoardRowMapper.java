package com.spring.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.biz.board.BoardVO;

public class BoardRowMapper implements RowMapper<BoardVO> {
	// spring jdbc 환경에서는 select 작업을 위해 rowmapper 설정이 필요
	
	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO board = new BoardVO();
		board.setSeq(rs.getInt("seq"));
		board.setTitle(rs.getString("title"));
		board.setWriter(rs.getString("writer"));
		board.setContent(rs.getString("content"));
		board.setRegdate(rs.getDate("regdate"));
		board.setCnt(rs.getInt("cnt"));
		
		return board;
	}
	
}
