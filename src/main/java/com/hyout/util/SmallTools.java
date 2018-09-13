package com.hyout.util;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by Administrator on 2017/5/27.
 */
public class SmallTools {

    private static Logger logger = LoggerFactory.getLogger(SmallTools.class);

    /**
     * 获取当前时间（格式自传）
     * @param dateFormat 要返回的时间格式，例如yyyy/MM/dd HH:mm:ss
     * @return
     */
    public static String getDate(String dateFormat){
        Date date = new Date();
        SimpleDateFormat dateF = new SimpleDateFormat(dateFormat);//可以方便地修改日期格式
        String retu = dateF.format(date);
        return retu;
    }

    public static String getDate(Long time, String dateFormat){
        Date date = new Date(time);
        SimpleDateFormat dateF = new SimpleDateFormat(dateFormat);//可以方便地修改日期格式
        String retu = dateF.format(date);
        return retu;
    }

    /**
     * Json格式数据验签
     * 传入返回数据和KEY，返回验签结果（验签成功返回true，失败返回false）
     * 如果请求报错，然后的参数中不会有sign字段也就不存在验签问题，直接提示参数中无sign字段无需验签，方法返回false
     */
    public static Boolean checkSign(String parameter, String key){
        if (!parameter.contains("sign")){
            System.out.println("参数中无sign字段无需验签");
            return false;
        }
        JSONObject jo = new JSONObject(parameter);
        Map<String, Object> map = jo.toMap();
        Iterator<String> a = jo.keys();
        a.toString();
        ArrayList<String> keys = new ArrayList<String>();
        while (true){
            String k = a.next();
            if (!k.equals("sign")){
                keys.add(k);
            }
            if (!a.hasNext()){
                break;
            }
        }
        // 进行升序排列
        Collections.sort(keys, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        StringBuilder mSign = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            mSign.append(keys.get(i));
            mSign.append("=");
            mSign.append(jo.get(keys.get(i)));
            mSign.append("&");
        }
        mSign.append("key="+key);
        System.out.println("返回签名参数："+mSign.toString());
        String msign = MD5Util.MD5(mSign.toString());
        System.out.println("本地签名结果："+msign);
        String rsign = jo.getString("sign");
        System.out.println("返回签名结果："+rsign);
        if (msign.equals(rsign)){
            System.out.println("签名验证成功");
            return true;
        }else {
            System.out.println("签名验证失败");
            return false;
        }
    }


    /**
     * aa=11&bb=22 格式数据转 Json格式数据 且
     * 指保留nameList里面包含的参数
     * @param parameter  aa=11&bb=22 格式数据
     * @param nameList   要保留的参数的名字集合
     * @return 截取想要的参数后并转换好的json格式数据
     */
    public static String toJson(String parameter ,ArrayList<String> nameList){
        JSONObject jsobj = new JSONObject();
        if (!parameter.contains("&")){
            if (!parameter.contains("=")){
                logger.error("toJson方法，参数格式不正确，无法转换");
            }else{
                String[] list = parameter.split("=");
                jsobj.put(list[0],list[1]);
                return jsobj.toString();
            }
        }else {
            String[] strings = parameter.split("&");
            for (int i=0;i<strings.length;i++){
                String[] list = strings[i].split("=");
                if (nameList.contains(list[0])){
                    if (list.length == 1){
                        jsobj.put(list[0],"");
                    }else {
                        jsobj.put(list[0],list[1]);
                    }
                }
            }
            return jsobj.toString();
        }
        return "";
    }
    /**
     * aa=11&bb=22 格式数据转 Json格式数据
     * @param parameter  aa=11&bb=22 格式数据
     * @return 转换好的json格式数据
     */
    public static String toJson(String parameter){
        JSONObject jsobj = new JSONObject();
        if (!parameter.contains("&")){
            if (!parameter.contains("=")){
                logger.error("toJson方法，参数格式不正确，无法转换");
            }else{
                String[] list = parameter.split("=");
                jsobj.put(list[0],list[1]);
                return jsobj.toString();
            }
        }else {
            String[] strings = parameter.split("&");
            for (int i=0;i<strings.length;i++){
                String[] list = strings[i].split("=");
                if (list.length == 1){
                    jsobj.put(list[0],"");
                }else {
                    jsobj.put(list[0],list[1]);
                }
            }
            return jsobj.toString();
        }
        return "";
    }



    /**
     * json格式参数生成签名数据
     * @param json json参数
     * @return 签名字符串
     */
    public static String JsonToSignString(String json){
        JSONObject jo = new JSONObject(json);
        if (json.contains("sign")){
            jo.remove("sign");
        }
        Iterator<String> a = jo.keys();
        a.toString();
        ArrayList<String> keys = new ArrayList<String>();
        while (true){
            String k = a.next();
            keys.add(k);
            if (!a.hasNext()){
                break;
            }
        }
        // 进行升序排列
        Collections.sort(keys, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        String key_v = "";
        StringBuilder mSign = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            if ("key".equals(keys.get(i))){
                key_v = jo.get(keys.get(i)).toString();
            }else {
                mSign.append(keys.get(i));
                mSign.append("=");
                mSign.append(jo.get(keys.get(i)));


                if (i< keys.size()-1){
                    mSign.append("&");
                }else {
                    mSign.append("&");
                    mSign.append("key");
                    mSign.append("=");
                    mSign.append(key_v);
                }
            }
        }
        return mSign.toString();
    }

    /**
     * 倒向截取 para 之前的字符串
     * 如： 输入 a=1&b=2 返回 a=1&b=
     * @param str 要截取的字符串
     * @param para 目标字符串
     * @return 截取后的字符串
     */
    public static String lastSplitString(String str,String para){
        int index = str.lastIndexOf(para);
        return str.substring(0,index + 1);
    }

    /**
     * 获取sign和signString
     * @param parameter 所有参数
     * @param payTypeName 支付类型名称
     * @param nameList 参与签名的参数名列表
     * @return 包含sign 和 signString的集合
     */
    public static String[] getSignAndSignString(String parameter, String payTypeName, ArrayList<String> nameList){
        String json = toJson(parameter,nameList);
        String signString = JsonToSignString(json);
        String sign = MD5Util.MD5(signString);
        logger.info("支付类型：{}请求参数：{}签名数据：{}签名结果：{}", payTypeName, parameter, signString, sign);
        String[] signAndSignString = new String[2];
        signAndSignString[0] = sign;
        signAndSignString[1] = signString;
        return signAndSignString;
    }

    /**
     * 获取sign和signString
     * @param parameter 所有参数
     * @param payTypeName 支付类型名称
     * @return 包含sign 和 signString的集合
     */
    public static String[] getSignAndSignString(String parameter, String payTypeName){
        String json = toJson(parameter);
        String signString = JsonToSignString(json);
        String sign = MD5Util.MD5(signString);
        logger.info("支付类型：{}请求参数：{}签名数据：{}签名结果：{}", payTypeName, parameter, signString, sign);
        String[] signAndSignString = new String[2];
        signAndSignString[0] = sign;
        signAndSignString[1] = signString;
        return signAndSignString;
    }


}
