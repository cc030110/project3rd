<!-- Index -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
    <title>GLOBALTIES</title>
</head>
<jsp:include page="header.jsp"></jsp:include>

<body>
    <div class="wrap">
<%--        <c:import url="header.jsp"/>--%>
        <p>empty log : ${empty log}</p>
        <div class="main_con">
            <spring:message code="index.hello"/>
            <div style="width: 100%;">
                <h2>세션 확인용</h2>
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
</body>

</html>
