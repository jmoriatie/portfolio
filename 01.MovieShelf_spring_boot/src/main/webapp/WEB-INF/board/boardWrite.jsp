<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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
    <c:set var="movieName" value="${requestScope.movieName}" scope="page" />
    <c:set var="moviePoster" value="${requestScope.moviePoster}" scope="page" />

    <section class="boardWriteSec">
        <article class="arti1">
            <div class="small text-muted">MovieShelf</div>
            <h2>게시글 작성</h2>
            <hr>

            <h4> 먼저 영화를 검색/선택하세요 </h4>
            <form>
                <input type="text" name="search" id="search" placeholder="영화검색...">
                <input type="button" name="go" id="go" value="검색" onclick="getMovie()">
            </form>

            <form id="movieSelect" action="/boardWrite/choice">
                <input type="hidden" name="idxHidden" id="idxHidden" value=""/>
                <input type="hidden" name="searchWord" id="searchWord" value=""/>
            <table class="table table--block--searchWrite" id="searchArea">

            </table>
            </form>

            <hr>
            <c:choose>
                <c:when test="${moviePoster != ''}">
                    <img src="${moviePoster}"/>                                </c:when>
                <c:otherwise>
                    -
                </c:otherwise>
            </c:choose>
            <form id="write" method="post" action="/boardWrite">
                <table class="table table--block--boardWrite">
                    <input name="moviePoster" type="hidden" value="${moviePoster}"/>
                    <input name="movieName" type="hidden" value="${movieName}"/>

                    <thead>
                    <tr>
                        <th>영화명</th>
                        <th>제목</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${movieName != null}">
                                    <span id="movieText"><c:out value="${movieName}"/></span>
                                </c:when>
                                <c:otherwise>
                                    영화를 선택하세요
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><input name="title" id="title" type="text" placeholder="제목을 입력하세요" class="form-control" aria-describedby="button-search" required/></td>
                    </tr>
                    </tbody>
                </table>

                <textarea height="500px" name="content" id="content" required></textarea>
                <hr>

                <p width="200px">게시글 비밀번호: <input class="form-control" aria-describedby="button-search" type="password" name="pw" id="pw" placeholder="게시글 비밀번호 입력" ></p>



                <input class="btn btn-primary" type="button" value="작성하기" id="submitButton" >
            </form>
        </article>
    </section>
</main>

<script src="/script/board/board.js" type="text/javascript"></script>
<c:import url="../footer_.jsp"></c:import>
