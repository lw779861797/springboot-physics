package com.physics.service;

import com.physics.pojo.CourseCode;

public interface RabbitMqService {
    // 发送字符消息
    public void sendMsg(String msg);

    // 发送用户消息
    public void sendCourseCode(CourseCode courseCode);
}
