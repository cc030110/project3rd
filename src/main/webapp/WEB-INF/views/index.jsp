<!-- Index -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
    <link rel="stylesheet" href="/css/index.css">
    <title>GLOBALTIES</title>
</head>

<body>
    <div class="wrap">
        <c:import url="header.jsp"/>
        <div class="main">
            <div class="intro">
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
                        <img src="https://ucarecdn.com/2d38448e-9601-4220-a4b9-d5f218510592/">
                    </div>
                </div>
            </div>
            <%-- 로그인 후, 회원 로그확인 어찌할지 고민
            <spring:message code="index.hello"/>
            <div style="width: 100%;">
                <c:choose>
                    <c:when test="${not empty log}">
                        <p>로그인 된 사용자: ${log}</p>
                    </c:when>
                    <c:otherwise>
                        <p>로그인 중이 아님</p>
                    </c:otherwise>
                </c:choose>
            </div>--%>
        </div>
        <c:import url="footer.jsp"/>
    </div>
</body>

</html>
