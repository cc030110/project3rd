// 파일처리 부분 추후수정 필요

$(document).ready(function(){
    $('#btn').click(e => {
        write();
    })
});

function write(){
    const platform = $('#platform').val();
    const title = $('#title').val();
    const creator = $('#creator').val();
    const contents = $('#contents').val();
    const participants = $('#participants').val();
    const deadline = $('#deadline').val();

    if(title===""){
        alert("제목을 입력해주세요.");
    }else if(contents===""){
        alert("내용을 입력해주세요.");
    }else if(participants===""){
        alert("인원 수를 입력해주세요.");
    }else{
        let write={
            "platformName":platform,
            "title":title,
            "id":creator,
            "contents":contents,
            "participantsNum":participants, // key값 설정해줄 때, vo에 정의된 변수명과 동일하게 넣어주자
            "deadline":deadline
        }
        console.log(write);

        $.ajax({
            method:'POST',
            url:'/board/community/write',
            data:JSON.stringify(write),
            contentType:'application/json', // 전송방식 에러 : multipart/form-data
            async:false
        }).done(function (response){
            if(response===null){
                alert("게시글 등록에 실패했습니다.")
            }else{
                alert("게시글이 등록되었습니다.");
                window.location.href="/board/community/"+response.boardNo;
            }

        }).fail(function(){
            alert("등록 오류")
        });
    }
}
