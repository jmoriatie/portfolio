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
<main>
    <c:set var="ses" value="${sessionScope.log}"/>
    <input type="hidden" value="${ses.getUser_id()}" id="session">
    <section>
            <div class="small text-muted">MovieShelf</div>
            <h1>영화 검색</h1>
        <form onsubmit="getSearchResult(); return  false">
            <input type="text" name="movieSearch" id="movieSearch">
            <button  class="btn btn-primary" id="search" type="submit">검색</button>
        </form>
        <table class="table table--block--search" id="searchArea">

        </table>
    </section>
</main>

<script type="text/javascript" src="script/search/search.js"></script>
<c:import url="../footer_.jsp"></c:import>

