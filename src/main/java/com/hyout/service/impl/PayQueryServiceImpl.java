package com.hyout.service.impl;

import com.hyout.base.PayStatusConsts;
import com.hyout.base.ResponseConsts;
import com.hyout.base.ServerResponse;
import com.hyout.base.UrlConsts;
import com.hyout.dao.OrderMapper;
import com.hyout.pojo.Order;
import com.hyout.service.IPayQueryService;
import com.hyout.util.HttpUtil;
import com.hyout.util.SmallTools;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/5.
 */
@Service("iPayQueryService")
public class PayQueryServiceImpl implements IPayQueryService{

    private Logger logger = LoggerFactory.getLogger(PayQueryServiceImpl.class);
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ServerResponse aggrPayQuery(HttpServletRequest request) {
        //获取参数
        String queryString = request.getQueryString();
        try {
            queryString = URLDecoder.decode(queryString,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            //log提示urldecoe出错
            return ServerResponse.createByError(ResponseConsts.URLDECODER_ERROR.getCode(), ResponseConsts.URLDECODER_ERROR.getDesc());
        }
        //创建签名数据参数名集合
        ArrayList<String> nameList = new ArrayList<String>();
        nameList.add("version");
        nameList.add("merchantId");
        nameList.add("merchantBillNo");
        nameList.add("requestTime");
        nameList.add("key");
        String[] signAndSignString = SmallTools.getSignAndSignString(queryString,"聚合查询", nameList);
        String sign = signAndSignString[0];
        String signString = signAndSignString[1];
        //拼接请求参数
        String parameter = SmallTools.lastSplitString(queryString,"=") + sign;
        String returnParameter = HttpUtil.sendPost(UrlConsts.AGGR_PAY_QUERY, parameter);
        JSONObject returnParameterJson = new JSONObject(returnParameter);
        if(returnParameterJson.getInt("retCode") == 1000){
            //查询订单状态是支付成功，判断订单在数据库中是否是支付成功状态，如果是，不做处理，如果不是，修改数据库单据状态并发货
            JSONObject returnParameterJO = new JSONObject(returnParameter);
            Order order = orderMapper.selectByMerchantOrderNo(returnParameterJO.getString("merchantBillNo"));
            int orderStatus = order.getOrderStatus();
            if (orderStatus != PayStatusConsts.PAY_SUCCEED){
                Order upStatusOrder = new Order();
                upStatusOrder.setId(order.getId());
                upStatusOrder.setOrderStatus(PayStatusConsts.PAY_SUCCEED);
                int upCount = orderMapper.updateByPrimaryKeySelective(upStatusOrder);
                if (upCount <= 0){
                    //订单状态更新失败
                    return ServerResponse.createByError(ResponseConsts.ORDER_UPDATA_ERROR.getCode(), ResponseConsts.ORDER_UPDATA_ERROR.getDesc());
                }
            }
        }
        //给前台返回查询结果
        return ServerResponse.createBySuccess(sign, signString, returnParameter);
    }

    @Override
    public ServerResponse otherPayQuery(HttpServletRequest request) {
        //获取参数
        String queryString = request.getQueryString();
        try {
            queryString = URLDecoder.decode(queryString,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            //log提示urldecoe出错
            return ServerResponse.createByError(ResponseConsts.URLDECODER_ERROR.getCode(), ResponseConsts.URLDECODER_ERROR.getDesc());
        }
        //判断productCode是否是支持查询的
//        HY_QUICKPAYAPI：快捷支付
//        HY_B2CEBANKPC：网银支付
//        HY_B2CUNIONWAP：银联WAP
//        HY_B2CUNIONPC：银联PC
//        HY_B2CUNIONCODE：银联一码付
        JSONObject queryJson = new JSONObject(SmallTools.toJson(queryString));
        String productCode = queryJson.getString("productCode");
        ArrayList productCodes = new ArrayList<>();
        productCodes.add("HY_QUICKPAYAPI");
        productCodes.add("HY_QUICKPAYH5");
        productCodes.add("HY_QUICKPAYPC");
        productCodes.add("HY_B2CEBANKPC");
        productCodes.add("HY_B2CUNIONWAP");
        productCodes.add("HY_B2CUNIONPC");
        productCodes.add("HY_B2CUNIONCODE");
        if (!productCodes.contains(productCode)){
            return ServerResponse.createByError(ResponseConsts.ERROR.getCode(),"productCode值错误");
        }
        if ("HY_QUICKPAYH5".equals(productCode)){
            productCode = "HY_QUICKPAYH5";
        }
        if ("HY_QUICKPAYPC".equals(productCode)){
            productCode = "HY_QUICKPAYPC";
        }
        //创建签名数据参数名集合
        ArrayList<String> nameList = new ArrayList<String>();
        nameList.add("version");
        nameList.add("merchantId");
        nameList.add("merchantOrderNo");
        nameList.add("requestTime");
        nameList.add("key");
        String[] signAndSignString = SmallTools.getSignAndSignString(queryString,productCode + "查询", nameList);
        String sign = signAndSignString[0];
        String signString = signAndSignString[1];
        //拼接请求参数
        String parameter = SmallTools.lastSplitString(queryString,"=") + sign;
        String url = UrlConsts.OTHER_PAY_QUERY.replace("{productCode}", productCode);
        String returnParameter = HttpUtil.sendPost(url, parameter);
        JSONObject returnParameterJson = new JSONObject(returnParameter);
        if(returnParameterJson.getInt("retCode") == 1000){
            //查询订单状态是支付成功，判断订单在数据库中是否是支付成功状态，如果是，不做处理，如果不是，修改数据库单据状态并发货
            JSONObject returnParameterJO = new JSONObject(returnParameter);
            Order order = orderMapper.selectByMerchantOrderNo(returnParameterJO.getString("merchantOrderNo"));
            int orderStatus = order.getOrderStatus();
            if (orderStatus != PayStatusConsts.PAY_SUCCEED){
                Order upStatusOrder = new Order();
                upStatusOrder.setId(order.getId());
                upStatusOrder.setOrderStatus(PayStatusConsts.PAY_SUCCEED);
                int upCount = orderMapper.updateByPrimaryKeySelective(upStatusOrder);
                if (upCount <= 0){
                    //订单状态更新失败
                    return ServerResponse.createByError(ResponseConsts.ORDER_UPDATA_ERROR.getCode(), ResponseConsts.ORDER_UPDATA_ERROR.getDesc());
                }
            }
        }
        //给前台返回查询结果
        return ServerResponse.createBySuccess(sign, signString, returnParameter);
    }
}
