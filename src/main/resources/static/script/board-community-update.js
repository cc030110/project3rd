$(document).ready(function(){
    // Radio check : DB에서 가져온 값
    let dbValue=$("#platform_name_hidden").val();

    $(".platform input[type='radio']").each(function(){
        let radioValue=$(this).val();
        if(dbValue === radioValue){
            $(this).prop("checked",true);
        }
    })


});

function back(){
    let boardNo = $('#board_num_hidden').val();

    window.location.href='/board/community/'+boardNo;
}

