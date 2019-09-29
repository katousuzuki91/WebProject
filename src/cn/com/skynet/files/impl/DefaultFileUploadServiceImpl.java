package cn.com.skynet.files.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import cn.com.skynet.files.FileUploadService;
import cn.com.skynet.net.service.FtpService;
import cn.com.skynet.util.MapUtil;

@Component
public class DefaultFileUploadServiceImpl implements FileUploadService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFileUploadServiceImpl.class);
    
    @Resource
    private FtpService ftpService;
    
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;
    
    @Override
    public List<String> saveFile(Map<String, MultipartFile> fileMap)
    {
        List<String> result = new ArrayList<>();
        
        if(MapUtil.isNull(fileMap)) return result;
        
        String fileName =  "";
        for (Map.Entry<String, MultipartFile> each : fileMap.entrySet())
        {
            MultipartFile file = each.getValue();
            try
            {
                fileName = new String(file.getOriginalFilename().getBytes(), "UTF-8");
                file.transferTo(new File(fileName));
                result.add(fileName);
            } 
            catch (IllegalStateException e)
            {
                e.printStackTrace();
            } 
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public List<String> up2Ftp(List<File> files)
    {
        List<String> result = new ArrayList<>();
        List<Future<Boolean>> futures = new ArrayList<>();
        for (File file : files)
        {
            Future<Boolean> f = taskExecutor.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception
                {
                    LOGGER.info("start ftp upload thread " + Thread.currentThread());
                    return ftpService.fileUpload2Ftp(ftpService.ftpClient.get(), file.getName(), new FileInputStream(file));
                }});
            futures.add(f);
        }
        return result;
    }
    

}
