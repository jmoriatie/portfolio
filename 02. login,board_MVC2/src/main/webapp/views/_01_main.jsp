<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
	<h1>메인페이지 입니다</h1>
	<button onclick="location.href='service?command=logout'">로그아웃</button>
	<button onclick="location.href='service?command=userDelete'">회원삭제</button>
	<button onclick="location.href='service?command=boardList'">게시판</button>
</body>
</html>