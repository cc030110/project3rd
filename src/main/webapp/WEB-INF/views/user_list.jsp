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
    <title><spring:message code="user_list.title"/></title>
    <link rel="stylesheet" href="/css/user_list.css">
    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">
</head>

<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <div class="main_logo">
            <h1><spring:message code="user_list.title"/></h1>
        </div>
        <!-- 콘텐츠 수 표시 -->
        <div class="content-row">
            <div class="contents-num">
                <span><spring:message code="user_list.total"/> ${userList.totalElements}<spring:message code="user_list.unit"/></span>
                <span>[${userList.number + 1}/${userList.totalPages}]</span>
            </div>

            <!-- 검색 -->
            <div id="select-search-box">
                <div class="select-box">
                    <select id="search-select">
                        <option value="title" selected><spring:message code="user_list.nickname"/></option>
                        <option value="author"><spring:message code="user_list.nation"/></option>
                    </select>

                    <span class="select-arrow">
                        <img src="https://ucarecdn.com/326e1541-5ce3-4908-836d-d735660c0300/" alt=""/>
                    </span>
                </div>
                <div class="search-box">
                    <input type="text" id="search-input" name="search-input">
                    <input type="button" id="search-btn" value=<spring:message code="user_list.search"/>>
                </div>
            </div>
        </div>


        <div class="users">
            <c:forEach items="${userList.content}" var="user">
                <div class="user-box">
                    <a href="/user/${user.name}">
                        <c:choose>
                            <c:when test="${user.profileImg ne null}">
                                <img src="${user.profileImg}" alt=""/>
                            </c:when>
                            <c:otherwise>
                                <img src="https://ucarecdn.com/1e359c2a-7124-4da9-9cd9-be5fe9e8c1f0/" alt=""/>
                            </c:otherwise>
                        </c:choose>
                    </a>
                    <p><spring:message code="user_list.nickname"/> : <a href="/user/${user.name}">${user.name}</a></p>
                    <p><spring:message code="user_list.nation"/> : ${user.liveCountry}</p>
                </div>
            </c:forEach>
        </div>

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