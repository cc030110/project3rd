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
            <h1><spring:message code="board_community_main.logo"/></h1>
            <br><br>

            <div class="main_top">
                <div class="contents_num">
                    <span><spring:message code="board_community_main.total"/>
                        ${boardList.totalElements}
                        <spring:message code="board_community_main.unit"/>
                    </span>
                    <span>[
                        <c:choose>
                            <c:when test="${boardList.totalPages ne 0}">
                                ${boardList.number+1}
                            </c:when>
                            <c:otherwise>
                                0
                            </c:otherwise>
                        </c:choose>
                        /${boardList.totalPages}]
                    </span>
                </div>

                <div class="search-box">
                    <select id="search-select">
                        <option value="title" selected><spring:message code="board_community_main.select1"/></option>
                        <option value="author"><spring:message code="board_community_main.select2"/></option>
                    </select>
                    <input type="text" id="search-input" name="search-input">
                    <input type="button" onclick="search()" value=<spring:message code="board_community_main.search"/>>
                </div>

                <div class="write_btn">
                    <a href="/board/community/write">
                        <button><spring:message code="board_community_main.write"/></button>
                    </a>
                </div>

            </div>
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
                                    <p><spring:message code="board_community_main.author"/> : ${board.name}</p>
                                    <p><spring:message code="board_community_main.participants"/>: ${board.participantsNum}</p>
                                    <p><spring:message code="board_community_main.createdAt"/> : ${board.createdAt}</p>
                                    <p><spring:message code="board_community_main.deadline"/>: ${board.deadline}</p>
                                </div>
                        </div>
                    </a>
                </c:forEach>
            </c:if>

            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <c:if test="${boardList.number > 4}">
                        <li class="page-item">
                            <a class="page-link" href="/board/community/main/${startPage - 1}">Previous</a>
                        </li>
                    </c:if>
                    <c:forEach var="pageNumber" begin="${startPage}" end="${endPage}" varStatus="vs">
                        <li class="page-item ${boardList.number == pageNumber - 1 ? 'active' : ''}">
                            <a class="page-link" href="/board/community/main/${pageNumber}">${pageNumber}</a>
                        </li>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${boardList.totalPages > endPage}">
                            <li class="page-item">
                                <a class="page-link" href="/board/community/main/${endPage + 1}">Next</a>
                            </li>
                        </c:when>
                    </c:choose>
                </ul>
            </nav>
        </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
