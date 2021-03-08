package com.spring.biz.view.controller;

import java.util.HashMap;
import java.util.Map;

import com.spring.biz.view.board.DeleteBoardController;
import com.spring.biz.view.board.GetBoardController;
import com.spring.biz.view.board.GetBoardListController;
import com.spring.biz.view.board.InsertBoardController;
import com.spring.biz.view.board.UpdateBoardController;
import com.spring.biz.view.user.LoginController;
import com.spring.biz.view.user.LogoutController;

/*
 * HandlerMapping 요청 정보와 처리 할 컨트롤러를 연결하는 정보를 저장
 * DispatcherServlet의 init() 호출 시 생성
 */
public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	public HandlerMapping() {
		// Controller 객체를 생성하여 요청과 매칭시키기
		System.out.println(">> HandlerMapping 객체 생성~");
		mappings = new HashMap<String, Controller>();
		mappings.put("/login.do", new LoginController());
		mappings.put("/logout.do", new LogoutController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
	}
	
	public Controller getController(String path) {
		
		return mappings.get(path);
	}
}
