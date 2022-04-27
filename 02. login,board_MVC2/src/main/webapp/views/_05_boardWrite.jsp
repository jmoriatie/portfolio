<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
</head>

<body>
	<h1>글작성</h1>
	<form action="service">
	<input type="hidden" name="command" value="boardWrite">
		<table border=1px;>
			<tr>
				<td>제목</td>
				<td><input type="text" id="title" name="title" placeholder="제목"
					style="width: 300px"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea id="content" name="content" placeholder="내용"
						style="overflow: scroll; width: 300px; height: 300px;"></textarea>
				</td>
			</tr>
		</table>

		<input type="submit" value="글저장">
	</form>
		<button onclick="location.href='service?command=boardList'">뒤로가기</button>


</body>
</html>