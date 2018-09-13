package com.hyout.service;

import com.hyout.base.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/9/5.
 */
public interface IPayQueryService {
    ServerResponse aggrPayQuery(HttpServletRequest request);
    ServerResponse otherPayQuery(HttpServletRequest request);
}
