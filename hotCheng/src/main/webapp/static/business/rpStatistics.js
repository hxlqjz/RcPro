$(function() {
	$('#datagrid').datagrid({
		fit : true,
		border : false,
		singleSelect : true,
		rownumbers : true,
		pageSize : 20,
		toolbar : "#datagrid_tb",
		pagination : true,
		url : 'statistics/getList.action',
		queryParams:{
		},
		columns : [ [
				{
					field : 'id',
					title : 'id',
					width : '100',
					hidden:true
				},
				{
					field : 'presaleArea',
					title : '预售地区',
					width : '100',
					align : 'center',
					halign : 'center'
					
				},
				{
					field : 'qrNo',
					title : '二维码序号',
					width : '120',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'productModel',
					title : '产品型号',
					width : '100',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'activiateStatus',
					title : '激活状态',
					width : '100',
					align : 'center',
					halign : 'center',
					formatter:function(val,row){
						if(val == 1){
							return "待销售";
						}else if(val == 2){
							return "已销售";
						}else if(val == 0){
							return "未激活";
						}
						else{return "";}
					}
				},{
					field : 'qualityStart',
					title : '起始质保日期',
					width : '100',
					align : 'center',
					halign : 'center'
					/*formatter:function(value,row,index){  
						if (value == null || value == '') {  
					        return '';  
					    }  
					    var dt;  
					    if (value instanceof Date) {  
					        dt = value;  
					    } else {  
					        dt = new Date(value);  
					    }  
					  
					    return dt.format("yyyy-MM-dd");  
					} */
				},{
					field : 'qualityEnd',
					title : '终止质保日期',
					width : '100',
					align : 'center',
					halign : 'center',
					formatter:function(value,row,index){  
						if (value == null || value == '') {  
					        return '';  
					    }  
					    var dt;  
					    if (value instanceof Date) {  
					        dt = value;  
					    } else {  
					        dt = new Date(value);  
					    }  
					  
					    return dt.format("yyyy-MM-dd");  
					} 
				},
				{
					field : 'scanTime',
					title : '扫码时间',
					width : '100',
					align : 'center',
					halign : 'center'
					/*formatter:function(value,row,index){  
						if (value == null || value == '') {  
					        return '';  
					    }  
					    var dt;  
					    if (value instanceof Date) {  
					        dt = value;  
					    } else {  
					        dt = new Date(value);  
					    }  
					  
					    return dt.format("yyyy-MM-dd hh:mm:ss");  
					} */
				},
				{
					field : 'scanWechat',
					title : '扫码微信号',
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
				}
			      
		 ] ],
		 onDblClickRow: function(rowIndex, rowData){		
				var url = "statistics/getDetail.action?id=" + rowData.id;
				layer.open({
			        type: 2
			        ,title: "查看"
			        ,area: ["880px", "400px"]
			        ,shade: 0.1
			        ,maxmin: true
			        ,offset: 'auto'
			        ,content: url
			        ,btn2: function(){
			          layer.closeAll();
			        },
			        success : function(layero){ 
			        	layer.setTop(layero); 
			        },
			        zIndex: layer.zIndex
			    });

		},
		onLoadSuccess : function() {
		}
	});
	$('#search').click(function() {
		
		$("#datagrid").datagrid('reload', {
			qrNo : $("#qrNo").val(),
			startTime : $("#startTime").datebox("getValue"),
			endTime : $("#endTime").datebox("getValue")
		});
	});
});

// 增加记录
function insert() {
	var url = "statistics/loadInfo.action";
	layer.open({
        type: 2
        ,title: "新增"
        ,area: ["880px", "300px"]
        ,shade: 0.1
        ,maxmin: true
        ,offset: 'auto'
        ,content: url
        ,btn2: function(){
          layer.closeAll();
        },
        success : function(layero){ 
        	layer.setTop(layero); 
        },
        zIndex: layer.zIndex
    });
	

	
}

// 修改记录
function update() {
	if (checkSelectOne(datagrid)) {
		var row = $('#datagrid').datagrid('getSelected');
		var url = "statistics/loadInfo.action?id=" + row.id;
		layer.open({
	        type: 2
	        ,title: "修改"
	        ,area: ["880px", "300px"]
	        ,shade: 0.1
	        ,maxmin: true
	        ,offset: 'auto'
	        ,content: url
	        ,btn2: function(){
	          layer.closeAll();
	        },
	        success : function(layero){ 
	        	layer.setTop(layero); 
	        },
	        zIndex: layer.zIndex
	    });

	}
}

//删除记录
function del() {
	deleteRecord(datagrid, 'statistics/del.action', 'id');
}
//激活
function activate() {
	layer.open({
        type: 2
        ,title: "激活"
        ,area: ["880px", "200px"]
        ,shade: 0.1
        ,maxmin: true
        ,offset: 'auto'
        ,content: 'view/business/rpActivate.jsp' 
        ,btn2: function(){
          layer.closeAll();
        },
        success : function(layero){ 
        	layer.setTop(layero); 
        },
        zIndex: layer.zIndex
    });
}
function exports(){
	$.ajax({
		type : "POST",
		url : 'statistics/export.action',
		data:{
			activiateStatus : $("#activiateStatus").val(),
			productModel : $("#productModel").val(),
			startTime : $("#startTime").datebox("getValue"),
			endTime : $("#endTime").datebox("getValue")
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
function imports(){
	layer.open({
		  title: '选择导入的文件'
		  ,shade: 0.1
		  ,btn: ['确定','取消']
		  ,btn1:function(index, layero){
			  if($("#excelPath").val().length>0){
				  $.ajax({
						type : "POST",
						url : 'statistics/importExcel.action',
						data:{
							excelPath : $("#excelPath").val()
						},
						success : function(result) {
							$("#datagrid").datagrid('reload',{});
							layer.alert('导入完成', {
							  skin: 'layui-layer-molv' //样式类名
							  ,closeBtn: 0
							});
							
						},
						failure : function(error) {
							console.log(error);
						}
					});
			  }else{
				  return false;
				
			  }
			  
		  },
		  cancel:function(){
			  
		  }
		  ,content: '<div><input type="file" id="excelPath"></div>'
		});  

	
}