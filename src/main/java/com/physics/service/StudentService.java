package com.physics.service;

import com.physics.pojo.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentService {
    String checkByUserName(String user_name) throws Exception;
    boolean updateStudentSelectedDel(int student_id);
    Student queryByUserName(String user_name);

    void updatePassWord(String user_name,
                         String original_password,
                        String input_password1);

    String queryUserNameById(int student_id);

    String queryNameById(int student_id);
}
