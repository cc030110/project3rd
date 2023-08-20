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
            <c:forEach items="${list}" var="listItem" varStatus="vs">
                <a href="/board/community/${listItem.boardNo}">
                    <div class="con_list">
                        <!-- 게시판에 넣을 플랫폼 -->
                            <div class="con_platform">
                                <img src="${platform.get(listItem.platformName)}" alt="이미지 불러오기 실패">
                                <div class="con_platform_name">
                                    <p>${listItem.platformName}</p>
                                </div>
                            </div>

                            <div class="con_text">
                                <p>${listItem.title}</p><!-- 제목 -->
                                <p>${listItem.contents}</p><!-- 내용 -->
                                <p><spring:message code="board_community_main.views"/> : ${listItem.views}</p> <!-- 조회수 -->
                                <p><spring:message code="board_community_main.author"/> : ${listItem.id}</p>
                                <p><spring:message code="board_community_main.participants"/>: ${listItem.participantsNum}</p>
                                <p><spring:message code="board_community_main.createdAt"/> : ${listItem.createdAt}</p>
                                <p><spring:message code="board_community_main.deadline"/>: ${listItem.deadline}</p>
                            </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
