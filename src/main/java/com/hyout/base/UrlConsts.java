package com.hyout.base;

/**
 * Created by Administrator on 2018/8/24.
 */
public class UrlConsts {
    //银联一码付下单地址
    public static final String UNION_ONE_CODE_PAY = "https://c.heepay.com/newOnlineBank/preorder.do";
    //银联扫码下单地址
    public static final String UNION_CODE_PAY = "https://open.heepay.com/unionpay/qRCodeGenerate.do";
    //快捷H5下单地址
    public static final String QUICK_H5_PAY = "https://open.heepay.com/quickpay/index.do";
    //聚合下单地址
    public static final String AGGR_PAY = "https://open.heepay.com/aggrPay.do";
    //聚合查单地址
    public static final String AGGR_PAY_QUERY = "https://open.heepay.com/aggrPayQuery.do";
    //聚合查单地址
    public static final String OTHER_PAY_QUERY = "https://open.heepay.com/{productCode}/transQuery.do";
}
