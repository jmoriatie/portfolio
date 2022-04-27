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
	<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@700&display=swap" rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@500&display=swap" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/_00_index.css">
    <title>렌터카</title>
</head>

<body>
   <div class="wrap">
        <header>
            <section>
                <article id="header_art1">
                    <div id="logo"><img src="img/logo.png" onclick="location.href='_00_index.jsp'"></div>
                </article>
                <article id="header_art2">
                	<div id="company_name"><h2 onclick="location.href='_00_index.jsp'">렌터카:MVC1</h2></div>
                </article>
                <article id="header_art3">
                <%
                if(session.getAttribute("log") == null){
                %>
				<button onclick="location.href='_01_login.jsp?pass=null'">로그인</button> <span class="sep">|</span>
				<button onclick="location.href='_02_join.jsp'">회원가입</button>
                <%
                }else{
                %>
				<button onclick="location.href='_03_myPage.jsp'">마이페이지</button> <span class="sep">|</span>
				<button onclick="location.href='_01-1_logoutPro.jsp'">로그아웃</button>
                <%}%> 
                </article>
            </section>
        </header>
        <nav>
            <section>
                <button onclick="location.href='_6-1_rentCarList.jsp'">렌트카 리스트</button><span class="sep">|</span>
                <button onclick="location.href='_5-1_boardList.jsp'">자유 게시판</button>
            </section>
        </nav>