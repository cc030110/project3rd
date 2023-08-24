<!-- Index -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
    <link rel="stylesheet" href="/css/index.css">
    <meta property="og:title" content="GLOBALTIES">
    <meta property="og:description" content="언어&문화교류 사이트">
    <meta property="og:image" content="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/">

    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">

    <title>GLOBALTIES</title>
</head>

<body>
    <div class="wrap">
        <c:import url="header.jsp"/>
        <div class="main">
                <!-- 환영인사 -->
                <div class="greetings">
                    <p><spring:message code="index.greeting"/></p>
                </div>

                <div class="intro_con">
                    <!-- 사이트 설명 -->
                    <div class="intro_explan">
                        <p><spring:message code="index.explan_1"/></p>
                        <p><spring:message code="index.explan_2"/></p>
                        <p><spring:message code="index.explan_3"/></p>
                        <p><spring:message code="index.explan_4"/></p>
                        <p><spring:message code="index.explan_5"/></p>
                    </div>

                    <!-- 사이트 이미지 -->
                    <div class="intro_img">
                        <img src="https://ucarecdn.com/0925f670-b521-465e-9f9e-211c95d59610/">
                    </div>
                </div>
        </div>
        <c:import url="footer.jsp"/>
    </div>
</body>

</html>
