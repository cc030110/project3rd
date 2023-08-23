<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>자유 게시판</title>
    <link rel="stylesheet" href="/css/board_free.css">
</head>
<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <c:choose>
            <c:when test="${board eq null}">
                <div id="not-exist">존재하지 않는 게시글입니다.</div>
            </c:when>

            <c:otherwise>
                <div class="first-line">
                    <c:if test="${not empty id && id eq board.id}">
                        <div class="btn-group">
                            <a href="/board/free/${board.boardNo}/update" id="update-btn">
                                <input type="button" id="modify-btn" value="수정"/>
                            </a>
                            <input type="button" id="delete-btn" onclick="deleteBoard(${board.boardNo})" value="삭제">
                        </div>
                    </c:if>

                    <div class="back_btn">
                        <input type="button" id="back-btn" value="뒤로가기">
                    </div>
                </div>

                <div class="second-line">
                    <div class="title">제목 : ${board.title}</div>
                </div>

                <div class="third-line">
                    <div class="creator">
                        작성자 : ${board.name}
                    </div>

                    <div class="etc">
                        <span>조회수 : ${board.views}</span> | <span>작성일 : ${board.createdAt}</span>
                    </div>
                </div>

                <div class="contents-line">
                    <textarea id="contents" readonly>${board.contents}</textarea>
                </div>

                <c:if test="${imgList ne null}">
                    <div class="img-box">
                        <c:forEach items="${imgList}" var="imgs" varStatus="vs">
                            <img src="${imgs.img}">
                        </c:forEach>
                    </div>
                </c:if>

                <div class="last-line"></div>
            </c:otherwise>
        </c:choose>
    </div>
    <c:import url="footer.jsp"/>
</div>
<script src="/script/board-free.js"></script>
</body>
</html>
