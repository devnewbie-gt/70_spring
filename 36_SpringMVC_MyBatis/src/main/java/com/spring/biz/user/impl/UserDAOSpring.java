package com.spring.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.biz.common.JDBCUtil;
import com.spring.biz.user.UserVO;

@Repository
public class UserDAOSpring {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String USER_GET
		= "select * from users where id = ? and password = ? ";
	
	public UserDAOSpring() {
		System.out.println(">> UserDAO() 객체 생성");
	}
	
	public UserVO getUser(UserVO vo) {
		System.out.println("===> JDBC로 getUser() 실행");
		
		// sql 문장을 확인하고 받아가는 매개변수의 종류와 개수를 숙지할 것
		Object args[] = {vo.getId(), vo.getPassword()};
		
		return jdbcTemplate.queryForObject(USER_GET, args, new UserRowMapper());
	}
	
}
