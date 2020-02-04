package com.physics.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.physics.pojo.Complaint;
import com.physics.pojo.Student;
import com.physics.service.ComplaintService;
import com.physics.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ComplaintController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ComplaintController.class);
    @Autowired
    StudentService studentService;
    @Autowired
    ComplaintService complaintService;
    @RequestMapping("/addComplaintLoing")
    public String addComplaintLoing(HttpServletRequest request){
        request.setAttribute("flag",6);
        return "login";
    }
    @RequestMapping("/queryComplaint")
    public String queryComplaint(@RequestParam(defaultValue = "1") Integer pageNum, Model model,
                                 HttpServletRequest request){
        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, 8);
        //startPage后紧跟的这个查询就是分页查询
        List<Complaint> complaints=complaintService.queryPage();
        PageInfo pageInfo = new PageInfo<Complaint>(complaints);
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Complaint complaint : complaints){
            String time=format.format(complaint.getDate());
            complaint.setTime(time);
        }
        model.addAttribute("pageInfo", pageInfo);
        return "complaint_view";
    }
    @RequestMapping("/addComplaint")
    public String addComplaint(@RequestParam("complaint_title")String title,
                               @RequestParam("complaint_context")String context,
                               HttpSession session){
        Student student= (Student) session.getAttribute("students");
        if(student == null){
            logger.warn("增加投诉页面 :用户信息为空");
            return "redirect:/base";
        }
        if(title.trim() == "" || context.trim() == ""){
            logger.warn("增加投诉页面 :学生id为"+student.getId()+" 输入的投诉内容为空");
            return "redirect:queryComplaint";
        }
        Complaint complaint=new Complaint();
        complaint.setAdmin_message(student.getName()+'('+student.getUser_name()+')');
        complaint.setDate(new Date());
        complaint.setContext(context);
        complaint.setTitle(title);
        complaint.setStudent_id(student.getId());
        complaintService.insert(complaint);
        return "redirect:queryComplaint";
    }
}
