<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="/view/common/jsp/common.jsp"></jsp:include>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',split:true">
		        <table border="0" id="backStage_datagrid"  data-options="toolbar: 'backStage_datagrid_tb'"></table>
				<div id="backStage_datagrid_tb">
	      用户名称：
	      <input id= "userName" 
						class="easyui-textbox" 
					 style="height: 30px;">
            <a id="searchSysCUser" class="btn default" onclick="searchMainPage()">查询</a>                   
					<a id="add_SysCUser" class="btn default"  onclick="addPageMain()" >新增</a>
					<a id="edit_SysCUser" class="btn default"  onclick="editBackStage()" >修改</a>									
					<a id="del_SysCUser" class="btn default"  onclick="delPageMain()" >删除</a>							
				</div>  
	</div>
</div>
 <div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index:2;width:100%;height:100%;display:none;">
 <div id="innerdiv" style="position:absolute;"><img id="bigimg" style="border:0px solid #fff;" src="" /></div></div>
	<script type="text/javascript" src="static/business/backStage.js"></script>
</body>
</html>
