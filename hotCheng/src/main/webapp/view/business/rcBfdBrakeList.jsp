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
<style type="text/css">
	.img{
		width:100px;
		height:100px;
		}
</style>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',split:true">
		        <table border="0" id="datagrid"  data-options="toolbar: 'datagrid_tb'"></table>
				<div id="datagrid_tb">
				技术BFD：
	      		<input id="bfd" class="easyui-textbox"  style="height: 30px;" prompt="技术BFD">
	     	宝丰号/奔德士号：
	      <input id= "bCode" class="easyui-textbox"  style="height: 30px;" prompt="宝丰号/奔德士号">
		
            	<a id="search" class="btn default">查询</a>                   
				<a id="insert" class="btn default"  onclick="insert()" >新增</a>
				<a id="update" class="btn default"  onclick="update()" >修改</a>									
				<a id="del" class="btn default"  onclick="del()" >删除</a>
				<a id="imports" class="btn default"  onclick="imports()" >导入</a>									
			</div>  
	</div>
</div>
 <div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
 <div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:0px solid #fff;" src="" /></div></div>

<script type="text/javascript" src="static/business/rcBfdBrake.js"></script>
</body>
</html>
