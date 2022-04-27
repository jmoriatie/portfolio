<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<c:import url="../header_.jsp"></c:import>
<!-- Page header with logo and tagline-->
<header class="py-5 bg-light border-bottom mb-4">
    <div class="container">
        <div class="text-center my-5">
            <h1 class="fw-bolder">나만의 영화 선반</h1>
            <p class="lead mb-0">Movie Shelf</p>
        </div>
    </div>
</header>
<main>
    <c:set var="log" value="${sessionScope.log}"/>
    <c:set var="select" value="${requestScope.select}" scope="page"/>
    <c:set var="ta" value="${requestScope.ta}" scope="page"/>
    <c:set var="ca" value="${requestScope.ca}" scope="page"/>
    <c:set var="wa" value="${requestScope.wa}" scope="page"/>

    <c:choose>
        <c:when test="${log != null}">
    <div class="small text-muted">MovieShelf</div>
    <h2>나의 마이페이지 목록</h2>
    <table>
    <button class="btn btn-primary" onclick="location.href='/main/mypage/update'">개인정보수정</button>
    <button class="btn btn-primary" onclick="location.href='/main/mypage/1'">게시글</button>
    <button class="btn btn-primary" onclick="location.href='/main/mypage/2'">댓글</button>
    <button class="btn btn-primary" onclick="location.href='/main/mypage/3'">위시리스트</button>
    </table><br>
            <section>
                <h2>개인정보 수정</h2>
                <form onSubmit="return false;">
                    <input type="hidden" name="uNo" value="${log.user_no}">
                    <ul>
                        <li>아이디 : <c:out value="${log.user_id}"/></li>
                        <li>비밀번호 : <input class="form-control" type="password" name="pw" id="pw"></li>
                        <p id="pPw"></p>
                        <li>이름 : <input class="form-control" type="text" name="name" id="name" value="${log.user_name}"></li>
                        <p id="pName"></p>
                        <li>닉네임 : <input class="form-control" type="text" name="nick_name" id="nick_name" value="${log.user_nickname}"></li>
                        <p id="pNickname"></p>
                        <li>가입일자 : <c:out value="${log.user_regdate}"/></li>
                    </ul>
                    <input class="btn btn-primary" type="submit" value="수정" onclick = "checkUpdate(form)">
                </form>

        </c:when>
    </c:choose>


</main>
<script type="text/javascript" src="/script/myPage/myPage.js"></script>
<c:import url="../footer_.jsp"></c:import>
