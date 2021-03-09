package com.spring.biz.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.user.UserVO;
import com.spring.biz.user.impl.UserDAO;

@Controller
public class UserController {
	
	/* *** @RequestMapping
	@RequestMapping : 요청과 처리(Controller) 연결(HandlerMapping 역할 대체)
	command 객체를 사용 : 파라미터로 전달되는 값을 Command 객체에 설정
	스프링 프레임워크에서 객체를 생성하고 파라미터 값을 설정하여 전달
	1. UserVO 타입의 객체 생성
	2. UserVO 타입의 객체에 전달받은 파라미터 값을 설정(이름이 일치하는 경우)
	3. UserVO 타입의 객체를 메소드의 파라미터(인수) 값으로 전달
	*/
	
	@RequestMapping("/login.do") // HandlerMapping 역할
	public String login(UserVO vo, UserDAO userDAO) {
		System.out.println(">>> 로그인 처리 - login()");
		System.out.println("> 전달받은 vo : " + vo);
		System.out.println("> 전달받은 userDAO : " + userDAO);
		
		UserVO user = userDAO.getUser(vo);
		if(user != null) {
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
		
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		// 1. 세션 초기화(세션 객체 종료)
		session.invalidate();
		
		return "login.jsp";
	}
	
}
