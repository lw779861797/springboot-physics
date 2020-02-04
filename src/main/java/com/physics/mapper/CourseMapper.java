package com.physics.mapper;

import com.physics.pojo.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
    List<Course> queryAll();

    Course queryByIdAndSoOn(@Param("lesson_id") int lesson_id,
                         @Param("lesson_weekly") String lesson_weekly,
                         @Param("lesson_week_day") String lesson_week_day,
                         @Param("lesson_week_day_section") String lesson_week_day_section);
    String queryAddressByCourseId(int course_id);

    void updateCourseIntoSql(@Param("course_id") int id,@Param("selected") int selected);

    public List<Course> queryCourseByLessonId(int lesson_id,String course_section);
}
