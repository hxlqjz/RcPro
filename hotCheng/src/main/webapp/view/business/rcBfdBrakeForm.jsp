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
				<tr>
					<td class="CTableTitle1" width="70px" align="right">技术BFD:</td>
					<td width="40%">
					<input id="bfd" name="bfd" type="text"
						value="${entity.bfd}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
						
					<td class="CTableTitle1" width="70px" align="right">前后轮:</td>
					<td width="40%">
					<input id="id" name="id" value="${entity.id}" type="hidden" />
					<input id="wheel" name="wheel" type="text"
						value="${entity.wheel}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
						
					<td class="CTableTitle1" width="70px" align="right">D码:</td>
					<td width="40%">
					<input id="dCode" name="dCode" type="text"
						value="${entity.dCode}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
						
					<td class="CTableTitle1" width="70px" align="right">FMSI码:</td>
					<td width="40%">
					<input id="fmsiCode" name="fmsiCode" type="text"
						value="${entity.fmsiCode}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>

				</tr> 
				
				<tr>
					<td class="CTableTitle1" width="10%" align="right">宝丰号:</td>
					<td width="40%">
					<input id="bfCode" name="bfCode" type="text"
						value="${entity.bfCode}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
						
					<td class="CTableTitle1" width="10%" align="right">备注:</td>
					<td width="40%" colspan="5">
					<input id="bfMemo" name="bfMemo" type="text"
						value="${entity.bfMemo}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>

				</tr> 

				<tr>
					<td class="CTableTitle1" width="10%" align="right">奔德士号:</td>
					<td width="40%">
					<input id="bdsCode" name="bdsCode" type="text"
						value="${entity.bdsCode}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
						
					<td class="CTableTitle1" width="10%" align="right">备注:</td>
					<td width="40%" colspan="5">
					<input id="bdsMemo" name="bdsMemo" type="text"
						value="${entity.bdsMemo}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>

				</tr> 
				
				<tr>
					<td class="CTableTitle1" width="60px" align="right">菲罗多码1:</td>
					<td width="40%">
					<input id="fldm1" name="fldm1" type="text"
						value="${entity.fldm1}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
						
					<td class="CTableTitle1" width="60px" align="right">菲罗多码2:</td>
					<td width="40%">
					<input id="fldm2" name="fldm2" type="text"
						value="${entity.fldm2}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
						
					<td class="CTableTitle1" width="60px" align="right">TRW码1:</td>
					<td width="40%">
					<input id="trw1" name="trw1" type="text"
						value="${entity.trw1}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
						
					<td class="CTableTitle1" width="60px" align="right">TRW码2:</td>
					<td width="40%">
					<input id="trw2" name="trw2" type="text"
						value="${entity.trw2}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>

				</tr> 
				<tr>
					<td class="CTableTitle1" width="60px" align="right">OE码1:</td>
					<td width="40%">
					<input id="oe1" name="oe1" type="text"
						value="${entity.oe1}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
						
					<td class="CTableTitle1" width="60px" align="right">OE码2:</td>
					<td width="40%">
					<input id="fldm2" name="oe2" type="text"
						value="${entity.oe2}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
						
					<td class="CTableTitle1" width="60px" align="right">OE码3:</td>
					<td width="40%">
					<input id="oe3" name="oe3" type="text"
						value="${entity.oe3}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
						
					<td class="CTableTitle1" width="60px" align="right">OE码4:</td>
					<td width="40%">
					<input id="oe4" name="oe4" type="text"
						value="${entity.oe4}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
	
					
				</tr> 
				<tr>
				<td class="CTableTitle1" width="60px" align="right">OE码5:</td>
					<td width="40%">
					<input id="oe5" name="oe5" type="text"
						value="${entity.oe5}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
				<td class="CTableTitle1" width="60px" align="right">选择图片:</td>
			<td width="40%" colspan="3">
			<input id="imgSrc" name="imgSrc" type="text"
				value="${entity.imgSrc}" class="easyui-textbox"
				style="width: 100%; height: 30px"></td>
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
		saveRecord(form, 'pcBfd/save.action', function(jsonData) {
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
	
	
	
	</script>
</body>
</html>