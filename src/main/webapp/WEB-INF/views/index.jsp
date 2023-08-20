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
                    <p>나만의 언어 교환 파트너를 찾아보세요!</p>
                </div>

                <div class="intro_con">
                    <!-- 사이트 설명 -->
                    <div class="intro_explan">
                        <p>GLOBALTIES에 오신 것을 환영합니다.</p>
                        <p>저희는 공간적 제약이 없는 상호 언어 학습의 장을 제공합니다.</p>
                        <p>전세계의 사람들과 언어를 교류하며 서로의 문화를 공유해보세요.</p>
                        <p>메세지, 커뮤니티 등을 통해 보다 좋은 학습의 기회도 얻으실 수 있습니다.</p>
                        <p>유익한 시간이 되시길 바랍니다:)</p>
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
