package com.example.movieshelf.service.WishService;

import com.example.movieshelf.domain.Wish.Wish;
import com.example.movieshelf.domain.Wish.WishRequestDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface WishService {
    public Wish addWish(WishRequestDTO wishRequestDTO);
    public Wish getWish(int code);
    public Wish updateWish(int wish_no, WishRequestDTO wishRequestDTO) ;
    public List<Wish> getWishes();
    public int deleteWish(int code);
}
