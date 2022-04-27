<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<c:import url="header_.jsp"></c:import>
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
    <!-- Page content-->
    <div class="container">
        <div class="row">
            <!-- Blog entries-->
            <div class="col-lg-8">
                <!-- Featured blog post-->
                <div class="card mb-4">
                    <a href="/academy"><img class="card-img-top" src="/img/OSCAR.jpg" alt="..." /></a>
                    <div class="card-body">
                        <h2 class="card-title">아카데미 작품상 목록</h2>
                        <a class="btn btn-primary" href="/academy">바로가기</a>
                    </div>
                </div>
                <!-- Nested row for non-featured blog posts-->

                <!-- Pagination-->

            </div>
            <!-- Side widgets-->
            <div class="col-lg-4">
                <!-- Search widget-->

                <!-- Categories widget-->
                <div class="card mb-4">
                    <a href="/boxOffice"><img class="card-img-top" src="/img/BoxOffice.jpg" alt="..." /></a>
                </div>
                <!-- Side widget-->
                <div class="card mb-4">
                    <div class="card-body">좋은 영화는 좋은 배우, 제작사가 아닌 좋은 내용을 품어야한다.</div>
                </div>
            </div>
        </div>
    </div>
</main>
<%--<script type="text/javascript" src="script/main/main.js"></script>--%>
<c:import url="footer_.jsp"></c:import>
