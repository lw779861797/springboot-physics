package com.physics.service.impl;

import com.physics.mapper.NoticeMapper;
import com.physics.pojo.Notice;
import com.physics.service.NoticeService;
import com.physics.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private NoticeMapper noticeMapper;
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public List<Notice> queryAll() {
        List<Notice> notices;
        notices= redisUtil.getList("notices".getBytes(),0,-1);
        if(notices.size() == 0){
            notices = noticeMapper.queryByAll();
            if(notices.size() != 0){
                redisUtil.setList("notices".getBytes(),notices);
            }
        }
        return notices.size() == 0 ? new ArrayList<>() : notices;
    }
}
