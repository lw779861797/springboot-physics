package com.physics.mapper;

import com.physics.pojo.CourseCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseCodeMapper {
    void insert(CourseCode courseCode);
    void delete(int coursecode_id);
    List<CourseCode> queryCourseCodeByStudentId(int id);

    void updateCourseCode();

    CourseCode queryCourseCodeByLessonId(@Param("course_name") String course_name,
                                         @Param("student_id") int student_id);

    List<Integer> queryAllSeat(int course_id);
}
