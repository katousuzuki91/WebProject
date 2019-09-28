package cn.com.skynet.web;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.com.skynet.common.ResponseResult;
import cn.com.skynet.database.entity.Paper;
import cn.com.skynet.database.mongodb.dao.MongodbPaperDao;

@Controller
public class ForumController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ForumController.class);
    
    @Resource
    private MongodbPaperDao paperDao;

    @RequestMapping(value = "/submit", method = {RequestMethod.POST})
    public @ResponseBody Object submit(HttpServletRequest request)
    {
        String id = String.valueOf(new Date().getTime());
        String author = "admin";
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Date createDate = new Date();
        Date lastModify = new Date();
        Paper paper = new Paper(id, title, author, content, createDate, lastModify);
        LOGGER.info(paper.toString());
        paperDao.insertPaper(paper);
        return ResponseResult.success();
    }
    
    @RequestMapping(value = "/showForum", method = {RequestMethod.POST})
    public @ResponseBody Object showForum(HttpServletRequest request)
    {
//        String id = request.getParameter("id");
//        Paper paper = paperDao.findById(id);
        List<Paper> list = paperDao.findPapers(null);
        return ResponseResult.success(list);
    }
    
    @RequestMapping(value = "/getForum", method = {RequestMethod.POST})
    public @ResponseBody Object getForum(HttpServletRequest request)
    {
        String id = request.getParameter("id");
        Paper paper = paperDao.findById(id);
        return ResponseResult.success(paper);
    }
}
