package com.example.movieshelf.domain.User;

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
@Table(name = "userlist")
@Entity
public class User {
    @Id
    private int user_no;

    @NonNull
    @Column
    private String user_name, user_id, user_pw, user_nickname;

    @Column
    private Timestamp user_regdate;
    private int user_resign;
    private Timestamp user_resigndate;

    public User(UserRequestDTO dto) {
        this.user_no = dto.getUser_no();
        this.user_id = dto.getUser_id();
        this.user_pw = dto.getUser_pw();
        this.user_nickname = dto.getUser_nickname();
        this.user_name = dto.getUser_name();
        this.user_regdate = dto.getUser_regdate();
    }

    public void update(UserRequestDTO userRequestDTO) {
        this.user_pw = userRequestDTO.getUser_pw();
        this.user_nickname = userRequestDTO.getUser_nickname();
        this.user_name = userRequestDTO.getUser_name();
        this.user_resign = userRequestDTO.getUser_resign();
        this.user_resigndate = userRequestDTO.getUser_resigndate();
    }
}
