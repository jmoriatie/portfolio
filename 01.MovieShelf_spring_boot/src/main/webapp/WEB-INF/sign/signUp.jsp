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
        <h2>회원가입</h2>
        <hr>

        <div class="card-body">
            <div class="input-group">
                <form onSubmit="return false;">
                    <article id="idpws">
                        <p>ID&#9;<input type="text" name="id" id="idInput" class="form-control"
                                        aria-describedby="button-search" placeholder="아이디 입력" required></p>
                        <p class="small text-muted" id="pId"></p>
                        <p>PW&#9;<input type="password" name="pw" id="pwInput" class="form-control"
                                        aria-describedby="button-search" placeholder="비밀번호 입력" required></p>
                        <p class="small text-muted" id="pPw"></p>
                        <p>이름&#9;<input type="text" name="name" id="nameInput" class="form-control"
                                        aria-describedby="button-search" placeholder="이름 입력" required></p>
                        <p class="small text-muted" id="pName"></p>
                        <p>닉네임&#9;<input type="text" name="nickname" id="nicknameInput" class="form-control"
                                         aria-describedby="button-search" placeholder="닉네임 입력" required></p>
                        <p class="small text-muted" id="pNickname"></p><br>
                        <input id="submit" type="submit" value="가입" class="btn btn-primary" onclick="signUpUser(form)">

                        <c:set var="signUpError" value="${sessionScope.signUpError}"></c:set>
                        <c:choose>
                            <c:when test="${signUpError == 1}">
                                <script>
                                    alert("중복되는 ID 입니다.");
                                </script>
                            </c:when>
                        </c:choose>
                    </article>
                </form>
            </div>
        </div>
    </section>
</main>
<script type="text/javascript" src="script/signUp.js"></script>
<c:import url="../footer_.jsp"></c:import>

