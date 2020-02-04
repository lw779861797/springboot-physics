package com.physics.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMapper {
    String queryNameById(int teacher_id);
}
