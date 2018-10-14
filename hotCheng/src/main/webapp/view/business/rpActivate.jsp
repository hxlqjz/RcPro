<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
					<td class="CTableTitle1" width="12%" align="right">起始号:</td>
					<td width="38%">
					  <input id="start" name="start" class="easyui-numberbox"
						data-options="required:true, precision:0"
						style="width: 100%; height: 30px">
						</td>
					<td class="CTableTitle1" width="10%" align="right">终止号:</td>
					<td width="40%"><input id="end" name="end" type="text"  class="easyui-numberbox"
						data-options="required:true, precision:0"
					style="width: 100%; height: 30px"></td>
					<td class="CTableTitle1" width="12%" align="right">预售地区:</td>
					<td width="38%">
					  <input id="presaleArea" name="presaleArea" data-options="required:true"
					 class="easyui-textbox" type="text" style="width: 100%; height: 30px">
						</td>

				</tr> 
			</table>
		</form>
		<table class="CTable" width="95%" border="0" cellpadding="3"
			cellspacing="8" style="margin: auto;padding-top:10px; table-layout: fixed">
			<tr>
				<td colspan="4" width="95%" align="center"><a
					href="javascript:;" onclick="activate()" class="btn default">提交</a> <a
					href="javascript:;" onclick="closeWin()" class="btn default">取消</a></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
	function activate() {
		saveRecord(form, 'statistics/activate.action', function(jsonData) {
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