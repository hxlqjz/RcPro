$(function() {
	$('#datagrid').datagrid({
		fit : true,
		border : false,
		singleSelect : true,
		rownumbers : true,
		pageSize : 20,
		toolbar : "#datagrid_tb",
		pagination : true,
		url : 'pcBfd/getList.action',
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
					field : 'imgSrc',
					title : '图片',
					width : '100',
					align : 'center',
					halign : 'center',
					formatter:function(val,row){
						var str;
						if(val !=null && val != ""){ 
							str = "<a href='javascript:void(0)'  onclick='upload("+row.id+")'>" +
									"<img src='/hotCheng/pcBfd/tofindPic.action?imgName="+val+"' width='50px' height='50px'/></a>";
						}else{
							str = "<a href='javascript:void(0)' onclick='upload("+row.id+")'>点击上传</a>";
						}
						return str;
					}
				},
				{
					field : 'wheel',
					title : '前后轮',
					width : '100',
					align : 'center',
					halign : 'center'
					
				},
				{
					field : 'bfd',
					title : '技术BFD',
					width : '120',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'bfCode',
					title : '宝丰号',
					width : '100',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'bdsCode',
					title : '奔德士号',
					width : '100',
					align : 'center',
					halign : 'center'
				},{
					field : 'dCode',
					title : 'D码',
					width : '100',
					align : 'center',
					halign : 'center'
					
				},{
					field : 'fmsiCode',
					title : 'FMSI码',
					width : '100',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'fldm1',
					title : '菲罗多码',
					width : '100',
					align : 'center',
					halign : 'center'
					
				},
				{
					field : 'trw1',
					title : 'TRW码',
					width : '100',
					align : 'center',
					halign : 'center'
				},
				{
					field : 'oe1',
					title : 'OE码',
					width : '100',
					align : 'center',
					halign : 'center'
				}
			      
		 ] ],
		 onDblClickRow: function(rowIndex, rowData){		
				var url = "pcBfd/getDetail.action?id=" + rowData.id;
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
			bfd : $("#bfd").val(),
			bCode : $("#bCode").val()
		});
	});
});

// 增加记录
function insert() {
	var url = "pcBfd/loadInfo.action";
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
		var url = "pcBfd/loadInfo.action?id=" + row.id;
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

// 删除记录
function del() {
	deleteRecord(datagrid, 'pcBfd/del.action', 'id');
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
						url : 'pcBfd/importExcel.action',
						data:{
							excelPath : $("#excelPath").val()
						},
						success : function(result) {
							$("#datagrid").datagrid('reload',{});
							layer.alert('导入完成', {
							  skin: 'layui-layer-molv' // 样式类名
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

function upload(id){
	var url = "pcBfd/loadImg.action?id=" + id;
	layer.open({
        type: 2
        ,title: "上传图片"
        ,area: ["400px", "450px"]
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
