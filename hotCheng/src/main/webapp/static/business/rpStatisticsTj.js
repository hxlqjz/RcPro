$(function() {
	$('#datagrid').datagrid({
		fit : true,
		border : false,
		singleSelect : true,
		rownumbers : true,
		pageSize : 20,
		toolbar : "#datagrid_tb",
		pagination : true,
		url : 'pcStatistics/getTjList.action',
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
					title : '微信openID',
					width : '100',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'userName',
					title : '姓名',
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
		url : 'pcSstatistics/exportTj.action',
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
$("#exports").click(function(){
	var url = 'pcStatistics/exportTj.action';
	var param = {
		wechat : $("#wechat").val(),
		startTime : $("#startTime").datebox("getValue"),
		endTime : $("#endTime").datebox("getValue")
	}; 
	//var p = "activiateStatus="+$("#activiateStatus").val()+"&productModel="+$("#productModel").val()+"&startTime="+$("#startTime").datebox("getValue")
	$('#exports').attr('href',url + '?'  + 'param=' +  encodeURI(JSON.stringify(param)));
})