$(document).ready(function(){
    /*$('#submit_btn').click(e => {
        update();
    })
*/
    // 파일 업로드처리
    $('#file').on('change', function() {
        let files = $('#file').prop('files'); // FileList 객체
        let max = 3; // 최대 선택 파일 개수

        if (files.length > max) {
            alert('최대 ' + max + '개까지 선택할 수 있습니다.');
            $(this).val(''); // 선택한 파일 초기화
        }

        // 이미지 미리보기
        let imgBox = $('.img_box');

        // 미리 보기 영역 초기화
        imgBox.empty();

        if(files.length>0){
            for(let i=0;i<files.length;i++){
                let file = files[i];
                let file_type = file.type;

                // 이미지 파일인 경우에만 미리 보기 추가
                if (file_type.startsWith('image/')) {
                    let preview_url = URL.createObjectURL(file);
                    let img = $('<img>').attr('src', preview_url).addClass('preview-image');
                    $('.img_box').append(img);
                }
            }
        }
    });
});

// 수정하기
function update(){
    const boardNo = $('#board_num_hidden').val();
    const title = $('#title').val();
    const creator = $('#creator').val();
    const contents = $('#contents').val();

    if(title===""){
        alert("제목을 입력해주세요.");
    }else if(contents===""){
        alert("내용을 입력해주세요.");
    }else{
        let update={
            "title":title,
            "id":creator,
            "contents":contents,
        }

        console.log(update);
        console.log(boardNo);

        $.ajax({
            method:'PUT',
            url:`/board/free/${boardNo}/update`,
            data:JSON.stringify(update),
            contentType:'application/json',
            async:false
        }).done(function (response){
            if(response===null){
                alert("게시글 등록에 실패했습니다.")
            }else{
                if($('#file').val()){
                    uploadImg(boardNo);
                }else{
                    alert("게시글 수정이 완료되었습니다.");
                }

                window.location.href='/board/free/'+boardNo;
            }
        }).fail(function(){
            alert("수정 실패")
        });
    }
}

function uploadImg(boardNo){
    let fileInput = $('#file');
    let files = fileInput.prop('files'); // FileList 객체

    let formData = new FormData(); // FormData 객체 생성

    for (let i = 0; i < files.length; i++) {
        formData.append("img", files[i]); // 파일 데이터 추가
    }

    console.log(formData);

    $.ajax({
        type: 'POST',
        url: `/board/free/upload/file?boardNo=${boardNo}`, // file?no=${boardNo}
        data: formData, // FormData 객체를 바로 전송
        contentType: false, // 파일 전송 시 false
        processData: false, // FormData 사용 시 false
        async:false
    }).done(function (response){
        alert("게시글이 등록되었습니다.");
    }).fail(function (){
        alert("파일 업로드 실패");
        // 해당 boardNo의 게시판 삭제
        deleteBoardTmp(boardNo);
    });
}


function deleteBoardTmp(boardNo){
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


function back(){
    let boardNo = $('#board_num_hidden').val();
    window.location.href='/board/free/'+boardNo;
}

