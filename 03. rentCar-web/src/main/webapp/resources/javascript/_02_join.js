// 타이틀 누르면 링크이동
document.querySelector('#title').addEventListener('click', e=>{
    location.href = '_00_index.jsp';
});

const submitBtn = document.querySelector('#submitBtn');

submitBtn.addEventListener('click', e=>{
    e.preventDefault();
    
    let cnt = 0;
    // 9개
    if(id_validate()) cnt++;
    if(pw_validate()) cnt++;
    if(pw_check_validate()) cnt++;
    if(email_validate()) cnt++;
    if(tel_validate()) cnt++;
    if(hobby_validate()) cnt++;
    if(job_validate()) cnt++;
    if(info_validate()) cnt++;
    if(age_validate()) cnt++;

    if(cnt === 9){
        // 다 true 나오면, 데이터 다 담아서 이동
        document.querySelector('#joinForm').submit();
    }
    else{
        // location.href = '_02_join.jsp';
        alert('문제있음!');
    }
});

function id_validate(){
    const id = document.querySelector('#id').value;
    const id_message = document.querySelector('#id_message');
    console.log('id: ',id);

    if(id === ''){
        id_message.innerHTML = '아이디를 입력하세요';
        return false;
    }else{
        id_message.innerHTML = '';
        return true;
    }
}

function pw_validate(){
    const pw = document.querySelector('#pw1').value;
    const pw_message = document.querySelector('#pw_message');
    console.log('pw: ',pw);

    if(pw === ''){
        pw_message.innerHTML = '비밀번호를 입력하세요';
        return false;
    }else{
        pw_message.innerHTML = '';
        return true;
    }
}

function pw_check_validate(){
    const pw_check = document.querySelector('#pw_check').value;
    const pw = document.querySelector('#pw1').value;
    const pw_check_message = document.querySelector('#pw_check_message');
    console.log('pw_check: ', pw_check);

    if(pw.valueOf() !== pw_check.valueOf()){
        pw_check_message.innerHTML = '비밀번호가 일치하지 않습니다';
        return false;
    }
    else{
        pw_check_message.innerHTML = '';
        return true;
    }
}

function email_validate(){
    const email = document.querySelector('#email').value;
    const email_message = document.querySelector('#email_message');
    console.log('email: ',email);

    if(email === ''){
        email_message.innerHTML = '이메일을 입력해주세요';
        return false;
    }else{
        email_message.innerHTML = '';
        return true;
    }
}
function tel_validate(){
    const tel = document.querySelector('#tel').value;
    const tel_message = document.querySelector('#tel_message');
    
    const tel_parse = parseInt(tel);

    console.log('tel: ',tel);
    console.log('tel_message_parse: ',tel_parse);
    
    const telTest = /^1([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
    if(isNaN(tel_parse) || !telTest.test(tel_parse)){
        tel_message.innerHTML = '전화번호를 확인하세요';
        return false;
    }else{
        tel_message.innerHTML = '';
        return true;
    }
}

function hobby_validate(){
    const hobby = document.querySelector('#hobby').value;
    const hobby_message = document.querySelector('#hobby_message');
    console.log('hobby: ',hobby);

    if(hobby === ''){
        hobby_message.innerHTML = '취미를 입력하세요';
        return false;
    }else{
        hobby_message.innerHTML = '';
        return true;
    }
}

function job_validate(){
    const job = document.querySelector('#job').value;
    const job_message = document.querySelector('#job_message');
    console.log('job: ',job);

    if(job === ''){
        job_message.innerHTML = '직업을 입력하세요';
        return false;
    }else{
        job_message.innerHTML = '';
        return true;
    }
}

function info_validate(){
    const info = document.querySelector('#info').value;
    const info_message = document.querySelector('#info_message');
    console.log('info: ',info);

    if(info === ''){
        info_message.innerHTML = '한줄소개를 입력하세요';
        return false;
    }else{
        info_message.innerHTML = '';
        return true;
    }
}

function age_validate(){
    const age = document.querySelector('#age').value;
    const age_message = document.querySelector('#age_message');
    console.log('age: ',age);

    if(age === ''){
        age_message.innerHTML = '나이를 입력하세요';
        return false;
    }else{
        age_message.innerHTML = '';
        return true;
    }
}

