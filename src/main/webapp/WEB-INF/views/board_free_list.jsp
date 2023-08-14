<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-09
  Time: 오전 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><
<html>
<head>
    <title>자유 게시판</title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <h2>자유 게시판</h2>
    <div class="list-container">
        <c:forEach items="${list}" var="listItem" varStatus="vs">
            <p>제목 : ${listItem.title}</p>
            <p>내용 : ${listItem.contents}</p>
            <p>생성일: ${listItem.createdAt}</p>
        </c:forEach>

    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
