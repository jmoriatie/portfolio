
$('body').append('<div class="search_test"></div>');
$('.search_test').append('<header></header>');
$('.search_test').append('<main></main>');

$('.search_test header').append('<div class="search"></div>');
$('.search').append('<p>BOOK SEARCH</p>');

$('.search').append('<form class="input_name"></form>');
$('.input_name').append('<input type="text" id="search-box">');
$('.input_name').append('<br><input type="submit" value="검색" id="name_submit">');

$('.search_test main').append('<div class="view"></div>');
$('.view').append('<section class="sec0"></section>'); // 여기에 결과들이 붙어야함
$('.view').append('<section class="sec1"></section>');
$('.view').append('<section class="sec2"></section>');

let books = [];

// 검색
document.querySelector('#name_submit').addEventListener('click', element => {
    element.preventDefault();
    delBookData();
    const inName = document.querySelector('#search-box').value;

    if (inName !== "") {
        getBooksData(inName, 1);
    }
    else {
        alert('입력하세요!!');
    }
});

function getBooksData(keyword, page) {
    let is_end = true;
    $.ajax({
        url: 'https://dapi.kakao.com/v3/search/book',
        host: 'dapi.kakao.com',
        method: 'GET',
        data: {
            query: keyword,
            size: 27,
            page: page
        },
        headers: {
            Authorization: 'KakaoAK c1b4339c26a7b747ee16c5c1657cae0a'
        }
    }).done(repBody => {
        if (repBody.documents.length !== 0) {
            console.log(repBody.documents.length);
            for(let i=0; i<repBody.documents.length; i++){
                books.push(repBody.documents[i]);
            }
        }
        else {
            alert('찾으시는 결과가 없습니다');
        }

        is_end = repBody.meta.is_end;
        console.log('is_end:', is_end); // 확인용

        if (is_end === false) {
            $('.sec0').text('loading...');
            return getBooksData(keyword, ++page);
        }
        else{
            $('.sec0').empty();
            setBookData(books);            
        }

    });
}


function setBookData(books) {
    delBookData();

    let num = 0;
    for (let i = 0; i < books.length; i++) {
        const data = books[i];
        
        let idx = i % 3; // 0 1 2
        
        $(`.sec${idx}`).append(`<article class="arti_class" id="arti${num}"></article>`);
        // console.log(`#arti${page}${i}`);
        
        // 이미지
        if (data.thumbnail === '') {
            $(`#arti${num}`).append(`<a href="${data.url}" ><img src="http://t1.daumcdn.net/friends/prod/editor/dc8b3d02-a15a-4afa-a88b-989cf2a50476.jpg"></a>`);
        }
        else {
            $(`#arti${num}`).append(`<a href="${data.url}"><img src="${data.thumbnail}"></a>`);
        }

        // 타이틀
        const title_len = data.title.length;
        console.log('books[i].title: ',books[i].title);
        console.log('title_len: ', title_len);

        if (title_len === 0) {
            $(`#arti$${num}`).append(`<a href="${data.url}"><p class="title">...</p></a>`);
        }
        else if (title_len < 11) {
            $(`#arti${num}`).append(`<a href="${data.url}"><p class="title">${data.title}</p></a>`);
        }
        else {
            const title_min = data.title.slice(0, 11); // 너무 많으면 ... 표시
            $(`#arti${num}`).append(`<a href="${data.url}"><p class="title">${title_min}...</p></a>`);
        }

        // 컨텐츠
        const string_len = data.contents.length;
        console.log('books[i].contents: ',books[i].contents);

        if (string_len === 0) {
            $(`#arti${num}`).append(`<p class="content">...</p>`);
        }
        else if (string_len < 25) {
            $(`#arti${num}`).append(`<p class="content">${data.contents}</p>`);
        }
        else {
            const contents_min = data.contents.slice(0, 25); // 너무 많으면 ... 표시
            $(`#arti${num}`).append(`<p class="content">${contents_min}...</p>`);
        }

        // 가격
        $(`#arti${num}`).append(`<p class="price">${data.price}원</p>`);
        num++;
    }
}

function delBookData() {
    $('.sec0').empty();
    $('.sec1').empty();
    $('.sec2').empty();
    books = [];
}