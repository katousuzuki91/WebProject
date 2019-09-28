package cn.com.skynet.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class LoginController 
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    @Resource
    private MongodbUserDao userDao;
    
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public @ResponseBody Object login(HttpServletRequest request)
	{
	    String uname = request.getParameter("name");
	    String password = request.getParameter("password");
	    User user = userDao.findByName(uname);
	    
	    
	    if(null == user || !user.getPwd().equals(password))
	    {
	        return ResponseResult.success(HttpRequestContant.REQUEST_FAIL, "Login fail, check name and password");
	    }
	    HttpSession session = request.getSession();
	    LOGGER.info("session id is {}", session.getId());
	    String username = (String) session.getAttribute("username");
	    if(null != username && username.equals(uname))
	    {
	        LOGGER.info("username is {}", username);
	        return ResponseResult.success(HttpRequestContant.REQUEST_FAIL, "Login fail, user had logined in");
	    }
	    else
	    {
	        session.setAttribute("username", uname);
	    }
	    
	    return ResponseResult.success();
	}
}
