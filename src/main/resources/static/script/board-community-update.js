$(document).ready(function(){
    // Radio check : DB에서 가져온 값
    let dbValue=$("#platform_name_hidden").val();

    $(".platform input[type='radio']").each(function(){
        let radioValue=$(this).val();
        if(dbValue === radioValue){
            $(this).prop("checked",true);
        }
    })


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

    const selectedPlatformId = $('input[name="platform"]:checked').attr('id');
    const platformNumber = selectedPlatformId.split("_")[1];
    const selectedPlatform = $('#'+selectedPlatformId).val();

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
            "platformNum":platformNumber,
            "platformName":selectedPlatform,
            "title":title,
            "id":creator,
            "contents":contents,
            "participantsNum":participants, // key값 설정해줄 때, vo에 정의된 변수명과 동일하게 넣어주자
            "deadline":deadline
        }
        console.log(write);

        $.ajax({
            method:'PUT',
            url:'/board/community/update/{boardNo}',
            data:JSON.stringify(write),
            contentType:'application/json', // 전송방식 에러 : multipart/form-data
            async:false
        }).done(function (response){
            if(response===null){
                alert("게시글 등록에 실패했습니다.")
            }else{
                if($('#file').val()){
                    uploadImg(response.boardNo);
                }else{
                    alert("게시글이 등록되었습니다.");
                }
                window.location.href="/board/community/"+response.boardNo;
            }
        }).fail(function(){
            alert("등록 오류")
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
        url: `/board/community/write/file?boardNo=${boardNo}`, // file?no=${boardNo}
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

    window.location.href='/board/community/'+boardNo;
}

