package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.factory.TransactionFactory;

import controller.action.Action;

/**
 * Servlet implementation class Service
 */
//@WebServlet("/Service")
public class Service extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Service() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 0. 커맨드 받기 (command)
		// ㄴ form 태그에서 hidden 으로 전달
		String command = request.getParameter("command");
		
		// 1. 액션 객체 먼저 선언
		Action action = null;
		// 2. ActionFactiory를 통해 들어온 객체를 선언해주고
		
		// 3. ActionFactory를 통해 들어온 action 객체가
		//    null이 아닐 경우 action의 execute 메서드 실행
		action = ActionFactory.getInstance().getAction(command);
		if(action != null) {
			action.execute(request, response);
		}
		else {
			System.out.println("잘못된 command 입니다");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 데이터 변환을 위한 메서드 2개
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doGet(request, response);
	}

}
