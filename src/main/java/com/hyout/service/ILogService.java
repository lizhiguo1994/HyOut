package com.hyout.service;

import com.hyout.base.ServerResponse;

/**
 * Created by Administrator on 2018/8/30.
 */
public interface ILogService {
    ServerResponse byOrderQuery(String order);
}
