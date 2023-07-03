package cn.com.skynet.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import cn.com.skynet.common.ResponseResult;
import cn.com.skynet.files.impl.DefaultFileUploadServiceImpl;

@Controller
public class FileUploadController
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
    
    @Resource
    private DefaultFileUploadServiceImpl fileUploadService;
    
    @RequestMapping(value="/image/upload", method= {RequestMethod.POST})
    public @ResponseBody Object imageUpload2Local(HttpServletRequest request)
    {

        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
        List<String> list = fileUploadService.saveFile(mulRequest.getFileMap());
        LOGGER.info(list.toString());
        return ResponseResult.success(list);
    }
    
    @RequestMapping(value="/image/upload/ftp", method= {RequestMethod.POST})
    public @ResponseBody Object imageUpload2Ftp(HttpServletRequest request)
    {
        List<File> f = new ArrayList<>();
        f.add(new File("d:\\tmp\\1.png"));
        fileUploadService.up2Ftp(f);
        return ResponseResult.success();
    }
}
