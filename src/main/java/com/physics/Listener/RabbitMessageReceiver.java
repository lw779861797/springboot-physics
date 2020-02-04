package com.physics.Listener;

import com.physics.mapper.CourseCodeMapper;
import com.physics.mapper.StudentMapper;
import com.physics.pojo.CourseCode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RabbitMessageReceiver {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RabbitListener.class);
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseCodeMapper courseCodeMapper;
    @RabbitListener(queues = {"${rabbitmq.queue.msg}"})
    public void receiveMsg(String msg){

    }
    @RabbitListener(queues = "${rabbitmq.queue.coursecode}",concurrency = "20")
    public void receiveCourseCode(CourseCode couseCode){
        for(int i=0;i<3;i++){
            int version=studentMapper.getVersion(couseCode.getStudent_id());
            int result=studentMapper.updateStudentSelected(couseCode.getStudent_id(),version);
            if(result == 0)
                continue;
            insert(couseCode);
            logger.info("学生用户ID为: "+couseCode.getStudent_id()+
                    " 预约了:"+couseCode.getCourse_code_time()+","+
                    couseCode.getCourse_weekly()+
                    "实验名为: "+couseCode.getCourse_name()+
                    " 座位: "+couseCode.getSeat());
            break;
        }
    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void insert(CourseCode courseCode){
        courseCodeMapper.insert(courseCode);
    }
}
