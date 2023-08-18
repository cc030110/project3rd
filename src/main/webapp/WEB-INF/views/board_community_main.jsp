<!-- 모임 게시판 -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%><!-- 다국어 처리 -->

<html>

<head>
    <!-- CSS -->
    <link rel="stylesheet" href="/css/grid.css">

    <title>GLOBALTIES</title>
</head>

<style>
    body{
        width:100%;
        padding:0;
        margin:0;
    }

    div.main{
        width:100%;
        margin:0 auto;
    }

    div.con_list{
        width:1000px;
        height:200px;
        border:1px solid black;
        margin:20px 0;
        padding:20px;
    }

    div.con_list a:hover{
        cursor:pointer;
        border:1px solid red;
    }

    div.con_platform{
        float:left;
    }

    div.con_platform>img{
        width:150px;
        height:150px;
        border:1px solid gray;
    }

    div.con_platform_name{
        width:80px;
        margin-top:10px;
        padding:10px;
        border:1px solid gray;
        text-align:center;
    }

    div.con_text{
        margin-left:200px;
    }
</style>

<body>
<div class="wrap">
    <c:import url="header.jsp"/>
        <div class="main">
            <c:forEach items="${list}" var="listItem" varStatus="vs">
                <a href="/board/community/${listItem.boardNo}">
                    <div class="con_list">
                        <!-- 게시판에 넣을 플랫폼 -->
                            <div class="con_platform">
                                <img src="${platform.get(listItem.platformName)}" alt="이미지 불러오기 실패">
                                <div class="con_platform_name">
                                    <p>${listItem.platformName}</p>
                                </div>
                            </div>

                            <div class="con_text">
                                <p>${listItem.title}</p><!-- 제목 -->
                                <p>${listItem.contents}</p><!-- 내용 -->
                                <p><spring:message code="board_community_main.author"/> : ${listItem.id}</p>
                                <p><spring:message code="board_community_main.participants"/>: ${listItem.participantsNum}</p>
                                <p><spring:message code="board_community_main.createdAt"/> : ${listItem.createdAt}</p>
                                <p><spring:message code="board_community_main.deadline"/>: ${listItem.deadline}</p>
                            </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    <c:import url="footer.jsp"/>
</div>
</body>
</html>
