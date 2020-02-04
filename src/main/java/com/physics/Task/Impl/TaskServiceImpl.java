package com.physics.Task.Impl;

import com.physics.Task.TaskService;
import com.physics.mapper.CourseCodeMapper;
import com.physics.mapper.CourseMapper;
import com.physics.pojo.CourseVo;
import com.physics.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TaskServiceImpl.class);
    @Autowired
    CourseCodeMapper courseCodeMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private String COURSE_SCHEDULE_SET="product_schedule_set";
    @Scheduled(cron = "0 0 2 * * *")
    @Override
    public void updateCourse() {
        logger.info("取出Redis数据(实验)更新入数据库(每天凌晨俩点)");
        BoundSetOperations<String, String> boundSetOperations =
                stringRedisTemplate.boundSetOps(COURSE_SCHEDULE_SET);
        Set<String> CourseIdList=boundSetOperations.members();
        for(String courseIdStr : CourseIdList){
            Long courseId = Long.parseLong(courseIdStr);
            String courseKey="course_"+courseId;
            BoundHashOperations boundHashOperations=stringRedisTemplate.boundHashOps(courseKey);
            logger.info("实验ID: "+boundHashOperations.get("id")+" 已选人数: "+
                    boundHashOperations.get("selected"));
            courseMapper.updateCourseIntoSql(Integer.valueOf((String)boundHashOperations.get("id")),
                    Integer.valueOf((String)boundHashOperations.get("selected")));
        }
        logger.info("更新完成");
    }
    @Scheduled(cron = "0 0 1 * * *")
    @Override
    public void updateCourseCode() {
        logger.info("开始自动更新实验记录的状态(将时间小于等于今日的实验记录状态设为不可选)(每天凌晨一点)");
        courseCodeMapper.updateCourseCode();
    }
}
