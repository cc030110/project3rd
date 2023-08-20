<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-10
  Time: 오전 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="/css/login.css">
    <title>로그인</title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <div class="login-box">
            <div class="login-box-right">
            <p>(대충 그럴듯한 이미지)</p>
            </div>
            <div class="login-box-left">
                <form>
                    <h1>로그인</h1>
                    <label for="id">아이디</label>
                    <input type="text" id="id" name="id" placeholder="아이디" required>
                    <p class="err" id="id-empty">아이디를 입력하세요.</p>
                    <label for="password">비밀번호</label>
                    <input type="password" id="password" name="password" placeholder="비밀번호" required>
                    <p class="err" id="password-empty">비밀번호를 입력하세요.</p>
                    <input type="button" onclick="login()" value="로그인">
                    <p class="ask-join">아직 회원이 아니십니까?</p>
                    <a class="join" href="join">회원가입</a>
                </form>
            </div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/log-api.js"></script>
</body>
</html>
