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
	int no = Integer.parseInt(request.getParameter("no"));
	String cmt = request.getParameter("cmt");
	
	BoardDAO.getInstance().deleteComment(no, cmt);
	
	response.sendRedirect("_5-4_boardView.jsp?no="+no);
%>
</body>
</html>