<!-- 모임 게시판 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!-- 다국어 처리 -->

<html>

<head>
    <!-- CSS -->
    <link rel="stylesheet" href="/css/gird.css">

    <title>GLOBALTIES</title>
</head>

<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <c:choose>
        <c:when test="${board eq null}">
            <p>존재하지 않는 게시글입니다.</p>
        </c:when>

        <c:otherwise>
            <ul>
                <li>
                    <p>게시글 번호 : ${board.boardNo}</p>
                </li>

                <li>
                    <p>플랫폼 : ${board.platformName}</p>
                </li>

                <li>
                    <p>제목 : ${board.title}</p>
                </li>

                <li>
                    <p>작성자 : ${board.id}</p>
                </li>

                <li>
                    <p>내용 : ${board.contents}</p>
                </li>

                <li>
                    <p>참가자 수 : ${board.participantsNum}</p>
                </li>

                <li>
                    <p>마감일 : ${board.deadline}</p>
                </li>

                <li>
                    <p>작성일 : ${board.createdAt}</p>
                </li>

                <c:if test="${imgList ne null}">
                    <div class="img-box">
                        <c:forEach items="${imgList}" var="imgs" varStatus="vs">
                            <img src="${imgs.img}">
                        </c:forEach>
                    </div>
                </c:if>

            </ul>

            <div>
                <spring:message code="board_community.board"/>
            </div>
        </c:otherwise>
    </c:choose>

    <c:import url="footer.jsp"/>
</div>
</body>
</html>
