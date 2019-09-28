package cn.com.skynet.database.mongodb.dao;

import java.util.List;
import java.util.Map;
import cn.com.skynet.database.entity.Paper;

public interface MongodbPaperDao
{
    void insertPaper(Paper paper);
    
    Paper findById(String id);
    
    void deletePaperById(String id);
    
    void deletePaper(Paper paper);
    
    List<Paper> findPapers(Map<String, String> params);
}
