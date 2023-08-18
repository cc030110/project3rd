<%--
  Created by IntelliJ IDEA.
  User: cc030
  Date: 2023-08-11
  Time: 오전 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>자유 게시판</title>
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
        <div class="main">
            <c:choose>
                <c:when test="${board eq null}">
                    <div>존재하지 않는 게시글입니다.</div>
                </c:when>
                <c:otherwise>
                    <div>게시글 번호 : ${board.boardNo}</div>
                    <div>작성자 : ${board.id}</div>
                    <div>제목 : ${board.title}</div>
                    <div>내용 : ${board.contents}</div>
                    <div>작성일 : ${board.createdAt}</div>
                    <div>조회수 : ${board.views}</div>
                    <c:if test="${imgList ne null}">
                        <div class="img-box">
                            <c:forEach items="${imgList}" var="imgs" varStatus="vs">
                                <img src="${imgs.img}">
                            </c:forEach>
                        </div>
                    </c:if>
                    <c:if test="${not empty id && id eq board.id}">
                        <button type="button">

                        </button>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
