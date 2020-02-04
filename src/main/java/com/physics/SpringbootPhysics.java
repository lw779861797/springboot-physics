package com.physics;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Controller
@MapperScan(basePackages = "com.physics.mapper",sqlSessionFactoryRef = "sqlSessionFactory",annotationClass = Repository.class)
@SpringBootApplication(scanBasePackages = "com.physics")
@EnableTransactionManagement
@EnableScheduling
public class SpringbootPhysics extends SpringBootServletInitializer {
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private RedisTemplate redisTemplate = null;
    @Autowired
    private RedisConnectionFactory connectionFactory = null;
    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName = null;
    @Value("${rabbitmq.queue.coursecode}")
    private String courseCodeQueueName = null;
    public static void main(String[] args) {
        SpringApplication.run(SpringbootPhysics.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootPhysics.class);
    }
//    配置redis管理器
    @Bean(name = "redisCacheManager" )
    public RedisCacheManager initRedisCacheManager() {
        // Redis加锁的写入器
        RedisCacheWriter writer= RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        // 启动Redis缓存的默认设置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // 设置JDK序列化器
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
        // 禁用前缀
        config = config.disableKeyPrefix();
        //设置10分钟超时
        config = config.entryTtl(Duration.ofMinutes(10));
        // 创建缓Redis存管理器
        RedisCacheManager redisCacheManager = new RedisCacheManager(writer, config);
        return redisCacheManager;
    }
    private void initRedisTemplate() {
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
    }
    // 自定义初始化方法,运行springboot 则会初始化
    @PostConstruct
    public void init() {
        initRedisTemplate();
    }
//    @Bean
//    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
////        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);    // 修改了messageConverter后需要重新设置手动确认
//        return factory;
//    }
//    @Bean(name = "mqConsumerlistenerContainer")
//    public SimpleRabbitListenerContainerFactory mqConsumerlistenerContainer(){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(cachingConnectionFactory);
//        factory.setPrefetchCount(100);
//        return factory;
//    }
    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
    @Bean
    public Queue createQueueMsg(){
//        创建字符串消息队列，boolean值代表是否持久化消息
        return new Queue(msgQueueName);
    }
    @Bean
    public Queue createQueueCourseCode(){
//        创建课程记录消息队列，boolean值代表是否持久化消息
        return new Queue(courseCodeQueueName,true);
    }
}
