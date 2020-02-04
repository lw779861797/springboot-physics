package com.physics.controller;

import com.physics.pojo.Course;
import com.physics.pojo.Lesson;
import com.physics.pojo.ListTeacher;
import com.physics.service.CourseService;
import com.physics.service.LessonService;
import com.physics.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;
    @Autowired
    LessonService lessonService;
//    教师查询先把所有实验查出显示，然后跳转
    @RequestMapping("/queryLessonTeacher")
    public String queryLessonTeacher(HttpSession session){
        List<Lesson> lessons= (List<Lesson>) session.getAttribute("lessons");
        if(lessons == null || lessons.size() == 0){
            lessons = lessonService.queryAll();
            session.setAttribute("lessons",lessons);
        }
        return "selectTeacherCourse";
    }
//    显示某个实验的老师，代码过于长，不再赘述
    @RequestMapping("/listTeacherLesson/{lesson_id}/{lesson_name}")
    public String listTeacherLesson(@PathVariable("lesson_id")String id,
                                    @PathVariable("lesson_name")String lesson_name,
                                    HttpServletRequest request){
        int lesson_id=Integer.parseInt(id);
        List<ListTeacher> listTeachers_front=new ArrayList<>();
        List<Course> courses_front=courseService.queryCourseByLessonId(lesson_id,"1-4节");
        ListTeacher listTeacher2=new ListTeacher();
        ListTeacher listTeacher3=new ListTeacher();
        ListTeacher listTeacher4=new ListTeacher();
        ListTeacher listTeacher5=new ListTeacher();
        ListTeacher listTeacher6=new ListTeacher();
        ListTeacher listTeacher7=new ListTeacher();
        ListTeacher listTeacher8=new ListTeacher();
        ListTeacher listTeacher9=new ListTeacher();
        ListTeacher listTeacher10=new ListTeacher();
        ListTeacher listTeacher11=new ListTeacher();
        ListTeacher listTeacher12=new ListTeacher();
        ListTeacher listTeacher13=new ListTeacher();
        ListTeacher listTeacher14=new ListTeacher();
        for(Course course : courses_front){
            switch (course.getWeek()){
                case "2":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher2.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher2.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher2.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher2.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher2.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher2.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher2.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "3":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher3.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher3.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher3.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher3.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher3.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher3.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher3.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "4":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher4.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher4.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher4.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher4.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher4.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher4.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher4.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "5":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher5.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher5.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher5.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher5.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher5.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher5.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher5.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "6":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher6.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher6.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher6.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher6.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher6.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher6.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher6.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "7":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher7.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher7.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher7.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher7.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher7.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher7.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher7.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "8":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher8.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher8.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher8.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher8.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher8.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher8.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher8.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "9":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher9.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher9.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher9.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher9.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher9.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher9.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher9.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "10":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher10.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher10.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher10.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher10.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher10.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher10.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher10.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "11":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher11.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher11.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher11.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher11.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher11.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher11.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher11.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "12":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher12.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher12.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher12.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher12.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher12.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher12.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher12.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "13":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher13.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher13.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher13.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher13.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher13.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher13.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher13.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "14":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher14.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher14.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher14.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher14.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher14.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher14.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher14.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
            }
        }
        listTeachers_front.add(listTeacher2);
        listTeachers_front.add(listTeacher3);
        listTeachers_front.add(listTeacher4);
        listTeachers_front.add(listTeacher5);
        listTeachers_front.add(listTeacher6);
        listTeachers_front.add(listTeacher7);
        listTeachers_front.add(listTeacher8);
        listTeachers_front.add(listTeacher9);
        listTeachers_front.add(listTeacher10);
        listTeachers_front.add(listTeacher11);
        listTeachers_front.add(listTeacher12);
        listTeachers_front.add(listTeacher13);
        listTeachers_front.add(listTeacher14);

        List<ListTeacher> listTeachers_last=new ArrayList<>();
        courses_front=courseService.queryCourseByLessonId(lesson_id,"5-8节");
        listTeacher2=new ListTeacher();
        listTeacher3=new ListTeacher();
        listTeacher4=new ListTeacher();
        listTeacher5=new ListTeacher();
        listTeacher6=new ListTeacher();
        listTeacher7=new ListTeacher();
        listTeacher8=new ListTeacher();
        listTeacher9=new ListTeacher();
        listTeacher10=new ListTeacher();
        listTeacher11=new ListTeacher();
        listTeacher12=new ListTeacher();
        listTeacher13=new ListTeacher();
        listTeacher14=new ListTeacher();
        for(Course course : courses_front){
            switch (course.getWeek()){
                case "2":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher2.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher2.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher2.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher2.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher2.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher2.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher2.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "3":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher3.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher3.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher3.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher3.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher3.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher3.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher3.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "4":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher4.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher4.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher4.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher4.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher4.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher4.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher4.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "5":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher5.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher5.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher5.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher5.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher5.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher5.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher5.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "6":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher6.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher6.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher6.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher6.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher6.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher6.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher6.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "7":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher7.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher7.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher7.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher7.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher7.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher7.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher7.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "8":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher8.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher8.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher8.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher8.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher8.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher8.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher8.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "9":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher9.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher9.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher9.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher9.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher9.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher9.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher9.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "10":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher10.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher10.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher10.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher10.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher10.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher10.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher10.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "11":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher11.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher11.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher11.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher11.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher11.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher11.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher11.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "12":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher12.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher12.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher12.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher12.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher12.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher12.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher12.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "13":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher13.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher13.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher13.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher13.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher13.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher13.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher13.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
                case "14":
                    switch (course.getWeek_day()){
                        case "1":
                            listTeacher14.setMonday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "2":
                            listTeacher14.setTuesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "3":
                            listTeacher14.setWednesday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "4":
                            listTeacher14.setThursday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "5":
                            listTeacher14.setFriday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "6":
                            listTeacher14.setSaturday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                        case "7":
                            listTeacher14.setSunday(teacherService.queryNameById(course.getTeacher_id()));
                            break;
                    }
                    break;
            }
        }
        listTeachers_last.add(listTeacher2);
        listTeachers_last.add(listTeacher3);
        listTeachers_last.add(listTeacher4);
        listTeachers_last.add(listTeacher5);
        listTeachers_last.add(listTeacher6);
        listTeachers_last.add(listTeacher7);
        listTeachers_last.add(listTeacher8);
        listTeachers_last.add(listTeacher9);
        listTeachers_last.add(listTeacher10);
        listTeachers_last.add(listTeacher11);
        listTeachers_last.add(listTeacher12);
        listTeachers_last.add(listTeacher13);
        listTeachers_last.add(listTeacher14);
        request.setAttribute("listTeachers_front",listTeachers_front);
        request.setAttribute("listTeachers_last",listTeachers_last);
        request.setAttribute("lesson_name",lesson_name);
        return "listTeacherCourse";
    }
}
