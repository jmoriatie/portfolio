// 시계(밀리초까지)가 달린, 디데이 카운터 만들기
// ㄴ html, js + css
// ㄴ 구글에서 'dday counter ui'(gui) 검색 -> 레퍼런스 선택 (이미지 선택)
// ㄴ 그대로 만들어보기
// ㄴ 이벤트 리스너 

// 기념일을 입력받고, 오늘을 기준으로 D-day 출력 ( +, - )

const inter1 = setInterval(currentTime, 1000);

const cur = document.querySelector('#curTime');
const p1 = document.createElement('p');
cur.append(p1);
p1.setAttribute('width', '500px');

const dday = document.querySelector('#dTime');
const p2 = document.createElement('p');
dday.append(p2);

const print = document.querySelector('#printC');
print.innerHTML = "디데이를 설정하세요";

const timeCon = document.querySelector('#time-con1');
const timeCon2 = document.querySelector('#time-con2');

// 초기 세팅 --------------------------------------
const firstTimeSet = new Date();

const year0 = firstTimeSet.getFullYear();
const month0 = firstTimeSet.getMonth();
const date0 = firstTimeSet.getDate();
const day0 = firstTimeSet.getDay();
const hour0 = firstTimeSet.getHours();
const minuten0 = firstTimeSet.getMinutes();
const second0 = firstTimeSet.getSeconds();

console.log("초기셋팅!");

p1.innerHTML = `${year0}년 ${month0}월 ${date0}일 
    ${hour0}시 ${minuten0}분 ${second0}초 ${dayChange(day0)}요일`;

p2.innerHTML = `${year0}년 ${month0}월 ${date0}일 
    ${hour0}시 ${minuten0}분 ${dayChange(day0)}요일`;


// 시간 선택
for (i = 1; i <= 24; i++) {
    const temp = document.createElement('option');
    temp.innerText = i + "";
    temp.setAttribute('class', 'time-conP')
    timeCon.append(temp);
    if (i === 1) {
        temp.setAttribute('selected', 'true');
    }
}
// 분 선택
for (i = 1; i <= 60; i++) {
    const temp = document.createElement('option');
    temp.innerText = i + "";
    temp.setAttribute('class', 'time-conP')
    timeCon2.append(temp);
    if (i === 1) {
        temp.setAttribute('selected', 'true');
    }
}
 
// ----------------------------------------------

timeCon.addEventListener('change', e=>{
    changeDate.setHours(parseInt(e.target.value));

    const year = changeDate.getFullYear();
    const month = changeDate.getMonth();
    const date1 = changeDate.getDate();
    const day = changeDate.getDay();
    const hour = changeDate.getHours();
    const minuten = changeDate.getMinutes();

    p2.innerHTML = `${year}년 ${month}월 ${date1}일 
    ${hour}시 ${minuten}분 ${dayChange(day)}요일`;
    
    alert(`${curDate.getFullYear()}년 ${curDate.getMonth()}월 ${curDate.getDate()}일(${dayChange(curDate.getDay())}요일)
    "${hour}시 ${minuten}분"으로 시간이 변경되었습니다`);
    console.log("시변경!");
});

timeCon2.addEventListener('change', e=>{
    changeDate.setMinutes(parseInt(e.target.value));
    
    const year = changeDate.getFullYear();
    const month = changeDate.getMonth();
    const date1 = changeDate.getDate();
    const day = changeDate.getDay();
    const hour = changeDate.getHours();
    const minuten = changeDate.getMinutes();

    p2.innerHTML = `${year}년 ${month}월 ${date1}일 
    ${hour}시 ${minuten}분 ${dayChange(day)}요일`;
    
    alert(`${curDate.getFullYear()}년 ${curDate.getMonth()}월 ${curDate.getDate()}일(${dayChange(curDate.getDay())}요일)
    "${hour}시 ${minuten}분"으로 시간이 변경되었습니다`);
    console.log("분변경!");
});


let curDate = new Date();
function currentTime() {
    curDate = new Date();

    const year = curDate.getFullYear();
    const month = curDate.getMonth();
    const date1 = curDate.getDate();
    const day = curDate.getDay();
    const hour = curDate.getHours();
    const minuten = curDate.getMinutes();
    const second = curDate.getSeconds();

    p1.innerHTML = `${year}년 ${month}월 ${date1}일 
    ${hour}시 ${minuten}분 ${second}초 ${dayChange(day)}요일`;
}

// 디데이 셋팅
const setDay = document.querySelector('#setDay');
let inter2;
let changeDate = new Date();
setDay.addEventListener('change', e => {
    changeDate = new Date(`${e.target.value}`);

    changeDate.setHours(0, 0);
    changeDate.setMinutes(0); /////////////////////////////
    const year = changeDate.getFullYear();
    const month = changeDate.getMonth();
    const date1 = changeDate.getDate();
    const day = changeDate.getDay();
    const hour = changeDate.getHours();
    const minuten = changeDate.getMinutes();

    console.log(changeDate.getMinutes());

    p2.innerHTML = `${year}년 ${month}월 ${date1}일 
    ${hour}시 ${minuten}분 ${dayChange(day)}요일`;
    console.log("디데이 변경!");
    alert(`"${curDate.getFullYear()}년 ${curDate.getMonth()}월 ${curDate.getDate()}일(${dayChange(curDate.getDay())}요일)"
    ${hour}시 ${minuten}분으로 D-DAY가 변경되었습니다`);    
    // Interval
    inter2 = setInterval(printDay, 1000);

    printDay();
})

function dayChange(day) {
    let temp = "";
    switch (day) {
        case 0: temp = "일"; break;
        case 1: temp = "월"; break;
        case 2: temp = "화"; break;
        case 3: temp = "수"; break;
        case 4: temp = "목"; break;
        case 5: temp = "금"; break;
        case 6: temp = "토"; break;
    }
    return temp;
}

const date2 = document.querySelector('#date');
const hour = document.querySelector('#hour');
const minuten = document.querySelector('#minuten');
const second = document.querySelector('#second');

// 남은(지난) 시간 초기셋팅 
clearPrintDay();

function printDay() {
    let gap = curDate - changeDate; // 안지난거
    print.innerHTML = "남았습니다";

    if (curDate > changeDate) { // 지난거
        gap = changeDate - curDate;
        print.innerHTML = "지났습니다";
    }

    const datee = Math.ceil(gap / (1000 * 60 * 60 * 24)); // 60분 *24시간
    const hourr = Math.ceil((gap / (1000 * 60 * 60))); // 60분
    const minutenn = Math.ceil(gap / (1000 * 60)); // 60초
    const secondd = Math.ceil(gap / 1000);

    date2.innerHTML = Math.abs(datee);
    hour.innerHTML = Math.abs(hourr - (datee * 24));  // 일로 표시된건 빼버리기 : 남은 일자 * 24시간
    minuten.innerHTML = Math.abs(minutenn - (hourr * 60));
    second.innerHTML = Math.abs(secondd - (minutenn * 60));

        // 디데이 됐을 때
        if(changeDate - curDate >= -1000 && changeDate - curDate <= 1000){
            clearInterval(inter2);
            alert("디데이!!!!!!!!!!!");
            clearPrintDay();
            console.log("Interval 종료");
            location.reload();
        }
}

function clearPrintDay(){
    date2.innerHTML = 0;
    hour.innerHTML = 0;
    minuten.innerHTML = 0;
    second.innerHTML = 0;
}
