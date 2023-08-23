<%--
  Created by IntelliJ IDEA.
  User: ho625
  Date: 2023-08-14
  Time: 오후 4:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="/css/mypage.css">
    <link rel="stylesheet" href="/css/mypage_update.css">
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <div id="sidebar">
            <div class="my-info">
                <h2>MY PAGE</h2>
                <div class="profile-img">
                    <c:choose>
                        <c:when test="${user.profileImg ne null}">
                            <img src="${user.profileImg}" alt=""/>
                        </c:when>
                        <c:otherwise>
                            <img src="https://ucarecdn.com/1e359c2a-7124-4da9-9cd9-be5fe9e8c1f0/" alt=""/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <nav>
                <ul id="menu">
                    <li id="update">정보 조회/수정</li>
                    <li id="like-block">즐겨찾기/차단 관리</li>
                    <li id="board">내 게시글 조회
                        <ul id="board-sub">
                            <li id="board-free">자유 게시판</li>
                            <li id="board-community">모임 게시판</li>
                        </ul>
                    </li>
                    <li id="resign">탈퇴</li>
                </ul>
            </nav>
        </div>
        <div class="content-container">
            <h2 id="selected-menu" class="update">정보 수정</h2>
            <div id="content">

            </div>
        </div>
    </div>
</div>
<c:import url="footer.jsp"/>
</div>
<script src="/script/mypage.js"></script>
</body>
</html>