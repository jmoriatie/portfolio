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
	request.setCharacterEncoding("utf-8");

	// 데이터 받기
	String noString = request.getParameter("no");
	int no = Integer.parseInt(noString);
	
	String pw = request.getParameter("pw");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	BoardDTO post = BoardDAO.getInstance().getOnePost(no);

	// 데이터 처리
	post.setTitle(title);
	post.setContents(content);
	
	if(post.getPw().equals(pw)){
		BoardDAO.getInstance().updateBoard(post);
		System.out.println("수정 성공");
		response.sendRedirect("_5-4_boardView.jsp?no=" + post.getNo()+"");
	}
	else{
		response.sendRedirect("_5-5_boardUpdate.jsp?no=" + post.getNo()+"");
		System.out.println("수정 실패!!!");

	}
%>
</body>
</html>