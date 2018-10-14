<%@ page import="com.hxxdemo.weixinsaomalogin.util.JsSignUtil" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Map<String, String> ret = new HashMap<String, String>();
    ret = JsSignUtil.sign("http://lancev.com/hotCheng/view/mypage/qrScan.jsp");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <script src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<head>
    <base href="<%=basePath%>">
    <title>微信扫码登录</title>

</head>
<body>
<div>
<p>成功</p>
<input id="wm_id" />
<button id='scanQRCode' style="width:40%;height:25px;"> 扫码二维码</button>
</div>

</body>
<script  type="text/javascript">
	wx.config({
		debug: false,
		appId: '<%=ret.get("appId")%>',
		timestamp:'<%=ret.get("timestamp")%>',
		nonceStr:'<%=ret.get("nonceStr")%>',
		signature:'<%=ret.get("signature")%>',
		jsApiList : [ 'checkJsApi', 'scanQRCode' ]
	});//end_config
 
	wx.error(function(res) {
		alert("出错了：" + res.errMsg);
	});
 
	wx.ready(function() {
		wx.checkJsApi({
			jsApiList : ['scanQRCode'],
			success : function(res) {
			}
		});
 
		//扫描二维码
		document.querySelector('#scanQRCode').onclick = function() {
			wx.scanQRCode({
				needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
				scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有
				success : function(res) {
					var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
					document.getElementById("wm_id").value = result;//将扫描的结果赋予到jsp对应值上
					alert("扫描成功::扫描码=" + result);
				}
			});
		};//end_document_scanQRCode
		
	});//end_ready
</script>

</html>
