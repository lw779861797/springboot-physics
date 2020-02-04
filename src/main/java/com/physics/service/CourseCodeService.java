package com.physics.service;

import com.physics.pojo.CourseCode;
import com.physics.pojo.CourseCodeVo;

import java.util.List;

public interface CourseCodeService {
    List<CourseCodeVo> queryCourseCodeByStudentId(int id);
    void delete(int coursecode_id);

    CourseCode queryCourseCodeByLessonId(String course_name, int student_id);

    List<Integer> queryAllSeat(int course_id);
}
