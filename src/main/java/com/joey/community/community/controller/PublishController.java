package com.joey.community.community.controller;

import com.joey.community.community.dto.QuestionDTO;
import com.joey.community.community.mapper.QuestionMapper;
import com.joey.community.community.mapper.UserMapper;
import com.joey.community.community.model.Question;
import com.joey.community.community.model.User;
import com.joey.community.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/publish/{id}")
    public String publishById(@PathVariable(name = "id") Integer id,
                              Model model) {

        QuestionDTO question = questionService.findQuestionById(id);
        model.addAttribute("question",question);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("content",question.getContent());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());

        return "publish";
    }

    @RequestMapping(value = "/publish",method = {RequestMethod.GET})
    public String publish() {
        return "publish";
    }

    @RequestMapping(value = "/publish",method = {RequestMethod.POST})
    public String doPublish(@RequestParam(name = "title",required = false) String title,
                            @RequestParam(name = "content" ,required = false) String content,
                            @RequestParam(name = "tag" , required = false) String tag,
                            @RequestParam(name = "id",required = false) Integer id,
                            HttpServletRequest request,
                            Model model) {

        model.addAttribute("title",title);
        model.addAttribute("content",content);
        model.addAttribute("tag",tag);


        if(StringUtils.isBlank(title)) {
            model.addAttribute("error","Title不能为空");
            return "publish";
        }

        if(StringUtils.isBlank(content)) {
            model.addAttribute("error","content不能为空");
            return "publish";
        }

        if(StringUtils.isBlank(tag)) {
            model.addAttribute("error","tag不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            model.addAttribute("error","用户不存在！");
            return "publish";
        }

        Question question = new Question();
        question.setContent(content);
        question.setTag(tag);
        question.setTitle(title);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
