package com.spring.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.user.UserService;
import com.spring.biz.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired	// userDAO 객체를 찾아 자동으로 할당
	private UserDAOSpring userDAO;
	
	public UserServiceImpl() {
		System.out.println(">> UserServiceImpl() 객체 생성");
	}
	
	@Override
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}
	
}