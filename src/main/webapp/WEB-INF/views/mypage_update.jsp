<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-23
  Time: 오후 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title></title>
</head>
<body>
<form>
    <div class="update-container">
        <div id="left-container">
            <div class="update-profile-img">
                <c:choose>
                    <c:when test="${user.profileImg ne null}">
                        <img id="saved-profile" src="${user.profileImg}" alt=""/>
                    </c:when>
                    <c:otherwise>
                        <img id="saved-profile" src="https://ucarecdn.com/1e359c2a-7124-4da9-9cd9-be5fe9e8c1f0/" alt=""/>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="update-profile">
                <label for="profileImg" id="profileImgBtn">사진 변경</label>
                <input type="file" id="profileImg" name="profileImg" style="visibility:hidden;"
                       accept="image/png, image/jpg, image/jpeg, image.gif" onclick="deleteSavedProfile()()">
            </div>
            <div class="update-id show-box line">
                <label for="user-id">아이디 : </label>
                <input type="text" id="user-id" name="user-id" value="${user.id}" readonly>
            </div>
            <div class="update-name show-box line">
                <label for="user-name">닉네임 : </label>
                <input type="text" id="user-name" name="user-name" value="${user.name}" readonly>
            </div>
            <div class="update-gender show-box line">
                <label for="user-gender">성&nbsp;&nbsp;&nbsp;별 : </label>
                <input type="text" id="user-gender" name="user-gender" value="${user.gender}" readonly>
            </div>
            <div class="show-box line">
                <label for="user-age">생년월일 : </label>
                <input type="text" id="user-age" name="user-age" value="${user.birth}" readonly>
            </div>
            <div class="show-box line">
                <label for="user-email">이메일 : </label>
                <input type="text" id="user-email" name="user-email" value="${user.email}" readonly>
            </div>


        </div>
        <div id="right-container">
            <div class="update-pwd line right-set">
                <label for="new-pwd">새 비밀번호 :&nbsp;&nbsp;</label>
                <input type="password" id="new-pwd" name="new-pwd">
            </div>
            <div class="update-pwd-chk line right-set">
                <label for="new-pwd-chk">새 비밀번호 확인 :&nbsp;&nbsp;</label>
                <input type="password" id="new-pwd-chk" name="new-pwd-chk">
            </div>

            <div class="line right-set">
                <label for="liveCountry"><spring:message code="user_join.liveCountry"/>&nbsp;</label>
                <select class="country-check" id="liveCountry">
                    <option value="" disabled selected>
                        <spring:message code="user_join.selectLang"/>
                    </option>
                    <option value="korea" ${user.liveCountry == 'korea' ? 'selected' : ''}>
                        <spring:message code="user_join.country_korea"/>
                    </option>
                    <option value="japan" ${user.liveCountry == 'japan' ? 'selected' : ''}>
                        <spring:message code="user_join.country_japan"/>
                    </option>
                    <option value="usa" ${user.liveCountry == 'usa' ? 'selected' : ''}>
                        <spring:message code="user_join.country_usa"/>
                    </option>
                </select>
            </div>
            <div class="line right-set">
                <label for="liveCity"><spring:message code="user_join.liveCity"/></label>
                <input type="text" id="liveCity" name="liveCity" value="${user.liveCity}">
            </div>
            <div class="line">
                <div class="lang-choose">
                    <label for="use-lang"><spring:message code="user_join.useLang"/></label>
                    <select class="lang-check" id="use-lang">
                        <option value="" disabled selected>
                            <spring:message code="user_join.selectLang"/>
                        </option>
                        <c:forEach var="lang" items="${languageCode}" varStatus="vs">
                            <option vlaue="${lang}" class="${lang}">
                                <spring:message code="user_join.select${lang}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="line">
                <div class="use-lang-box">
                    <c:forEach var="lang" items="${useLang}" varStatus="vs">
                        <span class="${lang}"><spring:message code="user_join.select${lang}"/></span>
                    </c:forEach>
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
                </div>
            </div>
            <div class="line">
                <div class="need-lang-box">
                    <c:forEach var="lang" items="${needLang}" varStatus="vs">
                        <span class="${lang}"><spring:message code="user_join.select${lang}"/></span>
                    </c:forEach>
                </div>
            </div>
            <div class="line ">
                <div class="intro">
                    <h3>소개글</h3><br>
                    <textarea class="fixed-textarea">${user.intro}</textarea>
                </div>

            </div>

            <div class="svg-wrapper">
                <svg height="40" width="150" xmlns="http://www.w3.org/2000/svg">
                    <rect id="shape" height="40" width="150"/>
                    <div id="text">
                                <span class="spot login-bnt" onclick="updateForm()"><spring:message
                                        code="user_join.joinBtn"/></span>
                    </div>
                </svg>
            </div>
        </div>


    </div>
</form>
<script src="/script/user-update.js"></script>
</body>
</html>
