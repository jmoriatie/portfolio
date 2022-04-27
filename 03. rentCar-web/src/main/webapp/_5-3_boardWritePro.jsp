<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	BoardDAO dao = BoardDAO.getInstance();

	String id = (String)session.getAttribute("log"); 
	// ㄴ getAttribute()  =>  Object 형식으로 넘어오는 듯 (캐스팅 필요)
	String title = request.getParameter("title");
	String content = request.getParameter("content");

	System.out.println("title: " + title);
	System.out.println("content: " + content);
	
	if(!title.equals("") && !content.equals("")){
		if(id != null){
			if (dao.boardWrite(id, title, content)) {
				// 성공시
				response.sendRedirect("_5-1_boardList.jsp");
			} else {
				response.sendRedirect("_5-2_boardWrite.jsp");
			}			
		}
		else{
			System.out.println("로그인 되어있지 않음");
			response.sendRedirect("_5-2_boardWrite.jsp");
		}
		
	}
	else{
		System.out.println("제목 또는 내용이 입력되지 않았습니다");
		response.sendRedirect("_5-2_boardWrite.jsp");
	}
	%>

</body>
</html>