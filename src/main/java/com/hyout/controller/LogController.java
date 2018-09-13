package com.hyout.controller;

import com.hyout.base.ResponseConsts;
import com.hyout.base.ServerResponse;
import com.hyout.service.ILogService;
import com.hyout.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/8/30.
 */
@Controller
public class LogController {

    @Autowired
    private ILogService iLogService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "log_query")
    @ResponseBody
    public ServerResponse logQuery(){
        String order = request.getParameter("order");
        return iLogService.byOrderQuery(order);
    }
}
