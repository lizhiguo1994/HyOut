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
    <title>快捷PC</title>
    <link type="text/css" rel="stylesheet" href="../css/gxs.css"/>
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
                    url:"/HyOut/pay",
                    data: $("#pay").serialize(),
                    dataType:"text",
                    success: function(data) {
                        var json = JSON.parse(data);
                        var code = json.status;
                        if (1000 == code){
                            var sign = json.sign;
                            var signString = json.signString;
                            $("#sign").val(sign);
                            $("#signString").val(signString);
                            $("#pay").submit();
                        }else {
                            var msg = json.msg;
                            alert(msg);
                        }
                    },
                    error:function(data){

                    }
                });
            });

            $("#query").click(function () {
                var merchantId = document.getElementById("merchantId").value;
                var merchantOrderNo = document.getElementById("merchantOrderNo").value;
                var productCode = document.getElementById("productCode").value;
                var key = document.getElementById("key").value;
                var url = "query/OtherPayQuery.jsp";
                url = url + "?merchantId=" + merchantId;
                url = url + "&merchantOrderNo=" + merchantOrderNo;
                url = url + "&productCode=" + productCode;
                url = url + "&key=" + key;
                window.open(url);
            });

        })
    </script>
</head>
<body>

<h1>快捷PC</h1>
<div class="div_y">
    <form id="pay" action="https://c.heepay.com/quick/pc/index.do" method="post" target="_blank">
        <p><label class="label_w">merchantId:(商户号)</label>          <input class="label_w" type="text" name="merchantId"        value="101053" id="merchantId"></p>
        <p><label class="label_w">merchantOrderNo:(订单号)</label>     <input class="label_w" type="text" name="merchantOrderNo"  value=<%= SmallTools.getDate("yyyyMMddHHmmss")+"hykj" %> id="merchantOrderNo" ></p>
        <p><label class="label_w">merchantUserId:(商户用户id)</label>  <input class="label_w" type="text" name="merchantUserId"   value="123"></p>
        <p><label class="label_w">productCode:(产品编码)</label>       <input class="label_w" type="text" name="productCode"       value="HY_QUICKPAYPC" id="productCode"></p>
        <p><label class="label_w">payAmount:(金额(元))</label>         <input class="label_w" type="text" name="payAmount"         value="0.02" ></p>
        <p><label class="label_w">requestTime:(请求时间)</label>       <input class="label_w" type="text" name="requestTime"        value=<%= SmallTools.getDate("yyyyMMddHHmmss")%> ></p>
        <p><label class="label_w">version:(版本号)</label>             <input class="label_w" type="text" name="version"          value="1.0"></p>
        <p><label class="label_w">notifyUrl:(异步地址)</label>         <input class="label_w" type="text" name="notifyUrl"         value="http://www.heepay.com"></p>
        <p><label class="label_w">callBackUrl:(同步地址)</label>       <input class="label_w" type="text" name="callBackUrl"       value="http://www.heepay.com"></p>
        <p><label class="label_w">description:(商品信息)</label>       <input class="label_w" type="text" name="description"       value="hy"></p>
        <p><label class="label_w">requestUserIp:(用户IP)</label>       <input class="label_w" type="text" name="requestUserIp"    value="168.168.8.8"></p>
        <p><label class="label_w">reqHyTime:(防钓鱼时间)</label>       <input class="label_w" type="text" name="reqHyTime"        value=<%= System.currentTimeMillis()%> ></p>
        <p><label class="label_w">key:(秘钥)</label>                   <input class="label_w" type="text" name="key"                value="5c93cbf3a31f5783b8bd76ba086e43fc" id="key"></p>
        <p><label class="label_w">sign:(签名字符串)</label>            <input class="label_w" type="text" name="sign"               value="" id="sign"></p>
    </form>
    <p style="color:red">注：异步通知地址需要在汇付宝后台配置</p>
    <button id="submit" name="submit" value="提交">提交支付</button>
    <button id="query" name="query" value="查询" style="float:right">订单查询</button>
    <br><br>
    <p>签名数据：<br><textarea class="textarea_bg" id="signString" rows="10" cols="50"></textarea></p>
</div>
</body>
</html>
