package com.physics.service.impl;

import com.physics.mapper.StudentMapper;
import com.physics.pojo.Student;
import com.physics.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Transactional(isolation = READ_UNCOMMITTED)
    @Override
    public String checkByUserName(String user_name) throws Exception {
        if(user_name == null || user_name == ""){
            throw new Exception("账号不正确");
        }
        return studentMapper.checkByUserName(user_name);
    }

    @Transactional(isolation = READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public boolean updateStudentSelectedDel(int student_id) {
        for(int i=0;i<3;i++){
            int version=studentMapper.getVersion(student_id);
            int result=studentMapper.updateStudentSelectedDel(student_id,version);
            if(result == 0)
                continue;
            return true;
        }
        return false;
    }

    @Transactional(isolation = READ_COMMITTED)
    @Override
    public Student queryByUserName(String user_name) {
        return studentMapper.queryByUserName(user_name);
    }

    @Transactional(isolation = READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public void updatePassWord(String user_name, String original_password, String input_password1) {
        studentMapper.updatePassWord(user_name,original_password,input_password1);
    }
    @Transactional(isolation = READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public String queryUserNameById(int student_id) {
        return studentMapper.queryUserNameById(student_id);
    }
    @Transactional(isolation = READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public String queryNameById(int student_id) {
        return studentMapper.queryNameById(student_id);
    }
}
