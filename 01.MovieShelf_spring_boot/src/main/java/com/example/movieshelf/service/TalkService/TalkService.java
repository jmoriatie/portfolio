package com.example.movieshelf.service.TalkService;

import com.example.movieshelf.domain.Talk.Talk;
import com.example.movieshelf.domain.Talk.TalkRequestDTO;

import java.util.List;

public interface TalkService {
    public Talk addTalk(TalkRequestDTO talkRequestDTO);
    public Talk getTalk(int talk_no);
    public List<Talk> getTalks();
    public Talk updateTalk(int talk_no, TalkRequestDTO talkRequestDTO);
    public int deleteTalk(int talk_no);
}
