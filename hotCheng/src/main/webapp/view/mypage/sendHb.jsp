<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <base href="<%=basePath%>">
    <script type="text/javascript" charset="utf-8" src="static/js/jquery-1.9.1.min.js"></script>

    <title>微信发红包</title>


</head>

<body>
<div>
<p>成功</p>
<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx8666da66ca071393&redirect_uri=http://lancev.com/wx/index/WeiXinTest.do&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect">登录</a>
<button onclick="clickBtn()">发红包</button>
</div>

</body>
<script type="text/javascript">
function clickBtn(){
	var opendid=localStorage.getItem("scanWechat");
	 $.post("<%=basePath%>smhb/sendHb.action",{opendid:opendid},function(data){      
        alert(JSON.stringify(data));
     });
}
</script>
</html>
