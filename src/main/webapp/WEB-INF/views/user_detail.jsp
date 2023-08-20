<%--
  Created by IntelliJ IDEA.
  User: ho625
  Date: 2023-08-20
  Time: 오후 7:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Title</title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
<p>ID: ${user.id}</p>
<p>Name: ${user.name}</p>
<p>Email: ${user.email}</p>
    </div>
</div>
    <c:import url="footer.jsp"/>
</body>
</html>
