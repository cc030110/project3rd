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
    console.log(id);
    console.log(password);
    console.log(name);
    console.log(gender);
    console.log(age);
    console.log(profileImg);
    console.log(email);
    console.log(liveCountry);
    console.log(liveCity);
    console.log(warningCount);
    console.log(isActive);


    let check = true;

    if(check===true){
        htmlForm.submit();
    }
}