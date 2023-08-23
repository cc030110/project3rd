<%--
  Created by IntelliJ IDEA.
  User: ho625
  Date: 2023-08-10
  Time: 오후 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="/css/join.css">
    <title>회원가입</title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <form>
            <div id="joinForm">
                <div class="join-box-left">
                    <div class="line err-care">
                        <div class="email-box">
                            <input class="id-check normal" type="text" id="id" name="id"
                                   placeholder="<spring:message code="user_join.id"/>">
                            <button type="button" id="id-check" onclick="idCheck()">중복 확인</button>
                        </div>
                        <p class="id-check err"><spring:message code="user_join.err-invalid"/></p>
                    </div>
                    <div class="line err-care">
                        <input class="password-check normal" type="password" id="password" name="password"
                               placeholder="<spring:message code="user_join.password"/>">
                        <p class="password-check err"><spring:message code="user_join.err-invalid"/></p>
                    </div>
                    <div class="line err-care err-care">
                        <input class="password-chk-check normal" type="password" id="password-chk" name="password-chk"
                               placeholder="<spring:message code="user_join.passwordChk"/>">
                        <p class="password-chk-check err"><spring:message code="user_join.err-password"/></p>
                    </div>
                    <div class="line err-care">
                        <div class="email-box">
                        <input class="name-check normal" type="text" id="name" name="name"
                               placeholder="<spring:message code="user_join.name"/>">
                            <button type="button" id="name-check" onclick="nameCheck()">중복 확인</button>
                        </div>
                        <p class="name-check err"><spring:message code="user_join.err-required"/></p>
                    </div>
                    <div class="line err-care">
                        <div class="email-box">
                            <input class="email-check" type="email" id="email" name="email"
                                   placeholder="<spring:message code="user_join.email"/>">
                            <button type="button" id="sendEmailButton" onclick="sendEmail()">인증번호 요청</button>
                        </div>
                        <p class="email-check err"><spring:message code="user_join.err-required"/></p>
                    </div>
                    <div class="line err-care">
                        <div class="email-box">
                            <input class="email-check-number" type="text" id="verificationCodeInput"
                                   placeholder="인증 번호 입력">
                            <button type="button" id="verifyButton" onclick="verifyCode()">확인</button>
                        </div>
                        <p class="email-check-number" id="resultMessage"></p>
                    </div>
                    <div class="line">
                        <label><spring:message code="user_join.gender"/></label>
                        <input type="radio" class="gender" id="male" name="gender" value="남" checked>
                        <label for="male"><spring:message code="user_join.male"/></label>
                        <input type="radio" class="gender" id="female" name="gender" value="여">
                        <label for="female"><spring:message code="user_join.female"/></label>
                    </div>
                    <div class="line err-care">
                        <input class="normal" type="text" id="birth" name="birth"
                               placeholder="<spring:message code="user_join.age"/>">
                    </div>
                </div>
                <div class="join-box-right">
                    <div class="line">
                        <div class="profile-img-box">

                        </div>
                        <label><spring:message code="user_join.profileImg"/></label>
                        <label for="profileImg" id="profileImgBtn"><spring:message
                                code="user_join.profileImgBtn"/></label>
                        <input type="file" id="profileImg" name="profileImg" style="visibility:hidden;">
                    </div>
                    <div class="line">
                        <label for="liveCountry"><spring:message code="user_join.liveCountry"/></label>
                        <select class="country-check" id="liveCountry">
                            <option value="" disabled selected>
                                <spring:message code="user_join.selectLang"/>
                            </option>
                            <option value="korea">
                                <spring:message code="user_join.country_korea"/>
                            </option>
                            <option value="japan">
                                <spring:message code="user_join.country_japan"/>
                            </option>
                            <option value="china">
                                <spring:message code="user_join.country_china"/>
                            </option>
                            <option value="usa">
                                <spring:message code="user_join.country_usa"/>
                            </option>
                        </select>
                        <p class="country-check err"><spring:message code="user_join.err-required"/></p>
                    </div>
                    <div class="line err-care">
                        <label for="liveCity"></label>
                        <input type="text" id="liveCity" name="liveCity"
                               placeholder="<spring:message code="user_join.liveCity"/>">
                        <p class="err"><spring:message code="user_join.err-required"/></p>
                    </div>
                    <div class="line">
                        <div class="lang-choose">
                            <label for="use-lang"><spring:message code="user_join.useLang"/></label>
                            <select class="lang-check" id="use-lang">
                                <option value="" disabled selected>
                                    <spring:message code="user_join.selectLang"/>
                                </option>
                                <c:forEach var="lang" items="${languageCode}" varStatus="vs">
                                    <option class="${lang}">
                                        <spring:message code="user_join.select${lang}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <p class="lang-check err"><spring:message code="user_join.err-required"/></p>
                        </div>
                    </div>
                    <div class="line">
                        <div class="use-lang-box">

                        </div>
                    </div>
                    <div class="line">
                        <div class="lang-choose">
                            <label for="need-lang"><spring:message code="user_join.needLang"/></label>
                            <select class="needlang-check" id="need-lang">
                                <option value="" disabled selected>
                                    <spring:message code="user_join.selectLang"/>
                                </option>
                                <c:forEach var="lang" items="${languageCode}" varStatus="vs">
                                    <option class="${lang}">
                                        <spring:message code="user_join.select${lang}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <p class="needlang-check err"><spring:message code="user_join.err-required"/></p>
                        </div>
                    </div>
                    <div class="line">
                        <div class="need-lang-box">

                        </div>
                    </div>
                    <div class="svg-wrapper">
                        <svg height="40" width="150" xmlns="http://www.w3.org/2000/svg">
                            <rect id="shape" height="40" width="150"/>
                            <div id="text">
                                <span class="spot login-bnt" onclick="joinForm()"><spring:message
                                        code="user_join.joinBtn"/></span>
                            </div>
                        </svg>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/user-join.js"></script>
</body>
</html>