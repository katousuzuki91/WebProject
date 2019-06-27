package cn.com.skynet.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util
{
    public static String string2MD5(String str) throws NoSuchAlgorithmException
    {
        MessageDigest md5 = MessageDigest.getInstance("md5");
        char[] charArr = str.toCharArray();
        byte[] byteArr = new byte[charArr.length];
        for(int i = 0; i < byteArr.length; i++)
        {
            byteArr[i] = (byte)charArr[i];
        }
        byte[] md5Bytes = md5.digest(byteArr);
        StringBuffer hexValue = new StringBuffer();
        for(int i = 0; i < md5Bytes.length; i++)
        {
            int val = ((int)md5Bytes[i])&0xff;
            if(val < 16)
            {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
