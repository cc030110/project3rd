<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="log" value="${id}"/>
<html>
<head>
    <title>게시글 등록</title>
    <link rel="stylesheet" href="/css/board_free_upload.css">
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <div class="board-content">
            <h2><a href="/board/free/list/1">자유게시판</a></h2>
            <h3>게시글 업로드</h3>
            <form>
                <input type="text" id="title" name="title" placeholder="제목" autofocus>
                <textarea id="contents" name="contents" placeholder="내용"></textarea>
                <input type="file" id="file" name="file" accept="image/png, image/jpg, image/jpeg, image.gif" multiple>
                <div class="img-box">
                </div>
                <input type="button" value="작성" id="submit-bnt" onclick="uploadBoard()">
            </form>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/board-free.js"></script>
</body>
</html>
