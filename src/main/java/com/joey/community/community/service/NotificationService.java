package com.joey.community.community.service;

import com.joey.community.community.dto.NotificationDTO;
import com.joey.community.community.dto.PaginationDTO;
import com.joey.community.community.dto.QuestionDTO;
import com.joey.community.community.mapper.NotificationMapper;
import com.joey.community.community.mapper.UserMapper;
import com.joey.community.community.model.*;
import com.joey.community.community.utils.PaginationUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private PaginationUtil paginationUtil;

    public PaginationDTO<NotificationDTO> list(Integer id, Integer page, Integer size) {

        Integer offset = size * (page - 1);
        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(id);
        List<Notification> notificationList = notificationMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO();




        for (Notification notification : notificationList) {
            User user = userMapper.selectByPrimaryKey(notification.getId());
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setUser(user);
            notificationDTOList.add(notificationDTO);
        }

        paginationDTO.setData(notificationDTOList);
        Integer totalCount = (int) notificationMapper.countByExample(example);
        paginationUtil.setPaginationDTO(paginationDTO);
        paginationUtil.setPagination(totalCount,page,size);
        return paginationDTO;
    }
}
