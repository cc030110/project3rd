$(document).ready(function() {
    // 입력 필드가 변경될 때 에러 클래스 및 에러 메시지 제거
    $('input').on('input', function() {
        $(this).removeClass('error');
        $(this).next('.err').hide()
    });

});

// 유효성 검사 함수
// 검사할 input 태그, 조건
function validateInput(input, condition) {
    // 유효한 정보가 아닐 경우
    if (!condition) {
        // 해당 input에 class="error" 추가
        input.addClass("error");
        // input 뒤에 있는 p.err 보여주기
        input.next('.err').show();
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
    const age = $('#age');
    const liveCountry = $('#liveCountry');
    const liveCity = $('#liveCity');
    // need-lang과 use-lang 선택 내용도 배열로 가져오고, 1개이상 필택 조건 유효성 확인 필요

    // id , password 조건
    // id혹은password.val().length >= 8 : 8자리 이상
    // /^[A-Za-z0-9]+$/.test(id.val()) : 영어 대소문자와 숫자로만 구성

    // id 유효성 검사
    let condition = id.val().length >= 8 && /^[A-Za-z0-9]+$/.test(id.val());
    const idValid = validateInput(id, condition);
    if(!idValid) return;

    // password 유효성 검사
    condition = password.val().length >= 8 && /^[A-Za-z0-9]+$/.test(password.val());
    const passwordValid = validateInput(password,condition);
    if(!passwordValid) return;

    // passwordChk 유효성 검사 (비밀번호 확인)
    condition = (passwordChk.val()===password.val());
    const passwordChkValid = validateInput(passwordChk,condition);
    if(!passwordChkValid) return;

    // name, email, liveCountry, liveCity 필수 입력사항
    const nameValid = validateInput(name, name.val()!== '');
    if(!nameValid) return;
    const emailValid = validateInput(email, email.val() !== '');
    if(!emailValid) return;
    const liveCountryValid = validateInput(liveCountry, liveCountry.val() !== '');
    if(!liveCountryValid) return;
    const liveCityValid = validateInput(liveCity, liveCity.val() !== '');
    if(!liveCityValid) return;

    // 여기까지 왔다면 통과!
    // 여기에 이제 ajax 만들면 됨
    // Map<String,Object>로 만들어서 넘길거임
    // key:"user"-value:UserRequestDto
    // key:"needLang"-value:String[] or List<String>
    // key:"useLang"-value:String[] or List<String>
    console.log("성공!");
    console.log("id : " + id.val());
    console.log("password : " + password.val());
    console.log("passwordChk : " + passwordChk.val());
    console.log("name : " + name.val());
    console.log("email : " + email.val());
    console.log("gender : " + gender.val());
    console.log("age : " + age.val());
    console.log("liveCountry : " + liveCountry.val());
    console.log("liveCity : " + liveCity.val());
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
    let selectedValue = $(this).val();
    let selectedText = $('option:selected', this).text();

    // 이미 선택된 값이 있는지 확인
    if (!$('.use-lang-box .'+selectedValue).length>0) {
        $('<span>').addClass(selectedValue).text(selectedText).appendTo('.use-lang-box');
    }
});

// 배울 언어 셀렉트 추가함수
$("#need-lang").change(function() {
    let selectedValue = $(this).val();
    let selectedText = $('option:selected', this).text();

    // 이미 선택된 값이 있는지 확인
    if (!$('.need-lang-box .'+selectedValue).length>0) {
        $('<span>').addClass(selectedValue).text(selectedText).appendTo('.need-lang-box');
    }
});

// 선택된 언어(span) 클릭 시 지우기
$(document).on("click", ".need-lang-box span", function() {
    $(this).remove();
});
$(document).on("click", ".use-lang-box span", function() {
    $(this).remove();
});