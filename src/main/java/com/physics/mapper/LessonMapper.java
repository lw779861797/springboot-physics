package com.physics.mapper;

import com.physics.pojo.Lesson;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonMapper {
    List<Lesson> queryAll();
}
