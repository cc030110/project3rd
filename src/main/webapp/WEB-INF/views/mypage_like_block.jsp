<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-23
  Time: 오후 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">
    <title></title>
</head>
<body>
<div>
    <div>
        즐겨찾기 목록
        <c:choose>
            <c:when test="${empty likeList}">
                즐겨찾기한 유저가 존재하지 않습니다.
            </c:when>
            <c:otherwise>
                <c:forEach items="${likeList}" var="likes">
                    ${likes}
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    <div>
        차단 목록
<%--        <c:choose>--%>
<%--            <c:when test="${empty blockList}">--%>
<%--                차단한 유저가 존재하지 않습니다.--%>
<%--            </c:when>--%>
<%--            <c:otherwise>--%>
<%--                <c:forEach items="${blockList}" var="blocks">--%>
<%--                    ${blocks}--%>
<%--                </c:forEach>--%>
<%--            </c:otherwise>--%>
<%--        </c:choose>--%>
    </div>
</div>
</body>
</html>
