$(function() {
	$('#datagrid').datagrid({
		fit : true,
		border : false,
		singleSelect : true,
		rownumbers : true,
		pageSize : 20,
		toolbar : "#datagrid_tb",
		pagination : true,
		url : 'statistics/getTjList.action',
		queryParams:{
		},
		columns : [ [
		       {
					field : 'scantime',
					title : '扫码时间',
					width : '100',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'scanWechat',
					title : '扫码微信号',
					width : '100',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'wechatName',
					title : '微信名',
					width : '100',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'nickName',
					title : '昵称',
					width : '100',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'wechatTel',
					title : '电话',
					width : '100',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'scannum',
					title : '扫码数量',
					width : '100',
					align : 'center',
					halign : 'center'
				}
			      
		 ] ],
		 onDblClickRow: function(rowIndex, rowData){		

		},
		onLoadSuccess : function() {
		}
	});
	$('#search').click(function() {
		
		$("#datagrid").datagrid('reload', {
			wechat : $("#wechat").val(),
			startTime : $("#startTime").datebox("getValue"),
			endTime : $("#endTime").datebox("getValue")
		});
	});
});

function exports(){
	$.ajax({
		type : "POST",
		url : 'statistics/exportTj.action',
		data:{
			/*activiateStatus : $("#activiateStatus").val(),
			startTime : $("#startTime").datebox("getValue"),
			endTime : $("#endTime").datebox("getValue")*/
		},
		success : function(result) {
			layer.alert('下载完成', {
			  skin: 'layui-layer-molv' //样式类名
			  ,closeBtn: 0
			});
		},
		failure : function(error) {
			console.log(error);
		}
	});
}