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
    <title>银联扫码</title>
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
            })

        })
    </script>
</head>
<body>

<h1>银联扫码</h1>
<h4>银联扫码为使用银联APP或各银行APP扫码完成支付的一种支付方式</h4>
<a href="https://billcloud.unionpay.com/upwxs-mktc/web/mapp/wxe990cdbcc189456e/custom/alllist" target="_blank" >点此查看支持的APP</a>
<div class="div_y">
    <form id="pay" action="https://c.heepay.com/newOnlineBank/paymentUnion.do" method="post" target="_blank">
        <p><label class="label_w">merchantId:(商户号)</label>            <input class="label_w" type="text" name="merchantId"        value="101053" id="merchantId"></p>
        <p><label class="label_w">subMerchantId:(二级商户号)</label>     <input class="label_w" type="text" name="subMerchantId"     value="" ></p>
        <p><label class="label_w">subMerchantName:(二级商户名称)</label> <input class="label_w" type="text" name="subMerchantName"  value="" ></p>
        <p><label class="label_w">subMerCatCode:(二级商户类别码)</label> <input class="label_w" type="text" name="subMerCatCode"     value="" ></p>
        <p><label class="label_w">merchantPreId:(商户预下单号)</label>   <input class="label_w" type="text" name="merchantPreId"     value=<%= SmallTools.getDate("yyyyMMddHHmmss")+"hykj" %> ></p>
        <p><label class="label_w">amount:(金额(元))</label>              <input class="label_w" type="text" name="amount"             value="0.02" ></p>
        <p><label class="label_w">requestTime:(交易时间)</label>         <input class="label_w" type="text" name="requestTime"       value=<%= SmallTools.getDate("yyyyMMddHHmmss") %> ></p>
        <p><label class="label_w">notifyUrl:(异步地址)</label>           <input class="label_w" type="text" name="notifyUrl"         value="http://www.heepay.com"></p>
        <p><label class="label_w">limitCardType:(卡类型)</label>         <input class="label_w" type="text" name="limitCardType"     value="0"></p>
        <p><label class="label_w">qrValidTime:(二维码有效时间)</label>   <input class="label_w" type="text" name="qrValidTime"       value="600"></p>
        <p><label class="label_w">limitCount:(支付次数限制)</label>      <input class="label_w" type="text" name="limitCount"         value="1"></p>
        <p><label class="label_w">invoiceSt:(是否支持发票)</label>       <input class="label_w" type="text" name="invoiceSt"          value="0"></p>
        <p><label class="label_w">payeeComments:(收款方附言)</label>     <input class="label_w" type="text" name="payeeComments"     value="hy"></p>
        <p><label class="label_w">version:(版本号)</label>               <input class="label_w" type="text" name="version"            value="1.0"></p>
        <p><label class="label_w">key:(秘钥)</label>                     <input class="label_w" type="text" name="key"                value="c937adc88717383be701459f3058e93d"></p>
        <p><label class="label_w">sign:(签名结果)</label>                <input class="label_w" type="text" id="sign" name="sign"    value=""></p>
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
