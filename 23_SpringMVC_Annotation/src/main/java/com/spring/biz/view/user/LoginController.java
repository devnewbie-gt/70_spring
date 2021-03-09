package com.spring.biz.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.biz.user.UserVO;
import com.spring.biz.user.impl.UserDAO;

// @Controller : DispatcherServlet 에서 인식할 수 있는 컨트롤러로 객체 생성
@Controller
public class LoginController {
	
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
	
	//@Override
	// 사용되지 않음(참고용)
	@Deprecated
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
			mav.setViewName("getBoardList.do");	// forwarding 처리(default)
		} else {
			System.out.println("> 로그인 실패");
			//response.sendRedirect("login.jsp");
			
			mav.setViewName("login.jsp"); //ViewResolver를 사용하지 않는 경우
		}
		
		return mav;
	}
	
}
