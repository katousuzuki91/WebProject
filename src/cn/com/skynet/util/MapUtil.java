package cn.com.skynet.util;

import java.util.Map;

public class MapUtil
{
    public static boolean isStringMapEmpty(Map<String, String> map)
    {
        return isNull(map);
    }
    
    public static boolean isNull(Map<?, ?> map)
    {
        if(null == map || map.size() == 0)
        {
            return true;
        }
        return false; 
    }

}
