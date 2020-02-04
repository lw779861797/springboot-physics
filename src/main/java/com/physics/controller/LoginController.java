package com.physics.controller;

import com.physics.pojo.Student;
import com.physics.service.StudentService;
import com.physics.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);
    @Autowired
    StudentService studentService;
//    第一次在前端验证
    //第二次验证输入的账号和密码是否符合标准
    private boolean check(String user_name,String pass_word){
        user_name.trim();
        pass_word.trim();
        if (!StringUtils.hasLength(user_name)){
            return false;
        }
        if(!StringUtils.hasLength(pass_word)){
            return false;
        }
        if(pass_word.length() != 6){
           return false;
        }
        for(int i = 0;i < user_name.length();i++){
            if (!Character.isDigit(user_name.charAt(i))) {
                return false;
            }
        }
        for(int i = 0;i < pass_word.length();i++){
            if (!Character.isDigit(pass_word.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    @RequestMapping("/check")
//    @ResponseBody
    public String  login_student(@RequestParam("user_name")String user_name, @RequestParam("pass_word")String pass_word,
                                 @RequestParam("flag") String flag,
                                 HttpServletRequest request){
        int f=Integer.valueOf(flag);
//        若密码格式不对，返回到login页面
        if(!check(user_name,pass_word)){
            request.setAttribute("flag",flag);
            return "login";
        }
        String passWord=null;
        try {
            passWord=studentService.checkByUserName(user_name);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("flag",flag);
            return "login";
        }
        if(passWord == null || passWord == ""){
            request.setAttribute("flag",flag);
            return "login";
        }
//        判断输入密码经过MD5加密后是否正确，若正确返回到前端
        if(passWord.equals(MD5Util.getMD5(pass_word,10))){
            Student student=studentService.queryByUserName(user_name);
            logger.info("学生用户: "+student.getName()+"登入成功");
            request.getSession().setAttribute("students",student);

            //返回到预约实验页面
            if(f == 0){
                return "forward:experiment";
            }
//            返回到留言榜页面
            else if (f == 5){
                return "messageBoard_release";
            }
//            返回到查看丢失物品的页面
            else if (f == 4){
                return "redirect:queryGoods";
            }
//            返回到增加丢失物品的页面
           else if (f == 3){
                return "lostAndFound_release";
            }
//           返回到投诉增加的页面
           else if (f == 6){
                return "complaint_release";
            }
        }
        request.setAttribute("flag",flag);
        return "login";
    }
//    实现一个页面跳转
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        request.setAttribute("flag",0);
        return "login";
    }
//    修改密码时，判断输入的俩次密码格式是否错误，是否相等等。
    private boolean checkPassWord(String original_password,String input_password1,String input_password2){
        original_password.trim();
        input_password1.trim();
        input_password2.trim();
        if (!StringUtils.hasLength(original_password)){
            return false;
        }
        if (!StringUtils.hasLength(input_password1)){
            return false;
        }
        if (!StringUtils.hasLength(input_password2)){
            return false;
        }
        if(input_password1.length() != 6){
            return false;
        }
        if(input_password2.length() != 6){
            return false;
        }
        for(int i = 0;i < input_password1.length();i++){
            if (!Character.isDigit(input_password1.charAt(i))) {
                return false;
            }
        }
        if(!input_password1.equals(input_password2)){
            return false;
        }
        return true;
    }
    @ResponseBody
    @RequestMapping("/updatePassWord")
    public String updatePassWord(@RequestParam("original_password") String original_password,
                                 @RequestParam("input_password1")String input_password1,
                                 @RequestParam("input_password2")String input_password2,
                                 HttpSession session){
//        检验格式是否正确
        boolean flag=checkPassWord(original_password,input_password1,input_password2);
        if(!flag){
            return "2";
        }
        Student student= (Student) session.getAttribute("students");
        if(!student.getPass_word().equals( MD5Util.getMD5(original_password,10))){
            return "0";
        }
//        若检验无错误，则将MD5加密后的密码存入数据库
        studentService.updatePassWord(student.getUser_name(),MD5Util.getMD5(original_password,10),
                MD5Util.getMD5(input_password1,10));
        logger.info("学生用户: "+student.getName()+"密码更换成功");
        session.removeAttribute("students");
        return "1";
    }
//    实现页面的跳转
    @RequestMapping("/update_pass_word")
    public String update_pass_word(){
        return "changePersonalPassword";
    }
}
