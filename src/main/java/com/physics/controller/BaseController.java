package com.physics.controller;

import com.physics.mapper.NoteMapper;
import com.physics.pojo.Code;
import com.physics.pojo.Lesson;
import com.physics.pojo.Note;
import com.physics.pojo.Notice;
import com.physics.service.CodeService;
import com.physics.service.LessonService;
import com.physics.service.NoteService;
import com.physics.service.NoticeService;
import com.physics.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//处理首页信息
@Controller
public class BaseController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoginController.class);
    @Autowired
    NoteMapper noteMapper;
    @Autowired
    private NoteService noteService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private NoticeService noticeService;
    @RequestMapping("/")
    public String indexHtml() {
        return "redirect:/base";
    }
//    首页
    @RequestMapping("base")
    public String index(HttpSession session) throws ParseException {
        List<Note> notes= (List<Note>) session.getAttribute("notes");
        List<Lesson> lessons= (List<Lesson>) session.getAttribute("lessons");
        List<Code> codes= (List<Code>) session.getAttribute("codes");
        List<Notice> notices= (List<Notice>) session.getAttribute("notices");
        if(notices == null || notices.size() == 0){
            notices = noticeService.queryAll();
            session.setAttribute("notices",notices);
        }
        if(notes == null || notes.size() == 0){
            notes = noteService.queryAll();
            session.setAttribute("notes",notes);
        }
        if(lessons == null || lessons.size() == 0){
            lessons = lessonService.queryAll();
            session.setAttribute("lessons",lessons);
        }
        if(codes == null || codes.size() == 0){
            codes = codeService.queryAll();
            session.setAttribute("codes",codes);
        }
        Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(DateUtils.getWeekCounterByEventStartDate("2019-09-01",format.format(date)));
        session.setAttribute("week",DateUtils.getWeekCounterByEventStartDate("2019-09-01",format.format(date)));
        return "index";
    }
}
