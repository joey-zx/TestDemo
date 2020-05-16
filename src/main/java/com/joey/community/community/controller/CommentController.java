package com.joey.community.community.controller;

import com.joey.community.community.dto.CommentCreateDTO;
import com.joey.community.community.dto.CommentDTO;
import com.joey.community.community.dto.ResultDTO;
import com.joey.community.community.enums.CommentTypeEnum;
import com.joey.community.community.exception.CustomizeExceptionCode;
import com.joey.community.community.model.Comment;
import com.joey.community.community.model.User;
import com.joey.community.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResultDTO post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute("user");

        if(user == null) {
            return ResultDTO.errorOf(CustomizeExceptionCode.NO_LGGIN);
        }

        if(commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeExceptionCode.NO_REPLY_COMMENT);
        }

        Comment comment = new Comment();
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setComentator(user.getId());
        comment.setLikeCount(0);
        comment.setCommentCount(0);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}")
    public ResultDTO<List<CommentDTO>> comment(@PathVariable(name = "id") Integer id) {
        List<CommentDTO> commentDTOS = commentService.listCommentByTargetId(id, CommentTypeEnum.COMMENT);

        return ResultDTO.okOf(commentDTOS);
    }

    @ResponseBody
    @RequestMapping(value = "/comment/like/{id}")
    public void commentLike(@PathVariable(name = "id") Integer id) {
        commentService.saveLikeCount(id);
    }
}
