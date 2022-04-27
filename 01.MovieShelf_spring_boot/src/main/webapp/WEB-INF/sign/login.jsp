<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h2>로그인</h2>
        <hr>
        <div class="card-body">
            <div class="input-group">
                <form method="post" action="loginPro">
                    <article id="idpws">
                        ID&#9;<input class="form-control" type="text" name="id" aria-describedby="button-search" placeholder="아이디 입력 " required>
                        PW&#9;<input class="form-control" type="password" name="pw" aria-describedby="button-search" placeholder="비밀번호 입력" required>
                        <br>
                        <input class="btn btn-primary" id="submit" type="submit" value="로그인">
                    </article>
                </form>
            </div>
        </div>
    </section>
</main>
<c:set var="logError" value="${sessionScope.logError}"></c:set>
<c:set var="logPlz" value="${sessionScope.logPlz}"></c:set>
<c:choose>
    <c:when test="${logError == 1}">
        <script>
            alert("ID, 비밀번호가 틀리거나 존재하지 않는 회원입니다.");
        </script>
        <c:remove var="logError" scope="session"/>
    </c:when>

</c:choose>
<c:import url="../footer_.jsp"></c:import>

