<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<main>
    <section>
        <div class="small text-muted">MovieShelf</div>
        <h2>오늘 한국영화 박스오피스 순위 TOP 10</h2>
        <hr>
        <div class="contentContainer">
            <table class="table table--block--boxoffice">
                <thead>
                <tr>
                    <th>순위</th>
                    <th>포스터</th>
                    <th>제목</th>
                    <th>개봉일</th>
                    <th>일일 관객수</th>
                    <th>누적관객수</th>
                </tr>
                </thead>
                <tbody id="boxOffice">

                </tbody>
            </table>
        </div>
    </section>
</main>
<script type="text/javascript" src="script/boxOffice/boxOffice.js"></script>
<c:import url="../footer_.jsp"></c:import>


