package com.spring.biz.user;

import org.springframework.context.support.GenericXmlApplicationContext;

public class UserTest {
	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동
		GenericXmlApplicationContext container
			= new GenericXmlApplicationContext("applicationContext.xml");
		
		System.out.println("---- 컨테이너 구동 후 ----");
		// 2. 스프링 컨테이너 사용
		UserService userService = (UserService)container.getBean("userService");
		
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("test111");
		UserVO user = userService.getUser(vo);
		System.out.println("user : " + user);
		
		// 3. 스프링 컨테이너 종료
		
		
	}
}
