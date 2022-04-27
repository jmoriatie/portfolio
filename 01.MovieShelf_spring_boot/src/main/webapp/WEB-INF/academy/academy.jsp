<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="small text-muted">MovieShelf</div>
<h2>아카데미 작품상 수상작</h2>
<hr>
<div id="academyRadio">
    <ul>
        <li><input type="radio" id="r1" name="academy" checked>연도별</li>
        <li><input type="radio" id="r2" name="academy">점수별</li>
        <li><input type="radio" id="r3" name="academy">상영시간별</li>
    </ul>
</div>
<main>
    <c:set var="ses" value="${sessionScope.log}"/>
    <input type="hidden" value="${ses.getUser_id()}" id="session">
    <section>
        <div class="contentContainer">
            <table class="table table--block--academy">
                <thead>
                <tr>
                    <th style="width:10%">포스터</th>
                    <th>영화명</th>
                    <th>장르</th>
                    <th>IMDB 점수</th>
                    <th>개봉일</th>
                    <th>상영시간</th>
                    <th>감독</th>
                    <th>주연</th>
                    <c:if test="${ses != null}">
                    <th>나중에 볼 영화</th>
                    </c:if>
                </tr>
                </thead>
                <tbody id="movie">

                </tbody>
            </table>
        </div>
    </section>
</main>

<script type="text/javascript" src="script/academy/academy.js"></script>
<c:import url="../footer_.jsp"></c:import>

