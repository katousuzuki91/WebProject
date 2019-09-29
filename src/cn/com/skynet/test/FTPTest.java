package cn.com.skynet.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mvc.xml"})
public class FTPTest
{
    
    @Test
    public void testFtp()
    {
        FTPClient client = new FTPClient();
        try
        {
            client.connect("192.168.1.141", 21);
            client.login("katou", "oomoyi");
            String[] names = client.listNames();
            for (String string : names)
            {
                
                System.out.println(string);
            }
        } catch (SocketException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    public void upload()
    {
        int defaultTimeoutSecond = 1, connectTimeoutSecond = 1, dataTimeoutSecond = 1;
        FTPClient ftp = new FTPClient();
        File f = new File("d:\\tmp\\问题14截图2.png");
        try
        {
            ftp.connect("192.168.1.141", 21);
            ftp.login("katou", "oomoyi");
            ftp.setDefaultTimeout(defaultTimeoutSecond * 60);
            ftp.setConnectTimeout(connectTimeoutSecond * 60);
            ftp.setDataTimeout(dataTimeoutSecond * 1000);
            ftp.setControlEncoding("UTF-8");
            
            ftp.enterLocalPassiveMode();
            System.out.println(ftp.getReplyCode());
            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            if(!FTPReply.isPositiveCompletion(ftp.getReplyCode()))
            {
                throw new RuntimeException("ftp connect failed");
            }
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.storeFile("test.png", new FileInputStream(f));
        } catch (SocketException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
