<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-09
  Time: 오전 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>자유 게시판</title>
    <link rel="stylesheet" href="/css/board_free_list.css">
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <h2><a href="/board/free/list/1">자유게시판</a></h2>
        <div></div>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/board-free-list.js"></script>
</body>
</html>
