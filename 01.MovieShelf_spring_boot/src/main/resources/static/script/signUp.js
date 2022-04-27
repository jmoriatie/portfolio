let checkId = false;
let checkPw = false;
let checkName = false;
let checkNickName = false;

let idInput = document.getElementById('idInput');
let pwInput = document.getElementById('pwInput');
let nameInput = document.getElementById('nameInput');
let nicknameInput = document.getElementById('nicknameInput');

let pId = document.getElementById('pId');
let pPw = document.getElementById('pPw');
let pName = document.getElementById('pName');
let pNickname = document.getElementById('pNickname');


$(idInput).focusout(function () {
    checkUserId(idInput.value);
});

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



function checkUserId(idValue) {
    const data = {
        "user_id": idValue
    };
    $.ajax({
        method: "post",
        url: "/user/checkUser",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(data)
    }).done(response => {
        pId.innerHTML = "이미 사용중이거나 탈퇴한 회원입니다.";
    })
        .fail(function () {
            if (idValue == "") {
                pId.innerHTML = "필수 입니다.";
                checkId = false;
            } else if (idValue.length < 8) {
                pId.innerHTML = "ID는 8자리 이상 입니다.";
                checkId = false;
            } else {
                pId.innerHTML = "사용가능한 ID입니다.";
                checkId = true;
            }
        });
}

function signUpUser(form) {
    let id = form.id.value;
    let pw = form.pw.value;
    let name = form.name.value;
    let nickname = form.nickname.value;
    if (checkId && checkPw && checkName && checkNickName){

        const data = {
            "user_id": id,
            "user_pw": pw,
            "user_nickname": nickname,
            "user_name": name
        };
        $.ajax({
            method: "post",
            url: "/user/addUser",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(data)
        }).done(response => {
            alert("회원가입 되셨습니다");
            location.href = "/";
        });
    }
}

