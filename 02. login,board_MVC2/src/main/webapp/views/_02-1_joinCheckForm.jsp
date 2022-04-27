<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>회원 비밀번호 찾기</h1>
	<form method="POST" action="service">
		<input type="hidden" name="command" value="joinCheck">
		<input type="text" name="id" id="id" placeholder="아이디를 입력해주세요"><br>
		<input type="submit" value="비밀번호 확인">
	</form>	
</body>
</html>