package com.joey.community.community.service;

import com.joey.community.community.dto.PaginationDTO;
import com.joey.community.community.dto.QuestionDTO;
import com.joey.community.community.mapper.QuestionMapper;
import com.joey.community.community.mapper.UserMapper;
import com.joey.community.community.model.Question;
import com.joey.community.community.model.User;
import com.joey.community.community.utils.PaginationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private PaginationUtil paginationUtil;

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.listById(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questionList) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        Integer totalCount = questionMapper.countById(userId);
        paginationUtil.setPaginationDTO(paginationDTO);
        paginationUtil.setPagination(totalCount,page,size);
        return paginationDTO;
    }

    public PaginationDTO list(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questionList) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        Integer totalCount = questionMapper.count();
        paginationUtil.setPaginationDTO(paginationDTO);
        paginationUtil.setPagination(totalCount,page,size);
        return paginationDTO;
    }

    public QuestionDTO findQuestionById(Integer id) {
        Question question = questionMapper.findById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.findUserById(question.getCreator());
        questionDTO.setUser(user);
        BeanUtils.copyProperties(question,questionDTO);
        return questionDTO;
    }
}
