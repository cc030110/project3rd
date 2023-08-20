<%--
  Created by IntelliJ IDEA.
  User: ho625
  Date: 2023-08-20
  Time: 오후 7:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="/css/user_detail.css">
    <title>Title</title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <div class="user-profile">
            <img src="${user.profileImg}" class="profile-img">
            <div class="user-info">
                <p class="user-info-item"><span class="label">Name:</span> ${user.name}</p>
                <p class="user-info-item"><span class="label">Gender:</span> ${user.gender}</p>
                <p class="user-info-item"><span class="label">Age:</span> ${user.age}</p>
                <p class="user-info-item"><span class="label">Country:</span> ${user.liveCountry}</p>
                <p class="user-info-item"><span class="label">City:</span> ${user.liveCity}</p>
            </div>
        </div>
        <c:if test="${not empty myUser}">
            <div class="user-actions">
                <button class="favorite-button">즐겨찾기</button>
                <button class="report-button">신고하기</button>
            </div>
        </c:if>
    </div>
</div>
<c:import url="footer.jsp"/>
</body>
</html>
