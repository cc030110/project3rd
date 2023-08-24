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
    }).fail(function (response){
        console.log(response);
    });

}
// 차단
function blockUser(userId,myId){
    let data = {
        "userId":myId,
        "blockId":userId
    }

}