function getMovie() {
    const movies = [];
    const searchTitle = $('#search').val();
    // hidden에 숨겨 뒀다가 넘길 때 찾기

    $('#searchWord').val(searchTitle);

    if (searchTitle == "") {
        alert("검색어를 입력해주세요.");
    } else {
        $.ajax({
            method: "get",
            url: "/boardSearch/" + searchTitle
                + "&display=20",
            dataType: "json"
        }).done(

            function (data) {
                data.items.forEach(e => {
                    movies.push(e);
                });
                printResult(movies);

            }).fail(function () {
            alert("오류");
        });
    }
}

// 검색 값 별도 출력 및 링크
function printResult(movies) {
    $("#searchArea").empty();

    $('#searchArea').append(
        `
            <thead>
                <tr>
                    <th style="width:10%">포스터</th>
                    <th>제목</th>
                    <th>감독</th>
                </tr>
            </thead>
            <tbody id="searchResult">

            </tbody>
        `
    );

    $("#searchResult").empty();

    let idx = 0; // 검색해서 나온 JSONArr의 인덱스와 맞추기 위함
    movies.forEach(e => {
        const moviePoster = e.image;
        const movieTitle = e.title;
        const movieDirector = e.director;
        const director = movieDirector.replaceAll("|", "");

        let rMovieTitle = movieTitle;
        rMovieTitle = rMovieTitle.replace("<b>", "");
        rMovieTitle = rMovieTitle.replace("</b>", "");

        $("#searchResult").append(
            ` <tr class="selectMovie" onclick="movieSelectSubmit(${idx})">
                    <td> ${isImage(moviePoster, idx)} </td>
                    <td> ${rMovieTitle} </td>
                    <td> ${director} </td>
                </tr>`
        );
        idx++;
    });
}

function isImage(moviePoster, idx){
    console.log(idx);
    if(moviePoster !== ""){
        return `<img src="${moviePoster}" >`;
    }
    else{
        return `<p>포스터 없음</p>`;
    }
}

//  ${idx}, ${movieTitle}, ${moviePoster}
// 값 저장 제출
function movieSelectSubmit(idx){
    const word = $('#searchWord').val();

    console.log(idx);
    console.log(word);

    $('#idxHidden').val(idx);

    $('#movieSelect').submit(); // hidden객체에 title 숨겨놓기
}

const write = document.getElementById('write');
const submitSel = document.getElementById('submitButton');
submitSel.addEventListener('click', e=> {
    e.preventDefault();
    const movieName = $("#movieText").text(); // 내부에 내용
    const title = $('#title').val();
    const content = $('#content').val(); // textarea : val()
    const pw = $('#pw').val();

    if (movieName === "") {
        alert("먼저 영화를 검색/선택하세요.");
    } else if (title === "") {
        alert("제목을 입력하세요.");
        title.focus();
    } else if (content === "") {
        alert("내용을 입력하세요.");
        content.focus();
    } else if (pw === "") {
        alert("비밀번호를 입력하세요.");
        pw.focus();
    } else {
        write.submit();
    }
});





