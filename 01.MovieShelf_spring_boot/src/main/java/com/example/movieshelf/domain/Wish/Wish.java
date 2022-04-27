package com.example.movieshelf.domain.Wish;

import com.example.movieshelf.domain.User.UserRequestDTO;
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
@Table(name = "wishlist")
@Entity
public class Wish {
    @Id
    private int wish_no;

    @NonNull
    @Column
    private int movie_no;
    private String user_id, movie_name, comment;
    private Timestamp add_date;

    public Wish(WishRequestDTO wishRequestDTO) {
        this.wish_no = wishRequestDTO.getWish_no();
        this.movie_no = wishRequestDTO.getMovie_no();
        this.user_id = wishRequestDTO.getUser_id();
        this.movie_name = wishRequestDTO.getMovie_name();
        this.comment = wishRequestDTO.getComment();
        this.add_date = wishRequestDTO.getAdd_date();
    }

    public void update(WishRequestDTO wishRequestDTO) {
        this.comment = wishRequestDTO.getComment();
    }
}
