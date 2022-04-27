package com.example.movieshelf.service.MovieService;

import com.example.movieshelf.domain.Movie.Movie;
import com.example.movieshelf.domain.Movie.MovieRequestDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface MovieService {

    public Movie addMovie(MovieRequestDTO movieRequestDTO);
    public Movie getMovie(int code);
    public List<Movie> getMovies();
    public List<Movie> getMoviesByDate();
    public List<Movie> getMoviesByScore();
    public List<Movie> getMoviesByTime();
    public Movie getMovieByName(String name);
    public Movie updateMovie (int code,MovieRequestDTO movieRequestDTO);
    public int deleteMovie(int code);
}
