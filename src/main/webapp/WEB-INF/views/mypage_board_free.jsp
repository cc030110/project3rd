<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-24
  Time: 오전 1:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">
    <title><spring:message code="mypage.title"/></title>
    <link href="/css/mypage_board.css" rel="stylesheet">
</head>
<body>
<div class="my-board-free">
    <c:choose>
        <c:when test="${empty boardList}">
            <spring:message code="mypage.warn"/>
        </c:when>
        <c:otherwise>
            <c:forEach items="${boardList}" var="board">
                <div class="my-board-free-list">
                    <a href="/board/free/${board.boardNo}">
                            ${board.title}
                    </a>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
