package com.physics.service.impl;

import com.physics.mapper.CourseCodeMapper;
import com.physics.mapper.CourseMapper;
import com.physics.pojo.CourseCode;
import com.physics.pojo.CourseCodeVo;
import com.physics.service.CourseCodeService;
import com.physics.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseCodeServiceImpl implements CourseCodeService {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseCodeMapper courseCodeMapper;
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<CourseCodeVo> queryCourseCodeByStudentId(int id) {
        List<CourseCode> courseCodes=courseCodeMapper.queryCourseCodeByStudentId(id);
        List<CourseCodeVo> courseCodeVos=new ArrayList<>();
        for(CourseCode courseCode : courseCodes){
            CourseCodeVo courseCodeVo=new CourseCodeVo();
            courseCodeVo.setId(courseCode.getId());
            courseCodeVo.setCourse_name(courseCode.getCourse_name());
            courseCodeVo.setCourse_weekly(courseCode.getCourse_weekly());
            courseCodeVo.setCourse_id(courseCode.getCourse_id());
            courseCodeVo.setAddress(courseMapper.queryAddressByCourseId(courseCode.getCourse_id()));
            courseCodeVos.add(courseCodeVo);
        }
        return courseCodeVos;
    }
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(int coursecode_id) {
        courseCodeMapper.delete(coursecode_id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public CourseCode queryCourseCodeByLessonId(String course_name, int student_id) {
        return courseCodeMapper.queryCourseCodeByLessonId(course_name,student_id);
    }
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<Integer> queryAllSeat(int course_id) {
        return courseCodeMapper.queryAllSeat(course_id);
    }
}
