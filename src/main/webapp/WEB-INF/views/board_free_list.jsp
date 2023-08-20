<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-09
  Time: 오전 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>자유 게시판</title>
    <link rel="stylesheet" href="/css/board_free_list.css">
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <h2><a href="/board/free/list/1">자유게시판</a></h2>
        <div class="content-row">
            <div>
                <span>총 ${boardList.totalElements} 건</span>
                <span>[
                    <c:choose>
                        <c:when test="${boardList.totalPages ne 0}">
                            ${boardList.number+1}
                        </c:when>
                        <c:otherwise>
                            0
                        </c:otherwise>
                    </c:choose>
                    /${boardList.totalPages}]</span>
            </div>
            <div class="search-box">
                <select id="search-select">
                    <option value="title" selected>제목</option>
                    <option value="author">작성자</option>
                </select>
                <input type="text" id="search-input" name="search-input">
                <input type="button" onclick="search(${boardList.number})" value="검색">
            </div>
        </div>
        <table>
            <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
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
                                ${authorList.get(board.id)}
                        </td>
                        <td class="createdAt">
                                ${board.createdAt}
                        </td>
                        <td class="views">
                                ${board.views}
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
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
