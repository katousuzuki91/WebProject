package cn.com.skynet.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import cn.com.skynet.util.StringUtil;

@Component("redisService")
public class RedisService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    
    public void setRedisTemplaete(RedisTemplate<String, Object> redisTemplate)
    {
        this.redisTemplate = redisTemplate;
    }
    
    /**
     * set expire time by key   
     * @param key
     * @param time（秒）
     * @return
     */
    public boolean expire(String key, long time)
    {
        if(time > 0)
        {
            return redisTemplate.expire(key, time, TIME_UNIT);
        }
        return false;
    }
    
    /**
     * get expire time by key
     * @param key
     * @return
     */
    public long getExpire(String key)
    {
        return redisTemplate.getExpire(key, TIME_UNIT);
    }
    
    /**
     * if key exists
     * @param key
     * @return
     */
    public boolean hasKey(String key)
    {
        return redisTemplate.hasKey(key);
    }
    
    /**
     * delete redis cache
     * @param key (can be one or more)
     */
    @SuppressWarnings("unchecked")
    public void del(String ... key)
    {
        if(!StringUtil.isNullDot(key))
        {
            if(key.length == 1)
            {
                redisTemplate.delete(key[0]);
            }
            else
            {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
    
    /**
     * get value by key
     * @param key
     * @return
     */
    public Object get(String key)
    {
        if(!StringUtil.isNull(key))
        {
            redisTemplate.opsForValue().get(key);
        }
        return null;
    }
    
    /**
     * set value
     * @param key
     * @param value
     */
    public void set(String key, Object value)
    {
        redisTemplate.opsForValue().set(key, value);
    }
    
    /**
     * set value and expire time
     * @param key
     * @param value
     * @param time
     */
    public void set(String key, Object value, long time)
    {
        if(time > 0)
        {
            redisTemplate.opsForValue().set(key, value, time);
        }
        else
        {
            set(key, value);
        }
    }
    
    /**
     * incr, 递增
     * @param key
     * @param delta 递增因子，大于0
     * @return
     */
    public long incr(String key, long delta)
    {
        if(delta <= 0)
        {
            throw new RuntimeException("delta must larger than 0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }
    
    /**
     * decr, 递减
     * @param key
     * @param delta
     * @return
     */
    public long decr(String key, long delta)
    {
        if(delta <= 0)
        {
            throw new RuntimeException("delta must larger than 0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }
    
    /**
     * get value from HashGet
     * @param key, can't be null
     * @param item, can't be null
     * @return
     */
    public Object hget(String key, String item)
    {
        return redisTemplate.opsForHash().get(key, item);
    }
    
    /**
     * get all key-value of HashKey
     * @param key
     * @return
     */
    public Map<Object, Object> hmget(String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }
    
    /**
     * HashSet
     * @param key
     * @param value
     */
    public void hmset(String key, Map<String, Object> value)
    {
        redisTemplate.opsForHash().putAll(key, value);
    }
    
    /**
     * HashSet, set time
     * @param key
     * @param value
     * @param time(秒)
     * @return
     */
    public boolean hmset(String key, Map<String, Object> value, long time)
    {
        redisTemplate.opsForHash().putAll(key, value);
        return expire(key, time);
    }
    
    /**
     * set data in hashmap
     * @param key
     * @param item
     * @param value
     */
    public void hset(String key, String item, Object value)
    {
        redisTemplate.opsForHash().put(key, item, value);
    }
    
    /**
     * set data in hash, and set expire time
     * @param key
     * @param item
     * @param value
     * @param time
     * @return
     */
    public boolean hset(String key, String item, Object value, long time)
    {
        redisTemplate.opsForHash().put(key, item, value);
        return expire(key, time);
    }
    
    /**
     * delete value in hash
     * @param key
     * @param objects
     */
    public void hdel(String key, Object ... objects)
    {
        redisTemplate.opsForHash().delete(key, objects);
    }
    
    /**
     * if exists hashKey
     * @param key
     * @param item
     * @return
     */
    public boolean hHasKey(String key, String item)
    {
        return redisTemplate.opsForHash().hasKey(key, item);
    }
    
    /**
     * get set by value
     * @param key
     * @return
     */
    public Set<Object> sGet(String key)
    {
        return redisTemplate.opsForSet().members(key);
    }
    
    /**
     * if exists value in set
     * @param key
     * @param value
     * @return
     */
    public boolean sHasKey(String key, Object value)
    {
        return redisTemplate.opsForSet().isMember(key, value);
    }
    
    /**
     * put data into set
     * @param key
     * @param objects
     * @return
     */
    public long sSet(String key, Object ... objects)
    {
        return redisTemplate.opsForSet().add(key, objects);
    }
    
    /**
     * put data into set and set expire time
     * @param key
     * @param time
     * @param objects
     * @return
     */
    public long sSet(String key, long time, Object ... objects)
    {
        Long count = redisTemplate.opsForSet().add(key, objects);
        expire(key, time);
        return count;
    }
    
    /**
     * get size of set
     * @param key
     * @return
     */
    public long sSetSize(String key)
    {
        return redisTemplate.opsForSet().size(key);
    }
    
    /**
     * remove data of set
     * @param ket
     * @param objects
     * @return
     */
    public long setRemove(String ket, Object ... objects)
    {
        return redisTemplate.opsForSet().remove(ket, objects);
    }
    
    /**
     * get list
     * @param key
     * @param start
     * @param end (0, -1) means all the list
     * @return
     */
    public List<Object> lGet(String key, long start, long end)
    {
        return redisTemplate.opsForList().range(key, start, end);
    }
    
    /**
     * get size of list refer to key
     * @param key
     * @return
     */
    public long lGetListSize(String key)
    {
        return redisTemplate.opsForList().size(key);
    }
    
    /**
     * get data of list refer to key, at the index of index value
     * @param key
     * @param index
     * @return
     */
    public Object lGetIndex(String key, long index)
    {
        return redisTemplate.opsForList().index(key, index);
    }
    
    /**
     * put data into list
     * @param key
     * @param value
     */
    public void lSet(String key, Object value)
    {
        redisTemplate.opsForList().rightPush(key, value);
    }
    
    /**
     * put data into list, and set expire time
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean lSet(String key, Object value, long time)
    {
        redisTemplate.opsForList().rightPush(key, value);
        return expire(key, time);
    }
    
    /**
     * put list of data into list
     * @param key
     * @param values
     */
    public void lSet(String key, List<Object> values)
    {
        redisTemplate.opsForList().rightPushAll(key, values);
    }
    
    
    /**
     * put list of data info list, and set expire time
     * @param key
     * @param values
     * @param time
     * @return
     */
    public boolean lSet(String key, List<Object> values, long time)
    {
        redisTemplate.opsForList().rightPushAll(key, values);
        return expire(key, time);
    }
    
    /** 
     * update list of index
     * @param key
     * @param value
     * @param index
     */
    public void lIpdataIndex(String key, Object value, long index)
    {
        redisTemplate.opsForList().set(key, index, value);
    }
    
    /**
     * remove list
     * @param key
     * @param value
     * @param count
     * @return
     */
    public long lRemove(String key, Object value, long count)
    {
        return redisTemplate.opsForList().remove(key, count, value);
    }
}
