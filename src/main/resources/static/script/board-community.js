// 파일처리 부분 추후수정 필요

// $(document).ready(function() {
//     $('#file').on('change', function() {
//         let files = $('#file').prop('files'); // FileList 객체
//         let max = 3; // 최대 선택 파일 개수
//
//         if (files.length > max) {
//             alert('최대 ' + max + '개까지 선택할 수 있습니다.');
//             $(this).val(''); // 선택한 파일 초기화
//         }
//     });
// });

function write(){
    const platform = $('#platform').val();
    const creator = $('#creator').val();
    const title = $('#title').val();
    const contents = $('#contents').val();
    const participants = $('#participants');
    const deadline = $('#deadline');

    if(title===""){
        alert("제목을 입력해주세요.");
    }else if(contents===""){
        alert("내용을 입력해주세요.");
    }else if(participantsNum===""){
        alert("인원 수를 입력해주세요.");
    }else{
        let write={
            "platform_name  ":platform,
            "id":creator,
            "title":title,
            "contents":contents,
            "participants_num":participants,
            "deadline":deadline
        }
        console.log(write);

        $.ajax({
            method:'POST',
            url:'/board/community/write',
            data:JSON.stringify(write),
            contentType:'application/json',
            async:false
        }).done(function (response){
            if(response===null){
                alert("등록 실패")
            }else{
                alert("등록 성공");
                window.location.href="/board/community/"+response.boardNo;
            }

        }).fail(function(){
            alert("등록 오류")
        });
    }
}
