<%@ page language="java" pageEncoding="utf-8"%>
<%
 String online = (String)session.getAttribute("onlines");
 %>
<html>
	<head>
		<title>欢迎访问</title>
		<jsp:include page="/WEB-INF/views/common/commjsp.jsp"></jsp:include>
		<link href="static/images/homepage/gorgeous/layout.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function toIndex(){
			if (window.confirm("确定要重新登陆吗？")) 
				 {
				parent.toIndex();
				 }else{
				 	return false;
				 }
			}
			function toMin(){
			parent.toMin();
			}
			function closePage()
			{
				if (window.confirm("确定要退出系统吗？")) 
				 {
					parent.closeWindow();
				}else{
				 	return false;
				 }
			}
			function modifyPwd()
			{
				parent.modifyPass();
			}
			
			//弹出登录用户列表
			function onlineUser(){
				parent.onlineUser();
			}
			
			
		</script>
	</head>
	<body>
		<div class="TOP">
			<div class="L"></div>
			<div class="R">
			    <table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="10"></td>
        </tr>
        <tr>
          <td align="center" style="color: white;font-weight: bold;">
            欢迎您：<a style="text-decoration:none;color:red;" id="curUserInfo"></a>
            最近登录：<span id="loginDate" style="color:#f00; font-size:14px;"></span> </td>
            
        </tr>
        <tr>
          <td height="30"><table class="box" width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="29" class="left">&nbsp;</td>
                <td class="bg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                    <td><a style="cursor:hand;text-decoration:none"><img src="static/images/homepage/images/topUser.png" /></a></td>
                    <td nowrap="nowrap">当前用户：<a style="cursor:hand;text-decoration:none" href="javaScript:onlineUser();"><span id="onlines" style="color:#f00; font-size:14px;"></span></a>人</td>
 <td class="split">&nbsp;</td>
                      <td nowrap="nowrap"><a href="#" onclick="return toMin()">收起</a> </td>
                      <td class="split">&nbsp;</td>
                       <td nowrap="nowrap"><a href="#" onclick="return toIndex()">重新登录</a> </td>
                      <td class="split">&nbsp;</td>
                      <td nowrap="nowrap"><a  href="#" onclick="return closePage()">关闭页面</a> </td>
                      <td class="split">&nbsp;</td>
                      <td nowrap="nowrap"><a href="javaScript:modifyPwd();">修改密码</a> </td>
                    </tr>
                  </table></td>
                <td class="right">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
      </table></td>
    <td width="10"></td>
  </tr>
</table>

			</div>
		    <div class="hackbox"></div>
		    <div class="centerflash">
		        <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="412"  height="64" vspace="0">
		        <param name="quality" value="high" />
		        <param name="SRC" value="" />
		        <param name="wmode" value="transparent" />
		        <embed src="gorgeous/images/TOP.swf" width="412" height="64" vspace="0" quality="high"
		         
		         pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" 
		         wmode="transparent"></embed>
		        </object>
		    </div>		   
        </div>
	</body>
	<script type="text/javascript">
	function checkOnLineNumber(){
		Ext.Ajax.request({
			url : 'system/checkOnLineNumber.action',
			method : 'post',
			success : function(result, request) {
				document.getElementById("onlines").innerHTML = result.responseText;
			}
		});
	}
	function checkOnLineNumberTime(){
		// document.getElementById("onlines").innerHTML =<%=online%>; 	
		Ext.Ajax.request({
			url : 'system/checkOnLineNumber.action',
			method : 'post',
			success : function(result, request) {
				document.getElementById("onlines").innerHTML = result.responseText;
			}
		});
	}
	checkOnLineNumber();
	//setInterval(checkOnLineNumberTime, 10000);   
	//弹出登录用户列表
	function onlineUser(){
		parent.onlineUser();
	}
	function getLoginDate(){
		Ext.Ajax.request({
			url : 'system/getLoginDate.action',
			method : 'post',
			success : function(result, request) {
				document.getElementById("loginDate").innerHTML = result.responseText;
			}
		});
	}
	getLoginDate();
	
	var curUserInfo = document.getElementById("curUserInfo");
	curUserInfo.innerText = userInfo.workerName + "【 " + userInfo.workerCode + "】";
	
	</script>
</html>