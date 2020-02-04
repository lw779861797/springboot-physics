package com.physics.service.impl;

import com.physics.mapper.LessonMapper;
import com.physics.pojo.Lesson;
import com.physics.service.LessonService;
import com.physics.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private LessonMapper lessonMapper;
    @Transactional(isolation = READ_UNCOMMITTED)
    @Override
    public List<Lesson> queryAll() {
        List<Lesson> lessons;
        lessons= redisUtil.getList("lessons".getBytes(),0,-1);
        if(lessons.size() == 0){
            lessons = lessonMapper.queryAll();
            if(lessons.size() != 0){
                redisUtil.setList("lessons".getBytes(),lessons);
            }
        }
        return lessons.size() == 0 ? new ArrayList<>() : lessons;
    }
}
