<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.kdgcsoft.power.common.bean.Principal"%> 
<%@page import="org.apache.shiro.SecurityUtils"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; 
	Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="/view/common/jsp/common.jsp"></jsp:include>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',split:true">
		        <table border="0" id="datagrid"  data-options="toolbar: 'datagrid_tb'"></table>
				<div id="datagrid_tb">
				微信信息：
	      <input id= "wechat" class="easyui-textbox"  style="height: 30px;" prompt="昵称/姓名/电话">
		扫码时间：
		<input type="text" class="easyui-datebox" id="startTime" >-
		<input type="text" class="easyui-datebox" id="endTime" >
        <a id="search" class="btn default" onclick="search()">查询</a>            
        <a id="exports" class="btn default" >下载</a>       
		</div>  
	</div>
</div>
 <div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
 <div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:0px solid #fff;" src="" /></div></div>
 <script type="text/javascript" src="static/business/rpStatisticsTj.js"></script>
</body>
</html>
