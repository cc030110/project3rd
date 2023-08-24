function deleteUser(id,password){
    const passwordchk = $('#password').val();

    if(password===passwordchk){
        let data = {
            "id":id,
            "password":password
        }
        $.ajax({
            type: "delete",
            url: `/user/resign`,
            data:JSON.stringify(data),
            contentType: 'application/json',
        }).done(function (response){
            if(response.value==="success"){
                alert("회원 탈퇴 완료");
                logout();
                window.location.href = "/";
            }else{
                window.location.href = "/";
            }
        }).fail(function (){
            alert("회원 탈퇴 실패");
            window.location.href = "";
        });
    }else{
        alert("비밀번호가 일치하지 않습니다.");
    }


}

// 로그아웃
function logout() {
    $.ajax({
        type: 'DELETE',
        url: '/user/logout',    // Trouble Shooting : url 틀렸었음
        success: function(response) {
            // 로그아웃 성공 시 페이지 새로고침
            window.location.href = '/';
        },
        error: function(xhr, status, error) {
            console.error('로그아웃 실패:', error);
            console.error("xhr:", xhr);
            console.error("status:", status);
        }
    });
}