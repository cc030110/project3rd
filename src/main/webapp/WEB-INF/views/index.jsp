<!-- Index -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
    <title>GLOBALTIES</title>
</head>
<body>
    <div class="wrap">
        <c:import url="header.jsp"/>
        <div class="main">
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
            </div>
        </div>
        <c:import url="footer.jsp"/>
    </div>

    <script>
        // 토큰 가져오기
        const token = localStorage.getItem("jwtToken");
        console.log(token);

        const tokenHeader = document.querySelector("meta[name='jwt-token']").getAttribute("content");
        const accessToken = tokenHeader.replace("Bearer ", "");

        if (token) {
            // 토큰 디코딩
            const tokenParts = token.split(".");
            const tokenPayload = JSON.parse(atob(tokenParts[1]));

            // 필요한 정보를 HTML에 표시
            const userInfoDiv = document.getElementById("userInfo");
            userInfoDiv.innerHTML = `
                <p>User ID: ${tokenPayload.id}</p>
                <p>Name: ${tokenPayload.name}</p>
            `;
        } else {
            // 토큰이 없는 경우 처리
            console.log("Token not found.");
        }
    </script>
</body>

</html>
