// 선택 옵션에 따른 언어 변경 : locale 변경(/?lang='국가 코드')
$(document).ready(function() {
    let selLang = localStorage.getItem("locales");
    $('#locales').val(selLang ? selLang : 'en');
    $("#locales").change(function() {
        let selectedOption = $('#locales').val();
        if (selectedOption) {
            window.location.replace('?lang=' + selectedOption);
            localStorage.setItem("locales", selectedOption);
        }
    });
});

// 로그아웃
function logout() {
    $.ajax({
        type: 'DELETE',
        url: '/user/logout',    // Trouble Shooting : url 틀렸었음
        success: function(response) {
            // 로그아웃 성공 시 페이지 새로고침
            window.location.reload();
            console.log(response, "로그아웃 성공")
        },
        error: function(xhr, status, error) {
            console.error('로그아웃 실패:', error);
            console.error("xhr:", xhr);
            console.error("status:", status);
        }
    });
}
