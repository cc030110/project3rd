<%-- Header --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!-- 다국어 처리 -->

<html>
<head>
    <!-- css -->
    <link rel="stylesheet" href="/css/grid.css">

    <!-- JS -->
    <!-- 선택 옵션에 따른 언어 변경 : locale 변경(/?lang='국가 코드') -->
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="/script/header.js"></script>
</head>

<body>
<div class="header">
    <div class="logo">
<%--        <img src="https://ucarecdn.com/b846c160-2f5f-4f09-9f7e-66baf85b2a90/" alt="로고">--%>
        <h1><a href="/">GLOBALTIES</a></h1>
    </div>

    <div class="header-menu">
        <ul>
            <li><a href="/user/list/1"><spring:message code="header.profile"/></a></li>
            <li><a href="/board/free/list/1"><spring:message code="header.free"/></a></li>
            <li><a href="/board/community/main/1"><spring:message code="header.community"/></a></li>
        </ul>
    </div>

    <div class="login" id="login">
        <c:choose>
            <c:when test="${not empty cookie.accessToken}">
                <a href="/user/logout" onclick="logout()">
                    <spring:message code="header.logout"/>
                </a>
            </c:when>

            <c:otherwise>
                <a href="/user/login">
                    <spring:message code="header.login"/>
                </a>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="join" id="join">
            <c:choose>
                <c:when test="${not empty cookie.accessToken}">
                    <a href="/">
                        <spring:message code="header.mypage"/>
                    </a>
                </c:when>

                <c:otherwise>
                    <a href="/user/join">
                        <spring:message code="header.join"/>
                    </a>
                </c:otherwise>
            </c:choose>
    </div>

    <div class="lang-select">
        <select name="locales" id="locales">
            <option value="en" selected>English</option>
            <option value="ko">한국어</option>
            <option value="ja">日本語</option>
            <%--<option value="spanish">Español</option>
            <option value="traditional_chinese">中文</option>
            <option value="russian">Русский</option>
            <option value="french">Français</option>
            <option value="italian">Italia</option>
            <option value="german">Deutsche</option>
            <option value="portuguese">Português</option>
            <option value="thai">ภาษาไทย</option>
            <option value="vietnamese">Tiếng Việt</option>
            <option value="indonesian">bahasa Indonesia</option>
            <option value="arabian">عربي</option>
            <option value="hindi">हिंदी</option>--%>
        </select>
    </div>

</div>
</body>

</html>