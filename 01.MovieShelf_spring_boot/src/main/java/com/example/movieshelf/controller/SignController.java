package com.example.movieshelf.controller;

import com.example.movieshelf.domain.User.User;
import com.example.movieshelf.domain.Wish.Wish;
import com.example.movieshelf.service.UserService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class SignController {
    private final UserService service;
    private final WishController wc;
    @GetMapping("/login")
    public String login() {
        return "/sign/login.jsp";
    }

    @PostMapping("/loginPro")
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        User user = service.getUserById(id);

        HttpSession session = request.getSession();
        if (user == null) {
            session.setAttribute("logError", 1);
            response.sendRedirect("/login");
        } else if (user.getUser_id().equals(id) && user.getUser_pw().equals(pw)) {
            List<Wish> wishCountlist = wc.getWishes(id);
            int wishCount = wishCountlist.size();

            session.setAttribute("log", user);
            session.setAttribute("logError", 0);
            session.setAttribute("wishCount",wishCount);
            response.sendRedirect("/");
        } else {
            session.setAttribute("logError", 1);
            response.sendRedirect("/login");
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("log");
        if (user != null) {
            HttpSession session = request.getSession();
            session.invalidate();
        }
        response.sendRedirect("/");
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "/sign/signUp.jsp";
    }


}
