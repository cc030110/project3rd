$(document).ready(function(){
    // 이벤트 핸들러 : onclick event js 내부처리
    $('#write_btn').click(e => {
        write();
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

// 수정페이지로 이동
function moveToUpdate(){
    let boardNo = $('#board_num_hidden').val();
    window.location.href='/board/community/'+boardNo+'/update';
}

// 메인으로 이동
function moveToMain(){
    window.location.href='/board/community/main/1'
}

// 게시글 검색
function searchBoardCommunity(boardNo){
    let selectedValue = $("#search-select").val();
    let searchText = $("#search-input").val();
    if (searchText===""){
        alert("검색어가 없습니다.");
        return;
    }
    let path = "/board/community/main/"+boardNo;
    let query = "?" + selectedValue + "=" + searchText;
    window.location.href = path + query;
}

// 게시판 쓰기
function write(){

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
            method:'POST',
            url:'/board/community/write',
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
        deleteBoard(boardNo);
    });
}


// 게시판 삭제
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

// 참가
function participate(boardNo){
    $.ajax({
        type: "POST",
        url: `/board/community/participant/${boardNo}`,
        contentType: "application/json",
    }).done(function (response){
        alert(response.value);
    }).fail(function (request){
        console.log("status: " + request.status);
        console.log("responseText: " + request.responseText);
        console.log("error: " + request.error);
    });

}