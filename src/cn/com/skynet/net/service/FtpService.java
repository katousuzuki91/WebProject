package cn.com.skynet.net.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class FtpService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FtpService.class);
    
    @Value("${ftp.host}")
    private String host;
    
    @Value("${ftp.port}")
    private int port;
    
    @Value("${ftp.username}")
    private String username;
    
    @Value("${ftp.password}")
    private String password;
    
    @Value("${ftp.basePath}")
    private String basePath;
    
    @Value("${ftp.defaultTimeoutSecond}")
    private String defaultTimeoutSecond;
    
    @Value("${ftp.connectTimeoutSecond}")
    private String connectTimeoutSecond;
    
    @Value("${ftp.dataTimeoutSecond}")
    private String dataTimeoutSecond;
    
    public ThreadLocal<FTPClient> ftpClient = new ThreadLocal<FTPClient>() {
        protected FTPClient initialValue() 
        {
            FTPClient ftp = new FTPClient();
            try
            {
                ftp.connect(host, port);
                ftp.login(username, password);
                ftp.setDefaultTimeout(Integer.valueOf(defaultTimeoutSecond) * 1000);
                ftp.setConnectTimeout(Integer.valueOf(connectTimeoutSecond) * 1000);
                ftp.setDataTimeout(Integer.valueOf(connectTimeoutSecond) * 1000);
                ftp.setControlEncoding("UTF-8");
                
                ftp.enterLocalPassiveMode();
                ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
                if(!FTPReply.isPositiveCompletion(ftp.getReplyCode()))
                {
                    throw new RuntimeException("ftp connect failed");
                }
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                return ftp;
            } catch (SocketException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        };
    };
    
    public boolean fileUpload2Ftp(FTPClient client, String fileName, InputStream input)
    {
        try
        {
            if(client == null) throw new RuntimeException("ftpclient is null");
            return client.storeFile(fileName, input);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
