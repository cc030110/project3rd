<!-- Index -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
    <title>GLOBALTIES</title>
</head>

<style>
    div.intro{
        display:flex;
        justify-content: space-around;
    }

    div.intro_explan{
        width:700px;
        height:450px;
        border:2px solid cyan;
    }

    div.intro_img{
        width:600px;
        height:450px;
        border:2px solid cyan;
    }

    div.intro_img>img{
        width:600px;
        height:450px;
    }


</style>
<body>
    <div class="wrap">
        <c:import url="header.jsp"/>
        <div class="main">
            <div class="intro">
                <!-- 사이트 설명 -->
                <div class="intro_explan">
                    대충 설명 설명 설명 설명
                </div>

                <!-- 사이트 이미지 -->
                <div class="intro_img">
                    <img src="https://ucarecdn.com/2d38448e-9601-4220-a4b9-d5f218510592/">
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
