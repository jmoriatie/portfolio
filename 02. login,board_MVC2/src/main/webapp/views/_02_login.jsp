<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인:MVC2_dev</h1>
	<form method="POST" action="service">
		<input type="hidden" name="command" value="login">
		<input type="text" name="id" id="id" placeholder="아이디"><br>
		<input type="password" name="pw" id="pw" placeholder="비밀번호">
		<input type="submit" value="로그인">
	</form>
	<button onclick="location.href='service?command=joinCheckForm'">비밀번호 찾기</button>
	<button onclick="location.href='service?command=index'">뒤로가기</button>
</body>
</html>