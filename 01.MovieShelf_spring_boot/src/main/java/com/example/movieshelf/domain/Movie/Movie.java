package com.example.movieshelf.domain.Movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Table(name = "movielist")
@Entity
public class Movie {
    @Id
    private int movie_no;

    @NonNull
    @Column
    private String movie_name, movie_genre, movie_director, movie_mainactor, movie_poster,
            movie_link;
    private int movie_time;
    private float movie_score;
    private Timestamp movie_reldate;


    public Movie(MovieRequestDTO movieRequestDTO) {
        this.movie_no = movieRequestDTO.getMovie_no();
        this.movie_name = movieRequestDTO.getMovie_name();
        this.movie_genre = movieRequestDTO.getMovie_genre();
        this.movie_director = movieRequestDTO.getMovie_director();
        this.movie_mainactor = movieRequestDTO.getMovie_mainactor();
        this.movie_time = movieRequestDTO.getMovie_time();
        this.movie_score = movieRequestDTO.getMovie_score();
        this.movie_reldate = movieRequestDTO.getMovie_reldate();
        this.movie_poster = movieRequestDTO.getMovie_poster();
        this.movie_link = movieRequestDTO.getMovie_link();
    }

    public void update(MovieRequestDTO movieRequestDTO) {
        this.movie_time = movieRequestDTO.getMovie_time();
        this.movie_name = movieRequestDTO.getMovie_name();
        this.movie_genre = movieRequestDTO.getMovie_genre();
        this.movie_director = movieRequestDTO.getMovie_director();
        this.movie_mainactor = movieRequestDTO.getMovie_mainactor();
        this.movie_score = movieRequestDTO.getMovie_score();
        this.movie_poster = movieRequestDTO.getMovie_poster();
        this.movie_link = movieRequestDTO.getMovie_link();
    }


}
