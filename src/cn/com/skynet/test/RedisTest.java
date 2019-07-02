package cn.com.skynet.test;

import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.com.skynet.cache.redis.RedisService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mvc.xml"})
public class RedisTest
{
    @Resource
    private RedisTemplate redisTemplate;
    
    @Resource 
    private RedisService redisService;
    
    @Test
    public void testRedis()
    {
        redisTemplate.opsForList().rightPush("myList", "T");
        System.out.println(redisTemplate.opsForList().size("myList"));
    }
    
    @Test
    public void testRedisService()
    {
        List<Object> lGet = redisService.lGet("myList", 0, -1);
        if(lGet.size() > 0)
        {
            System.out.println(lGet.get(0).toString());
            redisService.lRemove("myList", "T", 1);
        }
        System.out.println(lGet.size());
    }

}
