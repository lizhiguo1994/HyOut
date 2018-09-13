package com.hyout.base;

import com.hyout.service.impl.PayServiceImpl;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/24.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)//保证序列化json的时候，value为null时，key也会消失
public class ServerResponse implements Serializable{

    private static Logger logger = LoggerFactory.getLogger(ServerResponse.class);

    private int status;
    private String msg;
    private String sign;
    private String signString;
    private String payUrl;

    public ServerResponse(int status, String msg, String sign, String signStrin, String payUrl) {
        this.status = status;
        this.msg = msg;
        this.sign = sign;
        this.signString = signStrin;
        this.payUrl = payUrl;
    }

    public ServerResponse(int status, String msg, String sign, String signStrin) {
        this.status = status;
        this.msg = msg;
        this.sign = sign;
        this.signString = signStrin;
    }

    private ServerResponse(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore//使之不在json序列化结果当中
    public boolean isSuccess(){
        return this.status == ResponseConsts.SUCCESS.getCode();
    }

    @JsonIgnore//使之不在json序列化结果当中
    private static void printJson(ServerResponse response){
        ObjectMapper mapper = new ObjectMapper();
        try {
            //打印返回数据的json
            logger.info("最终返回：{}",mapper.writeValueAsString(response));
        } catch (IOException e) {
            //Log提示打印出现异常
            logger.error("最终返回出现异常：{}",e.getMessage());
        }
    }

    public int getStatus(){
        return status;
    }

    public String getMsg(){
        return msg;
    }

    public String getSign(){
        return sign;
    }

    public String getSignString(){
        return signString;
    }

    public String getPayUrl(){
        return payUrl;
    }


    public static ServerResponse createBySuccess(String msg){
        ServerResponse response = new ServerResponse(ResponseConsts.SUCCESS.getCode(), msg);
        printJson(response);
        return response;
    }

    public static ServerResponse createBySuccess(String sign, String signString, String payUrl){
        ServerResponse response = new ServerResponse(ResponseConsts.SUCCESS.getCode(), ResponseConsts.SUCCESS.getDesc(), sign, signString, payUrl);
        printJson(response);
        return response;
    }

    public static ServerResponse createBySuccess(String sign, String signString){
        ServerResponse response = new ServerResponse(ResponseConsts.SUCCESS.getCode(), ResponseConsts.SUCCESS.getDesc(), sign, signString);
        printJson(response);
        return response;
    }

    public static ServerResponse createByError(){
        ServerResponse response = new ServerResponse(ResponseConsts.ERROR.getCode(), ResponseConsts.ERROR.getDesc());
        printJson(response);
        return response;
    }

    public static ServerResponse createByError(int code, String errorMessage){
        ServerResponse response = new ServerResponse(code, errorMessage);
        printJson(response);
        return response;
    }

    public static ServerResponse logBySuccess(String msg){
        ServerResponse response = new ServerResponse(ResponseConsts.SUCCESS.getCode(), msg);
        return response;
    }


}
