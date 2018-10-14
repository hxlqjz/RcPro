<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.kdgcsoft.power.common.bean.Principal"%> 
<%@page import="org.apache.shiro.SecurityUtils"%> 
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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true">
		<form id="form" method="post">
			<table class="CTable" width="95%" border="0" cellpadding="3"
				cellspacing="8" style="margin: auto; table-layout: fixed">
				<!--主键-->

				<tr>
					<%-- <td class="CTableTitle1" width="12%" align="right">预售地区:</td>
					<td width="38%">
					<input type="hidden" id="id" name="id" value="${entity.id}" />
					<input type="hidden" id="activiateStatus" name="activiateStatus" value="${entity.activiateStatus}" />
					  <input id="presaleArea" name="presaleArea" value="${entity.presaleArea}"  
					 class="easyui-textbox"
						data-options="required:true"
						style="width: 100%; height: 30px">
						</td> --%>
					<td class="CTableTitle1" width="10%" align="right">二维码Code:</td>
					<td width="40%"><input id="qrCode" name="qrCode" type="text"
						value="${entity.qrCode}" class="easyui-textbox"
						style="width: 100%; height: 30px"
						<c:if test="${entity.qrCode != '' }">data-options="required:true,events:{change:focusoutCode}"</c:if>
						<c:if test="${entity.qrCode == '' }">data-options="required:true,events:{focusout:focusoutCode}"</c:if>
						></td>

				</tr> 
				<tr>
				<td class="CTableTitle1" width="12%" align="right">二维码序号:</td>
					<td width="38%">
					  <input id="qrNo" name="qrNo" value="${entity.qrNo}"  
					 class="easyui-textbox" type="text" 
					 <c:if test="${entity.qrNo != '' }">data-options="required:true,events:{change:focusoutNo}"</c:if>
						<c:if test="${entity.qrNo == '' }">data-options="required:true,events:{focusout:focusoutNo}"</c:if>
						style="width: 100%; height: 30px">
						</td>
					<%-- <td class="CTableTitle1" width="10%" align="right">产品型号:</td>
					<td width="40%"><input id="productModel" name="productModel"
						value="${entity.productModel}" class="easyui-textbox"
						style="width: 100%; height: 30px"
						data-options="required:true"></td> --%>

				</tr>
				<tr>
				<td class="CTableTitle1" width="10%" align="right">产品型号:</td>
					<td width="40%"><input id="productModel" name="productModel"
						value="${entity.productModel}" class="easyui-textbox"
						style="width: 100%; height: 30px"
						data-options="required:true"></td>
					<%-- <td class="CTableTitle1" width="12%" align="right">起始质保日期:</td>
					<td width="38%">
						 <input class="easyui-datebox"  type="text"  name="qualityStart" value="${entity.qualityStart}" data-options="required:true">
						</td>
					<td class="CTableTitle1" width="10%"  align="right">终止质保日期:</td>
					<td width="40%"><input class="easyui-datebox"  type="text"  name="qualityEnd"  value="${entity.qualityEnd}" data-options="required:true">
						</td> --%>
				</tr>


			</table>
		</form>
		<table class="CTable" width="95%" border="0" cellpadding="3"
			cellspacing="8" style="margin: auto; table-layout: fixed">
			<tr>
				<td colspan="4" width="95%" align="center"><a
					href="javascript:;" onclick="save()" class="btn default">保存</a> <a
					href="javascript:;" onclick="closeWin()" class="btn default">取消</a></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
	function save() {
		saveRecord(form, 'statistics/save.action', function(jsonData) {
			msgSaveSuc(jsonData.msg,function(){
				parent.$("#datagrid").datagrid('reload', {});
		        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		        parent.layer.close(index);
	    	});	
		});
	}

	function closeWin(){
		parent.$("#datagrid").datagrid('reload', {});
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    parent.layer.close(index);
	}
	
	function focusoutCode(){
		$.ajax({
			type : "POST",
			url : 'statistics/checkQr.action',
			data:{
				qrCode:this.value,
				qrNo : null
			},
			success : function(result) {
				result = JSON.parse(result);
				console.log(result.data.res)
				//var l = result.data
				if(result.data.res != null && result.data.res != {}){
					$("#qrCode").textbox('setValue',"");
					layer.alert('二维码编号已经存在', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					});
				}
			},
			failure : function(error) {
				console.log(error)
			}
		});
	}
	function focusoutNo(){
		$.ajax({
			type : "POST",
			url : 'statistics/checkQr.action',
			data:{
				//qrCode:null,  
				qrNo : this.value
			},
			success : function(result) {
				result = JSON.parse(result);
				console.log(result.data.res)
				//var l = result.data
				if(result.data.res != null && result.data.res != {}){
					$("#qrNo").textbox('setValue',"");
					layer.alert('二维码序号已经存在', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					});
				}
			},
			failure : function(error) {
				console.log(error)
			}
		});
	}
	
	</script>
</body>
</html>