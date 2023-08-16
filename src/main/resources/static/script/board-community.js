$(document).ready(function(){

});

function deleteBoard(){
    $.ajax({
        method:'DELETE',
        url:`/board/community/delete/${boardNo}`,
        contentType: 'application/json',
        async:false
    }).done(function (response){
        console.log(response);
    }).fail(function(){
        console.log("게시글 삭제 실패");
    })
}