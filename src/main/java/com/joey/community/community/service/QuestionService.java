package com.joey.community.community.service;

import com.joey.community.community.dto.PaginationDTO;
import com.joey.community.community.dto.QuestionDTO;
import com.joey.community.community.exception.CustomizeException;
import com.joey.community.community.exception.CustomizeExceptionCode;
import com.joey.community.community.mapper.QuestionExtMapper;
import com.joey.community.community.mapper.QuestionMapper;
import com.joey.community.community.mapper.UserMapper;
import com.joey.community.community.model.Question;
import com.joey.community.community.model.QuestionExample;
import com.joey.community.community.model.User;
import com.joey.community.community.utils.PaginationUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private PaginationUtil paginationUtil;

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        Integer offset = size * (page - 1);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        Integer totalCount = (int) questionMapper.countByExample(example);
        paginationUtil.setPaginationDTO(paginationDTO);
        paginationUtil.setPagination(totalCount,page,size);
        return paginationDTO;
    }

    public PaginationDTO list(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
        paginationUtil.setPaginationDTO(paginationDTO);
        paginationUtil.setPagination(totalCount,page,size);
        return paginationDTO;
    }

    public QuestionDTO findQuestionById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null) {
            throw new CustomizeException(CustomizeExceptionCode.QUESTION_EXCEPTION_MESSAGE);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        BeanUtils.copyProperties(question,questionDTO);
        return questionDTO;
    }

    public void updateReadingCountById(int id) {
        Question updateQuestion = new Question();
        updateQuestion.setViewCount(1);
        updateQuestion.setId(id);
        questionExtMapper.incView(updateQuestion);
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setLikeCount(0);
            question.setViewCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        } else {
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setContent(question.getContent());
            updateQuestion.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            int i = questionMapper.updateByExampleSelective(updateQuestion,questionExample);
            if(i == 0) {
                throw new CustomizeException(CustomizeExceptionCode.QUESTION_EXCEPTION_MESSAGE);
            }
        }
    }

    public List<QuestionDTO> findRelatedQuestionsByTag(QuestionDTO questionDTO) {

        Question question = new Question();
        question.setId(questionDTO.getId());
        String[] tag = questionDTO.getTag().split(",");
        String regTag = Arrays.stream(tag).collect(Collectors.joining("|"));
        question.setTag(regTag);
        List<Question> questionDTOList = questionExtMapper.selectRelatedByTag(question);

        List<QuestionDTO> questionDTOLists = questionDTOList.stream().map(q -> {
            QuestionDTO queryDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, queryDTO);
            return queryDTO;
        }).collect(Collectors.toList());

        return questionDTOLists;
    }
}
