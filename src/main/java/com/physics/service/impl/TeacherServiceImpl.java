package com.physics.service.impl;

import com.physics.mapper.TeacherMapper;
import com.physics.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Transactional(isolation = Isolation.READ_UNCOMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public String queryNameById(int teacher_id) {
        return teacherMapper.queryNameById(teacher_id);
    }
}
