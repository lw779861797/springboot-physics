package com.physics.pojo;

import org.springframework.amqp.rabbit.connection.CorrelationData;

public class CorrelationDatas extends CorrelationData {
    //消息体
    private volatile Object message;
    //重试次数
    private int retryCount = 0;

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
