package com.example.movieshelf.service.UserService;

import com.example.movieshelf.domain.User.User;
import com.example.movieshelf.domain.User.UserRepository;
import com.example.movieshelf.domain.User.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository repo;


    // CRUD
    // 1.Create
    public User addUser(UserRequestDTO userRequestDto) {
        User checkUser = getUserById(userRequestDto.getUser_id());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        userRequestDto.setUser_regdate(timestamp);

        if (checkUser != null) {
            return null;
        }else {
            User user = new User(userRequestDto);
            return repo.save(user);
        }
    }

    // 2.Read
    public User getUser(int code) {
        return repo.findById(code).orElseThrow(
                // 람다식
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );
    }

    public List<User> getUsers() {
        return repo.findAll();
    }

    public User getUserById(String id) {
        List<User> result = repo.findAllByUser_id(id);
        if (result.size() != 0) {
            return result.get(0);
        }
        return null;
    }


    // 3.Update
    @Transactional // 기존의 테이블에 쿼리가 일어나야함을 알려줌
    public User updateUser(int code, UserRequestDTO userRequestDto) {
        User user = getUser(code);
        user.update(userRequestDto);
        return user;
    }

    // 4.Delete
    @Transactional
    public int deleteUser(int code) {
        User user = getUser(code);
        repo.deleteById(user.getUser_no());
        return user.getUser_no();
    }


}
