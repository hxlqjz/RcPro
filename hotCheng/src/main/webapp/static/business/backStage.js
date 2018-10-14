
$('#backStage_datagrid').datagrid({
					fit : true,
					border : false,
					singleSelect : true,
					rownumbers : true,
					pageSize : 20,
					toolbar : "#backStage_datagrid_tb",
					pagination : true,
					url : 'CarSysNation/searchPageMainList.action',
					columns : [ [
							{
								field : 'sysId',
								title : 'sysId',
								width : '100',
								hidden:true
							},
							{
								field : 'content',
								title : 'logo',
								width : '100',
								align : 'center',
								halign : 'center',
								formatter : function(value, row) {
									return '<img style="height:50px;width:50px" src="'+value+'"  onclick="enlarge($(this))"  >';
								}
							},
							{
								field : 'carBrand',
								title : '品牌',
								width : '120',
								align : 'center',
								halign : 'center'
							},
							{
								field : 'carNation',
								title : '国别',
								width : '200',
								align : 'center',
								halign : 'center'
							},{
								field : 'alphabet',
								title : '字母表',
								width : '120',
								align : 'center',
								halign : 'center'
							},
							{
								field : 'orderNo',
								title : '排序',
								width : '200',
								align : 'center',
								halign : 'center'
							},
							{
								field : 'pageType',
								title : '类型',
								width : '120',
								align : 'center',
								halign : 'center',
								formatter : function(value, row) {
									var pageType=row.pageType;
									if(pageType == 'page'){
										return "页面";
									}else{
										return "目录";
									}
									
						       },
						       hidden:true
							},
							{
								field : 'pageUrl',
								title : '应用详情',
								width : '260',
								align : 'center',
								halign : 'center',
								formatter : function(value, row) {
									var pageType=row.pageType;
									if(pageType == 'page'){
										return value;
									}else{
										return "有子页面";
									}									
						       },
						       hidden:true
							}
							 ] ],
							 onDblClickRow: function(rowIndex, rowData){		
								   if(gridCodeRuleEndEdit()){   	   
									   $('#backStage_datagrid').datagrid("beginEdit", rowIndex);
								   }
							},
					onLoadSuccess : function() {
					//	$('.button-edit').linkbutton({});
					}
				});





// 查询表格
function searchMainPage() {	
	$('#backStage_datagrid').datagrid('load', {		
	});
}

// 增加记录
function addPageMain() {
	var url = "CarSysNation/loadCarSysNation.action";
	layer.open({
        type: 2
        ,title: "新增"
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
	

	
}

// 修改记录
function editBackStage() {
	if (checkSelectOne(backStage_datagrid)) {
		var row = $('#backStage_datagrid').datagrid('getSelected');
		var url = "CarSysNation/loadCarSysNation.action?id=" + row.sysId;
		layer.open({
	        type: 2
	        ,title: "新增"
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

	}
}

//删除记录
function delPageMain() {
	deleteRecord(backStage_datagrid, 'CarSysNation/deleCarSysNation.action', 'sysId');
}

function resetSysCUserPswd() {
	if (checkSelectOne(SysCUser_datagrid)) {
		var record = $('#SysCUser_datagrid').datagrid('getSelected');
		var url = "SysCUser/getResetUserPwdPage.action?userId=" + record.userId;
		parent.openwin(url, "400px", "300px", "密码重置");
	}
}

function setUserRole(userId) {
	var url = "configPage/getConfigPageDetail.action?userId=" + userId;
	parent.openwin(url, "850px", "500px", "应用页面配置");
}

function gridCodeRuleEndEdit(){
	var rows =$('#backStage_datagrid').datagrid('getRows');  
	for ( var  i = 0; i < rows.length; i++) {
		if ($('#backStage_datagrid').datagrid('validateRow', i)) {
			$('#backStage_datagrid').datagrid('endEdit', i);
		}else{
			jAlert("请您输入必填信息！", "提示");	
			return false;
		}
	}
	return true;
	};
	
	function enlarge(f){
		  var _this = f;//将当前的pimg元素作为_this传入函数  
	      imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);  
	}
	
	function imgShow(outerdiv, innerdiv, bigimg, _this){  
	    var src = _this.attr("src");//获取当前点击的pimg元素中的src属性  
	    $(bigimg).attr("src", src);//设置#bigimg元素的src属性  
	  
	      /*获取当前点击图片的真实大小，并显示弹出层及大图*/  
	    $("<img/>").attr("src", src).load(function(){  
	        var windowW = $(window).width();//获取当前窗口宽度  
	        var windowH = $(window).height();//获取当前窗口高度  
	        var realWidth = this.width;//获取图片真实宽度  
	        var realHeight = this.height;//获取图片真实高度  
	        var imgWidth, imgHeight;  
	        var scale = 0.9;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放  
	          
	        if(realHeight>windowH*scale) {//判断图片高度  
	            imgHeight = windowH*scale;//如大于窗口高度，图片高度进行缩放  
	            imgWidth = imgHeight/realHeight*realWidth;//等比例缩放宽度  
	            if(imgWidth>windowW*scale) {//如宽度扔大于窗口宽度  
	                imgWidth = windowW*scale;//再对宽度进行缩放  
	                imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度  
	            }  
	        } else if(realWidth>windowW*scale) {//如图片高度合适，判断图片宽度  
	            imgWidth = windowW*scale;//如大于窗口宽度，图片宽度进行缩放  
	            imgHeight = imgWidth/realWidth*realHeight;//等比例缩放高度  
	        } else {//如果图片真实高度和宽度都符合要求，高宽不变  
	            imgWidth = realWidth;  
	            imgHeight = realHeight;  
	        }  
	            $(bigimg).css("width",imgWidth);//以最终的宽度对图片缩放  
	          
	        var w = (windowW-imgWidth)/2;//计算图片与窗口左边距  
	        var h = (windowH-imgHeight)/2;//计算图片与窗口上边距  
	        $(innerdiv).css({"top":h, "left":w});//设置#innerdiv的top和left属性  
	        $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg  
	    });        
	    $(outerdiv).click(function(){//再次点击淡出消失弹出层  
	        $(this).fadeOut("fast");  
	    });  
	}	