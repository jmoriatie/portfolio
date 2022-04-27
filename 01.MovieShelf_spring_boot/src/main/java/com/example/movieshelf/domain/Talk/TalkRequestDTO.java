package com.example.movieshelf.domain.Talk;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class TalkRequestDTO {
    private int talk_no, talk_likes;
    private String user_id, talk_title, talk_content, talk_password, movie_name, movie_poster;
    private Timestamp talk_regdate;

    public TalkRequestDTO(int talk_no, String user_id, String talk_password, String talk_title, String talk_content,
                          int talk_likes, Timestamp talk_regdate, String movie_name, String movie_poster) {
        this.talk_no = talk_no;
        this.user_id = user_id;
        this.talk_password = talk_password;
        this.talk_title = talk_title;
        this.talk_content = talk_content;
        this.talk_likes = talk_likes;
        this.talk_regdate = talk_regdate;
        this.movie_name = movie_name;
        this.movie_poster = movie_poster;
    }

    public TalkRequestDTO(String user_id, String talk_password, String talk_title, String talk_content, String movie_name, String movie_poster) {
        this.user_id = user_id;
        this.talk_password = talk_password;
        this.talk_title = talk_title;
        this.talk_content = talk_content;
        this.talk_likes = 0;
        this.talk_regdate = Timestamp.valueOf(LocalDateTime.now());
        this.movie_name = movie_name;
        this.movie_poster = movie_poster;
    }



    public void setTalk_title(String talk_title) {
        this.talk_title = talk_title;
    }


    public void setTalk_content(String talk_content) {
        this.talk_content = talk_content;
    }


    public void setTalk_likes(int talk_likes) {
        this.talk_likes = talk_likes;
    }


}
