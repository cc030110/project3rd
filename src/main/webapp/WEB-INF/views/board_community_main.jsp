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
            <h2><a href="/board/community/main/1"><spring:message code="board_community_main.logo"/></a></h2>
            <div class="content-row">
                <div>
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
                <div id="select-search-box">
                    <div class="select-box">
                        <select id="search-select">
                            <option value="title" selected><spring:message code="board_community_main.select1"/></option>
                            <option value="author"><spring:message code="board_community_main.select2"/></option>
                        </select>
                        <span class="select-arrow">
                        <img src="https://ucarecdn.com/326e1541-5ce3-4908-836d-d735660c0300/" alt=""/>
                    </span>
                    </div>
                    <div class="search-box">
                        <input type="text" id="search-input" name="search-input">
                        <input type="button" id="search-btn" onclick="searchBoardCommunity(${boardList.number+1})" value=<spring:message
                                code="board_community_main.search"/>>
                    </div>
                </div>
            </div>
            <div class="list">
            <c:if test="${not empty boardList.content}">
                <c:forEach items="${boardList.content}" var="board" varStatus="vs">
                        <div class="list_top">
                            <div class="list_top_info">
                                <p><spring:message code="board_community_main.period"/> : ${board.createdAt} ~ ${board.deadline}</p>
                                <p><spring:message code="board_community_main.author"/> : ${board.name}</p>
                            </div>

                            <div class="list_top_info">
                                <p><spring:message code="board_community_main.views"/> : ${board.views}</p>
                            </div>
                        </div>

                        <div class="con_list">
                            <!-- 게시판에 넣을 플랫폼 -->
                                <div class="con_platform">
                                    <img src="${platform.get(board.platformName)}" alt="이미지 불러오기 실패">
                                    <div class="con_platform_name">
                                        <p>${board.platformName}</p>
                                    </div>
                                </div>

                                <div class="con_text">
                                    <a href="/board/community/${board.boardNo}">
                                        <p id="title">${board.title}</p><!-- 제목 -->
                                    </a>
                                    <p>${board.contents}</p><!-- 내용 -->
                                    <p><spring:message code="board_community_main.participants"/>: ${board.participantsNum}</p>
                                </div>
                        </div>

                </c:forEach>
            </c:if>
            </div>
            <div class="write">
                <a id="write-btn" href="/board/community/write">
                    <spring:message code="board_community_main.write"/>
                </a>
            </div>
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <c:if test="${boardList.number > 4}">
                        <li class="page-item">
                            <a class="page-link" href="/board/community/main/${startPage - 1}"><</a>
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
                                <a class="page-link" href="/board/community/main/${endPage + 1}">></a>
                            </li>
                        </c:when>
                    </c:choose>
                </ul>
            </nav>
        </div>
    <c:import url="footer.jsp"/>
</div>

<script src="/script/board-community.js"></script>
</body>
</html>
