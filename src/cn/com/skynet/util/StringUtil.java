package cn.com.skynet.util;

public class StringUtil
{

    public static boolean isNullDot(String ... str)
    {
        if(null != str && str.length > 0)
        {
            return false;
        }
        return true;
    }
    
    public static boolean isNull(String str)
    {
        if(null != str && !"".equals(str))
        {
            return false;
        }
        return true;
    }
}
