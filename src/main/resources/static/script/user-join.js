// 이메일 관련 전역변수
let serverGeneratedCode = ""; // 이메일 코드
let isEmailChecked = false; // 이메일 확인 체크 여부

$(document).ready(function() {
    // 입력 필드가 변경될 때 에러 클래스 및 에러 메시지 제거
    $('input, select').on('input change', function() {
        let inputClass = $(this).attr('class').split(" ");
        let classcheck = inputClass[0];
        console.log(classcheck);
        $(this).removeClass('error');

        $('p.'+classcheck).hide();
    });
});


// 유효성 검사 함수
// 검사할 input , 조건
function validateInput(input, condition) {
    // 유효한 정보가 아닐 경우
    if (!condition) {
        input.addClass("error");
        let inputClass = input.attr('class').split(" ");
        let classcheck = inputClass[0];
        // 에러 메세지 .err 보여주기
        $('p.' + classcheck).show().css('display', 'inline-block');
        // input.next('.err').show();
        // 해당 input을 focus
        input.focus();
        return false;
    }
    return true;
}

function joinForm() {
    const id = $('#id');
    const password = $('#password');
    const passwordChk = $('#password-chk');
    const name = $('#name');
    const email = $('#email');
    const gender = $('input[name="gender"]:checked');
    const birth = $('#birth');
    const liveCountry = $('#liveCountry');
    const liveCity = $('#liveCity');
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

    // id , password 조건
    // id혹은password.val().length >= n : n자리 이상
    // /^[A-Za-z0-9]+$/.test(id.val()) : 영어 대소문자와 숫자로만 구성

    // id,password 유효성 검사 잠깐 꺼둠!!!!

    // // id 유효성 검사
    // let condition = id.val().length >= 8 && /^[A-Za-z0-9]+$/.test(id.val());
    let condition = id.val()!=="";
    const idValid = validateInput(id, condition);

    if(!idValid) return;
    //
    // // password 유효성 검사
    // condition = password.val().length >= 4 && /^[A-Za-z0-9]+$/.test(password.val());
    condition = password.val()!=="";
    const passwordValid = validateInput(password,condition);
    if(!passwordValid) return;
    //
    // // passwordChk 유효성 검사 (비밀번호 확인)
    condition = (passwordChk.val()===password.val());
    const passwordChkValid = validateInput(passwordChk,condition);
    if(!passwordChkValid) return;

    // 이메일 유효성 검사 추가 필요
    // jsp에도 p.err 내용 추가해야함.. 그러면 message에도 등록해야하구...


    // 필수 입력사항들
    // name
    const nameValid = validateInput(name, name.val()!== '');
    if(!nameValid) return;
    // email
    const emailValid = validateInput(email, email.val() !== '');
    if(!emailValid) return;
    // liveCountry
    condition = liveCountry.val() !== '' && liveCountry.val() !== null;
    const liveCountryValid = validateInput(liveCountry, condition);
    if(!liveCountryValid) return;

    // useLang
    const useLangValid = validateInput($('#use-lang'),useLang.length!==0);
    if(!useLangValid) return;

    // needLang
    const needLangValid = validateInput($('#need-lang'),needLang.length!==0);
    if(!needLangValid) return;

    // 이메일 인증 여부
    // if(!isEmailChecked){
    //     alert("이메일 인증이 필요합니다."); // 나중에 alert말고 다른걸로 바꾸기?
    //     return;
    // }

    // // 여기까지 왔다면 통과!
    console.log("성공!");

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
        "id" : id.val(),
        "password" : password.val(),
        "name" : name.val(),
        "gender" : gender.val(),
        "email" : email.val(),
        "liveCountry" : liveCountry.val()
    }

    // 필수 정보 외에 정보는 입력한 정보가 있을 경우 추가
    if(profileUrl!==""){
        userData.profileImg = profileUrl;
    }
    if(birth.val().length>0) userData.birth = birth.val();
    if(liveCity.val().length>0) userData.liveCity=liveCity.val();

    // "useLang" : useLang
    userData.useLang=useLang;

    // "needLang" : needLang
    userData.needLang=needLang;

    // 보내는 data 확인
    console.log(userData);

    $.ajax({
        type: 'POST',
        url: `/user/join`,
        data:JSON.stringify(userData),
        contentType: 'application/json',
    }).done(function (response){
        console.log(response);
        alert(response.value);
        if(response.key==="join"){
            window.location.href = "/";
        }else{
            window.location.href = "/user/join";
        }
    }).fail(function (){
        alert("회원가입 실패");
        window.location.href = "/user/join";
    });
}


// 프로필 이미지 미리보기
$("#profileImg").change(function (){
    let files = $('#profileImg').prop('files');
    let file = files[0];

    // 프로필 이미지 보기 영역
    let imgBox = $('.profile-img-box');
    // 기존 이미지 영역 비우기
    imgBox.empty();

    if(files.length>0){
        let file_type = file.type;
        // 이미지 파일인 경우에만 미리 보기 추가
        if (file_type.startsWith('image/')) {
            let preview_url = URL.createObjectURL(file);
            let img = $('<img>').attr('src', preview_url).addClass('preview-profile');
            imgBox.append(img);
        }
    }
});

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


// 이메일 코드 전송

function sendEmail() {
    let userEmail = $("#email").val();
    serverGeneratedCode="";
    isEmailChecked=false;

    console.log(userEmail);
    if (userEmail==="") {
        // 이메일 입력 값이 비어있을 경우 처리
        $("#resultMessage").text("이메일을 입력해주세요.");
        return;
    }
    let sendEmail = {
        "email":userEmail
    }

    $.ajax({
        type: "POST",
        url: "/user/join/emailCheck", // 실제 서버의 엔드포인트 URL
        data: JSON.stringify(sendEmail), // 서버에 보낼 데이터 설정
        contentType: "application/json", // 데이터 타입 설정
    }).done(function (response){
        // 서버로부터 받은 인증 번호를 변수에 저장
        serverGeneratedCode = response.data;
        // console.log("인증코드: "+serverGeneratedCode);
    }).fail(function (request){
        console.log("status: " + request.status);
        console.log("responseText: " + request.responseText);
        console.log("error: " + request.error);
    });
}

// 서버에서 생성한 인증 번호를 이용해 서버와 통신하여 확인
function verifyCode() {
    let userEnteredCode = $("#verificationCodeInput").val(); // 사용자가 입력한 인증 번호

    if (userEnteredCode === serverGeneratedCode) {
        $("#resultMessage").text("인증 성공!");
        isEmailChecked = true;
    } else {
        $("#resultMessage").text("인증 실패!");
        isEmailChecked = false;
    }
}
// 아이디 체크
function idCheck() {
    let idCheck = $("#id").val();

    if (idCheck === "") {
            // 이메일 입력 값이 비어있을 경우 처리
        alert("아이디를 입력해주세요.");
            return;
    }
    let check = {
        "id":idCheck
    }

    $.ajax({
        type: "POST",
        url: "/user/join/idCheck",
        data: JSON.stringify(check),
        contentType: "application/json",
    }).done(function (response){
            // 서버로부터 받은 응답을 처리하는 코드
            console.log("ID:", response);
            if (response === "ID already exists") {
                // 이미 사용 중인 아이디 처리
                alert("아이디 중복!");
            } else {
                // 사용 가능한 아이디 처리
                alert("아이디 사용가능!");
            }
    }).fail(function (request){
        console.log("status: " + request.status);
        console.log("responseText: " + request.responseText);
        console.log("error: " + request.error);
    });
}