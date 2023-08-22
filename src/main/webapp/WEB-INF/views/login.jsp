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
                <img class="rounded-image" src="https://ucarecdn.com/fab34a50-2035-4a3e-b36d-e92de0cf7b32/" style="max-width: 100%; height: auto;">
            </div>
            <div class="login-box-left">
                <form>
                    <h1><spring:message code="login.login"/></h1>

                    <input class="inputs" type="text" id="id" name="id" placeholder="&nbsp;&nbsp;<spring:message code="login.id"/>" required>
                    <p class="err" id="id-empty"><spring:message code="login.id_empty"/></p>

                    <input class="inputs" type="password" id="password" name="password" placeholder="&nbsp;&nbsp;<spring:message code="login.password"/>" required>
                    <p class="err" id="password-empty"><spring:message code="login.password_empty"/></p>

                    <input class="login-bnt" type="button" onclick="login()" value="<spring:message code="login.login"/>">
                    <p class="ask-join"><spring:message code="login.ask_join"/></p>

                    <a class="join" href="join"><spring:message code="login.join"/></a>
                </form>
            </div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/log-api.js"></script>
</body>
</html>
