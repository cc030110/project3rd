<!-- 모임 게시판 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!-- 다국어 처리 -->

<html>

<head>
    <!-- CSS -->
    <link rel="stylesheet" href="/css/grid.css">

    <title>GLOBALTIES</title>
</head>

<style>
    div.con_list{
        border:1px solid black;
    }
</style>

<body>
<div class="wrap">
    <c:import url="header.jsp"/>
        <div class="main_con">
            <c:forEach items="${list}" var="listItem" varStatus="vs">
                <div class="con_list">
                    <p>작성일: ${listItem.createdAt}</p>
                    <p>플랫폼 : ${listItem.platformName}</p>
                    <p>작성자 : ${listItem.id}</p>
                    <p>제목 : ${listItem.title}</p>
                    <p>내용 : ${listItem.contents}</p>
                    <p>참가 인원: ${listItem.participantsNum}</p>
                    <p>마감일: ${listItem.deadline}</p>
                    <br><br><br>
                </div>
            </c:forEach>
        </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
