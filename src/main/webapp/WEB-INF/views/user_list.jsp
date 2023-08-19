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
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <span>총 ${userList.totalElements} 건</span>
        <span>[${userList.number+1}/${userList.totalPages}]</span>
        <c:forEach items="${userList.content}" var="user" varStatus="vs">
            <ul>
                <!-- Java에서 넘겨준 사용자 목록을 출력 -->
                <li>
                    <p>ID: ${user.id}</p>
                    <p>Name: ${user.name}</p>
                    <p>Email: ${user.email}</p>
                </li>
            </ul>
        </c:forEach>
    </div>
    <div class="pagination">
        <c:if test="${userList.totalPages > 1}">
            <ul>
                <c:set var="currentPage" value="${userList.number + 1}" />
                <c:set var="startPage" value="${currentPage - (currentPage - 1) % 5}" />
                <c:set var="endPage" value="${startPage + 4}" />
                <c:if test="${endPage > userList.totalPages}">
                    <c:set var="endPage" value="${userList.totalPages}" />
                </c:if>

                <c:choose>
                    <c:when test="${startPage > 1}">
                        <li><a href="${pageContext.request.contextPath}/list/1">1</a></li>
                        <li>...</li>
                    </c:when>
                    <c:otherwise>
                        <li class="disabled">1</li>
                    </c:otherwise>
                </c:choose>

                <c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
                    <c:choose>
                        <c:when test="${pageNum == currentPage}">
                            <li class="active">${pageNum}</li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${pageContext.request.contextPath}/list/${pageNum}">${pageNum}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${endPage < userList.totalPages}">
                        <li>...</li>
                        <li><a href="${pageContext.request.contextPath}/list/${userList.totalPages}">${userList.totalPages}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="disabled">${userList.totalPages}</li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </c:if>
    </div>


    <c:import url="footer.jsp"/>
</div>
</body>
</html>
