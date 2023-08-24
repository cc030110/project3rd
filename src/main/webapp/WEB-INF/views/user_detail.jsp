<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="/css/user_detail.css">
    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">
    <title><spring:message code="user_detail.title"/></title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <div class="user-profile">
            <c:choose>
                <c:when test="${user.profileImg ne null}">
                    <img class="profile-img" src="${user.profileImg}" alt=""/>
                </c:when>
                <c:otherwise>
                    <img class="profile-img" src="https://ucarecdn.com/1e359c2a-7124-4da9-9cd9-be5fe9e8c1f0/" alt=""/>
                </c:otherwise>
            </c:choose>
            <div class="user-info">
                <p class="user-info-item"><span class="label"><spring:message
                        code="user_detail.nickname"/> : </span> ${user.name}</p>
                <p class="user-info-item"><span class="label"><spring:message
                        code="user_detail.gender"/> : </span> ${user.gender}</p>
                <p class="user-info-item"><span class="label"><spring:message
                        code="user_detail.birth"/> : </span> ${user.birth}</p>
                <p class="user-info-item"><span class="label"><spring:message
                        code="user_detail.country"/> : </span> ${user.liveCountry}</p>
                <p class="user-info-item"><span class="label"><spring:message
                        code="user_detail.city"/> : </span> ${user.liveCity}</p>
                <div class="lang-box">
                    <span class="label">구사언어 : </span>
                    <c:forEach var="lang" items="${useLang}" varStatus="vs">
                    <p class="user-info-item ${lang}" style="display: inline-block"> <spring:message code="user_join.select${lang}"/></p>
                    </c:forEach>
                </div>
                <div class="lang-box">
                    <span class="label">학습언어 : </span>
                    <c:forEach var="lang" items="${needLang}" varStatus="vs">
                        <p class="user-info-item ${lang}" style="display: inline-block"> <spring:message code="user_join.select${lang}"/></p>
                    </c:forEach>
                </div>
                <p class="user-info-item"><span class="label">소개 : </span> ${user.intro}</p>
            </div>
        </div>
        <c:if test="${not empty myUser}">
            <div class="user-actions">
                <c:choose>
                    <c:when test="${user.id eq myUser.id}">

                    </c:when>
                    <c:when test="${isBlocked}">
                        <!-- 차단 상태일 때 -->
                        <div class="user-actions">
                            <button class="report-button" onclick="unblockUser('${user.id}','${myUser.id}')">차단 해제
                            </button>
                        </div>
                    </c:when>
                    <c:when test="${isLiked}">
                        <!-- 즐겨찾기 상태일 때 -->
                        <div class="user-actions">
                            <button class="favorite-button" onclick="cancelLikeUser('${user.id}','${myUser.id}')">즐겨찾기
                                해제
                            </button>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <!-- 둘 다 아닐 때 -->
                        <div class="user-actions">
                            <button class="favorite-button" onclick="likeUser('${user.id}','${myUser.id}')">
                                <spring:message code="user_detail.like"/></button>
                            <button class="report-button" onclick="blockUser('${user.id}','${myUser.id}')">
                                <spring:message code="user_detail.block"/></button>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/user-detail.js"></script>
</body>
</html>
