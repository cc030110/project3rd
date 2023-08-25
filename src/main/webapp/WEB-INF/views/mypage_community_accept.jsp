<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-25
  Time: 오전 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <link href="/css/mypage.css" rel="stylesheet">
</head>
<body>
    <div id="community-accept">
        <c:choose>
            <c:when test="${empty participants}">
                신청 내역이 없습니다.
            </c:when>
            <c:otherwise>
                <c:forEach items="${participants}" var="part">
                    <!-- Map<boardNo,board> boardList -->
                    <div class="line-box">
                        <!-- 제목 -->
                        <a href="/board/community/${part.boardNo}">
                            ${boardList.get(part.boardNo).title}
                        </a>
                        <c:choose>
                            <c:when test="${part.isAccept==1}">
                                <p>신청이 수락되었습니다.</p>
                            </c:when>
                            <c:when test="${part.isAccept==2}">
                                <p>신청이 거절되었습니다.</p>
                            </c:when>
                            <c:otherwise>
                                <p>신청이 대기중입니다.</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
