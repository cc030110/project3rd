$(document).ready(function() {
    $('#file').on('change', function() {
        let files = $('#file').prop('files'); // FileList 객체
        let max = 3; // 최대 선택 파일 개수

        if (files.length > max) {
            alert('최대 ' + max + '개까지 선택할 수 있습니다.');
            $(this).val(''); // 선택한 파일 초기화
        }
    });
});

function upload(){
    const author = $('#name').val();
    const title = $('#title').val();
    const contents = $('#contents').val();

    if(title===""){
        alert("제목 입력");
    }else if(contents===""){
        alert("내용 입력");
    }else{
        let write = {
            "id":author,
            "title":title,
            "contents":contents
        }
        console.log(write);

        $.ajax({
            method:'POST',
            url:'/board/free/upload',
            data:JSON.stringify(write),
            contentType: 'application/json',
            async:false
        }).done(function (response){
            if(response===null){
                alert("등록 실패");
            }else{
                uploadImg(response.boardNo);
                // uploadImg 실패 시 처리 어떻게?
                alert("등록 성공");
                window.location.href = "/board/free/"+response.boardNo;
            }

        }).fail(function (){
            alert("등록 오류")
        });
    }
}

function uploadImg(boardNo){
    let fileInput = $('#file');
    let files = fileInput.prop('files'); // FileList 객체
    // let data = [];
    //
    // for (let i = 0; i < files.length; i++) {
    //     data.push({
    //         "boardNo": boardNo,
    //         "img": files[i]
    //     });
    // }

    let formData = new FormData(); // FormData 객체 생성

    for (let i = 0; i < files.length; i++) {
        formData.append("boardNo", boardNo); // 추가 데이터 추가
        formData.append("img", files[i]); // 파일 데이터 추가
    }

    console.log(data);

    $.ajax({
        type: 'POST',
        url: '/board/free/upload/file',
        // data: JSON.stringify(data),
        // contentType: 'multipart/form-data',
        data: formData, // FormData 객체를 바로 전송
        contentType: false, // 필수: 파일 전송 시 false로 설정
        processData: false, // 필수: FormData 사용 시 false로 설정
    }).done(function (response){
        console.log(response);

    }).fail(function (){
        alert("파일 업로드 실패");

    });
}
