package com.physics.service.impl;

import com.physics.controller.AnserController;
import com.physics.mapper.CourseMapper;
import com.physics.pojo.Course;
import com.physics.pojo.CourseVo;
import com.physics.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CourseServiceImpl.class);
    private String shal=null;
    private String COURSE_SCHEDULE_SET="product_schedule_set";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private final String Course_Update_Cript=
            "redis.call('sadd', KEYS[1], ARGV[1]) \n"
            +"local course = 'course_'..ARGV[1] \n"
            +"local quantity = tonumber(ARGV[2]) \n"
            +"local select = tonumber(ARGV[3]) \n"
            +"local weekly = tonumber(ARGV[4]) \n"
            +"local lesson = tonumber(ARGV[5]) \n"
            +"redis.call('hset',course,'number',tostring(quantity)) \n"
            +"redis.call('hset',course,'selected',tostring(select)) \n"
            +"redis.call('hset',course,'id',tostring(ARGV[1])) \n"
            +"redis.call('hset',course,'weekly',tostring(weekly)) \n"
            +"redis.call('hset',course,'lesson',tostring(lesson)) \n"
            +"return 1 \n";
    private final String Course_Del_Cript=
            "local course = 'course_'..ARGV[1] \n"
            +"local number = tonumber(redis.call('hget',course,'number')) \n"
            +"local selected = tonumber(redis.call('hget',course,'selected')) \n"
            +"if number == selected then return 0 end \n"
//            +"number = number - 1 \n"
            +"selected = selected + 1 \n"
            +"redis.call('hset',course,'selected',tostring(selected)) \n"
            +"return 1 \n";
    private final String Course_Add_Cript=
            "local course = 'course_'..ARGV[1] \n"
              +"local selected = tonumber(redis.call('hget',course,'selected')) \n"
                +"if selected == 0 then return 0 end \n"
                +"selected = selected - 1 \n"
                 +"redis.call('hset',course,'selected',tostring(selected)) \n"
                  +"return 1 \n";
    @Autowired
    CourseMapper courseMapper;
    @Scheduled(cron = "0 0 3 * * *")
    public void updateCourse(){
        logger.info("取出数据库数据(实验)更新入Redis(日期大于今天)(每天凌晨三点)");
        List<Course> courses=courseMapper.queryAll();
        for(Course course : courses){
            update_Couse((long)course.getId(),(long)course.getNumber(),(long)course.getSelected(),
                    Long.parseLong(course.getWeek()),(long)course.getLesson_id());
            logger.info("实验名: "+course.getName()+
                    " 实验日期: "+course.getWeekly_time()+
                    " 实验地点: "+course.getAddress()+
                    " 实验人数: "+course.getNumber()+
                    "实验状态: "+course.getStatus());
        }
        logger.info("更新结束");
    }
    public boolean update_Couse(Long course_id,Long number,Long selected,Long weekly,Long lesson){
        Jedis jedis=null;
        try {
            jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
            shal=jedis.scriptLoad(Course_Update_Cript);
            Object result=jedis.evalsha(shal,1,COURSE_SCHEDULE_SET,course_id+"",
                    number+"",selected+"",weekly+"",lesson+"");
            Long res=(Long)result;
            return res == 1;
        }finally {
            if( jedis != null && jedis.isConnected()){
                jedis.close();
            }
        }
    }
    public List<CourseVo> queryAll(){
        List<CourseVo> courseVos=new ArrayList<>();
        BoundSetOperations<String, String> boundSetOperations =
                stringRedisTemplate.boundSetOps(COURSE_SCHEDULE_SET);
        Set<String> CourseIdList=boundSetOperations.members();
        for(String courseIdStr : CourseIdList){
            Long courseId = Long.parseLong(courseIdStr);
            String courseKey="course_"+courseId;
            BoundHashOperations boundHashOperations=stringRedisTemplate.boundHashOps(courseKey);
            if(Integer.valueOf((String)boundHashOperations.get("selected")) !=
                    Integer.valueOf((String)boundHashOperations.get("number"))) {
                CourseVo courseVo=new CourseVo();
                courseVo.setWeekly(Integer.valueOf((String)boundHashOperations.get("weekly")));
                courseVo.setLesson_id(Integer.valueOf((String)boundHashOperations.get("lesson")));
                courseVos.add(courseVo);
            }
        }
        return courseVos;
    }
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    public int delStock(int lesson_id,String lesson_weekly,String lesson_week_day,String lesson_week_day_section,Course course){
        if(course == null){
            return 2;
        }
        Jedis jedis=null;
        try {
            jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
            shal=jedis.scriptLoad(Course_Del_Cript);
            Object result=jedis.evalsha(shal,0,course.getId()+"");
            long res=(Long)result;
            return res == 1 ? 1 : 0;
        }finally {
            if( jedis != null && jedis.isConnected()){
                jedis.close();
            }
        }
    }
//    没有使用
    public int querySelected(int course_id){
        long courseId = course_id;
        String courseKey="course_"+courseId;
        BoundHashOperations boundHashOperations=stringRedisTemplate.boundHashOps(courseKey);
        int selected=Integer.parseInt((String)boundHashOperations.get("selected"));
        return selected;
    }
    public boolean addStock(int course_id){
        Jedis jedis=null;
        try {
            jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
            shal=jedis.scriptLoad(Course_Add_Cript);
            Object result=jedis.evalsha(shal,0,course_id+"");
            long res=(Long)result;
            return res == 1;
        }finally {
            if( jedis != null && jedis.isConnected()){
                jedis.close();
            }
        }
    }
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<Course> queryCourseByLessonId(int lesson_id,String course_section) {
        return courseMapper.queryCourseByLessonId(lesson_id,course_section);
    }
}
