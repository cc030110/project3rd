<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-23
  Time: 오후 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="/css/mypage_update.css">
</head>
<body>
<div class="update-profile-img">
    <c:choose>
        <c:when test="${user.profileImg ne null}">
            <img src="${user.profileImg}" alt=""/>
        </c:when>
        <c:otherwise>
            <img src="https://ucarecdn.com/1e359c2a-7124-4da9-9cd9-be5fe9e8c1f0/" alt=""/>
        </c:otherwise>
    </c:choose>
</div>
<div class="update-profile">
    <label for="profileImg" id="profileImgBtn">사진 업로드</label>
    <input type="file" id="profileImg" name="profileImg" style="visibility:hidden;"
           accept="image/png, image/jpg, image/jpeg, image.gif">
</div>
<div class="update-id">
    <label for="user-id">아이디</label>
    <input type="text" id="user-id" name="user-id" value="${user.id}" readonly>
</div>
<div class="update-name">
    <label for="user-name">닉네임</label>
    <input type="text" id="user-name" name="user-name" value="${user.name}" readonly>
</div>
<div class="update-pwd">
    <label for="new-pwd">새 비밀번호</label>
    <input type="password" id="new-pwd" name="new-pwd">
</div>
<div class="update-pwd-chk">
    <label for="new-pwd-chk">새 비밀번호 확인</label>
    <input type="password" id="new-pwd-chk" name="new-pwd-chk">
</div>
</body>
</html>
