package com.physics.controller;

import com.physics.mapper.CourseMapper;
import com.physics.pojo.*;
import com.physics.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//预约实现的controller
@Controller
public class ExperimentController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ExceptionController.class);
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    StudentService studentService;
    @Autowired
    RabbitMqService rabbitMqService;
    @Autowired
    CourseService courseService;
    @Autowired
    LessonService lessonService;
    @Autowired
    CourseCodeService courseCodeService;
    @RequestMapping("/experiment")
    public String sucess_Login_Experiment(HttpServletRequest request, ModelAndView modelAndView, HttpServletResponse response) throws IOException {
        return "redirect:listCourse";
    }
    @RequestMapping("/listCourse")
    private String  listCourse(HttpServletRequest request){
        List<Lesson> lessons=lessonService.queryAll();
        List<CourseVo> courseVos= courseService.queryAll();
//        把Lesson对象转化为LessonVo
        List<LessonVo> lessonVos=Transformation(lessons,courseVos);
        request.getSession().setAttribute("lessonVos",lessonVos);
        return "courseAppointment";
    }
    //        把Lesson对象转化为LessonVo
    private List<LessonVo> Transformation(List<Lesson> lessons, List<CourseVo> courseVos){
        List<LessonVo> lessonVos=new ArrayList<>();
        for(Lesson lesson : lessons){
            LessonVo lessonVo=new LessonVo();
            lessonVo.setId(lesson.getId());
            lessonVo.setAddress(lesson.getAddress());
            lessonVo.setName(lesson.getName());
            lessonVo.setRemarks(lesson.getRemarks());
            lessonVo.setTime(lesson.getTime());
            lessonVo.setType(lesson.getType());
            for(CourseVo courseVo : courseVos){
                if(courseVo.getLesson_id() == lesson.getId()){
                    lessonVo.getWeeklys().add(courseVo.getWeekly());
                }
            }
            lessonVos.add(lessonVo);
        }
        return lessonVos;
    }
//    预约实验
    @ResponseBody
    @RequestMapping("/appointment")
    public String make_An_Apointment(@RequestParam("lesson_id")String lesson,
                                     @RequestParam("weekly")String week, HttpSession session){
        Student student= (Student) session.getAttribute("students");
        if(student == null){
            logger.warn("预约实验页面 :用户信息为空");
            return "redirect:/base";
        }
        int lesson_id=Integer.valueOf(lesson);
        String lesson_weekly=week;
        int student_id=Integer.valueOf(student.getId());
        String lesson_week_day=student.getWeekday();
        String lesson_week_day_section=student.getWeek_day_section();

        Course course=courseMapper.queryByIdAndSoOn(lesson_id,lesson_weekly,
                lesson_week_day,lesson_week_day_section);
        if(course == null){
            return "2";
        }
        //        预约实验前查询该学生是否预约过该实验
        CourseCode courseCode=courseCodeService.queryCourseCodeByLessonId(course.getName(),student_id);
        if(courseCode != null){
            logger.info("学生ID为: "+student_id+" 已选择过实验 "+course.getName());
            return "3";
        }
//        调用redis
        int del=courseService.delStock(lesson_id,lesson_weekly,lesson_week_day,lesson_week_day_section,course);
        if(del == 0){
            return "0";
        }else if (del == 2){
            logger.error("学生ID :"+student_id+"选择的实验ID:"+lesson_id+
                    " 第"+lesson_weekly+"周星期"+lesson_week_day+" "+lesson_week_day_section+" 不存在");
            return "2";
        }
        Date date=new Date();
        courseCode=new CourseCode();
        courseCode.setCourse_id(course.getId());
        courseCode.setCourse_code_time(date);
        courseCode.setCourse_name(course.getName());
        int seat=0;

//        查询该实验已经占有的座位
        List<Integer> seats=courseCodeService.queryAllSeat(course.getId());
        for(int i=1;i <= course.getNumber();i++){
            boolean flag=true;
            for(int seatI : seats){
                if(seatI == i){
                    flag = false;
                }
            }
            if(flag){
                seat = i;
            }
        }
        courseCode.setSeat(seat);
        courseCode.setStudent_id(student_id);
        courseCode.setCourse_weekly("第"+lesson_weekly+"周星期"+lesson_week_day+" "+lesson_week_day_section);
//        简单使用消息队列将数据插入数据库
        rabbitMqService.sendCourseCode(courseCode);
        return "1";
    }
//    查询学生预约过的实验
    @RequestMapping("/queryStudentCourse")
    public String query_Student_Course(HttpSession session,HttpServletRequest request){
        Student student= (Student) session.getAttribute("students");
        List<CourseCodeVo> courseCodeVos=courseCodeService.queryCourseCodeByStudentId(student.getId());
        request.setAttribute("courseCodeVos",courseCodeVos);
        return "cancelAppointment";
    }
//    取消实验
    @RequestMapping("/cancel/{course_id}/{course_code_id}")
    public String cancel_an_Appointment(@PathVariable("course_id")String course,
                                        @PathVariable("course_code_id")String course_code_id,
                                        HttpSession session){
        Student student= (Student) session.getAttribute("students");
        if(student == null){
            logger.warn("取消预约实验页面 :用户信息为空");
            return "redirect:/base";
        }
        int student_id=student.getId();
        int course_id=Integer.valueOf(course);
        int coursecode_id=Integer.valueOf(course_code_id);
        courseService.addStock(course_id);
        studentService.updateStudentSelectedDel(student_id);
        courseCodeService.delete(coursecode_id);
        return "redirect:../../queryStudentCourse";
    }
}
