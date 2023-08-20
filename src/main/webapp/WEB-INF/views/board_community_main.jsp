<!-- 모임 게시판 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!-- 다국어 처리 -->

<html>

<head>
    <!-- CSS -->
    <link rel="stylesheet" href="/css/board_community_main.css">

    <title>GLOBALTIES</title>
</head>

<body>
<div class="wrap">
    <c:import url="header.jsp"/>
        <div class="main">
            <c:if test="${not empty boardList.content}">
                <c:forEach items="${boardList.content}" var="board" varStatus="vs">
                    <a href="/board/community/${board.boardNo}">
                        <div class="con_list">
                            <!-- 게시판에 넣을 플랫폼 -->
                                <div class="con_platform">
                                    <img src="${platform.get(board.platformName)}" alt="이미지 불러오기 실패">
                                    <div class="con_platform_name">
                                        <p>${board.platformName}</p>
                                    </div>
                                </div>

                                <div class="con_text">
                                    <p>${board.boardNo}</p>
                                    <p>${board.title}</p><!-- 제목 -->
                                    <p>${board.contents}</p><!-- 내용 -->
                                    <p><spring:message code="board_community_main.views"/> : ${board.views}</p> <!-- 조회수 -->
                                    <p><spring:message code="board_community_main.author"/> : ${authorList.get(board.id)}</p>
                                    <p><spring:message code="board_community_main.participants"/>: ${board.participantsNum}</p>
                                    <p><spring:message code="board_community_main.createdAt"/> : ${board.createdAt}</p>
                                    <p><spring:message code="board_community_main.deadline"/>: ${board.deadline}</p>
                                </div>
                        </div>
                    </a>
                </c:forEach>
            </c:if>
        </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
