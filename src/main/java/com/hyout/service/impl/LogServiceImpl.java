package com.hyout.service.impl;

import com.hyout.base.ResponseConsts;
import com.hyout.base.ServerResponse;
import com.hyout.dao.LogEventMapper;
import com.hyout.pojo.LogEvent;
import com.hyout.service.ILogService;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/8/30.
 */
@Service("iLogService")
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogEventMapper logEventMapper;

    @Override
    public ServerResponse byOrderQuery(String order) {
        List<LogEvent> logEventList = logEventMapper.selectByMerchantOrderNo(order);
        if (logEventList == null || logEventList.size() <= 0){
            return ServerResponse.createByError();
        }
        StringBuilder logString = new StringBuilder();
        for (LogEvent logItem : logEventList){
            logString.append(logItem.toString());
            logString.append("\n");
        }
        return ServerResponse.logBySuccess(logString.toString());



    }
}
