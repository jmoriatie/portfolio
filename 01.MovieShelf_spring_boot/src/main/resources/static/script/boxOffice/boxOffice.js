$(document).ready(function () {
    getFullData();
});

let date = new Date();

let y = date.getFullYear();
let year = y + "";
let month = "";
let day = "";

let m = date.getMonth() + 1;


if (m < 10) {
    month = "0" + m;
} else {
    month = m + "";
}

let d = date.getDate() - 1;
if (d < 10) {
    day = "0" + d;
} else {
    day = d + "";
}

let resultDate = year + month + day;

let key = "b7e3c237a8a4570c03e27bf1b7e2d371";

function getFullData() {
    // API done 한 메서드에서만 사용가능한 걸로 판단됨
    let movies = []; // top10 저장되어있음
    let movieCds = []; // top10 영화 프라이머리 키(movieCd)들 저장됨
    let movieInfos = []; // top10 영화들의 상세정보들 저장됨
    let printMovies = []; // 실제 출력할 영화 정보들을 저장

    // Top10 API를 리턴한 메서드를 받아 영화정보 movies에 저장
    getMoviesAPI().done(function (msg) {
        // 영화들 json
        msg.boxOfficeResult.dailyBoxOfficeList.forEach(e => {
            movies.push(e);
        });
    });
    // console.log(movies);

    // movieCd를 movieCds 리스트에 담아둠
    movies.forEach(e => {
        movieCds.push(e.movieCd);
    })

    // 코드정보를 영화 상세정보들 movieInfos에 저장
    movieCds.forEach(e => {
        getMovieInfo(e).done(function (data) {
            // 해당 코드를 가진 영화정보 넣기
            movieInfos.push(data.movieInfoResult.movieInfo);
        });
    });
    // console.log(movieInfos);

    // 네이버 api를 통해 영화를 검색하고 불러오는 메서드
    movieInfos.forEach(e => {
        const movieTitle = e.movieNm;
        const productionYear = e.prdtYear;
        const idx = movieInfos.indexOf(e);
        // console.log("kobis: ", title,"/",productionYear, "/",director);

        getSearchResult(movieTitle).done(function (data) {
            // 받아온 값을 비교
            data.items.forEach(e => {
                let nvrtitle = e.title;
                const nvrProductionYear = e.pubDate;

                // 영화제목 정규식
                nvrtitle = nvrtitle.replace("<b>", "");
                nvrtitle = nvrtitle.replace("</b>", "");

                // 일치하면 집어넣기
                if (movieTitle === nvrtitle && productionYear === nvrProductionYear) {
                    const movie = {
                        rank: movies[idx].rank, // top10 배열꺼
                        image: e.image, // 영화상세정보꺼
                        title: nvrtitle, // 네이버꺼
                        openDate: movies[idx].openDt,
                        audiCnt: parseInt(movies[idx].audiCnt).toLocaleString('ko-KR'),
                        totalAudiCnt: parseInt(movies[idx].audiAcc).toLocaleString('ko-KR')
                    }
                    printMovies.push(movie);
                    // console.log("naver: ", nvrtitle,"/",nvrProductionYear, "/",nvrDirector);
                }
            });
        });
    });

    // 출력
    printMovies.forEach(e => {
        $("#boxOffice").append(
            `
				<tr>
                    <td>${e.rank}</td>
                    <td>${isImage(e.image)}</td>
				    <td>${e.title}</td>
				    <td>${isRelease(e.openDate)}</td>
				    <td>+ ${e.audiCnt} </td>
				    <td>${e.totalAudiCnt} </td>
                </tr>
			`
        );
    });
};

// 포스터 있는지 식별 메서드
function isImage(image) {
    if (image !== "") {
        return `<img src="${image}">`;
    } else {
        return '(포스터 없음)';
    }
}

// 개봉일 없는 것 식별 메서드
function isRelease(releaseDate) {
    if (releaseDate !== " " && releaseDate !== "") {
        return releaseDate;
    } else {
        return '(미개봉 영화)';
    }
}

// kobis 영화 TOP10 API
function getMoviesAPI() {
    return $.ajax({
        async: false,
        method: 'GET',
        url: "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="
            + key
            + "&targetDt="
            + resultDate
            + "&itemPerPage=10",
        dataType: "JSON"
    });
}

// kobis 무비 코드를 받아 영화 상세정보 반환하는 API
function getMovieInfo(movieCd) {
    return $.ajax({
        method: "GET",
        async: false,
        url: "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json"
            + "?key=" + key
            + "&&movieCd=" + movieCd,
        dataType: "json"
    });
}

// naver 영화검색 api
function getSearchResult(title) {
    return $.ajax({
        method: "get",
        async: false,
        url: "/searchResultJson/" + title,
        dataType: "json"
    });

}



