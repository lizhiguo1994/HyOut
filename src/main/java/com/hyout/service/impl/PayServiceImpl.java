package com.hyout.service.impl;

import com.hyout.base.*;
import com.hyout.dao.OrderMapper;
import com.hyout.pojo.Order;
import com.hyout.service.IPayService;
import com.hyout.util.HttpUtil;
import com.hyout.util.SmallTools;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/8/24.
 */
@Service("iPayService")
public class PayServiceImpl implements IPayService{

    private Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 快捷PC或网银支付
     * @param request
     * @return
     */
    @Override
    public ServerResponse quickPcOrEBankPay(HttpServletRequest request, String payType) {
        //获取参数
        String queryString = request.getQueryString();
        //订单入库
        ServerResponse serverResponse = insertOrder(queryString,PayTypeConsts.EBANK_PAY);
        if (serverResponse != null){
            //insert返回不是null表示出现异常，直接返回
            return serverResponse;
        }
        try {
            queryString = URLDecoder.decode(queryString,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            //log提示urldecoe出错
            return ServerResponse.createByError(ResponseConsts.URLDECODER_ERROR.getCode(), ResponseConsts.URLDECODER_ERROR.getDesc());
        }
        //创建签名数据参数名集合
        ArrayList<String> nameList = new ArrayList<String>();
        nameList.add("merchantId");
        nameList.add("merchantOrderNo");
        nameList.add("merchantUserId");
        nameList.add("notifyUrl");
        nameList.add("payAmount");
        nameList.add("productCode");
        nameList.add("version");
        nameList.add("key");
        String[] signAndSignString = SmallTools.getSignAndSignString(queryString, PayTypeConsts.EBANK_PAY, nameList);
        return ServerResponse.createBySuccess(signAndSignString[0],signAndSignString[1]);
    }

    /**
     * 银联一码付
     * @param request
     * @return
     */
    @Override
    public ServerResponse unionOneCodePay(HttpServletRequest request, String payType) {
        //获取参数
        String queryString = request.getQueryString();
        //订单入库
        ServerResponse serverResponse = insertOrder(queryString,payType);
        if (serverResponse != null){
            //insert返回不是null表示出现异常，直接返回
            return serverResponse;
        }
        try {
            queryString = URLDecoder.decode(queryString,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            //urldecoe出错
            return ServerResponse.createByError(ResponseConsts.URLDECODER_ERROR.getCode(), ResponseConsts.URLDECODER_ERROR.getDesc());
        }
        String[] signAndSignString = SmallTools.getSignAndSignString(queryString, payType);
        String sign = signAndSignString[0];
        String signString = signAndSignString[1];
        //拼接请求参数
        String parameter = queryString + sign;
        String returnParameter = HttpUtil.sendPost(UrlConsts.UNION_ONE_CODE_PAY, parameter);
        //给前台返回支付链接
        JSONObject returnParameterJson = new JSONObject(returnParameter);
        if(returnParameterJson.getInt("retCode") == 1000){
            String payUrl;
            payUrl = returnParameterJson.getString("url");
            return ServerResponse.createBySuccess(sign,signString,payUrl);
        }
        return ServerResponse.createByError(ResponseConsts.ERROR.getCode(),returnParameter);
    }

    /**
     * 银联扫码
     * @param request
     * @return
     */
    @Override
    public ServerResponse unionPayCode(HttpServletRequest request, String payType) {
        //获取参数
        String queryString = request.getQueryString();
        //订单入库
        ServerResponse serverResponse = insertOrder(queryString, payType);
        if (serverResponse != null){
            //insert返回不是null表示出现异常，直接返回
            return serverResponse;
        }
        try {
            queryString = URLDecoder.decode(queryString,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            //urldecoe出错
            return ServerResponse.createByError(ResponseConsts.URLDECODER_ERROR.getCode(), ResponseConsts.URLDECODER_ERROR.getDesc());
        }
        String[] signAndSignString = SmallTools.getSignAndSignString(queryString, payType);
        String sign = signAndSignString[0];
        String signString = signAndSignString[1];
        //拼接请求参数
        String parameter = SmallTools.lastSplitString(queryString,"=") + sign;
        String returnParameter = HttpUtil.sendPost(UrlConsts.UNION_CODE_PAY, parameter);
        //给前台返回支付链接
        JSONObject returnParameterJson = new JSONObject(returnParameter);
        if(returnParameterJson.getInt("retCode") == 1){
            String payUrl;
            payUrl = returnParameterJson.getString("qrCode");
            return ServerResponse.createBySuccess(sign,signString,payUrl);
        }
        return ServerResponse.createByError(ResponseConsts.ERROR.getCode(),returnParameter);
    }

    /**
     * 银联PC或WAP
     * @param request
     * @return
     */
    @Override
    public ServerResponse unionPcOrWAP(HttpServletRequest request, String payType) {
        //获取参数
        String queryString = request.getQueryString();
        //订单入库
        ServerResponse serverResponse = insertOrder(queryString,payType);
        if (serverResponse != null){
            //insert返回不是null表示出现异常，直接返回
            return serverResponse;
        }
        try {
            queryString = URLDecoder.decode(queryString,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            //log提示urldecoe出错
            return ServerResponse.createByError(ResponseConsts.URLDECODER_ERROR.getCode(), ResponseConsts.URLDECODER_ERROR.getDesc());
        }
        //创建签名数据参数名集合
        ArrayList<String> nameList = new ArrayList<String>();
        nameList.add("callBackUrl");
        nameList.add("description");
        nameList.add("merchantId");
        nameList.add("merchantOrderNo");
        nameList.add("merchantUserId");
        nameList.add("notifyUrl");
        nameList.add("payAmount");
        nameList.add("key");
        String[] signAndSignString = SmallTools.getSignAndSignString(queryString, payType, nameList);
        return ServerResponse.createBySuccess(signAndSignString[0],signAndSignString[1]);
    }

    /**
     * 快捷H5
     * @param request
     * @param payType
     * @return
     */
    @Override
    public ServerResponse quickH5Pay(HttpServletRequest request, String payType) {
        //获取参数
        String queryString = request.getQueryString();
        //订单入库
        ServerResponse serverResponse = insertOrder(queryString, payType);
        if (serverResponse != null){
            //insert返回不是null表示出现异常，直接返回
            return serverResponse;
        }
        try {
            queryString = URLDecoder.decode(queryString,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            //urldecoe出错
            return ServerResponse.createByError(ResponseConsts.URLDECODER_ERROR.getCode(), ResponseConsts.URLDECODER_ERROR.getDesc());
        }
        //创建签名数据参数名集合
        ArrayList<String> nameList = new ArrayList<String>();
        nameList.add("merchantId");
        nameList.add("merchantOrderNo");
        nameList.add("merchantUserId");
        nameList.add("notifyUrl");
        nameList.add("payAmount");
        nameList.add("productCode");
        nameList.add("version");
        nameList.add("key");
        String[] signAndSignString = SmallTools.getSignAndSignString(queryString, payType, nameList);
        String sign = signAndSignString[0];
        String signString = signAndSignString[1];
        //拼接请求参数
        String parameter = SmallTools.lastSplitString(queryString,"=") + sign;
        String returnParameter = HttpUtil.sendPost(UrlConsts.QUICK_H5_PAY, parameter);
        //给前台返回支付链接
        JSONObject returnParameterJson = new JSONObject(returnParameter);
        if(returnParameterJson.getInt("respCode") == 1000){
            JSONObject data = returnParameterJson.getJSONObject("data");
            String tokenId = data.getString("tokenId");
            String hostUrl = data.getString("hostUrl");
            String payUrl = "https://c.heepay.com/pay/index.html";//支付URL
            payUrl = payUrl+"?tokenId="+tokenId+"&hostUrl="+hostUrl;
            return ServerResponse.createBySuccess(sign,signString,payUrl);
        }
        return ServerResponse.createByError(ResponseConsts.ERROR.getCode(),returnParameter);
    }

    /**
     * 聚合PC或H5
     * @param request
     * @param payType
     * @return
     */
    @Override
    public ServerResponse aggrPcOrH5(HttpServletRequest request, String payType) {
        //获取参数
        String queryString = request.getQueryString();
        //订单入库
        ServerResponse serverResponse = insertOrder(queryString,payType);
        if (serverResponse != null){
            //insert返回不是null表示出现异常，直接返回
            return serverResponse;
        }
        try {
            queryString = URLDecoder.decode(queryString,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            //log提示urldecoe出错
            return ServerResponse.createByError(ResponseConsts.URLDECODER_ERROR.getCode(), ResponseConsts.URLDECODER_ERROR.getDesc());
        }
        //创建签名数据参数名集合
        ArrayList<String> nameList = new ArrayList<String>();
        nameList.add("merchantBillNo");
        nameList.add("merchantId");
        nameList.add("notifyUrl");
        nameList.add("payAmt");
        nameList.add("requestTime");
        nameList.add("tradeType");
        nameList.add("userIp");
        nameList.add("version");
        nameList.add("key");
        String[] signAndSignString = SmallTools.getSignAndSignString(queryString, payType, nameList);
        String sign = signAndSignString[0];
        String signString = signAndSignString[1];
        //拼接请求参数
        String parameter = SmallTools.lastSplitString(queryString,"=") + sign;
        String returnParameter = HttpUtil.sendPost(UrlConsts.AGGR_PAY, parameter);
        //给前台返回支付链接
        JSONObject returnParameterJson = new JSONObject(returnParameter);
        if(returnParameterJson.getInt("retCode") == 1000){
            String payUrl = returnParameterJson.getString("payUrl");
            return ServerResponse.createBySuccess(sign, signString, payUrl);
        }
        return ServerResponse.createByError(ResponseConsts.ERROR.getCode(), returnParameter);
    }


    /**
     * 插入订单
     */
    private ServerResponse insertOrder(String queryString, String payType){
        String queryJsonString = SmallTools.toJson(queryString);
        JSONObject queryJson = new JSONObject(queryJsonString);
        Order order = new Order();
        String merchantOrderNo = "";
        String orderAmount = "";
        //由于聚合支付和银联扫码的商户订单号字段名与其他支付类型不同
        //所以分开获取订单号
        if (PayTypeConsts.UNION_CODE_PAY.equals(payType)){
            if (!queryJson.has("merchantPreId")){
                return ServerResponse.createByError(ResponseConsts.NO_FIELDS_ERROR.getCode(),ResponseConsts.NO_FIELDS_ERROR.getDesc()+"merchantPreId");
            }
            if (queryJson.isNull("merchantPreId")){
                return ServerResponse.createByError(ResponseConsts.FIELDS_IS_EMPTY_ERROR.getCode(),ResponseConsts.FIELDS_IS_EMPTY_ERROR.getDesc()+"merchantPreId");
            }
            merchantOrderNo = queryJson.getString("merchantPreId");
            orderAmount = queryJson.getString("amount");
        }
        if(PayTypeConsts.WEI_XIN_H5.equals(payType)
                || PayTypeConsts.WEI_XIN_MICRO.equals(payType)
                || PayTypeConsts.WEI_XIN_PC.equals(payType)
                || PayTypeConsts.WEI_XIN_PUB.equals(payType)
                || PayTypeConsts.ALI_PAY_H5.equals(payType)
                || PayTypeConsts.ALI_PAY_MICRO.equals(payType)
                || PayTypeConsts.ALI_PAY_PC.equals(payType)
                || PayTypeConsts.QQ_PC.equals(payType)
                || PayTypeConsts.JD_PC.equals(payType)) {
            if (!queryJson.has("merchantBillNo")){
                return ServerResponse.createByError(ResponseConsts.NO_FIELDS_ERROR.getCode(),ResponseConsts.NO_FIELDS_ERROR.getDesc()+"merchantBillNo");

            }
            if (queryJson.isNull("merchantBillNo")){
                return ServerResponse.createByError(ResponseConsts.FIELDS_IS_EMPTY_ERROR.getCode(),ResponseConsts.FIELDS_IS_EMPTY_ERROR.getDesc()+"merchantBillNo");
            }
            merchantOrderNo = queryJson.getString("merchantBillNo");
            orderAmount = queryJson.getString("payAmt");
        }
        if(PayTypeConsts.EBANK_PAY.equals(payType)
                || PayTypeConsts.UNION_ONE_CODE_PAY.equals(payType)
                || PayTypeConsts.UNION_PAY_PC.equals(payType)
                || PayTypeConsts.UNION_PAY_WAP.equals(payType)
                || PayTypeConsts.QUICK_PAP_H5.equals(payType)
                || PayTypeConsts.QUICK_PAP_PC.equals(payType)) {
            if (!queryJson.has("merchantOrderNo")){
                return ServerResponse.createByError(ResponseConsts.NO_FIELDS_ERROR.getCode(),ResponseConsts.NO_FIELDS_ERROR.getDesc()+"merchantOrderNo");

            }
            if (queryJson.isNull("merchantOrderNo")){
                return ServerResponse.createByError(ResponseConsts.FIELDS_IS_EMPTY_ERROR.getCode(),ResponseConsts.FIELDS_IS_EMPTY_ERROR.getDesc()+"merchantOrderNo");
            }
            merchantOrderNo = queryJson.getString("merchantOrderNo");
            orderAmount = queryJson.getString("payAmount");
        }
        Integer merchantId = Integer.valueOf(queryJson.getString("merchantId"));
        //查询商户单号是否已存在
        Order selectOrder = orderMapper.selectByMerchantOrderNo(merchantOrderNo);
        if (selectOrder != null){
            //订单已存在
            return ServerResponse.createByError(ResponseConsts.ORDER_EXISTING_ERROR.getCode(),merchantOrderNo + ResponseConsts.ORDER_EXISTING_ERROR.getDesc());
        }
        order.setMerchantId(merchantId);
        order.setMerchantOrderNo(merchantOrderNo);
        order.setPayType(payType);
        order.setOrderAmount(new BigDecimal(orderAmount));
        //新插入的订单状态都是处理中
        order.setOrderStatus(PayStatusConsts.PAY_IN_PROCESS);
        //写入成功条数
        int rowCount = 0;
        try {
            rowCount = orderMapper.insert(order);
        } catch (Exception e) {
            //TODO log订单入库失败
            return ServerResponse.createByError(ResponseConsts.ORDER_INSERT_ERROR.getCode(),ResponseConsts.ORDER_INSERT_ERROR.getDesc());
        }
        if (rowCount > 0){
            //订单入库成功返回null
            return null;
        }
        return ServerResponse.createByError(ResponseConsts.ORDER_INSERT_ERROR.getCode(),ResponseConsts.ORDER_INSERT_ERROR.getDesc());
    }
}
