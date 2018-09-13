package com.hyout.base;

/**
 * Created by Administrator on 2018/8/24.
 */
public enum ResponseConsts {
    SUCCESS(1000,"SUCCESS"),
    ERROR(2000,"ERROR"),

    EXCEPTION_ERROR(3000,"C11异常错误"),// C11表示类PayController中方法pay出错
    URLDECODER_ERROR(3001,"参数URLDecode出错"),
    ILLEGAL_SOURCES_ERROR(3002,"非法来源"),
    JSON_PRINT_ERROR(3003,"Json打印出现异常"),
    NO_FIELDS_ERROR(3004,"不包含字段："),
    FIELDS_IS_EMPTY_ERROR(3004,"该字段为空："),

    ORDER_INSERT_ERROR(4000,"订单入库失败"),
    ORDER_EXISTING_ERROR(4001,"订单已存在"),
    ORDER_UPDATA_ERROR(4002,"订单状态更新失败");

    private final int code;
    private final String desc;

    ResponseConsts(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }
}
