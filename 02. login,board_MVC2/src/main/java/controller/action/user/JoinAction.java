package controller.action.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.Action;
import model.dao.UserDAO;
import model.dto.UserDTO;

public class JoinAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
//		Timestamp regDate = Timestamp.valueOf(LocalDateTime.now());
		
		UserDAO dao = UserDAO.getInstance();
			
		// id란 비었는지 확인 (이미 있는 id인지는 DAO에서 확인) 
		UserDTO user =  new UserDTO(id, pw);
		if (dao.addUser(user)) {
			System.out.println("[회원가입]\nUser: " + user.toString());
			url = "service?command=loginForm";
		} else {
			url = "service?command=joinForm";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
