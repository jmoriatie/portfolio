package com.example.movieshelf.service.MovieService;


import com.example.movieshelf.domain.Movie.Movie;
import com.example.movieshelf.domain.Movie.MovieRepository;
import com.example.movieshelf.domain.Movie.MovieRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository repo;
    
    // Create
    public Movie addMovie(MovieRequestDTO movieRequestDTO){
        Movie movie = new Movie(movieRequestDTO);
        return repo.save(movie);
    }

    // Read
    public Movie getMovie(int code){
        return repo.findById(code).orElseThrow(
                // 람다식
                () -> new IllegalArgumentException("존재하지 않는 영화입니다.")
        );
    }


    public List<Movie> getMovies(){
        return repo.findAll();
    }

    public List<Movie> getMoviesByDate(){
        return repo.findMovieByRecentDate();
    }

    public List<Movie> getMoviesByScore(){
        return repo.findMovieByScore();
    }

    public List<Movie> getMoviesByTime(){
        return repo.findMovieByTime();
    }

    public Movie getMovieByName(String name){
        List<Movie> movie = repo.findMovieByName(name);
        if(movie.size()!=0){
            return movie.get(0);
        }
        return null;
    }

    @Transactional
    public Movie updateMovie (int code,MovieRequestDTO movieRequestDTO){
        Movie movie = getMovie(code);
        movie.update(movieRequestDTO);
        return movie;
    }

    @Transactional
    public int deleteMovie(int code){
        Movie movie = getMovie(code);
        repo.deleteById(movie.getMovie_no());
        return movie.getMovie_no();
    }
}
