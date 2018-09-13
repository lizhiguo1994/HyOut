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
    <title>QQ扫码</title>
    <link type="text/css" rel="stylesheet" href="../css/gxs.css"/>
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
                    url:"/HyOut/pay",
                    data: $("#pay").serialize(),
                    dataType:"text",
                    success: function(data) {
                        var json = JSON.parse(data);
                        var code = json.status;
                        if (1000 == code){
                            var sign = json.sign;
                            var signString = json.signString;
                            var payUrl = json.payUrl;
                            $("#sign").val(sign);
                            $("#signString").val(signString);
                            <!-- 生成二维码 -->
                            $('#qrcode').html("");
                            var qrcode = new QRCode(document.getElementById('qrcode'), {
                                width: 256,
                                height: 256,
                            });
                            qrcode.makeCode(payUrl);
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
                var merchantBillNo = document.getElementById("merchantBillNo").value;
                var tradeType = document.getElementById("tradeType").value;
                var key = document.getElementById("key").value;
                var url = "query/AggrPayQuery.jsp";
                url = url + "?merchantId=" + merchantId;
                url = url + "&merchantBillNo=" + merchantBillNo;
                url = url + "&tradeType=" + tradeType;
                url = url + "&key=" + key;
                window.open(url);
            });

        })
    </script>
</head>
<body>

<h1>QQ扫码</h1>
<div class="div_y">
    <form id="pay" action="https://open.heepay.com/aggrPay.do" method="post" target="_blank">
        <p><label class="label_w">version:(版本号)</label>               <input class="label_w" type="text" name="version"           value="1.0"></p>
        <p><label class="label_w">merchantId:(商户号)</label>            <input class="label_w" type="text" name="merchantId"       value="101053" id="merchantId"></p>
        <p><label class="label_w">merchantBillNo:(商户订单号)</label>    <input class="label_w" type="text" name="merchantBillNo"  value=<%= SmallTools.getDate("yyyyMMddHHmmss")+"hykj" %> id="merchantBillNo" ></p>
        <p><label class="label_w">requestTime:(请求时间)</label>         <input class="label_w" type="text" name="requestTime"     value=<%= SmallTools.getDate("yyyyMMddHHmmss") %> ></p>
        <p><label class="label_w">tradeType:(交易类型)</label>           <input class="label_w" type="text" name="tradeType"       value="qq_qr" id="tradeType"></p>
        <p><label class="label_w">payAmt:(金额(元))</label>              <input class="label_w" type="text" name="payAmt"           value="0.01" ></p>
        <p><label class="label_w">notifyUrl:(异步地址)</label>           <input class="label_w" type="text" name="notifyUrl"       value="http://www.heepay.com"></p>
        <p><label class="label_w">returnUrl:(同步地址)</label>           <input class="label_w" type="text" name="returnUrl"       value="http://www.heepay.com"></p>
        <p><label class="label_w">userIp:(用户IP)</label>                <input class="label_w" type="text" name="userIp"           value="168.168.18.18"></p>
        <p><label class="label_w">remark:(透传参数)</label>              <input class="label_w" type="text" name="remark"           value="张三"></p>
        <p><label class="label_w">goodsName:(商品名称)</label>           <input class="label_w" type="text" name="goodsName"        value="hy"></p>
        <p><label class="label_w">goodsDetail:(商品详细说明)</label>     <input class="label_w" type="text" name="goodsDetail"      value='{"n":"汇元网","id":"http://www.9186.com/index.aspx"}'></p>
        <p><label class="label_w">goodsNote:(支付说明)</label>           <input class="label_w" type="text" name="goodsNote"        value="123"></p>
        <p><label class="label_w">key:(秘钥)</label>                     <input class="label_w" type="text" name="key"              value="f2c027902d9619e88a5ddda3f5618a74" id="key"></p>
        <p><label class="label_w">sign:(签名结果)</label>                <input class="label_w" type="text" name="sign"              value="" id="sign"></p>
    </form>
    <p style="color:red">注：异步通知地址需要在汇付宝后台配置</p>
    <button id="submit" name="submit" value="提交">提交支付</button>
    <button id="query" name="query" value="查询" style="float:right">订单查询</button>
    <br><br>
    <p>签名数据：<br><textarea class="textarea_bg" id="signString" rows="10" cols="50"></textarea></p>
</div>

<div id="qrcode" style="width: 256px;height: 256px; border: 1px solid black;margin: 0 auto; margin-top: 10px;">支付二维码</div>
</body>
</html>
