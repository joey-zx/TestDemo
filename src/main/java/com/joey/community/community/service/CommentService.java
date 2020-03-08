package com.joey.community.community.service;

import com.joey.community.community.dto.CommentDTO;
import com.joey.community.community.enums.CommentTypeEnum;
import com.joey.community.community.exception.CustomizeException;
import com.joey.community.community.exception.CustomizeExceptionCode;
import com.joey.community.community.mapper.*;
import com.joey.community.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private CommentExtMapper commentExtMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED,timeout = 60)
    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeExceptionCode.NO_QUESTION_COMMENT);
        }

        if(comment.getType() == null || !CommentTypeEnum.isExists(comment.getType())) {
            throw new CustomizeException(CustomizeExceptionCode.TYPE_PARAM_WRONG);
        }

        if(comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            // reply comment
            Comment dbcomment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbcomment == null) {
                throw new CustomizeException(CustomizeExceptionCode.COMMENT_NOT_FOUND);
            } else {
                commentMapper.insert(comment);

                //增加评论数
                Comment parentComment = new Comment();
                parentComment.setId(comment.getParentId());
                parentComment.setCommentCount(1);
                commentExtMapper.incCommentCount(parentComment);
            }
        } else {
            // reply question
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeExceptionCode.QUESTION_NOT_FOUND);
            } else {
                commentMapper.insert(comment);
                question.setCommentCount(1);
                questionExtMapper.incCommentCount(question);
            }
        }
    }

    public List<CommentDTO> listCommentByTargetId(Integer id, CommentTypeEnum type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        // Query the all commet users Id
        List<Integer> userIds = comments.stream().map(comment -> comment.getComentator()).distinct().collect(Collectors.toList());

        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> users;
        if(userIds.size() > 0) {
            users = userMapper.selectByExample(userExample);
        } else {
            users = new ArrayList<User>();
        }

        // transfer the user object from list to map
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));


        // return the commentDTO
        List<CommentDTO> commentDTOList = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getComentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOList;
    }
}
