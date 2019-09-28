package cn.com.skynet.database.mongodb.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Component;
import com.mongodb.BasicDBObject;
import cn.com.skynet.database.entity.Paper;
import cn.com.skynet.database.mongodb.AbstractBaseMongoTemplate;
import cn.com.skynet.database.mongodb.dao.MongodbPaperDao;
import cn.com.skynet.util.MapUtil;

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

    @Override
    public List<Paper> findPapers(Map<String, String> params)
    {
        List<Paper> list = new ArrayList<Paper>();
        if(!MapUtil.isStringMapEmpty(params))
        {
            BasicQuery query = null;
            Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry<String, String> entry = it.next();
                query = new BasicQuery(new BasicDBObject(entry.getKey(), entry.getValue()));
            }
            list = mongoTemplate.find(query, Paper.class);
            return list;
        }
        list = mongoTemplate.findAll(Paper.class);
        return list;
    }
}
