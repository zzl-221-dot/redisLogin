package com.lsjc.beta.service;

import com.alibaba.fastjson.JSONObject;
import com.lsjc.beta.entity.Users;

public interface ILoginService {
    JSONObject login(Users users);

    JSONObject registe(Users users);
}
