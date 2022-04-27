package com.example.movieshelf.domain.Wish;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class WishRequestDTO {
    private int wish_no,movie_no;
    private String user_id, movie_name, comment;
    private Timestamp add_date;

    public WishRequestDTO(int wish_no, String user_id, int movie_no, String movie_name, String comment, Timestamp add_date) {
        this.wish_no = wish_no;
        this.user_id = user_id;
        this.movie_no = movie_no;
        this.movie_name = movie_name;
        this.comment = comment;
        this.add_date = add_date;
    }

    public WishRequestDTO(String user_id,  int movie_no,String movie_name, Timestamp add_date) {
        this.user_id = user_id;
        this.movie_no = movie_no;
        this.movie_name = movie_name;
        this.add_date = add_date;
    }

    public WishRequestDTO(String user_id, String movie_name, Timestamp add_date) {
        this.user_id = user_id;
        this.movie_name = movie_name;
        this.add_date = add_date;
    }

    public void setComment(String comment){this.comment = comment;}

}
