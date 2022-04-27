const session = document.getElementById("session").value;

function getSearchResult() {
    let movies = [];
    const title = $('#movieSearch').val();

    if (title == "") {
        alert("제목을 입력해주세요.");
    } else {
        $.ajax({
            method: "get",
            url: "/searchResultJson/" + title
                + "&display=25",
            dataType: "json"
        }).done(function (data) {

            data.items.forEach(e => {
                movies.push(e);
            });
            printResult(movies);
        }).fail(function () {
            alert("오류");
        });
    }
}


function printResult(movies) {
    console.log(movies.length);
    $("#searchArea").empty();

    $('#searchArea').append(
        `
            <thead>
                <tr>
                    <th style="width:10%">포스터</th>
                    <th>영화제목</th>
                    <th>제작일</th>
                    <th>감독</th>
                    <th>평점</th>
                    <th>찜하기</th>
                </tr>
            </thead>
            <tbody id="searchResult">

            </tbody>
        `
    );

    $("#searchResult").empty();
    movies.forEach(e => {
        const movieTitle = e.title;
        let movieTitleReplaced = movieTitle;
        // 정규식
        movieTitleReplaced = movieTitleReplaced.replace("<b>", "");
        movieTitleReplaced = movieTitleReplaced.replace("</b>", "");
        const moviePubDate = e.pubDate;
        const movieDirector = e.director;
        let movieDirectorReplaced = movieDirector;
        movieDirectorReplaced = movieDirectorReplaced.replace("|", "");
        const movieUserRating = e.userRating;
        const moviePoster = e.image;
        const movieLink = e.link;
        if (session != "") {
            $("#searchResult").append(
                `
                <tr>
                    <td><img class="image" src="${moviePoster}"/></td>
                    <td><a href="${movieLink}">${movieTitle}</a></td>
                    <td>${moviePubDate}</td>
                    <td>${movieDirectorReplaced}</td>
                    <td>${movieUserRating}</td>
                     <td>
                            <button class="btn btn-primary" onClick="location.href='/addWishFromSearch/${movieTitleReplaced}'">
                                +
                            </button>
                        
                    </td>
                </tr>
        `)
        } else {
            $("#searchResult").append(
                `
                <tr>
                    <td><img class="image" src="${moviePoster}"/></td>
                    <td><a href="${movieLink}">${movieTitle}</a></td>
                    <td>${moviePubDate}</td>
                    <td>${movieDirectorReplaced}</td>
                    <td>${movieUserRating}</td>
                </tr>
                `
            );
        }
    });
}