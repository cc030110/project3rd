<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <link rel="stylesheet" href="/css/login.css">
    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">
    <title>GLOBALTIES</title>
</head>

<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <div class="login-box">
            <div class="login-box-right">
                <img class="rounded-image" src="https://ucarecdn.com/fab34a50-2035-4a3e-b36d-e92de0cf7b32/"
                     style="max-width: 100%; height: auto;">
            </div>
            <div class="login-box-left">
                <form>
                    <h1><spring:message code="login.login"/></h1>

                    <input class="inputs" type="text" id="id" name="id"
                           placeholder="&nbsp;&nbsp;<spring:message code="login.id"/>" required>
                    <p class="err" id="id-empty"><spring:message code="login.id_empty"/></p>

                    <input class="inputs" type="password" id="password" name="password"
                           placeholder="&nbsp;&nbsp;<spring:message code="login.password"/>" required>
                    <p class="err" id="password-empty"><spring:message code="login.password_empty"/></p>

                    <div class="svg-wrapper">
                        <svg height="40" width="150" xmlns="http://www.w3.org/2000/svg">
                            <rect id="shape" height="40" width="150"/>
                            <div id="text">
                                <span class="spot login-bnt" onclick="login()"><spring:message
                                        code="login.login"/></span>
                            </div>
                        </svg>
                    </div>
                    <p class="ask-join"><spring:message code="login.ask_join"/></p>
                    <br>
                    <a class="spot login-bnt" href="join"><spring:message code="login.join"/></a>

                </form>
            </div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/log-api.js"></script>
</body>
</html>
