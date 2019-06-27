package cn.com.skynet.common;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import cn.com.skynet.constant.HttpRequestContant;

public class ResponseResult
{
    public static Object success()
    {
        return success(HttpRequestContant.REQUEST_SUCCESS, "success", new JSONObject());
    }
    
    public static Object success(String errorCode, String errorMsg)
    {
        return success(errorCode, errorMsg, new JSONObject());
    }
    
    public static Object success(String errorCode, String errorMsg, Object data)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("errorCode", errorCode);
        map.put("errorMsg", errorMsg);
        map.put("data", data);
        return JSONObject.toJSON(map);
    }
}
