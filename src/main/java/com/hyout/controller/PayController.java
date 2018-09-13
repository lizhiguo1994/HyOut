package com.hyout.controller;

import com.hyout.base.PayTypeConsts;
import com.hyout.base.ResponseConsts;
import com.hyout.base.ServerResponse;
import com.hyout.service.IPayQueryService;
import com.hyout.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/8/22.
 * @RequestMapping(value = "/get/")
 */
@Controller
public class PayController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IPayService iPayService;
    @Autowired
    private IPayQueryService iPayQueryService;

    @RequestMapping(value = "pay")
    @ResponseBody
    public ServerResponse pay(){
        String referer = request.getHeader("Referer");
        if (referer.contains(PayTypeConsts.EBANK_PAY)){//网银支付
            return iPayService.quickPcOrEBankPay(request, PayTypeConsts.EBANK_PAY);
        }
        if (referer.contains(PayTypeConsts.UNION_ONE_CODE_PAY)){//银联一码付
            return iPayService.unionOneCodePay(request, PayTypeConsts.UNION_ONE_CODE_PAY);
        }
        if (referer.contains(PayTypeConsts.UNION_CODE_PAY)){//银联扫码
            return iPayService.unionPayCode(request, PayTypeConsts.UNION_CODE_PAY);
        }
        if (referer.contains(PayTypeConsts.UNION_PAY_PC)){//银联PC
            return iPayService.unionPcOrWAP(request, PayTypeConsts.UNION_PAY_PC);
        }
        if (referer.contains(PayTypeConsts.UNION_PAY_WAP)){//银联WAP
            return iPayService.unionPcOrWAP(request, PayTypeConsts.UNION_PAY_WAP);
        }
        if (referer.contains(PayTypeConsts.QUICK_PAP_PC)){//快捷PC
            return iPayService.quickPcOrEBankPay(request, PayTypeConsts.QUICK_PAP_PC);
        }
        if (referer.contains(PayTypeConsts.QUICK_PAP_H5)){//快捷H5
            return iPayService.quickH5Pay(request, PayTypeConsts.QUICK_PAP_H5);
        }
        if (referer.contains(PayTypeConsts.WEI_XIN_PC)){//微信扫码
            return iPayService.aggrPcOrH5(request, PayTypeConsts.WEI_XIN_PC);
        }
        if (referer.contains(PayTypeConsts.ALI_PAY_PC)){//支付宝扫码
            return iPayService.aggrPcOrH5(request, PayTypeConsts.ALI_PAY_PC);
        }
        if (referer.contains(PayTypeConsts.QQ_PC)){//QQ扫码
            return iPayService.aggrPcOrH5(request, PayTypeConsts.QQ_PC);
        }
        if (referer.contains(PayTypeConsts.JD_PC)){//京东扫码
            return iPayService.aggrPcOrH5(request, PayTypeConsts.JD_PC);
        }
        if (referer.contains(PayTypeConsts.WEI_XIN_H5)){//微信H5
            return iPayService.aggrPcOrH5(request, PayTypeConsts.WEI_XIN_H5);
        }
        if (referer.contains(PayTypeConsts.ALI_PAY_H5)){//微信H5
            return iPayService.aggrPcOrH5(request, PayTypeConsts.ALI_PAY_H5);
        }
        if (referer.contains(PayTypeConsts.WEI_XIN_MICRO)){//微信刷卡
            return iPayService.aggrPcOrH5(request, PayTypeConsts.WEI_XIN_MICRO);
        }
        if (referer.contains(PayTypeConsts.ALI_PAY_MICRO)){//支付宝刷卡
            return iPayService.aggrPcOrH5(request, PayTypeConsts.ALI_PAY_MICRO);
        }
        if (referer.contains(PayTypeConsts.WEI_XIN_PUB)){//支付宝公众号
            return iPayService.aggrPcOrH5(request, PayTypeConsts.WEI_XIN_PUB);
        }
        //非法来源
        return ServerResponse.createByError(ResponseConsts.ILLEGAL_SOURCES_ERROR.getCode(),ResponseConsts.ILLEGAL_SOURCES_ERROR.getDesc());
    }


    @RequestMapping(value = "payQuery")
    @ResponseBody
    public ServerResponse payQuery(){
        String referer = request.getHeader("Referer");
        if (referer.contains(PayTypeConsts.EBANK_PAY)){//网银支付
        }
        if (referer.contains(PayTypeConsts.UNION_ONE_CODE_PAY)){//银联一码付
        }
        if (referer.contains(PayTypeConsts.UNION_CODE_PAY)){//银联扫码
        }
        if (referer.contains(PayTypeConsts.UNION_PAY_PC)){//银联PC
        }
        if (referer.contains(PayTypeConsts.UNION_PAY_WAP)){//银联WAP
        }
        if (referer.contains(PayTypeConsts.QUICK_PAP_PC)){//快捷PC
        }
        if (referer.contains(PayTypeConsts.QUICK_PAP_H5)){//快捷H5
        }
        if (referer.contains(PayTypeConsts.AGGR_PAY_QUERY)){//聚合支付查询
            return iPayQueryService.aggrPayQuery(request);
        }
        if (referer.contains(PayTypeConsts.OTHER_PAY_QUERY)){//其他支付查询
            return iPayQueryService.otherPayQuery(request);
        }
        //非法来源
        return ServerResponse.createByError(ResponseConsts.ILLEGAL_SOURCES_ERROR.getCode(),ResponseConsts.ILLEGAL_SOURCES_ERROR.getDesc());
    }




}
