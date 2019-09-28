package cn.com.skynet.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogWriter
{
    @Pointcut("@annotation(cn.com.skynet.spring.aop.LogRecoder)")
    public void annotationPointCut()
    {
        System.out.println("annotationPointCut");
    }
    
    @Before("annotationPointCut()")
    public void before()
    {
        System.out.println("before");
    }
    
    @After("annotationPointCut()")
    public void after()
    {
        System.out.println("after");
    }
    
    @Before("execution(** cn.com.skynet.database.mongodb.dao.MongodbPaperDao.deletePaperById(..))")
    public void beforeMethod()
    {
        System.out.println("before method execute");
    }
}
