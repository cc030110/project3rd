$(document).ready(function(){

});

function moveToUpdate(){
    let boardNo = $('#board_num_hidden').val();
    console.log(boardNo);

    window.location.href='/board/community/'+boardNo+'/update';
}

function deleteBoard(){
    // Trouble shooting : html 측에서 받아올 boardNo가 존재하지 않았음
    let boardNo=$('#board_num_hidden').val();

    if(confirm("게시글을 삭제하시겠습니까?")){
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
    }else{
        return false;
    }
}