<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
    <title>Title</title>
</head>

<link rel="stylesheet" href="css/reset.css">

<body>
<div class="wrap">
    <c:import url="header.jsp"/>
        <div class="main_con">
            <spring:message code="index.hello" text="Hello"/>
        </div>
    <c:import url="footer.jsp"/>
</div>
</body>

</html>
