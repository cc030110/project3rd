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
    <div id="sidebar">
        <div id="user-info">
            <h1>MY PAGE</h1>
        </div>
        <ul id="menu">
            <li><a href="#">내 정보수정</a></li>
            <li><a href="user_my_friend.jsp">친구 관리</a></li>
            <li><a href="#">내가 쓴 자유글</a></li>
            <li><a href="#">내가 쓴 모집글</a></li>
            <li><a href="#">모임 정보</a></li>
            <li><a href="#">탈퇴</a></li>
        </ul>
    </div>
    <div id="content">
        <h1>내 정보 수정</h1>
        <p>내용</p>
    </div>
    </div>
    <c:import url="footer.jsp"/>
</div>

<script src="/script/mypage.js"></script>
</body>
</html>