<%--
  Created by IntelliJ IDEA.
  User: ho625
  Date: 2023-08-14
  Time: 오후 4:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <p>empty log : ${empty log}</p>

    <p>${user.id}</p>
    <p>${user.password}</p>
    <p>${user.gender}</p>
    <p>${user.liveCountry}</p>
    <p>${user.liveCity}</p>
    <img src="${user.profileImg}">
    <c:import url="footer.jsp"/>
</div>
</body>
</html>