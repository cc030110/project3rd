$(document).ready(function(){

});

function moveToUpdate(){
    let boardNo = $('#board_num_hidden').val();

    window.location.href='/board/community/'+boardNo+'/update';
}

function moveToMain(){
    window.location.href='/board/community/main/1'
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
            alert("게시글이 삭제되었습니다.");
            window.location.href="/board/community/main/1"
        }).fail(function(){
            console.log("게시글 삭제 실패");
        })
    }else{
        return false;
    }
}