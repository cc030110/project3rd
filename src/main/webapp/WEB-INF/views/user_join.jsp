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
                <div class="line">
                    <label for="id">아이디:</label>
                    <input type="text" id="id" name="id" value="${user.id}" required><br>
                </div>
                <div class="line">
                    <label for="password">비밀번호:</label>
                    <input type="password" id="password" name="password" required><br>
                </div>
                <div class="line">
                    <label for="password-chk">비밀번호 확인:</label>
                    <input type="password-chk" id="password-chk" name="password-chk" required><br>
                </div>
                <div class="line">
                    <label for="name">닉네임</label>
                    <input type="text" id="name" name="name" value="${user.name}" required><br>
                </div>
                <div class="line">
                    <label for="email">이메일:</label>
                    <input type="email" id="email" name="email" value="${user.email}" required><br>
                </div>
                <div class="line">
                    <label>성별:</label>
                    <input type="radio" class="gender" name="gender" value="남">
                    <label>남성</label>
                    <input type="radio" class="gender" name="gender" value="여">
                    <label>여성</label><br>
                </div>
                <div class="line">
                    <label for="age">나이:</label>
                    <input type="number" id="age" name="age" value="${user.age}" required><br>
                </div>
            </div>

            <div class="join-box-right">
                <div class="line">
                    <label for="profileImg">프로필 사진:</label>
                    <input type="file" id="profileImg" name="profileImg"><br>
                </div>
                <div class="line">
                    <label for="liveCountry">거주 국가:</label>
                    <input type="text" id="liveCountry" name="liveCountry" value="${user.liveCountry}" required><br>
                </div>
                <div class="line">
                    <label for="liveCity">거주 도시:</label>
                    <input type="text" id="liveCity" name="liveCity" value="${user.liveCity}" required><br>
                </div>
                <div class="line">
                    <div class="lang-choose">
                        <label for="use-lang">사용하는 언어:</label>
                        <select id="use-lang">
                            <option value="" selected>-- 선택 --</option>
                            <option value="ko">한국어</option>
                            <option value="en">영어</option>
                            <option value="ja">일본어</option>
                        </select>
                    </div>
                </div>
                <div class="line">
                    <div class="use-lang-box">

                    </div>
                </div>
                <div class="line">
                    <div class="lang-choose">
                        <label for="wish-lang">학습할 언어:</label>
                        <select id="wish-lang">
                            <option value="" selected>-- 선택 --</option>
                            <option value="ko">한국어</option>
                            <option value="en">영어</option>
                            <option value="ja">일본어</option>
                        </select>
                    </div>
                </div>
                <div class="line">
                    <div class="wish-lang-box">

                    </div>
                </div>
                <div class="line">
                    <input type="submit" value="Join" onclick="joinForm()">
                </div>
            </div>
        </form>

    </div>
    <c:import url="footer.jsp"/>
</div>


<script src="/script/user-join.js"></script>
</body>
</html>
