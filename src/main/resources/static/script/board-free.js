$(document).ready(function() {
    $('#file').on('change', function() {
        let files = $('#file').prop('files'); // FileList 객체
        let max = 3; // 최대 선택 파일 개수

        if (files.length > max) {
            alert('최대 ' + max + '개까지 선택할 수 있습니다.');
            $(this).val(''); // 선택한 파일 초기화
        }

        // let data=[];
        // for (let i = 0; i < files.length; i++) {
        //     console.log("File["+i+"]:"+files[i].name);
        //     data.push({ key: i, value: files[i].name });
        // }
        //
        // console.log(data);

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
                alert("등록 성공");
                window.location.href = "/board/free/"+response.boardNo;
            }

        }).fail(function (){
            alert("등록 오류")
        });
    }
}

// let fileInput = $('#file');
// let files = fileInput.prop('files'); // FileList 객체
// if(files.length!==0){
//     uploadImg(response.boardNo);
// }

// function uploadImg(boardNo){
//     let fileInput = $('#file');
//     let files = fileInput.prop('files'); // FileList 객체
//     let data=[];
//     data.push({"boardNo":boardNo});
//     for (let i = 0; i < files.length; i++) {
//         console.log("File["+i+"]:"+files[i].name);
//         data.push({i:files[i].name});
//     }
//
//     console.log(data);
//
//     $.ajax({
//         type: 'POST',
//         url: '/board/free/upload/file',
//         data: JSON.stringify(data),
//         contentType: 'application/json',
//         // async:false
//     }).done(function (response){
//
//     }).fail(function (){
//         alert("파일 업로드 실패");
//
//     });
// }