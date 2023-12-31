<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-23
  Time: 오후 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">
    <link rel="stylesheet" href="/css/mypage_like_block.css">
    <title><spring:message code="mypage.title"/></title>
</head>
<body>
<div id="divide">
    <div id="like-list-box">
        <div class="like-title">
        <h3><spring:message code="mypage_like_block.like_list"/></h3>
        </div>
        <c:choose>
            <c:when test="${empty likeList}">
                <spring:message code="mypage_like_block.like_warn"/>
            </c:when>
            <c:otherwise>
                <c:forEach items="${likeList}" var="likes">
                    <div class="user-box">
                        <a href="/user/${likes}">${likes}</a>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    <div id="block-list-box">
        <div class="block-title">
            <h3><spring:message code="mypage_like_block.block_list"/></h3>
        </div>
        <c:choose>
            <c:when test="${empty blockList}">
                <spring:message code="mypage_like_block.block_warn"/>
            </c:when>
            <c:otherwise>
                <c:forEach items="${blockList}" var="blocks">
                    <div class="user-box">
                        <a href="/user/${blocks}">${blocks}</a>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
