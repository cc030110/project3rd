<!-- 모임 게시판 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- 다국어 처리 -->

<html>

<head>
    <title>GLOBALTIES</title>
</head>

<body>
<div class="wrap">
    <c:import url="header.jsp"/>
    <div class="main">
        <c:choose>
            <c:when test="${board eq null}">
                <p>존재하지 않는 게시글입니다.</p>
            </c:when>
            <c:otherwise>
                <input type="hidden" id="board_num_hidden"
                       value=${board.boardNo}> <!-- Trouble shooting : js에서 받아와야 할 값 추가-->
                <ul>
                    <li>
                        <p id="board_num">게시글 번호 : ${board.boardNo}</p>
                    </li>

                    <li>
                        <p id="platform">플랫폼 : ${board.platformName}</p>
                    </li>

                    <li>
                        <p id="title">제목 : ${board.title}</p>
                    </li>

                    <li>
                        <p id="creator">작성자 : ${board.id}</p>
                    </li>

                    <li>
                        <p id="content">내용 : ${board.contents}</p>
                    </li>

                    <li>
                        <p id="participants">참가자 수 : ${board.participantsNum}</p>
                    </li>

                    <li>
                        <p id="deadline">마감일 : ${board.deadline}</p>
                    </li>

                    <li>
                        <p id="created_at">작성일 : ${board.createdAt}</p>
                    </li>

                    <c:if test="${imgList ne null}">
                        <div class="img-box">
                            <c:forEach items="${imgList}" var="imgs" varStatus="vs">
                                <img src="${imgs.img}">
                            </c:forEach>
                        </div>
                    </c:if>

                    <li>
                        <c:if test="${board.id==id}">
                            <input type="button" id="modify_btn" value="수정하기" onclick="moveToUpdate()">
                            <input type="button" id="delete_btn" value="삭제하기" onclick="deleteBoard()">
                        </c:if>
                        <input type="button" id="back_btn" value="뒤로가기" onclick="moveToMain()">
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>

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
        </div>

        <c:import url="footer.jsp"/>
    </div>

    <script src="/script/board-community.js"></script>

</body>
</html>
