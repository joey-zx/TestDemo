package com.joey.community.community.controller;

import com.joey.community.community.dto.QuestionDTO;
import com.joey.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {

        questionService.updateReadingCountById(id);
        QuestionDTO questionDTO = questionService.findQuestionById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }

}
