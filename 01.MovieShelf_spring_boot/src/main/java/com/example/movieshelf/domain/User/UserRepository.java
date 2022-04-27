package com.example.movieshelf.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("Select u from User u where u.user_id like ?1")
    List<User> findAllByUser_id(String id);
}
