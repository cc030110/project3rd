$(document).ready(function() {
    $("#deleteButton").click(function() {
        // 회원탈퇴 버튼 클릭 시 실행될 함수
        sendDeleteRequest();
    });

    function sendDeleteRequest() {
        $.ajax({
            url: "user/delete",
            type: "DELETE",
            dataType: "json",
            success: function(response) {
                if (response.delete === "success") {
                    // 회원탈퇴 성공 처리
                    alert("회원탈퇴가 완료되었습니다.");
                    // 홈페이지로 리다이렉트
                    window.location.href = "/"; // 원하는 페이지 경로로 수정
                } else {
                    // 회원탈퇴 실패 처리
                    alert("회원탈퇴에 실패하였습니다.");
                }
            },
            error: function() {
                // 서버 에러 처리
                alert("서버 에러가 발생하였습니다.");
            }
        });
    }
});