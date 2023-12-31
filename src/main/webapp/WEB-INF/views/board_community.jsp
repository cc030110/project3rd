<!-- 모임 게시판 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <link rel="stylesheet" href="/css/board_community.css">

    <!-- favicon -->
    <link rel="icon" href="https://ucarecdn.com/fb9bcc20-6d13-4a5a-a5b7-541ecfbf373f/" type="image/x-icon">

    <title><spring:message code="board_community.main_title"/></title>
</head>

<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <c:choose>
            <c:when test="${board eq null}">
                <p><spring:message code="board_community.exist"/></p>
            </c:when>
            <c:otherwise>
                <input type="hidden" id="board_num_hidden"
                       value=${board.boardNo}> <!-- Trouble shooting : js에서 받아와야 할 값 추가-->
                <div class="first_line">
                    <c:if test="${board.id==id}">
                        <div class="btns">
                            <input type="button" id="modify_btn" value="<spring:message code="board_community.modify"/>"
                                   onclick="moveToUpdate()">
                            <input type="button" id="delete_btn" value="<spring:message code="board_community.delete"/>"
                                   onclick="deleteBoard()">
                        </div>
                    </c:if>

                    <div class="btns">
                        <input type="button" id="back_btn" value="<spring:message code="board_community.back"/>"
                               onclick="moveToMain()">
                    </div>
                </div>


                <div class="second_line">
                    <div class="main_info1 title">
                        <p id="title">${board.title}</p>
                    </div>

                    <div class="main_info1 etc">
                        <span id="creator"><spring:message code="board_community.author"/> : ${board.name} | </span>
                        <span id="views"><spring:message code="board_community.views"/> : ${board.views}</span>
                    </div>
                </div>

                <div class="third_line">
                    <img src="${platform.get(board.platformName)}" alt="이미지 업로드 실패">
                    <div class="third_line_text">
                        <p id="platform"><spring:message code="board_community.platform"/> : ${board.platformName}</p>
                        <fmt:parseDate value="${board.createdAt}" var="parsedCreatedAt" pattern="yyyy-MM-dd'T'HH:mm:ss" />
                        <fmt:formatDate value="${parsedCreatedAt}" pattern="yyyy-MM-dd" var="formattedCreatedAt" />
                        <p id="period"><spring:message code="board_community_main.period"/> : ${formattedCreatedAt}
                            ~ ${board.deadline}</p>
                        <p id="participants"><spring:message code="board_community.participants"/>
                            : ${board.participantsNum}</p>
                    </div>
                </div>

                <div class="contents_line">
                    <textarea id="contents" readonly>${board.contents}</textarea>
                </div>

                <c:if test="${imgList ne null}">
                    <div class="img-box">
                        <c:forEach items="${imgList}" var="imgs" varStatus="vs">
                            <img src="${imgs.img}">
                        </c:forEach>
                    </div>
                </c:if>

                <div class="participants_line">
                    <div class="participants_num">
                        <span>참가자
                        <c:choose>
                            <c:when test="${names.size()==board.participantsNum}">
                                [모집 완료]
                            </c:when>
                            <c:otherwise>
                                [모집 중]
                            </c:otherwise>
                        </c:choose>
                        </span>
                    </div>
                    <div class="participants_list">
                        <c:forEach items="${names}" var="name">
                            <a href="/user/${name}">${name}</a>
                        </c:forEach>
                    </div>
                    <div>
                        [ ${names.size() >= 1 ? names.size() : 0} / ${board.participantsNum} ]
                    </div>
                </div>
                <div class="participate_btn">
                    <c:if test="${board.id!=id}">
                        <input type="button" id="participate_btn" onclick="participate(`${board.boardNo}`)" value='<spring:message code="board_community.join"/>'>
                    </c:if>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <c:import url="footer.jsp"/>
</div>

<script src="/script/board-community.js"></script>

</body>
</html>

<%-- # 댓글
<div class="card">
            <div class="card_header">댓글 목록</div>
            <ul>
                <li id="comment_1" class="list-group-item d-flex justify-content-between">
                    <div>댓글 내용</div>
                    <div class="d_flex">
                        <div class="font-italic">작성자 : </div>
                        <button class="badge">삭제</button>
                    </div>
                </li>
            </ul>
        </div>

        <br>

        <div class="card">
            <div class="card-body">
                <textarea class="form_contorl" rows="1"></textarea>
            </div>

            <div class="card_footer">
                <button class="btn_primary">등록</button>
            </div>
        </div>--%>
