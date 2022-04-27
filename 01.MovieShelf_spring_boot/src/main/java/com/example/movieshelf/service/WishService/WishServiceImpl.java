package com.example.movieshelf.service.WishService;

import com.example.movieshelf.domain.Wish.Wish;
import com.example.movieshelf.domain.Wish.WishRepository;
import com.example.movieshelf.domain.Wish.WishRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WishServiceImpl implements WishService{
    private final WishRepository repo;

    public Wish addWish(WishRequestDTO wishRequestDTO){
        Wish wish = new Wish(wishRequestDTO);
        return repo.save(wish);
    }

    public Wish getWish(int code){
        return repo.findById(code).orElseThrow(
                // 람다식
                () -> new IllegalArgumentException("존재하지 않는 위시리스트입니다.")
        );
    }

    // 3.Update
    @Transactional // 기존의 테이블에 쿼리가 일어나야함을 알려줌
    public Wish updateWish(int wish_no, WishRequestDTO wishRequestDTO) {
        Wish wish = getWish(wish_no);
        wish.update(wishRequestDTO);
        return wish;
    }

    public List<Wish> getWishes(){
        return repo.findAll();
    }

    @Transactional
    public int deleteWish(int code){
        Wish wish = getWish(code);
        repo.deleteById(code);
        return wish.getWish_no();
    }
}
