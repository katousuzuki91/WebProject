package cn.com.skynet.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import cn.com.skynet.database.entity.Paper;
import cn.com.skynet.database.entity.User;
import cn.com.skynet.database.mongodb.dao.MongodbPaperDao;
import cn.com.skynet.database.mongodb.dao.MongodbUserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mvc.xml"})
public class MongodbTest
{

    @Resource
    private MongoTemplate mongoTemplate;
    
    @Resource
    private MongodbPaperDao dao;
    
    @Resource
    private MongodbUserDao userDao;
    
    @Test
    public void testinsertPaper()
    {
        Paper p = new Paper("125166666asdasdfsd", "lom", "aaaa", "aaaaaaa", new Date(), new Date());
        mongoTemplate.insert(p);;
    }
    
    @Test
    public void testdeletePaper()
    {
        Paper p = new Paper("125166666asdasdfsd", "lom", "aaaa", "aaaaaaa", new Date(), new Date());
        mongoTemplate.remove(p);;
    }
    
    @Test 
    public void testdeleteid()
    {
        String id = "125166666asdasdfsd";
        dao.deletePaperById(id);
    }
    
    @Test
    public void testFind()
    {
        Paper p = mongoTemplate.findById("125166666asdasdfsd", Paper.class);
        if(null != p)
        {
            System.out.println(p.getAuthor());
        }
        else
        {
            System.out.println("id is not exists");
        }
    }
    
    @Test
    public void testUser()
    {
        User findByName = userDao.findByName("aaa");
        System.out.println(findByName.getId());
        User user = userDao.findById(findByName.getId());
        System.out.println(user.getId() + ":" + user.getEmail());
    }
    
    @Test
    public void testMongo() 
    {
        List<ServerAddress> adds = new ArrayList<>();
        //ServerAddress()两个参数分别为 服务器地址 和 端口
        ServerAddress serverAddress = new ServerAddress("192.168.1.177", 27017);
        adds.add(serverAddress);

        List<MongoCredential> credentials = new ArrayList<>();
        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("admin", "admin", "admin".toCharArray());
        credentials.add(mongoCredential);

        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(adds, credentials);
        MongoDatabase databaseNames = mongoClient.getDatabase("test");
        System.out.println(databaseNames.getName());
        MongoCollection<org.bson.Document> collection = databaseNames.getCollection("test");
        System.out.println(collection.getNamespace());
        System.out.println(collection.count());
        org.bson.Document document = new org.bson.Document("name", "张三2");
        collection.insertOne(document);
    }
}
