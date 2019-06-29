package cn.com.skynet.database.mongodb.dao;

import cn.com.skynet.database.entity.Paper;

public interface MongodbPaperDao
{
    void insertPaper(Paper paper);
    
    Paper findById(String id);
    
    void deletePaperById(String id);
    
    void deletePaper(Paper paper);
}
