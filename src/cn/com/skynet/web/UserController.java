package cn.com.skynet.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.com.skynet.common.ResponseResult;
import cn.com.skynet.constant.HttpRequestContant;
import cn.com.skynet.database.entity.User;
import cn.com.skynet.database.mongodb.dao.MongodbUserDao;

@Controller
public class UserController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Resource
    private MongodbUserDao userDao;
    @RequestMapping(value = "/regist", method = {RequestMethod.POST})
    public @ResponseBody Object regist(HttpServletRequest request)
    {
        String uname = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String email = request.getParameter("email");
        User user = userDao.findByName(uname);
        if(null == user)
        {
            userDao.addUser(new User(uname, pwd, email));
        }
        else
        {
            LOGGER.info("user {} already exists.", uname);
            return ResponseResult.success(HttpRequestContant.REQUEST_FAIL, "user already exists");
        }
        return ResponseResult.success();
        /**
         * 
         * 熱いお湯
         * 冷たい水
         * 浴衣がちょっといい
         * ちょっぢ待って下さい
         * 北京から横浜まで飛行機で行きます
         */
    }

}
