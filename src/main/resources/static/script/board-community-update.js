$(document).ready(function(){
    // DB에서 가져온 값
    let dbValue=$("#platform_name");

    $(".platform input[type='radio']").each(function(){
        let radioValue=$(this).val();
        if(dbValue===radioValue){
            $(this).prop("checked",true);
        }
    })
});

