<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>게시글 등록</title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <div class="board-content">
            <form>
                <input type="text" id="name" name="name" value="${sessionScope.log}" readonly>
                <input type="text" id="title" name="title" placeholder="제목." autofocus>
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
