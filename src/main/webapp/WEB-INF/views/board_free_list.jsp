<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><spring:message code="board_free_list.logo"/></title>
    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">
    <link rel="stylesheet" href="/css/board_free_list.css">
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <h2><a href="/board/free/list/1"><spring:message code="board_free_list.logo"/></a></h2>
        <div class="content-row">
            <div>
                <span>
                    <spring:message code="board_free_list.total"/>
                        ${boardList.totalElements}
                    <spring:message code="board_free_list.unit"/>
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
                    / ${boardList.totalPages} ]</span>
            </div>
            <div id="select-search-box">
                <div class="select-box">
                    <select id="search-select">
                        <option value="title" selected><spring:message code="board_free_list.sel1"/></option>
                        <option value="author"><spring:message code="board_free_list.sel2"/></option>
                    </select>
                    <span class="select-arrow">
                        <img src="https://ucarecdn.com/326e1541-5ce3-4908-836d-d735660c0300/" alt=""/>
                    </span>
                </div>
                <div class="search-box">
                    <input type="text" id="search-input" name="search-input">
                    <input type="button" id="search-btn" onclick="searchBoard(${boardList.number+1})" value=<spring:message code="board_free_list.search"/>>
                </div>
            </div>
        </div>
        <table>
            <thead>
            <tr>
                <th><spring:message code="board_free_list.num"/></th>
                <th><spring:message code="board_free_list.title"/></th>
                <th><spring:message code="board_free_list.author"/></th>
                <th><spring:message code="board_free_list.date"/></th>
                <th><spring:message code="board_free_list.views"/></th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty boardList.content}">
                <c:forEach items="${boardList.content}" var="board" varStatus="vs">
                    <tr>
                        <td class="no">
                                ${board.boardNo}
                        </td>
                        <td class="title">
                            <a href="/board/free/${board.boardNo}">
                                    ${board.title}
                            </a>
                        </td>
                        <td class="author">
                                ${board.name}
                        </td>
                        <td class="createdAt">
                            <fmt:parseDate value="${board.createdAt}" var="parsedCreatedAt" pattern="yyyy-MM-dd'T'HH:mm:ss" />
                            <fmt:formatDate value="${parsedCreatedAt}" pattern="yyyy-MM-dd" var="formattedCreatedAt" />
                            ${formattedCreatedAt}
                        </td>
                        <td class="views">
                                ${board.views}
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <div class="write">
            <a href="/board/free/upload" id="write-btn">
                <spring:message code="board_free_list.write"/>
            </a>
        </div>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:if test="${boardList.number > 4}">
                    <li class="page-item">
                        <a class="page-link" href="/board/free/list/${startPage - 1}">Previous</a>
                    </li>
                </c:if>
                <c:forEach var="pageNumber" begin="${startPage}" end="${endPage}" varStatus="vs">
                    <li class="page-item ${boardList.number == pageNumber - 1 ? 'active' : ''}">
                        <a class="page-link" href="/board/free/list/${pageNumber}">${pageNumber}</a>
                    </li>
                </c:forEach>
                <c:choose>
                    <c:when test="${boardList.totalPages > endPage}">
                        <li class="page-item">
                            <a class="page-link" href="/board/free/list/${endPage + 1}">Next</a>
                        </li>
                    </c:when>
                </c:choose>
            </ul>
        </nav>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/board-free.js"></script>
</body>
</html>
