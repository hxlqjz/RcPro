<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.kdgcsoft.power.common.bean.Principal"%> 
<%@page import="org.apache.shiro.SecurityUtils"%> 
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; 
	Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
%>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Untitled Document</title>
<jsp:include page="/view/common/jsp/common.jsp"></jsp:include>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true">
		<form id="form" method="post">
			<table class="CTable" width="95%" border="0" cellpadding="3"
				cellspacing="8" style="margin: auto; table-layout: fixed">

				<tr>
					<td class="CTableTitle1" width="12%" align="right">预售地区:</td>
					<td width="38%">
					<lable>${entity.presaleArea}</lable>
						</td> 
					<td class="CTableTitle1" width="12%" align="right">二维码序号:</td>
					<td width="38%">
					<lable>${entity.qrNo}</lable>
						</td>
					<td class="CTableTitle1" width="10%" align="right">产品型号:</td>
					
					<td width="40%"><lable>${entity.productModel}</lable></td>
				</tr> 
				<tr>
					<td class="CTableTitle1" width="12%" align="right">扫码时间:</td>
					<td width="38%">
					<lable><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" 
            		value="${entity.scanTime}" /></lable>
						</td> 
					<td class="CTableTitle1" width="12%" align="right">起始质保日期:</td>
					<td width="38%">
					<lable><fmt:formatDate pattern="yyyy-MM-dd" 
            		value="${entity.qualityStart}" /></lable>
						</td>
					<td class="CTableTitle1" width="10%" align="right">终止质保日期:</td>
					<td width="40%">
					<lable><fmt:formatDate pattern="yyyy-MM-dd" 
            		value="${entity.qualityEnd}" /></lable>
					</td>
				</tr> 
				<tr>
				<td class="CTableTitle1" width="12%" align="right">扫码微信号:</td>
					<td width="38%">
					<lable>${entity.scanWechat}</lable>
						</td> 
					<td class="CTableTitle1" width="12%" align="right">返现时间:</td>
					<td width="38%">
					<lable><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" 
            		value="${entity.cashTime}" /></lable>
						</td>
					<td class="CTableTitle1" width="10%" align="right">激活状态:</td>
					<td width="40%">
					<lable><c:if test="${entity.activiateStatus == 0}">未激活</c:if>
					<c:if test="${entity.activiateStatus == 1}">待销售</c:if>
					<c:if test="${entity.activiateStatus == 2}">已销售</c:if>
					</lable>
					</td>
				</tr>
				<tr>
				<td class="CTableTitle1" width="12%" align="right">微信名:</td>
					<td width="38%">
					<lable>${entity.wechatName}</lable>
						</td> 
					<td class="CTableTitle1" width="12%" align="right">电话:</td>
					<td width="38%">
					<lable>${entity.wechatTel}</lable>
						</td>
					<td class="CTableTitle1" width="10%" align="right">门店:</td>
					<td width="40%">
					<lable>${entity.wechatInfo}</lable>
					</td>
				</tr>
				<tr>
				<td class="CTableTitle1" width="12%" align="right">省份:</td>
					<td width="38%">
					<lable>${entity.wechatInfo}</lable>
						</td> 
					<td class="CTableTitle1" width="12%" align="right">信息查询权限:</td>
					<td width="38%">
					<lable><c:if test="${entity.queryPower == 1}">高</c:if>
					<c:if test="${entity.queryPower == 2}">低</c:if>
					</lable>
						</td>
					<td class="CTableTitle1" width="10%" align="right">车主车牌号:</td>
					<td width="40%">
					<lable>${entity.plateNumber}</lable>
					</td>
				</tr>
				<tr>
				<td class="CTableTitle1" width="12%" align="right">门店名:</td>
					<td width="38%">
					<lable>${entity.storeName}</lable>
						</td> 
					<td class="CTableTitle1" width="12%" align="right">角色权限:</td>
					<td width="38%">
					<lable><lable><c:if test="${entity.rolePower == 1}">总权限</c:if>
					<c:if test="${entity.rolePower == 2}">门店权限</c:if>
					<c:if test="${entity.rolePower == 3}">个人权限</c:if>
					</lable>
						</td>
					<td class="CTableTitle1" width="10%" align="right">车主电话:</td>
					<td width="40%">
					<lable>${entity.carTel}</lable>
					</td>
				</tr>

			</table>
		</form>
		<table class="CTable" width="95%" border="0" cellpadding="3"
			cellspacing="8" style="margin: auto; table-layout: fixed">
			<tr>
				<td colspan="4" width="95%" align="center">
				 <a href="javascript:;" onclick="closeWin()" class="btn default">关闭</a></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
	

	function closeWin(){
		parent.$("#datagrid").datagrid('reload', {});
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    parent.layer.close(index);
	}
	function dateFtt(fmt,date)   
	{ //author: meizz   
	  var o = {   
	    "M+" : date.getMonth()+1,                 //月份   
	    "d+" : date.getDate(),                    //日   
	    "h+" : date.getHours(),                   //小时   
	    "m+" : date.getMinutes(),                 //分   
	    "s+" : date.getSeconds(),                 //秒   
	    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
	    "S"  : date.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	}
	</script>
</body>
</html>