package com.physics.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.physics.mapper.AnswerMapper;
import com.physics.pojo.Answer;
import com.physics.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerMapper answerMapper;
    @Transactional(isolation = READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(Answer answer) {
        answerMapper.insert(answer);
    }
}
