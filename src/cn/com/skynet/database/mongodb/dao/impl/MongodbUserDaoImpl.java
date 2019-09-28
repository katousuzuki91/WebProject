package cn.com.skynet.database.mongodb.dao.impl;

import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Component;
import com.mongodb.BasicDBObject;
import cn.com.skynet.database.entity.User;
import cn.com.skynet.database.mongodb.AbstractBaseMongoTemplate;
import cn.com.skynet.database.mongodb.dao.MongodbUserDao;
import cn.com.skynet.spring.aop.LogRecoder;

@Component("mongodbUserDao") 
public class MongodbUserDaoImpl extends AbstractBaseMongoTemplate implements MongodbUserDao
{

    @Override
    public void addUser(User user)
    {
        mongoTemplate.insert(user);
        
    }

    @LogRecoder
    @Override
    public User findById(String id)
    {
        return mongoTemplate.findById(id, User.class);
    }

    @LogRecoder
    @Override
    public User findByName(String name)
    {
        BasicQuery query = new BasicQuery(new BasicDBObject("name", name));
        return mongoTemplate.findOne(query, User.class);
    }

}
