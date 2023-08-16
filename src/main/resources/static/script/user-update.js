
function checkValue(htmlForm) {
    const id = htmlForm.id.value;
    const password = htmlForm.password.value;
    const name = htmlForm.name.value;
    const gender = htmlForm.gender.value;
    const age = htmlForm.age.value;
    const profileImg = htmlForm.profileImg.value;
    const email = htmlForm.email.value;
    const liveCountry = htmlForm.liveCountry.value;
    const liveCity = htmlForm.liveCity.value;
    const warningCount = htmlForm.warningCount.value;
    const isActive = htmlForm.isActive.value;



    let check = true;

    // 각 필드에 대한 조건 검사 추가
    if (password.length < 6) {
        alert("Password must be at least 6 characters.");
        check = false;
    }

    if (age <= 0 || age >= 100) {
        alert("Age must be between 1 and 99.");
        check = false;
    }

    if (liveCountry.trim() === "") {
        alert("Living Country cannot be empty.");
        check = false;
    }

    if (liveCity.trim() === "") {
        alert("Living City cannot be empty.");
        check = false;
    }

    // 모든 조건이 만족할 때 폼 제출
    if (check === true) {
        htmlForm.submit();
    }
}