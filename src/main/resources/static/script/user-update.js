function updateForm() {
    const id = $('#user-id').val();
    const password = $('#new-pwd').val();
    const passwordChk = $('#new-pwd-chk').val();
    const liveCountry = $('#liveCountry').val();
    const liveCity = $('#liveCity').val();
    // use-lang과 need-lang 선택 내용도 배열로 가져오기
    let useLang = [];
    $(".use-lang-box span").each(function() {
        // span의 클래스 이름을 추가함
        let lang = $(this).attr('class');
        useLang.push(lang);
    });
    let needLang = [];
    $(".need-lang-box span").each(function() {
        let lang = $(this).attr('class');
        needLang.push(lang);
    });
    const intro = $('.fixed-textarea').val();

    // console.log("아나");
    // console.log(id);
    // console.log(password);
    // console.log(passwordChk);
    // console.log(liveCountry);
    // console.log(liveCity);
    // console.log(useLang);
    // console.log(needLang);
    // console.log(intro);

    //
// password 유효성 검사
    const isPasswordValid = password.length >= 4 && /^[A-Za-z0-9]+$/.test(password);

    if (!password) {
        alert("비밀번호를 입력해주세요.");
        return;
    }

    if (!isPasswordValid) {
        alert("비밀번호를 올바르게 입력해주세요.");
        return;
    }

    if (password !== password) {
        alert("비밀번호 확인이 일치하지 않습니다.");
        return;
    }


    //
    // function isValidInputPassword(input) {
    //     // 정규식 패턴: 최소 6자 이상의 영어와 숫자만 허용
    //     var pattern = /^[A-Za-z\d]{6,20}$/;
    //     return pattern.test(input);
    // }
    // // passwordChk 유효성 검사 (비밀번호 확인)
    // condition = (passwordChk.val()===password.val());
    // const passwordChkValid = validateInput(passwordChk,condition);
    // if(isValidInputPassword(passwordChk)){
    //     console.log(nameCheck + " is valid");
    // } else {
    //     console.log(nameCheck + " is invalid");
    //     alert("최소 6자 이상의 영어와 숫자만 허용")
    //     return;
    // }
    // if(!passwordChkValid) return;

    // 필수 입력사항들

    // liveCountry
    // condition = liveCountry.val() !== '' && liveCountry.val() !== null;
    // const liveCountryValid = validateInput(liveCountry, condition);
    // if(!liveCountryValid) return;

    // useLang
    // const useLangValid = validateInput($('#use-lang'),useLang.length!==0);
    // if(!useLangValid) return;

    // needLang
    // const needLangValid = validateInput($('#need-lang'),needLang.length!==0);
    // if(!needLangValid) return;

    // 프로필 이미지를 먼저 처리 해야함
    let uploadStatus = true; // 파일 업로드 실패 시 false
    let profileUrl="";

    let file = $('#profileImg').prop('files'); // FileList 객체
    let formData = new FormData(); // FormData 객체 생성
    if (file.length > 0) {
        formData.append("img", file[0]); // 첫 번째 파일 데이터 추가
        $.ajax({
            type: 'POST',
            url: `/user/join/profile`,
            data: formData, // FormData 객체를 바로 전송
            contentType: false, // 파일 전송 시 false
            processData: false, // FormData 사용 시 false
            async:false
        }).done(function (response){
            if(response!==""){
                profileUrl = response;
                console.log("이미지 업로드 성공")
            }else{
                uploadStatus=false;
            }
        }).fail(function (){
            alert("파일 업로드 실패");
            isEmailChecked=false;
        });
    }

    if(!uploadStatus){
        return;
    }

    // userData <- UserRequestDto
    let userData = {
        "id" : id,
        "password" : password,
        "liveCountry" : liveCountry
    }

    // 필수 정보 외에 정보는 입력한 정보가 있을 경우 추가
    userData.profileImg = profileUrl;

    if(liveCity !== "") {
        userData.liveCity=liveCity;
    }

    // "useLang" : useLang
    userData.useLang=useLang;

    // "needLang" : needLang
    userData.needLang=needLang;
    userData.intro=intro;

    // 보내는 data 확인
    // console.log(userData);
    // console.log(useLang);
    // console.log(needLang);

    $.ajax({
        type: 'PUT',
        url: `/user/update`,
        data:JSON.stringify(userData),
        contentType: 'application/json',
    }).done(function (response){
        console.log(response);
        alert(response.value);
        if(response.key==="update"){
            alert("회원수정 성공");
            window.location.reload();
        }else{
            window.location.reload();
        }
    }).fail(function (){
        alert("회원수정 실패");
        window.location.reload();
    });
}



// 사용하는 언어 셀렉트 추가함수
$("#use-lang").change(function() {
    let selected = $(this).find('option:selected');
    let selectedClass = selected.attr('class');
    let selectedText = selected.text();

    // 이미 선택된 값이 있는지 확인
    if (!$('.use-lang-box .'+selectedClass).length>0) {
        $('<span>').addClass(selectedClass).text(selectedText).appendTo('.use-lang-box');
    }
});

// 배울 언어 셀렉트 추가함수
$("#need-lang").change(function() {
    let selected = $(this).find('option:selected');
    let selectedClass = selected.attr('class');
    let selectedText = selected.text();

    // 이미 선택된 값이 있는지 확인
    if (!$('.need-lang-box .'+selectedClass).length>0) {
        $('<span>').addClass(selectedClass).text(selectedText).appendTo('.need-lang-box');
    }
});

// 선택된 언어(span) 클릭 시 지우기
$(document).on("click", ".need-lang-box span", function() {
    $(this).remove();
});
$(document).on("click", ".use-lang-box span", function() {
    $(this).remove();
});


// 사진 삭제 후 등록
function deleteSavedProfile() {
    var savedProfile = document.getElementById("saved-profile");

    if (savedProfile) {
        savedProfile.remove();
    }
}

$("#profileImg").change(function (){
    let files = $('#profileImg').prop('files');
    let file = files[0];

    // 프로필 이미지 보기 영역
    let imgBox = $('.update-profile-img');
    // 기존 이미지 영역 비우기
    imgBox.empty();

    if(files.length>0){
        let file_type = file.type;
        // 이미지 파일인 경우에만 미리 보기 추가
        if (file_type.startsWith('image/')) {
            let preview_url = URL.createObjectURL(file);
            let img = $('<img>').attr('src', preview_url).addClass('saved-profile');
            imgBox.append(img);
        }
    }
});