<!-- 모임 게시판 쓰기 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!-- 다국어 처리 -->

<html>

<head>
    <!-- CSS -->
    <link rel="stylesheet" href="/css/grid.css">

    <title>GLOBALTIES</title>
</head>

<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main_con">
        <div>
            <input type="hidden" id="board_num_hidden" value="${board.boardNo}">
            <input type="hidden" id="platform_name_hidden" value="${board.platformName}">

            <ul>
                <li>
                    <label for="platform">
                        <spring:message code="board.community_write.platform"/>
                    </label>
                    <div class="platform" id="platform">
                        <input type="radio" id="platform_0" name="platform" value="Off line">
                        <label for="platform_0">Off-line</label>

                        <input type="radio" id="platform_1" name="platform" value="Discord">
                        <label for="platform_1">Discord</label>

                        <input type="radio" id="platform_2" name="platform" value="Google Meet">
                        <label for="platform_2">Google Meet</label>

                        <input type="radio" id="platform_3" name="platform" value="Line">
                        <label for="platform_3">Line</label>

                        <input type="radio" id="platform_4" name="platform" value="Microsoft Teams">
                        <label for="platform_4">Microsoft Teams</label>

                        <input type="radio" id="platform_5" name="platform" value="Skype">
                        <label for="platform_5">Skype</label>

                        <input type="radio" id="platform_6" name="platform" value="Telegram">
                        <label for="platform_6">Telegram</label>

                        <input type="radio" id="platform_7" name="platform" value="WebX">
                        <label for="platform_7">WebX</label>

                        <input type="radio" id="platform_8" name="platform" value="Zoom">
                        <label for="platform_8">Zoom</label>
                    </div>
                </li>

                <li>
                    <label for="title">
                        <spring:message code="board_community_write.title"/>
                    </label>
                    <input type="text" id="title" name="title" value="${board.title}">
                </li>

                <li>
                    <label for="creator">
                        <spring:message code="board_community_write.creator"/>
                    </label>
                    <input type="text" id="creator" name="creator" value="${board.id}" readonly>
                </li>

                <li>
                    <label for="contents">
                        <spring:message code="board_community_write.content"/>
                    </label>
                    <textarea id="contents" name="contents">${board.contents}</textarea>
                </li>

                <li>
                    <label for="participants">
                        <spring:message code="board_community_write.participants"/>
                    </label>
                    <input type="text" id="participants" name="participants" value="${board.participantsNum}">
                </li>

                <li>
                    <label for="deadline">
                        <spring:message code="board_community_write.deadline"/>
                    </label>
                    <input type="date" id="deadline" name="deadline" value="${board.deadline}">
                </li>

                <li>
                    <label for="file">
                        <spring:message code="board_community_write.file"/>
                    </label>
                    <input type="file" id="file" name="file" accept="image/png, image/jpg, image/jpeg, image.gif" multiple>

                    <div class="img-box">
                    <c:if test="${imgList ne null}">
                            <c:forEach items="${imgList}" var="imgs" varStatus="vs">
                                <img src="${imgs.img}">
                            </c:forEach>
                    </c:if>
                    </div>
                </li>

                <li>
                    <input type="button" id="modify_btn" value="수정하기">
                    <input type="button" id="back_btn" value="뒤로가기" onclick="back()">
                </li>

            </ul>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>

<script src="/script/board-community-update.js"></script>
</body>
</html>
