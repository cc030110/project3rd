function searchUser(page){
    let selectedValue = $("#search-select").val();
    let searchText = $("#search-input").val();
    if (searchText===""){
        alert("검색어가 없습니다.");
        return;
    }
    let path = "/user/list/"+page;
    let query = "?" + selectedValue + "=" + searchText;
    window.location.href = path + query;
}