<%--
  Created by IntelliJ IDEA.
  User: ho625
  Date: 2023-08-14
  Time: 오후 4:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="/css/mypage.css">
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <div class="button-container">
            <a class="button-link" href="#" id="updateButton">회원 수정</a>
            <a class="button-link" id="deleteButton">회원 탈퇴</a>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/mypage.js"></script>
</body>
</html>