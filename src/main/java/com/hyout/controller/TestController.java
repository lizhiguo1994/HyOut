package com.hyout.controller;

import com.hyout.base.ServerResponse;
import com.hyout.pojo.NotifyData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/9/14.
 */
@Controller
public class TestController {

    @RequestMapping(value = "test")
    @ResponseBody
    public ServerResponse test(@RequestBody String param){
        System.out.println(param);
        return ServerResponse.createBySuccess(param);
    }
}
