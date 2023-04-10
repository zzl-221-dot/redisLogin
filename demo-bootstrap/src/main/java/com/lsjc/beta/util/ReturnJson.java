package com.lsjc.beta.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description: 返回
 * @author: zhang zl
 * @created: 2023/04/04 17:10
 */
public class ReturnJson {
    /**
     * 若成功后将json 返回
     *
     * @param login
     * @return
     */
    public JSONObject resStatus(boolean login, String message) {
        JSONObject jsonObject = new JSONObject();
        if (login) {
            jsonObject.put("code", 200);
            jsonObject.put("SUCCESS", "SUCCESS");
            jsonObject.put("MESSAGE", message);
        } else {
            jsonObject.put("code", 500);
            jsonObject.put("SUCCESS", "FALSE");
            jsonObject.put("MESSAGE", message);
        }

        return jsonObject;
    }
}
