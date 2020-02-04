package com.physics.controller;

//import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice(basePackages = "com.physics.controller.*",annotations = Controller.class)
public class ExceptionController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ExceptionController.class);
//    绑定日期的格式化
    @InitBinder
    public void initDataBinder(WebDataBinder binder){
        CustomDateEditor dateEditor=new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"),false);
        binder.registerCustomEditor(Date.class,dateEditor);
    }
//    异常处理，使得被拦截的控制器发生异常时，都能获得相同的视图相应
//    @ExceptionHandler
    public String exception(Model model,Exception e){
        logger.error("控制台异常 : "+e.toString());
        model.addAttribute("exception_message","系统繁忙,请稍后再试");
        return "exception";
    }
}
