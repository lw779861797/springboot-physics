package com.physics.util;

import com.physics.pojo.Student;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Student.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target == null ){
            errors.rejectValue("",null,"用户不能为空");
            return;
        }
        Student student=(Student)target;
        if (StringUtils.hasLength(student.getUser_name())){
            errors.rejectValue("user_name1", null,"学号不能为空");
        }
        if(StringUtils.hasLength(student.getPass_word())){
            errors.rejectValue("pass_word1", null,"密码不能为空");
        }
        if(student.getUser_name().length() != 6 ){
            errors.rejectValue("user_name2", null,"学号错误");
        }
        if(student.getPass_word().length() != 6){
            errors.rejectValue("pass_word2", null,"密码错误");
        }
        for(int i = 0;i < student.getUser_name().length();i++){
            if (!Character.isDigit(student.getUser_name().charAt(i))) {
                errors.rejectValue("user_name3", null,"学号应为数字");
            }
        }
        for(int i = 0;i < student.getPass_word().length();i++){
            if (!Character.isDigit(student.getPass_word().charAt(i))) {
                errors.rejectValue("pass_word3", null,"密码应为数字");
            }
        }
    }
}
