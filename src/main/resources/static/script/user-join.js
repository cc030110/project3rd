function joinForm() {
    const id = $('#id').val();
    const password = $('#password').val();
    const passwordChk = $('#password-chk').val();
    const name = $('#name').val();
    const email = $('#email').val();
    const gender = $('#gender').val();
    const age = $('#age').val();
    const profileImg = $('#profileImg').val();
    const liveCountry = $('#liveCountry').val();
    const liveCity = $('#liveCity').val();


    if(id==="" && password==="" && name==="" && email==="" && liveCountry){
        alert("필수 정보를 입력해주세요");
    } else if(password!==passwordChk){
        alert("비밀번호가 일치 하지않습니다");
    } else {
        let join = {
            "id":id,
            "password":password,
            "name":name,
            "gender":gender,
            "age":age,
            "profileImg":profileImg,
            "email":email,
            "liveCountry":liveCountry,
            "liveCity":liveCity
        }
        console.log(join);

        $.ajax({
            method:'POST',
            url:'/user/join',
            data:JSON.stringify(join),
            contentType: 'application/json',
            async:false
        }).done(function (response){
            if(response===null){
                alert("회원가입 실패");
            }else{
                if($('#file').val()){
                    // uploadImg(response.boardNo);
                }else{
                    window.location.href = "/user/login";
                    alert("회원가입 되었습니다.");
                }
            }
        }).fail(function (){
            alert("등록 오류")
        });
    }

}

function uploadImg(boardNo) {
    let fileInput = $('#file');
    let files = fileInput.prop('files'); // FileList 객체

    let formData = new FormData(); // FormData 객체 생성

    for (let i = 0; i < files.length; i++) {
        formData.append("img", files[i]); // 파일 데이터 추가
    }

    console.log(formData);

    $.ajax({
        type: 'POST',
        url: `/board/free/upload/file?no=${boardNo}`,
        data: formData, // FormData 객체를 바로 전송
        contentType: false, // 파일 전송 시 false
        processData: false, // FormData 사용 시 false
        async: false
    }).done(function (response) {
        alert("게시글이 등록되었습니다.");
    }).fail(function () {
        alert("파일 업로드 실패");
        // 해당 boardNo의 게시판 삭제
        deleteBoard(boardNo);
    });
}