<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@500&display=swap" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="resources/css/_02_join.css">
    <title>렌트카:회원가입</title>
</head>

<body>
    <div id="a">
        <section id="sec1"></section>
        <section id="sec2">
            <article >
                <div class="join_header">
                    <span id="title">RENT CAR</span><br><br>
                </div>
                <div class="join_main">
                    <form method="POST" action="_02_joinPro.jsp" id="joinForm" >
                        <span>아이디</span><br>
                        <input type="text" name="id" id="id" placeholder="아이디">
                        <p class="message" id="id_message"></p>

                        <span>비밀번호</span><br>
                        <input type="password" name="pw1" id="pw1" placeholder="비밀번호">
                        <p class="message" id="pw_message"></p>

                        <span>비밀번호 재확인</span><br>
                        <input type="password" name="pw_check" id="pw_check" placeholder="비밀번호 재확인">
                        <p class="message" id="pw_check_message"></p>

						<span>이메일</span><br>
                        <input type="email" name="email" id="email" placeholder="이메일">
                        <p class="message" id="email_message"></p>

 						<span>전화번호</span><br>
                        <input type="tel" id="tel" name="tel" placeholder="010-0000-0000">
                        <p class="message" id="tel_message"></p>

                        <span>취미</span><br>
                        <input type="text" name="hobby" id="hobby">
                        <p class="message" id="hobby_message"></p>

						<span>직업</span><br>
                        <input type="text" name="job" id="job">
                        <p class="message" id="job_message"></p>

						<span>한줄소개</span><br>
                        <input type="text" name="info" id="info">
                        <p class="message" id="info_message"></p>

                        <span>나이</span><br>
                        <input type="number" name="age" id="age">
                        <p class="message" id="age_message"></p>

                        <input type="button" id="submitBtn" value="가입하기">
                    </form>
                </div>
            </article>
        </section>
        <section id="sec3"></section>
    </div>
	<script src="resources/javascript/_02_join.js"></script>
</body>

</html>