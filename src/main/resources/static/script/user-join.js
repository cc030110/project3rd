function joinForm() {
    const id = $('#id').val();
    const password = $('#password').val();
    const passwordChk = $('#password-chk').val();
    const name = $('#name').val();
    const email = $('#email').val();
    const gender = $('.gender').val();
    const age = $('#age').val();
    const liveCountry = $('#liveCountry').val();
    const liveCity = $('#liveCity').val();

    const useSel = document.getElementsByClassName("use-sel");
    const wishSel = document.getElementsByClassName("wish-sel");

    const useLang = [];
    const needLang = [];

    for (var i = 0; i < useSel.length; i++) {
        useLang.push(useSel[i].textContent);
    }
    for (var i = 0; i < wishSel.length; i++) {
        needLang.push(wishSel[i].textContent);
    }

    console.log(useLang);
    console.log(needLang);

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
            "email":email,
            "liveCountry":liveCountry,
            "liveCity":liveCity,
            "useLang":useLang,
            "needLang":needLang
        }
        console.log(join);

        $.ajax({
            method:'POST',
            url:'/user/join',
            data: JSON.stringify(join),
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




// 사용하는 언어 셀렉트 추가함수
$("#use-lang").change(function() {
    let selectedValue = $(this).val();

    // 이미 선택된 값인지 확인
    let isDuplicate = $(".use-lang-box").find(".sel[value='" + selectedValue + "']").length > 0;

    if (!isDuplicate) {
        // 선택한 옵션의 값을 포함하는 <div> 생성
        var newDiv = $("<div>", {
            "class": "use-sel",
            "id": selectedValue,
            "value": selectedValue,
            html: selectedValue + '<img src=""/>'
        });

        // 생성한 <div>를 #sel-box에 추가
        $(".use-lang-box").append(newDiv);
    }
});

// 배울 언어 셀렉트 추가함수
$("#wish-lang").change(function() {
    let selectedValue = $(this).val();

    // 이미 선택된 값인지 확인
    let isDuplicate = $(".wish-lang-box").find(".sel[value='" + selectedValue + "']").length > 0;

    if (!isDuplicate) {
        // 선택한 옵션의 값을 포함하는 <div> 생성
        var newDiv = $("<div>", {
            "class": "wish-sel",
            "id": selectedValue,
            "value": selectedValue,
            html: selectedValue + '<img src=""/>'
        });

        // 생성한 <div>를 #sel-box에 추가
        $(".wish-lang-box").append(newDiv);
    }
});