package com.lsjc.beta.controller;

import com.alibaba.fastjson.JSONObject;
import service.impl.DemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description: 测试用例
 * @author: zhang zl
 * @created: 2023/04/04 10:02
 */

@RestController
@RequestMapping("demo")
public class Demo01Controller {
    @Autowired
    private DemoServiceImpl demoServiceImpl;

    @RequestMapping("demo01")
    private JSONObject demo() {

        return demoServiceImpl.demo01();
    }
}
