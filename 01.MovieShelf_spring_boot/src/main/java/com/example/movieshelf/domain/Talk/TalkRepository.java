package com.example.movieshelf.domain.Talk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TalkRepository extends JpaRepository<Talk,Integer> {

    @Query("select " +
            "u  " +
            "from Talk u")
    List<Talk> findAll();

}
