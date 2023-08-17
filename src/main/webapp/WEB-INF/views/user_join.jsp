<%--
  Created by IntelliJ IDEA.
  User: ho625
  Date: 2023-08-10
  Time: 오후 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="/css/join.css">
    <title>회원가입</title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">

        <form id="joinForm" action="/user/join" method="post" enctype="multipart/form-data">
            <div class="join-box-left">
                <label for="id">아이디:</label>
                <input type="text" id="id" name="id" value="${user.id}" required><br>

                <label for="password">비밀번호:</label>
                <input type="password" id="password" name="password" required><br>

                <label for="password-chk">비밀번호 확인:</label>
                <input type="password-chk" id="password-chk" name="password-chk" required><br>

                <label for="name">닉네임</label>
                <input type="text" id="name" name="name" value="${user.name}" required><br>

                <label for="email">이메일:</label>
                <input type="email" id="email" name="email" value="${user.email}" required><br>

                <label>성별:</label>
                <input type="radio" id="male" name="gender" value="남">
                <label for="male">남성</label>
                <input type="radio" id="female" name="gender" value="여">
                <label for="female">여성</label><br>

                <label for="age">나이:</label>
                <input type="number" id="age" name="age" value="${user.age}" required><br>




            </div>
            <div class="join-box-right">
                <label for="profileImg">프로필 사진:</label>
                <input type="file" id="profileImg" name="profileImg"><br>
                <label for="liveCountry">거주 국가:</label>
                <input type="text" id="liveCountry" name="liveCountry" value="${user.liveCountry}" required><br>
                <label for="liveCity">거주 도시:</label>
                <input type="text" id="liveCity" name="liveCity" value="${user.liveCity}" required><br>
                <label for="useLang">사용하는 언어:</label>
                <input type="useLang" id="useLang" name="useLang" value="${user.useLang}" required><br>
                <label for="wishLang">학습할 언어:</label>
                <input type="wishLang" id="wishLang" name="wishLang" value="${user.wishLang}" required><br>
                <input type="submit" value="Join" onclick="joinForm()">
            </div>
        </form>

    </div>
    <c:import url="footer.jsp"/>
</div>


<script src="/script/user-join.js"></script>
</body>
</html>
