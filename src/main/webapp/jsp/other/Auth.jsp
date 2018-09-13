<%@ page import="com.hyout.util.SmallTools" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/7
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>鉴权</title>
    <link type="text/css" rel="stylesheet" href="../../css/gxs.css">
    <%--<style>
        .div_y {margin:0 auto;width:510px;text-align:left; }
        .label_w{display:inline-block;width:250px;
            background-color:#59784E; }
    </style>--%>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script>
        $(function () {
            $("#submit").click(function () {
                $.ajax({
                    type:"get",
                    url:"/HyOut/GetSign",
                    data: $("#pay").serialize(),
                    dataType:"text",
                    success: function(data) {
                        var json = JSON.parse(data);
                        var sign = json.sign;
                        var signString = json.signString;
                        var bankCardInfo = json.bankCardInfo;
                        var ret = json.ret;
                        $("#sign").val(sign);
                        $("#signString").val(signString);
                        $("#bankCardInfo").val(bankCardInfo);
                        alert(ret);
                    },
                    error:function(data){

                    }
                });
            })

        })
    </script>
</head>
<body>

<h1>鉴权</h1>
<div class="div_y">
    <form id="pay" action="https://open.heepay.com/auth.do" method="post" target="_blank">
        <p><label class="label_w">merchantId:(商户号)</label>          <input class="label_w" type="text" name="merchantId"        value="101053"></p>
        <p><label class="label_w">merchantOrderNo:(订单号)</label>     <input class="label_w" type="text" name="merchantOrderNo"  value=<%= SmallTools.getDate("yyyyMMddHHmmss")+"hykj" %> ></p>
        <p><label class="label_w">merchantUserId:(商户用户id)</label>  <input class="label_w" type="text" name="merchantUserId"   value="123"></p>
        <p><label class="label_w">requestTime:(请求时间)</label>       <input class="label_w" type="text" name="requestTime"        value=<%= SmallTools.getDate("yyyyMMddHHmmss")%> ></p>
        <p><label class="label_w">version:(版本号)</label>             <input class="label_w" type="text" name="version"        value="1.0"></p>
        <p><label class="label_w">authType:(鉴权类型)</label>          <input class="label_w" type="text" name="authType"       value="3"></p>
        <p><label class="label_w">bankCardInfo:(鉴权明细)</label>      <input class="label_w" type="text" name="bankCardInfo" id="bankCardInfo"        value="张三^62148301646888888^130323199401011111" ></p>
        <p><label class="label_w">key:(秘钥)</label>                   <input class="label_w" type="text" name="key"                value="6969e3512ba8a013653b569b6a2aaaaa"></p>
        <p><label class="label_w">signString:(签名字符串)</label>      <input class="label_w" type="text" id="sign" name="sign"   value=""></p>
    </form>
    <p style="color:red">注：异步通知地址需要在汇付宝后台配置</p>
    <button id="submit" name="submit" value="提交">提交支付</button><br><br>
    <p>签名数据：<br><textarea class="textarea_bg" id="signString" rows="10" cols="50"></textarea></p>
</div>
</body>
</html>
