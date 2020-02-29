package com.joey.community.community.controller;
import com.joey.community.community.dto.PaginationDTO;
import com.joey.community.community.mapper.UserMapper;
import com.joey.community.community.model.User;
import com.joey.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/profile/{action}")
    public String profile(@PathVariable(value = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          Model model,
                          HttpServletRequest request) {


        User user = (User) request.getSession().getAttribute("user");

        if(user == null) {
            return "redirect:/";
        }

        if("question".equals(action)) {
            model.addAttribute("section",action);
            model.addAttribute("sectionName","我的问题");
        } else if("replies".equals(action)){
            model.addAttribute("section",action);
            model.addAttribute("sectionName","我的回复");
        }

        PaginationDTO pagination = questionService.list(user.getId(),page,size);
        model.addAttribute("pagination", pagination);
        return "profile";
    }
}
