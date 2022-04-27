package com.example.movieshelf.domain.Movie;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class MovieRequestDTO {
    private int movie_no, movie_time;
    private String movie_name, movie_genre, movie_director, movie_mainactor, movie_poster,
            movie_link;
    private float movie_score;
    private Timestamp movie_reldate;

    public MovieRequestDTO(int movie_no, String movie_name, float movie_score, int movie_time, Timestamp movie_reldate,
                        String movie_genre, String movie_director, String movie_mainactor, String movie_poster,String movie_link) {
        this.movie_no = movie_no;
        this.movie_time = movie_time;
        this.movie_name = movie_name;
        this.movie_genre = movie_genre;
        this.movie_director = movie_director;
        this.movie_mainactor = movie_mainactor;
        this.movie_score = movie_score;
        this.movie_reldate = movie_reldate;
        this.movie_poster = movie_poster;
        this.movie_link = movie_link;
    }

    public void setMovie_time(int movie_time) {
        this.movie_time = movie_time;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public void setMovie_genre(String movie_genre) {
        this.movie_genre = movie_genre;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }

    public void setMovie_mainactor(String movie_mainactor) {
        this.movie_mainactor = movie_mainactor;
    }

    public void setMovie_score(float movie_score) {
        this.movie_score = movie_score;
    }

    public void setMovie_poster(String movie_poster) {
        this.movie_poster = movie_poster;
    }

    public void setMovie_link(String movie_link) {
        this.movie_link = movie_link;
    }
}
