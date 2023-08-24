// 즐겨찾기
function likeUser(userId,myId){
    let data = {
        "userId":myId,
        "likeId":userId
    }

    $.ajax({
        'method' : 'POST',
        'url' : '/user/like',
        'data' : JSON.stringify(data),
        'contentType' : 'application/json',
    }).done(function (response){
        console.log(response);
        window.location.reload();
    }).fail(function (response){
        console.log(response);
        window.location.reload();
    });

}
// 차단
function blockUser(userId,myId){
    let data = {
        "userId":myId,
        "blockId":userId
    }

    $.ajax({
        'method' : 'POST',
        'url' : '/user/block',
        'data' : JSON.stringify(data),
        'contentType' : 'application/json',
    }).done(function (response){
        console.log(response);
        window.location.reload();
    }).fail(function (response){
        console.log(response);
        window.location.reload();
    });
}

// 즐겨찾기 해제
function cancelLikeUser(userId,myId){
    let data = {
        "userId":myId,
        "likeId":userId
    }

    $.ajax({
        'method' : 'DELETE',
        'url' : '/user/like/cancel',
        'data' : JSON.stringify(data),
        'contentType' : 'application/json',
    }).done(function (response){
        console.log(response);
        window.location.reload();
    }).fail(function (response){
        console.log(response);
        window.location.reload();
    });

}

// 차단 해제
function unblockUser(userId,myId){
    let data = {
        "userId":myId,
        "blockId":userId
    }

    $.ajax({
        'method' : 'DELETE',
        'url' : '/user/unblock',
        'data' : JSON.stringify(data),
        'contentType' : 'application/json',
    }).done(function (response){
        console.log(response);
        window.location.reload();
    }).fail(function (response){
        console.log(response);
        window.location.reload();
    });
}