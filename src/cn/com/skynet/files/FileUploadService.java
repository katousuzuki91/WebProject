package cn.com.skynet.files;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService
{
    List<String> saveFile(Map<String, MultipartFile> fileMap);
    
    List<String> up2Ftp(List<File> files);
}
