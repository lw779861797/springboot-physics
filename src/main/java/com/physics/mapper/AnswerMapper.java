package com.physics.mapper;

import com.physics.pojo.Answer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerMapper {
    List<Answer> queryPage();

    void insert(Answer answer);
}
