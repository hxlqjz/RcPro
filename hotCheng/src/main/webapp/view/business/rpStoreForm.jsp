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
					<td class="CTableTitle1" width="12%" align="right">识别码:</td>
					<td width="38%">
					<input type="hidden" id="id" name="id" value="${entity.id}" />
					<c:if test="${entity.idCode!='' and entity.idCode!=null}">
					    <input id="idCode" name="idCode" value="${entity.idCode}"  
					     class="easyui-textbox" readonly="readonly"  type="text"
						style="width: 100%; height: 30px" data-options="required:true">
					</c:if>
					<c:if test="${entity.idCode=='' or entity.idCode==null}">
					  <input id="idCode" name="idCode" value="${entity.idCode}"  
					 class="easyui-textbox" type="text"
						style="width: 100%; height: 30px" data-options="required:true,events:{focusout:focusoutFunction}"></c:if>
						</td>
					<td class="CTableTitle1" width="10%" align="right">门店名:</td>
					<td width="40%"><input id="storeName" name="storeName"
						value="${entity.storeName}" class="easyui-textbox"
						style="width: 100%; height: 30px"
						data-options="required:true"></td>

				</tr>
				<tr>
					<td class="CTableTitle1" width="12%" align="right">查询权限:</td>
					<td width="38%">
					<select id="queryPower" class="easyui-combobox"
						name="queryPower" style="width: 200px;">
							<option value="1" <c:if test="${ entity.queryPower != 2 }" >  selected="selected" </c:if> >高</option>
							<option  value="2" <c:if test="${ entity.queryPower == 2 }" >  selected="selected" </c:if>	>低</option>
					</select>
					</td>
					<td class="CTableTitle1" width="10%" align="right">角色权限:</td>
					<td width="40%">
					<select id="rolePower" class="easyui-combobox"
						name="rolePower" style="width: 200px;">
							<option value="1" <c:if test="${ entity.rolePower != 1 and entity.rolePower !=3 }" >  selected="selected" </c:if>	>总权限</option>
							<option  value="2" <c:if test="${ entity.rolePower == 2 }" >  selected="selected" </c:if>	>门店权限</option>
							<option  value="3" <c:if test="${ entity.rolePower == 3 }" >  selected="selected" </c:if>	>个人权限</option>
					</select>
					</td>

				</tr>
				<tr>
					<td class="CTableTitle1" width="12%" align="right">省份:</td>
					<td width="38%"><input id="province" name="province"
						value="${entity.province}" class="easyui-textbox"
						<c:if test="${entity.province !='' and entity.province != null}">
						readonly="readonly" 
						</c:if>
						style="width: 100%; height: 30px" data-options="required:true"></td>
					<td class="CTableTitle1" width="10%" align="right">说明:</td>
					<td width="40%"><input id="marks" name="marks"
						value="${entity.marks}" class="easyui-textbox"
						style="width: 100%; height: 30px"
						data-options=""></td>
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

			saveRecord(form, 'pcStore/save.action', function(jsonData) {
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
		
		function focusoutFunction(){
			$.ajax({
				type : "POST",
				url : 'pcStore/getInfoByIdCode.action',
				data:{
					idCode:this.value
				},
				success : function(result) {
					result = JSON.parse(result);
					console.log(result.data.res)
					//var l = result.data
					if(result.data.res != null && result.data.res != {}){
						$("#idCode").textbox('setValue',"");
						layer.alert('识别码已经存在', {
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