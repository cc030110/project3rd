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
