package controller.action.board;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.action.Action;
import model.dao.BoardDAO;
import model.dto.UserDTO;

public class BoardWriteAction implements Action{
		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String url = "";
			
			HttpSession session = request.getSession(false);
			
			UserDTO user = (UserDTO)session.getAttribute("log"); 
			// ㄴ getAttribute()  =>  Object 형식으로 넘어오는 듯 (캐스팅 필요)
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			Timestamp regDate = Timestamp.valueOf(LocalDateTime.now());

			System.out.println("[글등록]\n제목: " + title);
			System.out.println("내용: " + content);
			
			if(!title.equals("") && !content.equals("")){
				if (BoardDAO.getInstance().boardWrite(user.getId(), title, content)) {
					url = "service?command=boardList";
				} else {
					url = "service?command=boardWriteForm";
				}
			}
			else{
				System.out.println("제목 또는 내용이 입력되지 않았습니다");
				url = "service?command=boardWriteForm";
			}
			request.getRequestDispatcher(url).forward(request, response);
			
		
			
		}
	
}
