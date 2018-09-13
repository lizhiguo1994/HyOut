<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/8
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>汇付宝测试</title>
    <link href="css/gxs.css" rel="stylesheet" type="text/css"/>
    <style>
        a{
            text-decoration:none;
            font-size: 28px;
            display: inline-block;
            text-align: center;
            color: #000;
            width : 200px;
            height: 100px;
            border:1px solid #000;
            line-height:100px;
        }
        a:hover {color:white;}/*鼠标经过样式*/
        table,td
        {
            text-align: center;
        }

    </style>
</head>
<body>
<h1 align="center" style="size: 100px">汇付宝测试</h1>
<h2 align="center" style="size: 100px;color: crimson;">注：以下所有测试功能均是正式环境下进行，所以一切支付行为均会产生真实资金交易，请自行设置好测试金额</h2>
<div class="main">
    <table align="center">
        <tr>
            <td><a href="jsp/UnionPayPC.jsp" target="_blank" >银联PC</a></td>
            <td><a href="jsp/UnionPayWAP.jsp" target="_blank" >银联WAP</a></td>
            <td><a href="jsp/UnionPayCode.jsp" target="_blank" >银联扫码</a></td>
            <td><a href="jsp/UnionPayOneCodePay.jsp" target="_blank">银联一码付</a></td>
        </tr>

        <tr>
            <td><a href="jsp/EBank.jsp" target="_blank">网银</a></td>
            <td><a href="jsp/QuickPayPC.jsp" target="_blank">快捷PC</a></td>
            <td><a href="jsp/QuickPayH5.jsp" target="_blank">快捷H5</a></td>
            <td><a href="jsp/WeiXinPub.jsp" target="_blank">微信公众号</a></td>
        </tr>
        <tr>
            <td><a href="jsp/WeiXinPC.jsp" target="_blank">微信扫码</a></td>
            <td><a href="jsp/AliPayPC.jsp" target="_blank">支付宝扫码</a></td>
            <td><a href="jsp/QQPC.jsp" target="_blank">QQ扫码</a></td>
            <td><a href="jsp/JDPC.jsp" target="_blank">京东扫码</a></td>
        </tr>
        <tr>
            <td><a href="jsp/WeiXinH5.jsp" target="_blank">微信H5</a></td>
            <td><a href="jsp/WeiXinMicro.jsp" target="_blank">微信刷卡</a></td>
            <td><a href="jsp/AliPayH5.jsp" target="_blank">支付宝H5</a></td>
        </tr>
        <%--<tr>--%>
            <%--<td><a href="jsp/other/Auth.jsp" target="_blank">鉴权</a></td>--%>
        <%--</tr>--%>
    </table>
</div>

</body>
</html>
