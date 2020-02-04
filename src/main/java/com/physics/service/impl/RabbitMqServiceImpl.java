package com.physics.service.impl;

import com.physics.controller.AnserController;
import com.physics.pojo.CorrelationDatas;
import com.physics.pojo.CourseCode;
import com.physics.service.RabbitMqService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqServiceImpl implements RabbitTemplate.ConfirmCallback, RabbitMqService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RabbitMqServiceImpl.class);
    @Value("${rabbitmq.queue.msg}")
    private String msgRouting = null;
    @Value("${rabbitmq.queue.coursecode}")
    private String courseCodeRouting = null;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    public void sendMsg(String msg) {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(msgRouting,msg);
    }

    @Override
    public void sendCourseCode(CourseCode courseCode) {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(courseCodeRouting,courseCode);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack == true) {
            logger.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
        } else {
            if (correlationData instanceof CorrelationDatas) {
                CorrelationDatas messageCorrelationData = (CorrelationDatas) correlationData;
                Object message = messageCorrelationData.getMessage();
                CourseCode courseCode= (CourseCode) message;
                logger.info("消息发送失败: "+courseCode.getCourse_name()+
                        " 学生ID为: "+courseCode.getStudent_id()+
                        " 实验ID为: "+courseCode.getCourse_id());
                int retryCount = messageCorrelationData.getRetryCount();
                //重试次数+1
                ((CorrelationDatas) correlationData).setRetryCount(retryCount + 1);
                this.sendCourseCode(courseCode);
            }
        }
    }
}
