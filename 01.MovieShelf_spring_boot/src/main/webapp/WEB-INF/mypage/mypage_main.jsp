<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
            </table>
            <br>
            <section>

                <c:choose>
                    <c:when test="${select == 1}">
                        <h4>나의 게시글 수 : <c:out value="${ta.size()}"/></h4>
                        <h4>게시글 목록</h4>
                        <table class="table table--block--mypage1">
                            <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>좋아요</th>
                                <th>작성일</th>
                                <th>삭제</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="post" items="${ta}">
                                <tr>
                                    <td><c:out value="${post.talk_no}"/></td>
                                    <td onclick="location.href='/board/${post.talk_no}'"><c:out
                                            value="${post.talk_title}"/></td>
                                    <td><c:out value="${post.talk_likes}"/></td>
                                    <td><c:out value="${post.talk_regdate}"/></td>
                                    <td>
                                        <button class="btn btn-primary"
                                                onclick="location.href='${pageContext.request.contextPath}/mypage/boardDelete/${post.talk_no}'">
                                            삭제
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <br>
                    </c:when>
                    <c:when test="${select == 2}">
                        <h4>나의 댓글 수 : <c:out value="${ca.size()}"/></h4>
                        <h4>댓글 목록</h4>
                        <table class="table table--block--mypage2">
                            <thead>
                            <tr>
                                <th>번호</th>
                                <th>댓글</th>
                                <th>대댓글수</th>
                                <th>작성일</th>
                                <th>삭제</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="comment" items="${ca}">
                                <tr>
                                    <td><c:out value="${comment.talk_no}"/></td>
                                    <td onclick="location.href='/board/${comment.talk_no}'"><c:out
                                            value="${comment.comment_content}"/></td>
                                    <td><c:out value="${comment.depth}"/></td>
                                    <td><c:out value="${comment.comment_date}"/></td>
                                    <td>
                                        <button class="btn btn-primary"
                                                onclick="location.href='/mypage/comment/deleteComment/${comment.comment_id}'">
                                            삭제
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <br>
                    </c:when>
                    <c:when test="${select == 3}">
                        <h4>나의 볼영화 수 : <c:out value="${wa.size()}"/></h4>
                        <h4>찜 목록</h4>
                        <table class="table table--block--mypage3">
                            <thead>
                            <tr>
                                <th>번호</th>
                                <th>영화번호</th>
                                <th>영화제목</th>
                                <th>선택일</th>
                                <th>그때생각</th>
                                <th>수정</th>
                                <th>삭제</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="wish" items="${wa}">
                                <tr>
                                    <td><c:out value="${wish.wish_no}"/></td>
                                    <td><c:out value="${wish.movie_no}"/></td>
                                    <td onclick="location.href='#'"><c:out value="${wish.movie_name}"/></td>
                                    <td><c:out value="${wish.add_date}"/></td>
                                    <c:choose>
                                        <c:when test="${wish.comment == null}">
                                            <td>
                                                <button class="btn btn-primary"
                                                        onclick="location.href='/CommentWrite/${wish.wish_no}/${wish.movie_no}'">
                                                    코멘트적기
                                                </button>
                                            </td>
                                            <td></td>
                                        </c:when>
                                        <c:when test="${wish.comment != null}">
                                            <td><c:out value="${wish.comment}"/></td>
                                            <td>
                                                <button class="btn btn-primary"
                                                        onclick="location.href='/CommentWrite/${wish.wish_no}/${wish.movie_no}'">
                                                    수정
                                                </button>
                                            </td>
                                        </c:when>
                                    </c:choose>
                                    <td>
                                        <button class="btn btn-primary"
                                                onclick="location.href='/mypage/wishList/deleteWish/${wish.wish_no}'">삭제
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <br>
                    </c:when>
                </c:choose>
            </section>
        </c:when>
        <c:otherwise>
            <h2>메인페이지 이동</h2>
            <c:redirect url="../main.jsp"></c:redirect>
        </c:otherwise>
    </c:choose>

</main>
<c:import url="../footer_.jsp"></c:import>
