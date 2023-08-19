<%-- Footer --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!-- 다국어 처리 -->

<html>

<head>
    <title>GLOBALTIES</title>
</head>

<body>
    <div class="footer">
        <div class="footer_logo">
            <h2>©2023 All Rights Reserved | GLOBALTIES</h2>
        </div>

        <div class="footer_detail">
            <ul>
                <li><spring:message code="footer.terms"/> | </li>
                <li><spring:message code="footer.privacy"/> | </li>
                <li><spring:message code="footer.contact"/> | </li>
                <li><spring:message code="footer.administrator"/> | </li>
                <li><spring:message code="footer.creator"/></li> <!-- 나중에 제작자들 들어갈 페이지 -->
            </ul>
        </div>
    </div>
</body>

</html>