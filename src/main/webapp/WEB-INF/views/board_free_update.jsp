<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <link rel="stylesheet" href="/css/board_free_upload_update.css">
    <title>GLOBALTIES</title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <div class="input_title">
            <label for="title" id="label_title">Title</label>
            <input type="text" id="title" name="title" autofocus>
        </div>

        <div class="input_contents">
            <label for="contents" id="label_contents">Contents</label>
            <textarea id="contents" name="contents"></textarea>
        </div>

        <div class="input_file">
            <label for="file" id="file_btn">
                파일 업로드
            </label>
            <input type="file" id="file" name="file" accept="image/png, image/jpg, image/jpeg, image.gif" multiple>
        </div>

        <div class="img-box">

        </div>

        <div class="submit_btn">
            <input type="button" value="작성" id="submit_btn" onclick="uploadBoard()">
            <input type="button" value="뒤로가기" id="back_btn">
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>

<script src="/script/board-free.js"></script>
</body>
</html>
