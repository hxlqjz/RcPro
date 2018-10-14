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
	     	微信：
	      <input id= "name" class="easyui-textbox"  style="height: 30px;" prompt="微信号/微信名">
	       标识码：
	      <input id= "idCode" class="easyui-textbox"  style="height: 30px;"  prompt="门店识别码">
            	<a id="search" class="btn default" onclick="search()">查询</a>                   
				<!-- <a id="insert" class="btn default"  onclick="insert()" >新增</a> -->
				<a id="update" class="btn default"  onclick="update()" >修改</a>									
				<!-- <a id="del" class="btn default"  onclick="del()" >删除</a>	 -->
				<!-- <a id="black" class="btn default"  onclick="black()" >拉黑</a>		 -->					
			</div>  
	</div>
</div>
 <div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
 <div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:0px solid #fff;" src="" /></div></div>
 <script type="text/javascript" src="static/business/rpUser.js"></script>
</body>
</html>
