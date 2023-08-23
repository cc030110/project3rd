let menu = $('#selected-menu').attr('id');
$(document).ready(function () {
    // 메뉴의 li 클릭 시
    $('#menu li').click(function () {
        // 클릭한 li 요소의 id를 가져옴
        let selectedId = $(this).attr('id');
        // #board는 제외, 기존과 다른 메뉴를 클릭했을 경우
        if (selectedId !== "board" && menu!==selectedId) {
            // 해당 클릭한 li의 text 내용 가져옴
            let selectedText = $(this).text();
            // selected-menu에 해당 내용 집어넣기
            $('#selected-menu').text(selectedText);
            // 게시글 조회가 아닌 다른 리스트 클릭 시 슬라이드 업
            if(selectedId!=="board-free" && selectedId!=="board-community"){
                $('#board-sub').slideUp('fast');
            }
            // 메뉴의 id 업데이트
            menu = selectedId;
            // content 내용 변화
            changeContent(menu);
        }
    });

    // 내 게시글 조회 메뉴 클릭 시
    $('#board').click(function (event) {
        $('#board-sub').slideToggle('fast');
    });
    $('#board-sub').click(function (event){
        event.stopPropagation();
    });

});

// 선택한 내용에 따라 정보 요청 후 content에 출력
function changeContent(menu){
    let content = $('#content');
    console.log("menu : "+menu);
    // content 비우기
    content.empty();
    content.removeClass();
    content.addClass(menu);

    $.ajax({
        'method' : 'GET',
        'url' : '/user/mypage/update',
    }).done(function (response){
        content.html(response);
    }).fail(function (){
        alert("불러오기 실패");
    });

}