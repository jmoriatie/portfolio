<%@page import="model.dao.UserDAO"%>
<%@page import="model.dto.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Board List</title>
</head>
<body>
	<h1>Board List</h1>
	<div class="wrap">
		<table border="1" style="border-collapse: collapse; width: 350px;">
			<td>no</td>
			<td>title</td>
			<td>id</td>
			<td>like</td>
			<td>regdate</td>

			<c:set var="boardList" value="${requestScope.boardList}"
				scope="page" />

			<c:forEach var="post" items="${boardList }">
				<tr>
					<td>${ post.no}</td>
					<td	onclick="location.href='service?command=boardView&no=${ post.no}'">${ post.title }</td>
					<td>${ post.id}</td>
					<td>${ post.likes}</td>
					<td>${ post.regDate}</td>
				</tr>
			</c:forEach>

		</table>
		<button onclick="location.href='service?command=boardWriteForm'">글쓰기</button>
		<button onclick="location.href='service?command=main'">뒤로가기</button>
	</div>

</body>
</html>