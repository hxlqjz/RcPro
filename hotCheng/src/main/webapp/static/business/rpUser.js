$(function() {
	$('#datagrid').datagrid({
		fit : true,
		border : false,
		singleSelect : true,
		rownumbers : true,
		pageSize : 20,
		toolbar : "#datagrid_tb",
		pagination : true,
		url : 'pcUser/getList.action',
		queryParams:{
			name : $("#name").val(),
			idCode : $("#idCode").val()
		},
		columns : [ [
				{
					field : 'id',
					title : 'id',
					width : '1',
					hidden:true
				},
				{
					field : 'wechatNo',
					title : '微信openID',
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
					field : 'idCode',
					title : '识别码',
					width : '100',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'userName',
					title : '姓名',
					width : '120',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'tel',
					title : '电话',
					width : '120',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'info',
					title : '备注信息',
					width : '200',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'queryPower',
					title : '信息查询权限',
					width : '100',
					align : 'center',
					halign : 'center',
					formatter:function(val,row){
						if(val == 1){
							return "高";
						}else if(val == 2){
							return "底";
						}else{return "";}
					}
				},
				{
					field : 'rolePower',
					title : '角色权限',
					width : '100',
					align : 'center',
					halign : 'center',
					formatter:function(val,row){
						if(val == 1){
							return "总权限";
						}else if(val == 2){
							return "门店权限";
						}else if(val == 3){
							return "个人权限";
						}else{
							return "";
						}
					}
				},
				{
					field : 'isBlack',
					title : '黑名单',
					width : '100',
					align : 'center',
					halign : 'center',
					formatter:function(val,row){
						if(val == 0){
							return "正常";
						}else if(val == 1){
							return "拉黑";
						}else{return "";}
					}
				}
			      
		 ] ],
		 onDblClickRow: function(rowIndex, rowData){		
			   if(gridCodeRuleEndEdit()){   	   
				   $('#datagrid').datagrid("beginEdit", rowIndex);
			   }
		},
		onLoadSuccess : function() {
		//	$('.button-edit').linkbutton({});
		}
	});
	$('#search').click(function() {
		$("#datagrid").datagrid('reload', {
			name : $("#name").val(),
			idCode : $("#idCode").val()
		});
	});
});


// 增加记录
function insert() {
	var url = "pcUser/loadInfo.action";
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
		var url = "pcUser/loadInfo.action?id=" + row.id;
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
	deleteRecord(datagrid, 'pcUser/del.action', 'id');
}

$("#exports").click(function(){
	var url = 'pcUser/export.action';
	var param = {
		name : $("#name").val(),
		idCode : $("#idCode").val()
	}; 
	$('#exports').attr('href',url + '?'  + 'param=' +  encodeURI(JSON.stringify(param)));
});
