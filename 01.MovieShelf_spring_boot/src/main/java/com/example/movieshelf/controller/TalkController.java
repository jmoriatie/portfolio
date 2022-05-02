package com.example.movieshelf.controller;

import com.example.movieshelf.domain.Talk.Talk;
import com.example.movieshelf.domain.Talk.TalkRequestDTO;
import com.example.movieshelf.service.TalkService.TalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class TalkController {

    // 게시판 RestController
    private final TalkService service;

    @PostMapping("/v1/post")
    public Talk addTalk(@RequestBody TalkRequestDTO talkRequestDTO){
        return service.addTalk(talkRequestDTO);
    }

    @GetMapping("/v1/posts/")
    public List<Talk> getTalks(){
        return service.getTalks();
    }

    @GetMapping("/v1/post/page={nowPageInt}")
    public List<Talk> getTalksInNowPage(@PathVariable int nowPageInt){
        List<Talk> allList = this.getTalks();
        List<Talk> pageList = new ArrayList<>();

        // 5개씩만 보여주기 위함 => 보여주는 개수 바꾸려면 추가 설정 가능할 듯
        int endIdx = nowPageInt * 5; // 페이지 * 5 == endIdx
        int startIdx = endIdx-5; // endIdx - 5 == startIdxs

        // endIdx가 가장 마지막 인덱스보다 크면, 예외처리
        if(endIdx > allList.size()-1){
            startIdx = 0;
            endIdx = allList.size()-1;
        }

        for(int i=startIdx; i<endIdx; i++){
            pageList.add(allList.get(i)); // 인덱스에 해당되는 애들만 넣어서 반환
        }

        return pageList;
    }

    @GetMapping("/v1/post/no={talk_no}")
    public Talk getTalk(@PathVariable int talk_no){
        return service.getTalk(talk_no);
    }

    @GetMapping("/v1/post/id={user_id}")
    public ArrayList<Talk> getTalk(@PathVariable String user_id){
        List<Talk> talks = service.getTalks();
        ArrayList<Talk> sendTalks = new ArrayList<Talk>();
        for(Talk t : talks){
            if(t.getUser_id().equals(user_id)){
                sendTalks.add(t);
            }
        }
        return sendTalks;
    }

    @PutMapping("/v1/post/{talk_no}")
    public Talk updateTalk(@PathVariable int talk_no, @RequestBody TalkRequestDTO talkRequestDTO){
        return service.updateTalk(talk_no, talkRequestDTO);
    }

    @DeleteMapping("/v1/post/{talk_no}")
    public int deleteTalk(@PathVariable int talk_no){
        return service.deleteTalk(talk_no);
    }

}
