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
    <link rel="stylesheet" href="/css/board-free-list.css">
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <h2>자유 게시판</h2>
        <div class="list-container">
            <ul>
                <li>
                    <div class="number">
                        <p>번호</p>
                    </div>
                    <div class="title">
                        <p>제목</p>
                    </div>
                    <div class="author">
                        <p>작성자</p>
                    </div>
                    <div class="created">
                        <p>작성일</p>
                    </div>
                    <div class="views">
                        <p>조회수</p>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
