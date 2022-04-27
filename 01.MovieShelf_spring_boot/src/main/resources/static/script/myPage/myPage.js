let checkPw = false;
let checkName = false;
let checkNickName = false;

let pwInput = document.getElementById('pw');
let nameInput = document.getElementById('name');
let nicknameInput = document.getElementById('nick_name');

let pPw = document.getElementById('pPw');
let pName = document.getElementById('pName');
let pNickname = document.getElementById('pNickname');

$(document).ready(
    function(){
        if(pwInput.value != ""){
            pPw.innerHTML = "";
            checkPw = true;
        }
        if (nameInput.value != "") {
            pName.innerHTML = "";
            checkName = true;
        }

        if(nicknameInput.value != ""){
            pNickname.innerHTML = "";
            checkNickName = true;
        }

    }
);
$(pwInput).focusout(function () {
    if (pwInput.value == "") {
        pPw.innerHTML = "필수 항목입니다.";
        checkPw = false;
    } else if (pwInput.value.length < 6) {
        console.log(pwInput.value.length);
        pPw.innerHTML = "비밀번호는 6자리 이상입니다.";
        checkPw = false;
    } else {
        pPw.innerHTML = "";
        checkPw = true;
    }
});
$(nameInput).focusout(function () {
    if (nameInput.value == "") {
        pName.innerHTML = "필수 항목입니다.";
        checkName = false;
    } else {
        pName.innerHTML = "";
        checkName = true;
    }
});

$(nicknameInput).focusout(function () {
    if (nicknameInput.value == "") {
        pNickname.innerHTML = "필수 항목입니다.";
        checkNickName = false;
    } else {
        pNickname.innerHTML = "";
        checkNickName = true;
    }
});

function checkUpdate(form){
    let pw = form.pw.value;
    let name = form.name.value;
    let nickname = form.nick_name.value;
    let uNo = form.uNo.value;
    if(pw == ""){
        form.pw.focus();
        pPw.innerHTML = "필수 항목입니다.";
        checkPw = false;
    }
    if(name == ""){
        form.name.focus();
        pName.innerHTML="필수 항목입니다.";
        checkName = false;
    }
    if(nickname == ""){
        form.nick_name.focus();
        pNickname.innerHTML="필수 항목입니다.";
        checkNickName = false;
    }
    if(checkPw && checkName && checkNickName){
        const data ={
            "user_pw" : pw,
            "user_name" : name,
            "user_nickname" : nickname
        };
        $.ajax({
            method:"put",
            url:"/user/update/"+uNo,
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data)
        }).done(response=>{
            alert("수정 되셨습니다.");
            location.href="/main/Identification";
            });
    }

}