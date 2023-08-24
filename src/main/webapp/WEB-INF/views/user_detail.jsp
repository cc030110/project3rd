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
                <p class="user-info-item"><span class="label"><spring:message code="user_detail.nickname"/> : </span> ${user.name}</p>
                <p class="user-info-item"><span class="label"><spring:message code="user_detail.gender"/> : </span> ${user.gender}</p>
                <p class="user-info-item"><span class="label"><spring:message code="user_detail.birth"/> : </span> ${user.birth}</p>
                <p class="user-info-item"><span class="label"><spring:message code="user_detail.country"/> : </span> ${user.liveCountry}</p>
                <p class="user-info-item"><span class="label"><spring:message code="user_detail.city"/> : </span> ${user.liveCity}</p>
            </div>
        </div>
        <c:if test="${not empty myUser}">
            <div class="user-actions">
                <button class="favorite-button" onclick="likeUser(`${user.id}`,`${myUser.id}`)"><spring:message code="user_detail.like"/></button>
                <button class="report-button" onclick="blockUser(`${user.id}`,`${myUser.id}`)"><spring:message code="user_detail.block"/></button>
            </div>
        </c:if>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/user-detail.js"></script>
</body>
</html>
