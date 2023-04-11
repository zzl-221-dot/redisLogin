package com.lsjc.beta.controller;

import com.alibaba.fastjson.JSONObject;
import entity.Users;
import service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description: 单点登录
 * @author: zhang zl
 * @created: 2023/04/04 13:55
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private ILoginService loginServiceImpl;

    /**
     * 用户登录
     * @param users
     * @return
     */
    @RequestMapping("login")
    public JSONObject login(@RequestBody Users users){
        return loginServiceImpl.login(users);
    }

    /**
     * 用户注册
     * @param users
     * @return
     */
    @RequestMapping("registe")
    public JSONObject register(@RequestBody Users users){
        return loginServiceImpl.registe(users);
    }

    @RequestMapping("restPwd")
    public JSONObject restPwd(@RequestBody Users users){
        return loginServiceImpl.resetPassword(users);
    }

}
