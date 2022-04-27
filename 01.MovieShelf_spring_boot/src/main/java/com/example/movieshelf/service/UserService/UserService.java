package com.example.movieshelf.service.UserService;

import com.example.movieshelf.domain.User.User;
import com.example.movieshelf.domain.User.UserRepository;
import com.example.movieshelf.domain.User.UserRequestDTO;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

public interface UserService {
    public User addUser(UserRequestDTO userRequestDto);
    public User getUser(int code);
    public List<User> getUsers();
    public User getUserById(String id);
    public User updateUser(int code, UserRequestDTO userRequestDto);
    public int deleteUser(int code);

}
