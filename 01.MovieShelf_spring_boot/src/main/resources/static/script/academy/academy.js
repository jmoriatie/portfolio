const session = document.getElementById("session").value;

$(document).ready(
    function () {
        let movies = [];

        if ($("#r1").is(':checked')) {

            $.ajax({
                method: "get",
                url: "/movies/date"
            })
                .done(function (data) {
                    data.forEach(e => {
                        movies.push(e);
                    });
                    getMovies(movies);
                });
        }
    });

$("input#r1").click(function () {
    let movies = [];

    if ($("#r1").is(':checked')) {

        $.ajax({
            method: "get",
            url: "/movies/date"
        })
            .done(function (data) {
                data.forEach(e => {
                    movies.push(e);
                });
                getMovies(movies);
            });
    }
});

$("input#r2").click(function () {
    let movies = [];

    if ($("#r2").is(':checked')) {

        $.ajax({
            method: "get",
            url: "/movies/score"
        })
            .done(function (data) {
                data.forEach(e => {
                    movies.push(e);
                });
                getMovies(movies);
            });
    }
});

$("input#r3").click(function () {

    let movies = [];
    if ($("#r3").is(':checked')) {
        $.ajax({
            method: "get",
            url: "/movies/time"
        })
            .done(function (data) {
                data.forEach(e => {
                    movies.push(e);
                });
                getMovies(movies);
            });
    }
});


function getMovies(movies) {
    $("#movie").empty();
    movies.forEach(e => {
        const movie_poster = e.movie_poster;
        const movie_no = e.movie_no;
        const movie_director = e.movie_director;
        const movie_mainactor = e.movie_mainactor;
        const movie_name = e.movie_name;
        const movie_link = e.movie_link;
        const movie_score = e.movie_score;
        const movie_reldate = e.movie_reldate;
        const movie_time = e.movie_time;
        const movie_genre = e.movie_genre;
        const d = new Date(movie_reldate);

        if (session != "") {
            $("#movie").append(
                `
                    <tr>
                        <td><img src="${movie_poster}"/></td>
                        <td><a href="${movie_link}">${movie_name}</a></td>
                        <td>${movie_genre}</td>
                        <td>${movie_score}</td>
                        <td>${d.getFullYear()}-${(d.getMonth() + 1)}-${d.getDate()}</td>
                        <td>${movie_time} 분</td>
                        <td>${movie_director}</td>
                        <td>${movie_mainactor}</td>
                        <td>
                            <button class="btn btn-primary" onclick="location.href='/addWishFromAca/${movie_no}'">
                                +
                            </button>
                        </td>
                    </tr>
            `
            );
        } else {
            $("#movie").append(
                `
                    <tr>
                        <td><img src="${movie_poster}"/></td>
                        <td><a href="${movie_link}">${movie_name}</a></td>
                        <td>${movie_genre}</td>
                        <td>${movie_score}</td>
                        <td>${d.getFullYear()}-${(d.getMonth() + 1)}-${d.getDate()}</td>
                        <td>${movie_time} 분</td>
                        <td>${movie_director}</td>
                        <td>${movie_mainactor}</td>
                    </tr>
            `
            );
        }
    });

}