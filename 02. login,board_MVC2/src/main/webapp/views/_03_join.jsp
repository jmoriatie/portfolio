<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
    <style>
        span{
            white-space: pre;
        }
    </style>
    
    <title>join</title>
</head>

<body>
    <h1>회원가입</h1>

    <form method="POST" action="service">
    	<input type="hidden" name="command" value="join">
        <span>id:&#9;</span><input type="text" name="id"><br><br>
        <span>pw:&#9;</span><input type="text" name="pw"><br><br>
        <input type="submit" value="가입">
    </form>
    <button onclick="location.href='service?command=main'">뒤로가기</button>
</body>

</html>