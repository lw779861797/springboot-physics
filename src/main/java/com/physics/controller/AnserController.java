package com.physics.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.physics.mapper.AnswerMapper;
import com.physics.mapper.ReplyMapper;
import com.physics.pojo.Answer;
import com.physics.pojo.Reply;
import com.physics.pojo.Student;
import com.physics.service.AnswerService;
import com.physics.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AnserController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AnserController.class);
    @Autowired
    AnswerService answerService;
    @Autowired
    ReplyMapper replyMapper;
    @Autowired
    StudentService studentService;
    @Autowired
    AnswerMapper answerMapper;
    @RequestMapping(value = "/queryAnswers")
    public String queryAnswers(@RequestParam(defaultValue = "1") Integer pageNum, Model model,
                               HttpServletRequest request) throws ParseException {

        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, 3);
        //startPage后紧跟的这个查询就是分页查询
        List<Answer> answers = answerMapper.queryPage();
        PageInfo pageInfo = new PageInfo<Answer>(answers);
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Answer answer : answers){
            String time=format.format(answer.getDate());
            answer.setTime(time);
            String student_username=studentService.queryUserNameById(answer.getStudent_id());
            String student_name=studentService.queryNameById(answer.getStudent_id());
            Reply reply=replyMapper.queryReplyById(answer.getId());
            if(reply != null){
                reply.setDate(format.format(reply.getTime()));
                answer.setReply(reply);
            }
            answer.setMessage(student_name+'('+student_username+')');
        }
        model.addAttribute("pageInfo", pageInfo);
        return "messageBoard_view";
    }
    @RequestMapping("/addAnswerLogin")
    public String addAnswerLogin(HttpServletRequest request){
        request.setAttribute("flag",5);
        return "login";
    }
    @RequestMapping("/addAnswer")
    public String addAnswer(HttpSession session,@RequestParam("answer_context")String context) throws ParseException {
        Student student= (Student) session.getAttribute("students");
        if(student == null){
            logger.warn("增加留言页面 :用户信息为空");
            return "redirect:/base";
        }
        if(context.trim() == ""){
            logger.warn("增加留言页面 :学生id为"+student.getId()+" 输入的留言为空");
            return "redirect:queryAnswers";
        }
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(new Date());
        Answer answer=new Answer();
        answer.setContext(context);
        answer.setDate((format.parse(time)));
        answer.setStudent_id(student.getId());
        answerService.insert(answer);
        return "redirect:queryAnswers";
    }
}
