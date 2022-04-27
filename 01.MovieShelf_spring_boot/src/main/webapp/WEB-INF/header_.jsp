<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <head>
        <!-- favicon image -->
        <link rel="shortcut icon" href="/img/favicon.ico">

        <meta property="og:title" content="나만의 영화 선반 Movie Shelf">
        <meta property="og:description" content="문득 '나중에 저 영화는 꼭 봐야겠다'는 생각이 드신다면, 잊지않고 저장해둘 수 있는 나만의 영화 선반">
        <meta property="og:image" content="/img/OSCAR.jpg">

        <!-- ... -->
    </head>

    <!-- Favicon-->
<%--    <link rel="icon" type="image/x-icon" sizes="16x16" href="/img/favicon.ico" />--%>

    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="/css/styles.css" rel="stylesheet" />
    <link href="/css/tablestyle.css" rel="stylesheet" />

    <title>MovieShelf</title>
</head>
<body>
<!-- Responsive navbar-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/">MovieShelf</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="/academy">아카데미 작품상 수상작</a></li>
                <li class="nav-item"><a class="nav-link" href="/boxOffice">일일 한국 박스오피스 TOP10</a></li>
                <li class="nav-item"><a class="nav-link" href="/boardList/1">영화 TalkTalk</a></li>
                <li class="nav-item"><a class="nav-link" href="/search">영화 검색</a></li>
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="/mypage">마이페이지</a></li>
                <c:set var="ses" value="${sessionScope.log}"/>
                <c:set var="wishCount" value="${sessionScope.wishCount}"/>

                <c:choose>
                    <c:when test="${ses != null}">
                        <c:if test="${wishCount != 0}">
                            <button onclick="location.href='/main/mypage/3'" class="btn btn-primary" id="button-search" type="button"><c:out value="${wishCount}"/></button>
                        </c:if>
                        <li class="nav-link active" aria-current="page">${ses.getUser_nickname()}님</li>
                        <li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
                        <li class="nav-item"><a class="nav-link" href="/signUp">회원가입</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

