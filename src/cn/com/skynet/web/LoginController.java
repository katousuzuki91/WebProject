package cn.com.skynet.web;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.com.skynet.common.ResponseResult;

@Controller
public class LoginController 
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public @ResponseBody Object login(HttpServletRequest request)
	{
	    String uname = request.getParameter("name");
	    String password = request.getParameter("password");
	    LOGGER.info("login request: name---{}, password---{}", uname, password);
	    return ResponseResult.success();
	}
}
