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
    <title>聚合订单查询</title>
    <link type="text/css" rel="stylesheet" href="../../css/gxs.css"/>
    <%--<style>
        .div_y {margin:0 auto;width:510px;text-align:left; }
        .label_w{display:inline-block;width:250px;
            background-color:#59784E; }
    </style>--%>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="http://static.runoob.com/assets/qrcode/qrcode.min.js"></script>
    <script>
        $(function () {
            $("#submit").click(function () {
                $.ajax({
                    type:"get",
                    url:"/HyOut/payQuery",
                    data: $("#pay").serialize(),
                    dataType:"text",
                    success: function(data) {
                        var json = JSON.parse(data);
                        var code = json.status;
                        if (1000 == code){
                            var sign = json.sign;
                            var signString = json.signString;
                            var returnData = json.payUrl;
                            $("#sign").val(sign);
                            $("#signString").val(signString);
                            $("#returnData").val(returnData);
                        }else {
                            var msg = json.msg;
                            alert(msg);
                        }
                    },
                    error:function(data){

                    }
                });
            })

        })
    </script>
</head>
<body>

<h1>聚合订单查询</h1>
<div class="div_y">
    <form id="pay" action="https://open.heepay.com/aggrPay.do" method="post">
        <p><label class="label_w">version:(版本号)</label>               <input class="label_w" type="text" name="version"           value="2.0"></p>
        <p><label class="label_w">merchantId:(商户号)</label>            <input class="label_w" type="text" name="merchantId"       value=<%= request.getParameter("merchantId") %> ></p>
        <p><label class="label_w">merchantBillNo:(商户订单号)</label>    <input class="label_w" type="text" name="merchantBillNo"  value=<%= request.getParameter("merchantBillNo") %> ></p>
        <p><label class="label_w">requestTime:(请求时间)</label>         <input class="label_w" type="text" name="requestTime"     value=<%= SmallTools.getDate("yyyyMMddHHmmss") %> ></p>
        <p><label class="label_w">transNo:(汇付宝交易号)</label>         <input class="label_w" type="text" name="transNo"          value="" ></p>
        <p><label class="label_w">tradeType:(交易类型)</label>           <input class="label_w" type="text" name="tradeType"        value=<%= request.getParameter("tradeType") %> ></p>
        <p><label class="label_w">key:(秘钥)</label>                     <input class="label_w" type="text" name="key"              value=<%= request.getParameter("key") %> ></p>
        <p><label class="label_w">sign:(签名结果)</label>                <input class="label_w" type="text" id="sign" name="sign"  value=""></p>
    </form>
    <p style="color:red">注：异步通知地址需要在汇付宝后台配置</p>
    <button id="submit" name="submit" value="提交">订单查询</button><br><br>
    <p>签名数据：<br><textarea class="textarea_bg" id="signString" rows="10" cols="50"></textarea></p>
    <p>返回数据：<br><textarea class="textarea_bg" id="returnData" rows="10" cols="50"></textarea></p>
</div>

</body>
</html>
