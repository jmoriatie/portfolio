<%@page import="model.dto.UserDTO"%>
<%@page import="model.dao.BoardDAO"%>
<%@page import="model.dto.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
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
String id = ((UserDTO)session.getAttribute("log")).getId();
BoardDTO post = (BoardDTO)request.getAttribute("post");
%>
	<h1>글 수정</h1>
	<form method="POST" action="service">
	
	<!-- 이렇게 커맨드 보내면 안됨 -->
		<input type="hidden" name="command" value="boardUpdate">
		<input type="hidden" name="no" value="<%=post.getNo()%>">
		<table border=1px;>
			<tr>
				<td>제목</td>
				<td><input type="text" id="title" name="title" value="<%= post.getTitle() %>"
					style="width: 300px"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea id="content" name="content"
						style="overflow: scroll; width: 300px; height: 300px;"><%= post.getContents() %>
						</textarea>
				</td>
			</tr>
		</table>

        <span>pw:&nbsp;</span><input type="text" name="pw"><br><br>
        
		<input type="submit" value="저장">
	</form>
		<button id="back" onclick="location.href='service?command=boardView&no=<%=post.getNo()%>'">뒤로가기</button>
</body>
</html>