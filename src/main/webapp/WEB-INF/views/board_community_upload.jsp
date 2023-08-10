<!-- 모임 게시판 쓰기 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!-- 다국어 처리 -->

<html>

<head>
    <!-- CSS -->
    <link rel="stylesheet" href="/css/reset.css">

    <title>GLOBALTIES</title>
</head>

<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main_con">
        <ul>
            <li>
                <label for="title">
                    <spring:message code="board_community_upload.title"/>
                </label>
                <input type="text" id="title" name="title">
            </li>

            <li>
                <label for="content">
                    <spring:message code="board_community_upload.content"/>
                </label>
                <textarea id="content" name="content"></textarea>
            </li>

            <li>
                <label for="participants">
                    <spring:message code="board_community_upload.participants"/>
                </label>
                <input type="text" id="participants" name="participants">
            </li>

            <li>
                <label for="deadline">
                    <spring:message code="board_community_upload.deadline"/>
                </label>
                <input type="date" id="deadline" name="deadline"/>
            </li>

        </ul>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
