package com.joey.community.community.controller;

import com.joey.community.community.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        User user = (User)request.getAttribute("user");
        model.addAttribute("user",user);
        return "index";
    }
}
