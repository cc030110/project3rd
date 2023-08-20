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
<%--            <p>(대충 그럴듯한 이미지)</p>--%>
            </div>
            <div class="login-box-left">
                <form>
                    <h1><spring:message code="login.login"/></h1>
                    <label for="id"><spring:message code="login.id"/></label>
                    <input type="text" id="id" name="id" placeholder="<spring:message code="login.id"/>" required>
                    <p class="err" id="id-empty"><spring:message code="login.id_empty"/></p>
                    <label for="password"><spring:message code="login.password"/></label>
                    <input type="password" id="password" name="password" placeholder="<spring:message code="login.password"/>" required>
                    <p class="err" id="password-empty"><spring:message code="login.password_empty"/></p>
                    <input type="button" onclick="login()" value="<spring:message code="login.login"/>">
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
