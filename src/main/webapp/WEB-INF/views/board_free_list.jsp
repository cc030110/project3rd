<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-09
  Time: 오전 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
        <div>
            <span>총 ${boardList.totalElements} 건</span>
            <span>[${boardList.number}/${boardList.totalPages}]</span>
            ${boardList.}
        </div>
        <div class="search-box">
            <select>
                <option value="title" selected>제목</option>
                <option value="author">작성자</option>
            </select>
            <input type="text">
            <input type="button" onclock="search()" value="검색">
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
        <div id="paging">
            <div class="page-link">
                <a href="/board/free/list/1" ${boardList.hasPrevious() ? '' : 'class="disabled-link"'}>&lt;&lt;</a>
            </div>
            <div class="page-link">
                <a href="/board/free/list/${boardList.c}" ${boardList.hasPrevious() ? '' : 'class="disabled-link"'}>&lt;</a>
            </div>

            <c:forEach var="pageNumber" begin="${boardList.startPageNumber}" end="${boardList.endPageNumber}">
                <c:choose>
                    <c:when test="${pageNumber == boardList.pageNumber}">
                        <div class="page-link current"><a href="/board/free/list/${pageNumber}">${pageNumber}</a></div>
                    </c:when>
                    <c:otherwise>
                        <div class="page-link"><a href="/board/free/list/${pageNumber}">${pageNumber}</a></div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <div class="page-link"><a href="/board/free/list/${boardList.nextOrLastPageNumber}">&gt;</a></div>
            <div class="page-link"><a href="/board/free/list/${boardList.totalPages}">&gt;&gt;</a></div>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/board-free-list.js"></script>
</body>
</html>
