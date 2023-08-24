<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="board_free.title"/></title>
    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">
    <link rel="stylesheet" href="/css/board_free.css">
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <c:choose>
            <c:when test="${board eq null}">
                <div id="not-exist"><spring:message code="board_free.exist"/></div>
            </c:when>

            <c:otherwise>
                <div class="first-line">
                    <c:if test="${not empty id && id eq board.id}">
                        <div class="btn-group">
                            <a href="/board/free/${board.boardNo}/update" id="update-btn">
                                <input type="button" id="modify-btn" value=<spring:message code="board_free.modify"/>>
                            </a>
                            <input type="button" id="delete-btn" onclick="deleteBoard(${board.boardNo})" value=<spring:message code="board_free.delete"/>>
                        </div>
                    </c:if>

                    <div class="back_btn">
                        <input type="button" id="back-btn" value=<spring:message code="board_free.back"/>>
                    </div>
                </div>

                <div class="second-line">
                    <div class="title"><spring:message code="board_free.boardTitle"/> : ${board.title}</div>
                </div>

                <div class="third-line">
                    <div class="creator">
                        <spring:message code="board_free.author"/> : ${board.name}
                    </div>

                    <div class="etc">
                        <span><spring:message code="board_free.views"/> : ${board.views}</span> | <span><spring:message code="board_free.createdAt"/> : ${board.createdAt}</span>
                    </div>
                </div>

                <div class="contents-line">
                    <textarea id="contents" readonly>${board.contents}</textarea>
                </div>

                <c:if test="${imgList ne null}">
                    <div class="img-box">
                        <c:forEach items="${imgList}" var="imgs" varStatus="vs">
                            <img src="${imgs.img}">
                        </c:forEach>
                    </div>
                </c:if>

                <div class="last-line"></div>
            </c:otherwise>
        </c:choose>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/board-free.js"></script>
</body>
</html>
