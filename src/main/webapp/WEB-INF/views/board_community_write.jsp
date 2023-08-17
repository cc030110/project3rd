<!-- 모임 게시판 쓰기 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!-- 다국어 처리 -->

<html>

<head>
    <!-- CSS -->
    <link rel="stylesheet" href="/css/grid.css">
    <link rel="stylesheet" href="/css/board_write_update.css">

    <title>GLOBALTIES</title>
</head>

<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <li>
        <nav class="main_con">
            <ul>
                <li>
                    <label for="platform">
                        <spring:message code="board.community_write.platform"/>
                    </label>

                    <nav class="platform" id="platform">
                        <ul>
                            <li>
                                <input type="radio" id="platform_1" name="platform" value="Off line" checked>
                                <label for="platform_1">Off-line</label>

                                <input type="radio" id="platform_2" name="platform" value="Discord">
                                <label for="platform_2">Discord</label>

                                <input type="radio" id="platform_3" name="platform" value="Google Meet">
                                <label for="platform_3">Google Meet</label>

                                <input type="radio" id="platform_4" name="platform" value="Line">
                                <label for="platform_4">Line</label>
                            </li>

                            <li>
                                <input type="radio" id="platform_5" name="platform" value="Microsoft Teams">
                                <label for="platform_5">Microsoft Teams</label>

                                <input type="radio" id="platform_6" name="platform" value="Telegram">
                                <label for="platform_6">Telegram</label>

                                <input type="radio" id="platform_7" name="platform" value="WebX">
                                <label for="platform_7">WebX</label>

                                <input type="radio" id="platform_8" name="platform" value="Zoom">
                                <label for="platform_8">Zoom</label>
                            </li>
                        </ul>
                    </nav>
                </li>

                <li>
                    <label for="title">
                        <spring:message code="board_community_write.title"/>
                    </label>
                    <input type="text" id="title" name="title">
                </li>

                <li>
                    <label for="creator">
                        <spring:message code="board_community_write.creator"/>
                    </label>
                    <input type="text" id="creator" name="creator"  readonly>
                </li>

                <li>
                    <label for="contents">
                        <spring:message code="board_community_write.content"/>
                    </label>
                    <textarea id="contents" name="contents"></textarea>
                </li>

                <li>
                    <label for="participants">
                        <spring:message code="board_community_write.participants"/>
                    </label>
                    <input type="number" id="participants" name="participants" placeholder="숫자로 입력하세요.">
                </li>

                <li>
                    <label for="deadline">
                        <spring:message code="board_community_write.deadline"/>
                    </label>
                    <input type="date" id="deadline" name="deadline">
                </li>

                <li>
                    <label for="file">
                        <spring:message code="board_community_write.file"/>
                    </label>
                    <input type="file" id="file" name="file" accept="image/png, image/jpg, image/jpeg, image.gif" multiple>
                    <div class="img_box">

                    </div>
                </li>

                <li>
                    <input type="button" id="write_btn" value=<spring:message code="board_community_write.submit"/>>
                    <input type="button" id="back_btn" value=<spring:message code="board_community_write.back"/>>
                </li>
            </ul>
    <c:import url="footer.jsp"/>
</div>

<script src="/script/board-community-write.js"></script>
</body>
</html>
