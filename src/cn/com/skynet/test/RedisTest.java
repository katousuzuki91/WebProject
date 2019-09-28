package cn.com.skynet.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
        redisTemplate.opsForList().rightPush("myList-myList", "T");
        System.out.println(redisTemplate.opsForList().size("myList"));
    }
    
    @Test
    public void testRedisService()
    {
        List<Object> lGet = redisService.lGet("myList-myList", 0, -1);
        if(lGet.size() > 0)
        {
            System.out.println(lGet.get(0).toString());
            redisService.lRemove("myList-myList", "T", 1);
        }
        System.out.println(lGet.size());
    }
    
    @Test
    public void testSession()
    {
        Object hgets = redisService.hget("session", "test");
        System.out.println(hgets);
//        String s = (String) redisService.hget("spring:session:sessions:46a05280-c718-413c-ab3a-fbe396c950da", "maxInactiveInterval");
//        System.out.println(s + "====");
        
//        Set<Object> set = redisService.sGet("spring.session.expirations.1566372960000");
//        System.out.println(set.size() + " size");
//        Iterator<Object> iterator = set.iterator();
//        while(iterator.hasNext())
//        {
//            System.out.println(iterator.next());
//        }
//        String st = (String) redisService.get("spring.session.sessions.expires.46a05280-c718-413c-ab3a-fbe396c950da");
//        System.out.println(st + "+++++");
    }

}
