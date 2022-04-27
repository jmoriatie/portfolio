package controller;

import controller.action.Action;
import controller.action.IndexAction;
import controller.action.IndexFormAction;
import controller.action.board.BoardDeleteAction;
import controller.action.board.BoardLikeAction;
import controller.action.board.BoardListAction;
import controller.action.board.BoardUpdateAction;
import controller.action.board.BoardUpdateFormAction;
import controller.action.board.BoardViewAction;
import controller.action.board.BoardWriteAction;
import controller.action.board.BoardWriteFormAction;
import controller.action.user.JoinAction;
import controller.action.user.JoinCheckAction;
import controller.action.user.JoinCheckForm;
import controller.action.user.JoinFormAction;
import controller.action.user.LoginAction;
import controller.action.user.LoginFormAction;
import controller.action.user.LogoutAction;
import controller.action.user.MainAction;
import controller.action.user.UserDeleteAction;

public class ActionFactory {
	// 적용되는 디자인 패턴
	// ㄴ singleton Pattern, Factory Method, Command Pattern 
	
	// 1. 싱글톤
	// 2. getAction 메소드 (command)
	// 3. 각 Action 메서드 선언
	
	private static ActionFactory instance = null;
	private ActionFactory() {}
	public static ActionFactory getInstance() {
		if(instance == null) {
			instance = new ActionFactory();
		}
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("index")) action = new IndexAction();
		else if(command.equals("indexForm")) action = new IndexFormAction();
		
		else if(command.equals("loginForm")) action = new LoginFormAction();
		else if(command.equals("login")) action = new LoginAction();
		else if(command.equals("main")) action = new MainAction();
		else if(command.equals("logout")) action = new LogoutAction();
		else if(command.equals("joinForm")) action = new JoinFormAction();
		else if(command.equals("join")) action = new JoinAction();
		else if(command.equals("joinCheckForm")) action = new JoinCheckForm();
		else if(command.equals("joinCheck")) action = new JoinCheckAction();
		else if(command.equals("userDelete")) action = new UserDeleteAction();
		
		else if(command.equals("boardList")) action = new BoardListAction();
		else if(command.equals("boardView")) action = new BoardViewAction();
		else if(command.equals("boardWriteForm")) action = new BoardWriteFormAction();
		else if(command.equals("boardWrite")) action = new BoardWriteAction();
		else if(command.equals("boardUpdateForm")) action = new BoardUpdateFormAction();
		else if(command.equals("boardUpdate")) action = new BoardUpdateAction();
		else if(command.equals("boardDelete")) action = new BoardDeleteAction();
		else if(command.equals("boardLike")) action = new BoardLikeAction();
		
		return action;
	}
}
