<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-24
  Time: 오전 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="/css/mypage_delete.css">
    <title></title>
</head>
<body>
<div class="resign-container">
    <h2 class="title">회원 탈퇴</h2>
    <form>
        <div class="input-field">
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="submit-btn" onclick="deleteUser()">회원 탈퇴</button>
    </form>
</div>
<script src="/script/user-delete.js"></script>
</body>
</html>
