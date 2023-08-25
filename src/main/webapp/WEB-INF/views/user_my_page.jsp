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
    <title><spring:message code="mypage.title"/></title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="/css/mypage.css">
    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <div id="sidebar">
            <div class="my-info">
                <h2><spring:message code="user_mypage.logo"/></h2>
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
                    <li id="update"><spring:message code="user_mypage.info"/></li>
                    <li id="like-block"><spring:message code="user_mypage.like"/></li>
                    <li id="board"><spring:message code="user_mypage.board"/>
                        <ul id="board-sub">
                            <li id="board-free"><spring:message code="user_mypage.freeboard"/></li>
                            <li id="board-community"><spring:message code="user_mypage.communityboard"/></li>
                        </ul>
                    </li>
                    <li id="community-accept">참여한 커뮤니티</li>
                    <li id="resign"><spring:message code="user_mypage.resign"/></li>
                </ul>
            </nav>
        </div>
        <div class="content-container">
            <h2 id="selected-menu"></h2>
            <div id="content">

            </div>
        </div>
    </div>
<c:import url="footer.jsp"/>
</div>
<script src="/script/mypage.js"></script>
</body>
</html>