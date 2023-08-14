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
        <form>
            <ul>
                <li>
                    <label for="platform">플랫폼</label>
                    <select name="platform" id="platform">
                        <option value="platform">Discord</option>
                    </select>
                </li>

                <li>
                    <label for="title">
                        <spring:message code="board_community_upload.title"/>
                    </label>
                    <input type="text" id="title" name="title">
                </li>

                <li>
                    <label for="creator">작성자</label>
                    <%--                <p id="creator" name="creator">${sessionScope.log}</p>--%>
                    <input type="text" id="creator" name="creator" value="${sessionScope.log}" readonly>
                </li>

                <li>
                    <label for="contents">
                        <spring:message code="board_community_upload.content"/>
                    </label>
                    <textarea id="contents" name="contents"></textarea>
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

                <li>
                    <input type="button" value="등록" id="submit_btn" onclick="write()">
                </li>

            </ul>
        </form>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/board-community.js"></script>
</body>
</html>
