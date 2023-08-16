<%-- Header --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!-- 다국어 처리 -->

<html>
<head>
    <!-- css -->
    <link rel="stylesheet" href="/css/grid.css">

    <!-- JS -->
    <!-- 선택 옵션에 따른 언어 변경 : locale 변경(/?lang='국가 코드') -->
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="/script/lang-select.js"></script>
</head>

<body>
<div class="header">
    <div class="logo">
        <h1><a href="/">GLOBALTIES</a></h1>
    </div>
    <div class="header-menu">
        <ul>
            <li><a href="#">회원게시판</a></li>
            <li><a href="board/free/list/1">자유게시판</a></li>
            <li><a href="#">모집게시판</a></li>
        </ul>
    </div>
    <div class="login" id="login">
        <a href="/login">
            <spring:message code="header.login"/>
        </a>
    </div>
    <div class="join" id="join">
        <a href="user/join">
            <spring:message code="header.join"/>
        </a>
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