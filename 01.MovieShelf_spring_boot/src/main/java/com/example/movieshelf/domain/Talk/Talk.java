package com.example.movieshelf.domain.Talk;

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
@Table(name = "talklist")
@Entity
public class Talk {
    @Id
    private int talk_no;

    @NonNull
    @Column
    private String user_id,talk_title, talk_content,talk_password, movie_name, movie_poster;
    private Timestamp talk_regdate;

    @Column
    private int talk_likes;

    public Talk(TalkRequestDTO talkRequestDTO){
        this.talk_no = talkRequestDTO.getTalk_no();
        this.user_id = talkRequestDTO.getUser_id();
        this.talk_password = talkRequestDTO.getTalk_password();
        this.talk_title = talkRequestDTO.getTalk_title();
        this.talk_content = talkRequestDTO.getTalk_content();
        this.talk_likes = talkRequestDTO.getTalk_likes();
        this.talk_regdate = talkRequestDTO.getTalk_regdate();
        this.movie_name = talkRequestDTO.getMovie_name();
        this.movie_poster = talkRequestDTO.getMovie_poster();
    }

    public void update(TalkRequestDTO talkRequestDTO){
        this.talk_title = talkRequestDTO.getTalk_title();
        this.talk_content = talkRequestDTO.getTalk_content();
        this.talk_likes = talkRequestDTO.getTalk_likes();
    }

}
