<%--
  Created by IntelliJ IDEA.
  User: ho625
  Date: 2023-08-11
  Time: 오후 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/user_list.css">
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <span>총 ${userList.totalElements} 건</span>
        <span>[${userList.number + 1}/${userList.totalPages}]</span>

        <c:forEach items="${userList.content}" var="user" varStatus="vs">
            <c:if test="${vs.index % userList.size == 0}">
                <!-- 페이지 번호가 바뀔 때마다 사용자 정보 출력 -->
                <ul>
                    <li>
                        <p>ID: ${user.id}</p>
                        <p>Name: ${user.name}</p>
                        <p>Email: ${user.email}</p>
                    </li>
                </ul>
            </c:if>
        </c:forEach>

        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:if test="${userList.number > 4}">
                    <li class="page-item">
                        <a class="page-link" href="/user/list/${startPage - 1}">Previous</a>
                    </li>
                </c:if>
                <c:forEach var="pageNumber" begin="${startPage}" end="${endPage}" varStatus="vs">
                    <li class="page-item ${userList.number == pageNumber - 1 ? 'active' : ''}">
                        <a class="page-link" href="/user/list/${pageNumber}">${pageNumber}</a>
                    </li>
                </c:forEach>
                <c:choose>
                    <c:when test="${userList.totalPages > endPage}">
                        <li class="page-item">
                            <a class="page-link" href="/user/list/${endPage + 1}">Next</a>
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