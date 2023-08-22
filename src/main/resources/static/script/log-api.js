// 로그인
$(document).ready(function() {
    $('#id').keydown(function (){
        const idVal = $('#id').val();
        if(idVal!==""){
            $('#id-empty').hide();
        }
    });

    $('#password').keydown(function (){
        const pwdVal = $('#password').val();
        if(pwdVal!==""){
            $('#password-empty').hide();
        }
    });
});

function login(){
    const idVal = $('#id').val();
    const pwdVal = $('#password').val();

    console.log("ID:", idVal);
    console.log("Password:", pwdVal);

    if(idVal===""){
        $('#id-empty').show();
        $('#id').focus();
    }else if(pwdVal===""){
        $('#password-empty').show();
        $('#password').focus();
    }else{
        let data = {
            "id" : idVal,
            "password" : pwdVal
        }
        $.ajax({
            url:'/user/login',
            type:'POST',
            data: JSON.stringify(data), // 데이터를 JSON 형식으로 변환하여 보냄
            contentType: 'application/json', // 데이터의 타입을 JSON으로 지정
        }).done(function (result){
            console.log(result);
            if(result!==""){
                let path = window.location.pathname.split('/');
                alert("로그인되었습니다.");
                if(path[2]==="login"){
                    window.location.href="/";
                }else{
                    window.location.reload();
                }
            }else{
                alert("아이디 혹은 비밀번호가 일치하지 않습니다.");
                window.location.href="/user/login";
            }
        }).fail(function (){
            alert("로그인 오류");
            window.location.href="/user/login";
        });
    }
}