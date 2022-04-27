<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <article>
            <c:set var="log" value="${sessionScope.log}" scope="page"/>
            <c:set var="boardListPageSize" value="${requestScope.boardListPageSize}" scope="page"/>
            <c:set var="nowPageList" value="${requestScope.boardListInNowPage}" scope="page"/>
            <c:set var="nowPage" value="${requestScope.nowPage}" scope="page"/>

            <div class="small text-muted">MovieShelf</div>
            <h2>영화 게시판</h2>
            <hr>
            <table class="table table--block--boardList" cellspacing="0" cellpadding="0">
                <thead>
                <tr>
                    <th style="width:10%">포스터</th>
                    <th>영화명</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>좋아요</th>
                    <th>작성일</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="post" items="${nowPageList}">
                    <tr onclick="location.href='/board/${post.talk_no}'">
                        <td><img src="${post.movie_poster}"/></td>
                        <td><c:out value="${post.movie_name}"/></td>
                        <td><c:out value="${post.talk_title}"/></td>
                        <td><c:out value="${post.user_id}"/></td>
                        <td><c:out value="${post.talk_likes}"/></td>
                        <td><c:out value="${post.talk_regdate}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
            <c:if test="${log != null}">
                <button class="btn btn-primary"
                        onclick="location.href='${pageContext.request.contextPath}/boardWriteForm'">글작성
                </button>
            </c:if>
            <br>
            <!-- Pagination-->
            <nav aria-label="Pagination">
                <hr class="my-0"/>
                <ul class="pagination justify-content-center my-4">
                    <li class="page-item"><a class="page-link" href="/boardList/1" >처음</a></li>
                    <c:choose>
                        <%--전체page - 현재 페이지 수가 5보다 작다면(5페이지 이하 && 마지막 - 5 경우) : 현재페이지부터 마지막 페이지까지--%>
                        <c:when test="${boardListPageSize - nowPage < 5}">
                            <%-- 일단 뒷부분으로 가정하고, start를 5페이지로 셋팅해줌 --%>
                            <c:set var="start" value="${boardListPageSize - 4}"/>
                            <%--전체 페이지가 5보다 작을 경우는 1부터 시작하게 셋팅--%>
                            <c:if test="${boardListPageSize <= 5}">
                                <c:set var="start" value="1"/>
                            </c:if>
                            <%--1이 아닐 경우 전 페이지 먼저 출력--%>
                            <c:if test="${nowPage != 1}">
                                <li class="page-item"><a class="page-link" href="/boardList/${start-1}" >${start-1}</a></li>
                            </c:if>
                            <c:forEach begin="${start}" end="${boardListPageSize}" var="page">
                                <c:choose>
                                <%--현재페이지랑 같은 경우--%>
                                <c:when test="${page == nowPage}">
                                    <li class="page-item active" aria-current="page"><a class="page-link"  href="/boardList/${page}">${page}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="/boardList/${page}">${page}</a></li>
                                </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:when>

                        <%--page수 5보다 크다면--%>
                        <c:otherwise>
                            <c:if test="${nowPage != 1}">
                                <li class="page-item"><a class="page-link" href="/boardList/${nowPage-1}" >${nowPage-1}</a></li>
                            </c:if>
                            <c:forEach begin="${nowPage}" end="${nowPage + 4}" var="page">
                                <c:choose>

                                <%--현재페이지랑 같은 경우--%>
                                <c:when test="${page == nowPage}">
                                    <li class="page-item active" aria-current="page"><a class="page-link"  href="/boardList/${page}">${page}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="/boardList/${page}">${page}</a></li>
                                </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <li class="page-item"><a class="page-link">...</a></li>
                            <li class="page-item"><a class="page-link" href="/boardList/${boardListPageSize}">${boardListPageSize}</a></li>
                        </c:otherwise>
                    </c:choose>
                    <li class="page-item"><a class="page-link" href="/boardList/${boardListPageSize}">마지막</a></li>
                </ul>
            </nav>
        </article>
    </section>
</main>
<c:import url="../footer_.jsp"></c:import>
