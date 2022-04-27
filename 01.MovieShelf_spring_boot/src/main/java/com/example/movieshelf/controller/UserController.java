package com.example.movieshelf.controller;

import com.example.movieshelf.domain.User.User;
import com.example.movieshelf.domain.User.UserRequestDTO;
import com.example.movieshelf.service.UserService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService service;

    // CRUD
    // 1.Create
    @PostMapping("/user/addUser")
    public User addUser(@RequestBody UserRequestDTO userRequestDto) {
        return service.addUser(userRequestDto);
    }

    // 2.Read
    @GetMapping("/user/getUser/{code}")
    public User getUser(@PathVariable int code) {
        return service.getUser(code);
    }

    @GetMapping("/user/getUsers")
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/user/getUserByID/{id}")
    public User getUserByID(@PathVariable String id){
        return service.getUserById(id);
    }

    @PostMapping("/user/checkUser")
    public User checkUser(@RequestBody UserRequestDTO userRequestDto) {
        return service.getUserById(userRequestDto.getUser_id());
    }

    // 3.Update
    @PutMapping("/user/update/{code}")
    public User updateUser(@PathVariable int code,@RequestBody UserRequestDTO userRequestDto){
        return service.updateUser(code , userRequestDto);
    }


    // 4.Delete
    @DeleteMapping("/user/delete/{code}")
    public int deleteUser(@PathVariable int code){
        return service.deleteUser(code);
    }
}
