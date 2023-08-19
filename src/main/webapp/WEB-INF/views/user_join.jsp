<%--
  Created by IntelliJ IDEA.
  User: ho625
  Date: 2023-08-10
  Time: 오후 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <link rel="stylesheet" href="/css/join.css">
    <title>회원가입</title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <form>
            <div class="join-box-left">
                <div class="line">
                    <label for="id"><spring:message code="user_join.id"/></label>
                    <input type="text" id="id" name="id">
                    <p class="err"><spring:message code="user_join.err-invalid"/> </p>
                    <br>
                </div>
                <div class="line">
                    <label for="password"><spring:message code="user_join.password"/></label>
                    <input type="password" id="password" name="password">
                    <p class="err"><spring:message code="user_join.err-invalid"/> </p><br>
                </div>
                <div class="line">
                    <label for="password-chk"><spring:message code="user_join.passwordChk"/> </label>
                    <input type="password" id="password-chk" name="password-chk">
                    <p class="err"><spring:message code="user_join.err-password"/> </p><br>
                </div>
                <div class="line">
                    <label for="name"><spring:message code="user_join.name"/></label>
                    <input type="text" id="name" name="name">
                    <p class="err"><spring:message code="user_join.err-required"/> </p><br>
                </div>
                <div class="line">
                    <label for="email"><spring:message code="user_join.email"/></label>
                    <input type="email" id="email" name="email">
                    <p class="err"><spring:message code="user_join.err-required"/> </p><br>
                </div>
                <div class="line">
                    <label><spring:message code="user_join.gender"/></label>
                    <input type="radio" class="gender" id="male" name="gender" value="남" checked>
                    <label for="male"><spring:message code="user_join.male"/></label>
                    <input type="radio" class="gender" id="female" name="gender" value="여">
                    <label for="female"><spring:message code="user_join.female"/></label><br>
                </div>
                <div class="line">
                    <label for="age"><spring:message code="user_join.age"/></label>
                    <input type="number" id="age" name="age"><br>
                </div>
            </div>
            <div class="join-box-right">
                <div class="line">
                    <div class="profile-img-box">

                    </div>
                    <label><spring:message code="user_join.profileImg"/></label>
                    <label for="profileImg" id="profileImgBtn"><spring:message code="user_join.profileImgBtn"/></label>
                    <input type="file" id="profileImg" name="profileImg" style="visibility:hidden;"><br>
                </div>
                <div class="line">
                    <label for="liveCountry"><spring:message code="user_join.liveCountry"/></label>
                    <input type="text" id="liveCountry" name="liveCountry">
                    <p class="err"><spring:message code="user_join.err-required"/> </p> <br>
                </div>
                <div class="line">
                    <label for="liveCity"><spring:message code="user_join.liveCity"/></label>
                    <input type="text" id="liveCity" name="liveCity">
                    <p class="err"><spring:message code="user_join.err-required"/> </p><br>
                </div>
                <div class="line">
                    <div class="lang-choose">
                        <label for="use-lang"><spring:message code="user_join.useLang"/></label>
                        <select id="use-lang">
                            <option value="" disabled selected>
                                <spring:message code="user_join.selectLang"/>
                            </option>
                            <c:forEach var="lang" items="${languageCode}" varStatus="vs">
                                <option class="${lang}">
                                    <spring:message code="user_join.select${lang}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="line">
                    <div class="use-lang-box">

                    </div>
                </div>
                <div class="line">
                    <div class="lang-choose">
                        <label for="need-lang"><spring:message code="user_join.needLang"/></label>
                        <select id="need-lang">
                            <option value="" disabled selected>
                                <spring:message code="user_join.selectLang"/>
                            </option>
                            <c:forEach var="lang" items="${languageCode}" varStatus="vs">
                                <option class="${lang}">
                                    <spring:message code="user_join.select${lang}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="line">
                    <div class="need-lang-box">

                    </div>
                </div>
                <div class="line">
                    <input type="button" onclick="joinForm()" value=<spring:message code="user_join.joinBtn"/>>
                </div>
            </div>
        </form>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/user-join.js"></script>
</body>
</html>