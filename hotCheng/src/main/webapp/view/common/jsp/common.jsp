<%@ page language="java" pageEncoding="UTF-8"%>
<%-- <%@page import="com.kdgcsoft.power.common.bean.Principal"%>  --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":8080"  + path + "/"; 
/* 	Principal principal =(principal) session.getAttribute("principal"); */
%>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> 



<!--[if lt IE 8]> <script src="static/js/common/json2.js"></script> <![endif]-->						
<script type="text/javascript" src="static/thirdparty/easyuiThem/jquery.min.js"></script>
<script type="text/javascript" src="static/thirdparty/easyuiThem/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/thirdparty/easyuiThem/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/js/common/easyuiExtend.js"></script>
<script type="text/javascript" src="static/js/common/exceptionHander.js"></script>
<script type="text/javascript" src="static/thirdparty/easyuiThem/themes/insdep/jquery.insdep-extend.min.js"></script>
<script type="text/javascript" src="static/js/common/Constants.js"></script>
<script type="text/javascript" src="static/js/common/common.js"></script>
<script type="text/javascript" src="static/js/common/easyuiWidget.js"></script>
<script type="text/javascript" src="static/js/common/commonOp.js"></script>
<script type="text/javascript" src="static/thirdparty/datepicker/WdatePicker.js"></script>
<link href="static/thirdparty/easyuiThem/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
<link href="static/thirdparty/easyuiThem/themes/insdep/master.css" rel="stylesheet" type="text/css">
<link href="static/thirdparty/easyuiThem/themes/insdep/icon.css" rel="stylesheet" type="text/css">
<link href="static/thirdparty/easyuiThem/extra/mystyle.css" rel="stylesheet" type="text/css">
<script src="static/thirdparty/layui/layui.js" charset="utf-8"></script>

<script src="static/js/common/datagridTip.js" type="text/javascript"></script>

<script type="text/javascript">
   //用户信息
<%--    var userInfo = {
	   empId : "<%=principal.getEmpId()%>", 	   
	   enterpriseCode : "<%=principal.getEnterpriseCode()%>", 	   
	   enterpriseName : "<%=principal.getEnterpriseName()%>", 	
	   enterpriseChar : "<%=principal.getEnterpriseChar()%>", 	
	   workerId : "<%=principal.getWorkerId()%>", 	   
	   workerCode : "<%=principal.getWorkerCode()%>", 	   
	   workerName : "<%=principal.getWorkerName()%>", 	   
	   deptId : "<%=principal.getDeptId()%>", 
	   deptCode : "<%=principal.getDeptCode()%>", 	   
	   deptName : "<%=principal.getDeptName()%>", 
	   deptLevel : "<%=principal.getDeptLevel()%>", 
	   roleIds : "<%=principal.getRoleIds()%>",
	   roleTypes : "<%=principal.getRoleTypes()%>",
	   immobilePhoneNo : "<%=principal.getImmobilePhoneNo()%>"
   }; --%>
   //日期格式化
   Date.prototype.format = function(fmt){
		  var o = {   
				    "M+" : this.getMonth()+1,    
				    "d+" : this.getDate(),   
				    "h+" : this.getHours(),  
				    "m+" : this.getMinutes(),   
				    "s+" : this.getSeconds(),                 
				    "q+" : Math.floor((this.getMonth()+3)/3),
				    "S"  : this.getMilliseconds()
				  };   
				  if(/(y+)/.test(fmt))   
				    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
				  for(var k in o)   
				    if(new RegExp("("+ k +")").test(fmt))   
				  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
				  return fmt;   
	};
	// 退格跳转首页处理
	$(document).keydown(function (e) {
	    var doPrevent;
	    if (e.keyCode == 8) {
	        var d = e.srcElement || e.target;
	        if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') {
	            doPrevent = d.readOnly || d.disabled;
	        }
	        else
	            doPrevent = true;
	    }
	    else
	        doPrevent = false;

	    if (doPrevent)
	        e.preventDefault();
	});
   
	//项目路径
	var ctx = "<%=path%>" + "/";
	
	//页面宽度和高度
	var pageWidth = 0;
	var pageHeight = 0;
	window.onload = function(){
		pageWidth = document.body.clientWidth - 20;
		pageHeight = document.body.clientHeight;
	}
	
	layui.use('layer', function(){ //独立版的layer无需执行这一句
		  var $ = layui.jquery;
		  layer = layui.layer; //独立版的layer无需执行这一句
	});
	
</script>