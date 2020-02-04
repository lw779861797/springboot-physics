package com.physics.service;

import com.physics.pojo.Course;
import com.physics.pojo.CourseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseService {
    List<CourseVo> queryAll();
    int delStock(int lesson_id, String weekly, String lesson_week_day, String lesson_week_day_section, Course course_lag);
    int querySelected(int course_id);
    void updateCourse();
     boolean addStock(int course_id);

     List<Course> queryCourseByLessonId(@Param("lesson_id") int lesson_id,
                                              @Param("course_section") String course_section);
}
