<!-- Index -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
    <!-- CSS -->
    <link rel="stylesheet" href="css/reset.css">

    <title>GLOBALTIES</title>
</head>

<body>
<p>empty log : ${empty log}</p>
<div class="wrap">
    <c:import url="header.jsp"/>
        <div class="main_con">
            <spring:message code="index.hello"/>
        </div>
    <c:import url="footer.jsp"/>
</div>
</body>

</html>
