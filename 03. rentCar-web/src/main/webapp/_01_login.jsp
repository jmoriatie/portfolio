<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@500&display=swap" rel="stylesheet">

	<link type="text/css" rel="stylesheet" href="resources/css/_01_login.css">
    <title>렌트카: 로그인</title>
</head>

<body>
    <div id="a">
        <section id="sec1"></section>
        <section id="sec2">
            <article>
                <div class="input_area">
                    <div class="login_header">
                        <span id="title" onclick="location.href='_00_index.jsp'">RENT CAR</span><br><br>
                    </div>
                    <div>
                        <form method="POST" action="_01_loginPro.jsp">
                            <% 
                            String pass = request.getParameter("pass");
                            String id = request.getParameter("id"); 
                            %>
                            <input id="id" name="id" type="text"
							<% if(id == null){ %>
							placeholder="아이디"
							<% } else{%>
							value="<%=id%>" readonly
							<%} %>
							><br>
                            <input id="pw" name="pw" type="password" placeholder="비밀번호"><br>
                            <div id="remember_div">
                                <input name="remember" name="remember" id="remember" type="checkbox">
                                <label id="remember_label" for="remember"></label>
                                <!-- 상태유지 기능처리 : 쿠키 저장 필요-->
                                <span id="remember_text">아이디 저장</span><br>
                            </div>
                            <input type="submit" id="submit" value="로그인">
                            <!-- <input type="button"  id="submit"  value="로그인" onclick="checkVal(form)"> -->
                            <%if(pass.equals("1")){%>	
                            <p id="message">아이디 또는 패스워드가 입력되지 않았습니다</p>
                            <%}%>
                            <%if(pass.equals("2")){%>	
                            <p id="message">아이디 또는 비밀번호가 일치하지 않습니다</p>
                            <%}%>
                        </form>
                    </div>
                    <br>
                    <hr>
                    <div class="login_sub">
                        <span class="regist">회원가입</span> |
                        <span >아이디 찾기</span> |
                        <span >비밀번호 찾기</span>
                    </div>
                </div>
            </article>
        </section>
        <section id="sec3"></section>
    </div>
    <script>
        // 타이틀 누르면 링크이동 => 본 페이지로 이동
        document.querySelector('#title').addEventListener('click', e=>{
            location.href = '_00_index.jsp';
        });

        // 회원가입 버튼 링크
        document.querySelector(".regist").addEventListener('click', e => {
            location.href = "_02_join.jsp";
        });
        
    </script>
</body>

</html>