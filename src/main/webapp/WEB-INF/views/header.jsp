<%-- Header --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!-- 다국어 처리 -->

<html>

<head>
    <title>Title</title>

    <!-- css -->
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/header.css">

    <!-- JS -->
    <!-- 선택 옵션에 따른 언어 변경 : locale 변경(/?lang='국가 코드') -->
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="/script/lang-select.js"></script>
</head>

<body>
    <div class="header">
        <div class="logo">
            <h1>GLOBALTIES</h1>
        </div>

        <div class="login" id="login">
            <spring:message code="header.login"/>
        </div>

        <div class="join" id="join">
            <spring:message code="header.join"/>
        </div>

        <div class="lang_select">
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
