package service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.RedisUtil;

/**
 * @program: demo
 * @description:
 * @author: zhang zl
 * @created: 2023/04/04 10:32
 */
@Service
public class DemoServiceImpl implements IDemoService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public JSONObject demo01() {
        JSONObject jsonObject = new JSONObject();

        redisUtil.set("name", "露娜");
        redisUtil.set("age", "19");

        jsonObject.put("name", redisUtil.get("name"));
        jsonObject.put("age", redisUtil.get("age"));

        return jsonObject;
    }
}
