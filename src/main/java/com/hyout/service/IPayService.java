package com.hyout.service;

import com.hyout.base.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/8/24.
 */
public interface IPayService {

    ServerResponse quickPcOrEBankPay(HttpServletRequest request, String payType);

    ServerResponse unionOneCodePay(HttpServletRequest request, String payType);

    ServerResponse unionPayCode(HttpServletRequest request, String payType);

    ServerResponse unionPcOrWAP(HttpServletRequest request, String payType);

    ServerResponse quickH5Pay(HttpServletRequest request, String payType);

    ServerResponse aggrPcOrH5(HttpServletRequest request, String payType);
}
