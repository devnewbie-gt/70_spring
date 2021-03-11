package com.spring.biz.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.spring.biz.user.UserService;
import com.spring.biz.user.UserVO;
import com.spring.biz.user.impl.UserDAO;

@Controller
//@EnableWebMvc	// 스프링 4.3 버전에서 @GetMapping, @PostMapping 사용을 위해 선언.(mvc:annotation-driven)
public class UserController {
	@Autowired
	private UserService userService;
	
	public UserController() {
		System.out.println("---->>>> UserController() 객체 생성");
	}
	
	/* *** @RequestMapping
	@RequestMapping : 요청과 처리(Controller) 연결(HandlerMapping 역할 대체)
	command 객체를 사용 : 파라미터로 전달되는 값을 Command 객체에 설정
	스프링 프레임워크에서 객체를 생성하고 파라미터 값을 설정하여 전달
	1. UserVO 타입의 객체 생성
	2. UserVO 타입의 객체에 전달받은 파라미터 값을 설정(이름이 일치하는 경우)
	3. UserVO 타입의 객체를 메소드의 파라미터(인수) 값으로 전달
	*/
	
	//@RequestMapping("/login.do") // GET, POST 모두 처리
	//@RequestMapping(value = "/login.do", method = RequestMethod.POST) // POST만 처리
	@PostMapping("/login.do") // POST 방식 요청만 수행 -> 위에 선언 된 @RequestMapping과 같은 역할
	public String login(UserVO vo) {
		System.out.println(">>> 로그인 처리 - login()");
		System.out.println("> 전달받은 vo : " + vo);
		System.out.println("> 전달받은 userDAO : " + userService);
		
		// 고의적으로 예외 발생(Exception 처리 테스트 용도)
		if(vo.getId() == null || vo.getId().trim().equals("")) {
			throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다");
		}
		
		UserVO user = userService.getUser(vo);
		if(user != null) {
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
		
	}
	
	/* @ModelAttribute : 모델(Moedl) 속성 값으로 저장(속성 명을 별도 지정)
		별도로 지정 명칭을 부여하지 않으면 <데이터 타입>의 첫 글자를 소문자로 작성된 명칭을 사용 ex) UserVO -> userVO로 사용
	   @ModelAttibute UserVO vo -> 속성명 userVO 형태
	   @ModelAttibute ("user") UserVO -> 속성 명 user 사용
	   Command 객체가 기본 request scope에 저장하여 사용됨
	*/
	// get 방식을 통한 단순 페이지 이동
	//@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	@GetMapping("/login.do") // GET 방식만 수행
	//public String loginView(UserVO vo) { // 뷰에서 사용할 명칭 userVO 데이터 저장
	public String loginView(@ModelAttribute("user") UserVO vo) { // Model 속성 명 user 사용
							// parameter 앞에 선언되는 경우, 해당 메소드가 수행될 때 한정으로 적용
		vo.setId("test");
		vo.setPassword("test");
		return "login.jsp";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		// 1. 세션 초기화(세션 객체 종료)
		session.invalidate();
		
		return "login.jsp";
	}
	
}
