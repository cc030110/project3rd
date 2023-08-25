<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-24
  Time: 오전 1:11
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
<div class="my-board-community">
    <c:choose>
        <c:when test="${empty boardList}">
            <spring:message code="mypage.warn"/>
        </c:when>
        <c:otherwise>
            <c:forEach items="${boardList}" var="board">
                <div class="board-community-line">
                    <a href="/board/community/${board.boardNo}">
                        ${board.title}
                    </a>
                    <div class="accept-box">
                        <!-- Map<Integer,List<User>> participantUsers -->
                        <c:set var="userList" value="${participantUsers.get(board.boardNo)}"/>
                        <c:if test="${userList ne null}">
                            <!-- List<User> == acceptUser -->
                            <c:forEach items="${userList}" var="acceptUser">
                                <div class="accept-user">
                                    <a href="/user/${acceptUser.name}">${acceptUser.name}</a>
                                    <input type="button" class="accept-btn" onclick="userAccept(`${board.boardNo}`,`${acceptUser.id}`,true)" value=<spring:message code="mypage_board_community.accept"/>>
                                    <input type="button" class="refuse-btn" onclick="userAccept(`${board.boardNo}`,`${acceptUser.id}`,false)" value=<spring:message code="mypage_board_community.deny"/>>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<script src="/script/mypage.js"></script>
</body>
</html>
