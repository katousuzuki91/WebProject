package cn.com.skynet.test;

import java.security.NoSuchAlgorithmException;
import org.junit.Test;
import cn.com.skynet.util.MD5Util;

public class UtilsTest
{
    @Test
    public void teststring2MD5()
    {
        String s = "";
        try
        {
            s = MD5Util.string2MD5("aaa");
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
