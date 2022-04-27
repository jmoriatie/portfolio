package com.example.movieshelf.controller;

import com.example.movieshelf.domain.Wish.Wish;
import com.example.movieshelf.domain.Wish.WishRequestDTO;
import com.example.movieshelf.service.WishService.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class WishController {
    private final WishService service;

    @PostMapping("/wish/addWish")
    public Wish addWish(@RequestBody WishRequestDTO wishRequestDTO){
        return service.addWish(wishRequestDTO);
    }

    @GetMapping("/wish/getWish/{wish_no}")
    public Wish getWish(@PathVariable int wish_no){
        return service.getWish(wish_no);
    }

    @GetMapping("/wish/getWishes")
    public List<Wish> getWishes()
    {
        return service.getWishes();
    }

    @GetMapping("/wish/getWish/{user_id}")
    public ArrayList<Wish> getWishes(@PathVariable String user_id){
        List<Wish> wishes = service.getWishes();
        ArrayList<Wish> sendWishes = new ArrayList<Wish>();
        for(Wish w : wishes){
            if(w.getUser_id().equals(user_id)){
                sendWishes.add(w);
            }
        }
        return sendWishes;
    }

    @PutMapping
    public Wish updateWish(int wish_no, WishRequestDTO wishRequestDTO){
        return service.updateWish(wish_no, wishRequestDTO);
    }

    @DeleteMapping("/wish/delete/{wish_no}")
    public int deleteWish(@PathVariable int wish_no)
    {
        return service.deleteWish(wish_no);
    }
}
