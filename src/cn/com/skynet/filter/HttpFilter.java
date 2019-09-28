package cn.com.skynet.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpFilter implements Filter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpFilter.class);
	@Override
	public void destroy() 
	{
	    
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{
	    HttpServletRequest hReq = (HttpServletRequest) request;
	    HttpServletResponse hRes = (HttpServletResponse) response;
	    
	    //deal with the CROS issues
	    hRes.setHeader("Access-Control-Allow-Origin", "*");
		chain.doFilter(hReq, hRes);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException 
	{
	    
	}

}
