package com.example.movieshelf.domain.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

    @Query("select m from Movie m order by m.movie_reldate desc")
    List<Movie> findMovieByRecentDate();

    @Query("select m from Movie m order by m.movie_score desc")
    List<Movie> findMovieByScore();

    @Query("select m from Movie m order by m.movie_time")
    List<Movie> findMovieByTime();

    @Query("Select m from Movie m where m.movie_name like ?1")
    List<Movie> findMovieByName(String name);
}
