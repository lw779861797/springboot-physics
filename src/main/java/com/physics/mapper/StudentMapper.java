package com.physics.mapper;

import com.physics.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {
    String checkByUserName(String user_name);

    Student queryByUserName(String user_name);
    int updateStudentSelectedDel(@Param("student_id") int student_id,@Param("version") int version);
    int updateStudentSelected(@Param("student_id") int student_id,
                             @Param("version") int version);

    int getVersion(int student_id);

    void updatePassWord(@Param("user_name") String user_name,
                        @Param("original_password")String original_password,
                        @Param("input_password1")String input_password1);

    String queryUserNameById(int student_id);

    String queryNameById(int student_id);
}
