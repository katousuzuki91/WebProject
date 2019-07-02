package cn.com.skynet.database.mongodb.dao;

import cn.com.skynet.database.entity.User;

public interface MongodbUserDao
{
    void addUser(User user);
    
    User findById(String id);
    
    User findByName(String name);
}
