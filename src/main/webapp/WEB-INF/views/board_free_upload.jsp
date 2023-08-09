<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-09
  Time: 오후 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시글 등록</title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <form id="board-free-upload-form">
            <div>
                <label for="title">제목</label>
                <input type="text" id="title" name="title" required>
            </div>
            <div>
                <label for="contents">내용</label>
                <textarea id="contents" name="contents" maxlength="4000"></textarea>
            </div>
            <div>
                <input type="file" id="file-img" name="file-img" accept="image/png, image/jpg, image/jpeg, image.gif">
                <div class="img-container">

                </div>
            </div>
            <input type="button" value="등록" id="upload-btn" onclick="boardFreeUpload(form)">
        </form>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
