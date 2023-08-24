<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <link rel="stylesheet" href="/css/board_free_upload_update.css">
    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">
    <title><spring:message code="board_free_upload_update.main_title"/></title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <div class="input_title">
            <label for="title" id="label_title"><spring:message code="board_free_upload_update.title"/></label>
            <input type="text" id="title" name="title" autofocus>
        </div>

        <div class="input_contents">
            <label for="contents" id="label_contents"><spring:message code="board_free_upload_update.content"/></label>
            <textarea id="contents" name="contents"></textarea>
        </div>

        <div class="input_file">
            <label for="file" id="file_btn">
                <spring:message code="board_free_upload_update.upload"/>
            </label>
            <input type="file" id="file" name="file" accept="image/png, image/jpg, image/jpeg, image.gif" multiple>
        </div>

        <div class="img-box">

        </div>

        <div class="submit_btn">
            <input type="button" value=<spring:message code="board_free_upload_update.write"/> id="submit_btn" onclick="uploadBoard()">
            <input type="button" value=<spring:message code="board_free_upload_update.back"/> id="back_btn" onclick="back()">
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>

<script src="/script/board-free.js"></script>
</body>
</html>
