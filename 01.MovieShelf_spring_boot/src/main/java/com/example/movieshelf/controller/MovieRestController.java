package com.example.movieshelf.controller;

import com.example.movieshelf.domain.Movie.APIRequestDTO;
import com.example.movieshelf.domain.Movie.Movie;
import com.example.movieshelf.domain.Movie.MovieRequestDTO;
import com.example.movieshelf.service.MovieService.MovieService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MovieRestController {
    private final MovieService service;


    @PostMapping("/movie/addMovie")
    public Movie addMovie(@RequestBody MovieRequestDTO movieRequestDTO) {
        return service.addMovie(movieRequestDTO);
    }

    @GetMapping("/movie/getMovie/{code}")
    public Movie getMovie(@PathVariable int code) {
        return service.getMovie(code);
    }

    @GetMapping("/movie/getMovie/{movie_no}")
    public ArrayList<Movie> getMovieNoList(@PathVariable int movie_no){
        List<Movie> movies = service.getMovies();
        ArrayList<Movie> sendMovies = new ArrayList<Movie>();
        for(Movie m : movies){
            if(m.getMovie_no() == movie_no){
                sendMovies.add(m);
            }
        }
        return sendMovies;
    }


    @GetMapping("/movie/getMovies")
    public List<Movie> getMovies() {
        return service.getMovies();
    }

    @PutMapping("/movie/update/{code}")
    public Movie updateMovie(@PathVariable int code, @RequestBody MovieRequestDTO movieRequestDTO) {
        return service.updateMovie(code, movieRequestDTO);
    }

    @DeleteMapping("/movie/delete/{code}")
    public int deleteMovie(@PathVariable int code) {
        return service.deleteMovie(code);
    }

    @GetMapping("/movies/date")
    public List<Movie> getMoviesByDate() {
        return service.getMoviesByDate();
    }

    @GetMapping("/movies/score")
    public List<Movie> getMoviesByScore() {
        return service.getMoviesByScore();
    }

    @GetMapping("/movies/time")
    public List<Movie> getMoviesByTime() {
        return service.getMoviesByTime();
    }

    @GetMapping("/searchResultJson/{name}")
    public JSONObject getSearchJsonResult(@PathVariable String name) throws ParseException {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "IHlnAD4_LEMbS_rg0qbb");
        headers.add("X-Naver-Client-Secret", "9sDt2YKXk3");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/movie.json?query="+name, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        JSONParser jsonParser = new JSONParser(); //  문자열을 Json 형식에 맞게 Object로 파싱할 수 있는 Parser를 생성
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        Object obj = jsonParser.parse(response); // jsonParser를 통해 Json 문자열을 Object 형식으로 파싱
        JSONObject jsonObj = (JSONObject) obj; // Object 형식의 데이터를 JSONObject 형식으로 형변환
//        System.out.println("Response status: " + status);
//        System.out.println(jsonObj);

        return jsonObj;
    }


    @GetMapping("/searchResult/{name}")
    public List<APIRequestDTO> getSearchListResult(@PathVariable String name) throws ParseException {
        List<APIRequestDTO> searchResult = null;
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "IHlnAD4_LEMbS_rg0qbb");
        headers.add("X-Naver-Client-Secret", "9sDt2YKXk3");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/movie.json?query="+name, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        JSONParser jsonParser = new JSONParser(); //  문자열을 Json 형식에 맞게 Object로 파싱할 수 있는 Parser를 생성
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        Object obj = jsonParser.parse(response); // jsonParser를 통해 Json 문자열을 Object 형식으로 파싱
        JSONObject jsonObj = (JSONObject) obj; // Object 형식의 데이터를 JSONObject 형식으로 형변환
        JSONObject items = (JSONObject) jsonObj.get("items");

        System.out.println("Response status: " + status);
        System.out.println(jsonObj);

        return searchResult;
    }
}
