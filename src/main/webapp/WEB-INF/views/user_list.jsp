<%--
  Created by IntelliJ IDEA.
  User: ho625
  Date: 2023-08-11
  Time: 오후 12:38
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
    <div class="user">
        <ul>
            <li>이름${name}</li>
            <li>성별</li>
            <li>나이</li>
            <li>이미지</li>
            <li>거주국가</li>
            <li>거주도시</li>
        </ul>
    </div>


    <c:import url="footer.jsp"/>
</div>
</body>
</html>
