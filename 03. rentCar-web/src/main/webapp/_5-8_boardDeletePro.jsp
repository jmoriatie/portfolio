<%@page import="java.util.ArrayList"%>
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
	// 비교할 데이터
	String noString = request.getParameter("no");
	int no = Integer.parseInt(noString);
	
	String id = (String)session.getAttribute("log");
	String input_pw = request.getParameter("pw");
	
	// 비교당할 데이터(DTO 객체)
	BoardDTO post = BoardDAO.getInstance().getOnePost(no);

	// id, pw 확인 => DAO 메서드 호출
	if(post.getId().equals(id) && post.getPw().equals(input_pw)){ 
		if(BoardDAO.getInstance().deleteBoard(no)){ // DAO 실행
			System.out.println("삭제 성공!!!");
			response.sendRedirect("_5-1_boardList.jsp");
		}
	}
	else{
		System.out.println("삭제 실패!!!");
		response.sendRedirect("_5-7_boardDelete.jsp?no="+noString);
	}
%>
</body>
</html>