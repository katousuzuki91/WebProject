package cn.com.skynet.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import cn.com.skynet.spring.aop.LogWriter;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses={LogWriter.class})
public class Configration
{
    public Configration() {
        System.out.println("Configration容器启动初始化。。。");
    }
    
}
