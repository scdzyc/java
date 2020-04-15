package com.scdzyc.session.config;

//import io.lettuce.core.RedisClient;
//import io.lettuce.core.RedisURI;
//import io.lettuce.core.resource.DefaultClientResources;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @EnableRedisHttpSession 注释创建Spring Bean与名称springSessionRepositoryFilter实现Filter。
 * 该过滤器负责替换HttpSessionSpring Session支持的实现。在这种情况下，Spring Session由Redis支持
 */
@EnableRedisHttpSession
public class RedisConfig {

    /**
     * 我们创建一个RedisConnectionFactory将Spring Session连接到Redis Server的。
     *  我们将连接配置为在默认端口（6379）上连接到localhost
     * @return
     */
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();//new LettuceConnectionFactory("120.24.251.165",6379);
    }


//    public RedisClient redisClient() {
//        return RedisClient.create("120.24.251.165:6379");//new (DefaultClientResources.create(),
//                //new RedisURI("120.24.251.165",6379, null));
//    }
}
