<!-- 모임 게시판 쓰기 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!-- 다국어 처리 -->

<html>

<head>
    <!-- CSS -->
    <link rel="stylesheet" href="/css/board_community_write_update.css">
    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">
    <title><spring:message code="board_community_main.main_title"/></title>
</head>

<body>
<div class="wrap">
    <c:import url="header.jsp"/>
        <div class="main">
                <label for="platform" id="label_platform">
                    <spring:message code="board.community_write.platform"/>
                </label>

                <div class="platform" id="platform">
                    <input type="radio" id="platform_1" name="platform" value="Offline" checked>
                    <label for="platform_1">Off-line</label>

                    <input type="radio" id="platform_2" name="platform" value="Discord">
                    <label for="platform_2">Discord</label>

                    <input type="radio" id="platform_3" name="platform" value="Google Meet">
                    <label for="platform_3">Google Meet</label>

                    <input type="radio" id="platform_4" name="platform" value="Line">
                    <label for="platform_4">Line</label>

                    <input type="radio" id="platform_5" name="platform" value="Microsoft Teams">
                    <label for="platform_5">Microsoft Teams</label>

                    <input type="radio" id="platform_6" name="platform" value="Telegram">
                    <label for="platform_6">Telegram</label>

                    <input type="radio" id="platform_7" name="platform" value="WebX">
                    <label for="platform_7">WebX</label>

                    <input type="radio" id="platform_8" name="platform" value="Zoom">
                    <label for="platform_8">Zoom</label>
                </div>

                <div class="input_title" >
                    <label for="title" id="label_title">
                        <spring:message code="board_community_write.title"/>
                    </label>
                    <input type="text" id="title" name="title">
                </div>

                <div class="input_contents">
                    <label for="contents" id="label_contents">
                        <spring:message code="board_community_write.content"/>
                    </label>
                    <textarea id="contents" name="contents"></textarea>
                </div>

                <div class="input_etc">
                    <div class="input_participants">
                        <label for="participants" id="label_participants">
                            <spring:message code="board_community_write.participants"/>
                        </label>
                        <input type="number" id="participants" name="participants" placeholder=<spring:message code="board_community_write.explain"/>>
                    </div>

                    <div class="input_deadline">
                        <label for="deadline" id="label_deadline">
                            <spring:message code="board_community_write.deadline"/>
                        </label>
                        <input type="date" id="deadline" name="deadline">
                    </div>

                    <div class="input_img">
                        <label for="file" id="file_btn">
                            <spring:message code="board_community_write.file"/>
                        </label>

                        <input type="file" id="file" name="file" accept="image/png, image/jpg, image/jpeg, image.gif" multiple>
                    </div>
                </div>

                <div class="img_box">

                </div>

                <div class="btns">
                    <input type="button" id="write_btn" value=<spring:message code="board_community_write.submit"/>>
                    <input type="button" id="back_btn" onclick="moveToMain()" value=<spring:message code="board_community_write.back"/>>
                </div>
        </div>
    <c:import url="footer.jsp"/>
</div>

<script src="/script/board-community.js"></script>
</body>
</html>
