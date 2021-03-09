package com.spring.biz.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.spring.biz.user.UserVO;
import com.spring.biz.user.impl.UserDAO;

// Spring에서 제공하는 Controller를 상속
public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 사용자 입력 데이터 확인(추출)
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// 2. DB 연동 작업(id, password 유무 확인)
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);
		
		// 3. 화면 내비게이션(화면 이동)
		// 로그인 성공 : 게시글 보여주기 - getBoardList.jsp
		// 로그인 실패 : 로그인 화면으로 이동 - login.jsp
		ModelAndView mav = new ModelAndView();
		if(user != null){ // 사용자 정보가 존재하는 경우
			System.out.println("> 로그인 성공");
			request.getSession().setAttribute("id", user.getId());
			//response.sendRedirect("getBoardList.do");
			
			// ViewResolver를 이용하지 않는 경우에는 .do를 붙임
			mav.setViewName("redirect:getBoardList.do");	// forwarding 처리(default)
		} else {
			System.out.println("> 로그인 실패");
			//response.sendRedirect("login.jsp");
			
			// redirect(재요청) 처리
			mav.setViewName("redirect:login.jsp"); //ViewResolver를 사용하지 않는 경우
		}
		
		return mav;
	}
	
}
