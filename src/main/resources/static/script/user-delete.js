function deleteUser(){
    const password = $('#password').val();

    console.log(password);
    $.ajax({
        type: "delete",
        url: `/user/resign`,
        data:JSON.stringify(password),
        contentType: 'application/json',
        }).done(function (response){
            console.log(response);
            alert(response);
            if(response.delete==="success"){
                alert("회원 탈퇴 성공");
                logout();
                window.location.href = "/";
            }else{
                window.location.href = "/";
            }
        }).fail(function (){
            alert("회원 탈퇴 실패");
            window.location.href = "";
        });
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