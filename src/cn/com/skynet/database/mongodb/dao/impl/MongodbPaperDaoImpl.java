package cn.com.skynet.database.mongodb.dao.impl;

import org.springframework.stereotype.Component;
import cn.com.skynet.database.entity.Paper;
import cn.com.skynet.database.mongodb.AbstractBaseMongoTemplate;
import cn.com.skynet.database.mongodb.dao.MongodbPaperDao;

@Component("mongodbPaperDao") 
public class MongodbPaperDaoImpl extends AbstractBaseMongoTemplate implements MongodbPaperDao
{
    @Override
    public void insertPaper(Paper paper)
    {
        mongoTemplate.insert(paper);
    }

    @Override
    public void deletePaper(Paper paper)
    {
        mongoTemplate.remove(paper);
    }

    @Override
    public Paper findById(String id)
    {
        return mongoTemplate.findById(id, Paper.class);
    }

    @Override
    public void deletePaperById(String id)
    {
        Paper p = mongoTemplate.findById(id, Paper.class);
        if(null != p)
        {
            mongoTemplate.remove(p);
        }
    }
}
