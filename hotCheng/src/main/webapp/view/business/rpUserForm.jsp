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
					<input type="hidden" id="id" name="id" value="${entity.id}" />
					<td class="CTableTitle1" width="10%" align="right">微信openID:</td>
					<td width="40%" colspan="3"><input id="wechatNo" name="wechatNo" <c:if test="${type == 'edit' }">readonly="readonly"</c:if>
						value="${entity.wechatNo}" class="easyui-textbox"
						style="width: 100%; height: 30px"
						data-options="required:true"></td>
				</tr>
				<tr>
				<td class="CTableTitle1" width="10%" align="right">微信昵称:</td>
					<td width="40%"><input id="nickName" name="nickName" <c:if test="${type == 'edit' }">readonly="readonly"</c:if>
						value="${entity.nickName}" class="easyui-textbox"
						style="width: 100%; height: 30px"
						data-options="required:true"></td>
					<td class="CTableTitle1" width="12%" align="right">识别码:</td>
					<td width="38%">
					 <input class="easyui-combobox"
									style="width: 200px" name="idCode" id="idCode"
									editable="false" value="${entity.idCode}"  
									data-options="url:'store/getIdCodeList.action',valueField:'idCode', textField:'idCode', panelHeight:'auto'" />
					<%-- <input id="idCode" name="idCode" value="${entity.idCode}"  
					 class="easyui-textbox"
						data-options="required:true"
						style="width: 100%; height: 30px"> --%>
					</td>
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
					<td class="CTableTitle1" width="12%" align="right">姓名:</td>
					<td width="38%"><input id="userName" name="userName"
						value="${entity.userName}" class="easyui-textbox"
						data-options=""
						style="width: 100%; height: 30px"></td>
					<td class="CTableTitle1" width="10%" align="right">电话:</td>
					<td width="40%"><input id="tel" name="tel"
						value="${entity.tel}" class="easyui-textbox"
						style="width: 100%; height: 30px"
						data-options=""></td>
				</tr>
				<tr>
					<td class="CTableTitle1" width="12%" align="right">备注信息:</td>
					<td width="38%"><input id="info" name="info"
						value="${entity.info}" class="easyui-textbox"
						style="width: 100%; height: 30px"></td>
					<td class="CTableTitle1" width="10%" align="right">黑名单:</td>
					<td width="40%">
					<select id="isBlack" class="easyui-combobox"
						name="isBlack" style="width: 200px;">
							<option value="0"  <c:if test="${ entity.isBlack == 0 }" >  selected="selected" </c:if>>正常</option>
							<option  value="1" <c:if test="${ entity.isBlack == 1 }" >  selected="selected" </c:if>	>拉黑</option>
					</select>
				</td>
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

			saveRecord(form, 'pcUser/save.action', function(jsonData) {
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
		$("input[name=idCode]").focusout(function(){
			$.ajax({
				type : "POST",
				url : 'pcStore/getInfoByIdCode.action',
				data:{
					idCode:$("#idCode").val()
				},
				success : function(result) {
					result = JSON.parse(result);
					console.log(result.data.res)
					//var l = result.data
					if(result.data.res == null || result.data.res == {}){
						$("#idCode").val("") ;
						layer.alert('识别码不存在', {
						  skin: 'layui-layer-molv' //样式类名
						  ,closeBtn: 0
						});
					}else{
					}
				},
				failure : function(error) {
					console.log(error)
				}
			});

        });
	</script>
</body>
</html>