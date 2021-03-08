package com.spring.biz.view.controller;

/* ViewResolver 클래스는 Controller가 리턴한 String(뷰 명칭)에
 * 접두어(prefix)와 접미어(suffix)를 결합하여 
 * 응답할 페이지의 경로와 파일 명을 완성하여 DispatcherServlet으로 리턴.
 * DispatcherServlet의 init() 호출 시 생성
 */
public class ViewResolver {
	private String prefix;	// 경로 명
	private String suffix;	// 파일 확장자
	
	public ViewResolver() {
		System.out.println(">> ViewResolver 객체 생성~");
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	// login.jsp 응답 시 -> 전달되는 viewName = login
	// 리턴 값 : "./" + "login" + ".jsp" ===> ./login.jsp
	//		  prefix + viewName + suffix
	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}
	
}
