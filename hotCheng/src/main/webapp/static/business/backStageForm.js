var imgStr=$("#carShowImg").attr("src"); //图片数据
$("#pageTypeName").combobox({
	editable : false,
	required : true,
	data : [ {
		text : '目录',
		value : 'folder'
	}, {
		text : '页面',
		value : 'page'
	} ],
	valueField : 'value',
	textField : 'text',
	panelHeight : 'auto',
	onSelect: function (data) {
	   var val=data.value;
	   $('#pageType').val(val);
	   if(val == 'folder'){
		   document.getElementById('main_url').style.display="none";
		   document.getElementById('open_detail').style.display="block";
	   }else{
		   document.getElementById('main_url').style.display="block";
		   document.getElementById('open_detail').style.display="none";
	   }
	}
});

function configPageDetail() {
	var paId=$("#pageId").val();
	  if(paId == null || paId == ''){
		   var fileName = $("#uploadFile").filebox('getValue');
		  if(fileName!=null&&fileName!=''){		
			  var url = "CarSysNation/getConfigPageDetail.action?pageId="+$("#pageId").val()+"&pageTitle="+$("#pageTitle").textbox('getValue');
				parent.openwin(url, "880px", "400px", "子页面配置"); 
			 }else{
				 msgWarn("请您先选择应用logo！"); 
			 }
	 }else{
		  var url = "CarSysNation/getConfigPageDetail.action?pageId="+$("#pageId").val()+"&pageTitle="+$("#pageTitle").textbox('getValue');
			parent.openwin(url, "880px", "400px", "子页面配置"); 
	 }
	 
	
}

$(function(){
	var uId=$('#userId').val();
	if(uId!=null){
		queryFile(uId);
	}
	if(imgStr!=null && imgStr !=""){
		$('#carTdImg').css('display','block'); 	
	}

	
	
});

$("#pageSuperior").combobox({
	url : 'BaseData/getDicData.action?dicTypeCode=mainPage',
	valueField : 'dictCode',
	textField : 'dictName',
	panelHeight : "auto",
	editable : false
});

$("#rmk").textbox({
	multiline : true,
	editable : true,
	required : false
});
$("#ordBy").textbox({
	editable : true,
	required : false
});
$('#mobile').textbox({
	editable:true,
	required:false
});
$('#tel').textbox({
	editable:true,
	required:false
});

$("#deptName").combotree({
	url:"HrCDept/getHrCDeptEasyUI.action",
	queryParams : {
		node : Constants.rootNodeCode
	},
	method : 'post',
	animate : true,
	lines : true,
	onBeforeExpand : function(node, param) {
		$(this).tree('options').queryParams = {
			node : node.code
		};
	},
	onLoadSuccess:function(node,data){
    },
    onClick : function(node){
    	$('#deptName').combotree('setValues',node.text);
    	$('#deptCode').val(node.code);
    }
});

$("#flag").combobox({
	data : Constants.flag,
	valueField : 'value',
	textField : 'text',
	editable : false,
	required : true,
	panelHeight : 'auto'
});

function saveFunLocal(){
	var sysId=$("#sysId").val();
	 if(sysId == null || sysId == ''){
		   var fileName = $("#uploadFile").val();
		  if(fileName ==null  || fileName==''){		
			  msgWarn("请您先选择应用logo！"); 
			  return ;
			 }
	 }
	 var param={
			 imgStr:imgStr
	 };
	saveRecord(backStage_Form,'CarSysNation/savePageMain.action',function(jsonData){	
		msgSaveSuc(jsonData.msg,function(){
			parent.window.location.reload();
	         var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	         parent.layer.close(index);
    	});				
	},param);
}


//保存方法
function saveFun() {
	var paId=$("#pageId").val();
	 if(paId == null || paId == ''){
		   var fileName = $("#uploadFile").filebox('getValue');
		  if(fileName ==null  || fileName==''){		
			  msgWarn("请您先选择应用logo！"); 
			  return ;
			 }
	 }
	saveRecord(backStage_Form,'CarSysNation/savePageMain.action',function(jsonData){
		/*msgSaveSuc(jsonData.msg,function(){
		    var fileName = $("#uploadFile").filebox('getValue');
			 if(fileName!=null&&fileName!=''){				
             	uploadFile1(jsonData.data.pageId);             	
             }
			
    	});*/		
		 var fileName = $("#uploadFile").filebox('getValue');
		 if(fileName!=null&&fileName!=''){		
			 $("#pageId").val(jsonData.data.pageId);
         	uploadFile1(jsonData.data.pageId);             	
         }else{
        	 var obj={
 					pageId: $("#pageId").val()
 			}
 			parent.callbackFun("CarSysNation/getConfigPageDetail.action","saveDetail",obj);
        	 msgWarn("保存成功！");
         }
	});
}

//上传附件
function uploadFile(pageId) {
	var fileName = $("#uploadFile").filebox('getValue');
	fileName = fileName.substring(fileName.lastIndexOf("\\")+1,fileName.length);
	$("#fileName").val(fileName);		
	$('#tblKey').val(pageId);	
	$.messager.progress({title : '提示',text : '上传文件中，请稍后....'});
	$('#addEquFile').form('submit', {
		url : 'CarSysNation/savePicContentData.action',
		method : 'post',
		success : function(data) {
			$.messager.progress('close');
			var result = JSON.parse(data);
			if(result.success){
				msgWarn("保存成功!");
			}
			parent.closewin();
			parent.callbackFun("views/system/backStage.action","searchMainPage");
			console.log(data);
		},
		error: function(data){
			
		}
	});		
	};

//上传附件
function uploadFile1(pageId) {
	var fileName = $("#uploadFile").filebox('getValue');
	fileName = fileName.substring(fileName.lastIndexOf("\\")+1,fileName.length);
	$("#fileName").val(fileName);		
	$('#tblKey').val(pageId);	
	$.messager.progress({title : '提示',text : '上传文件中，请稍后....'});
	$('#addEquFile').form('submit', {
		url : 'CarSysNation/savePicContentData.action',
		method : 'post',
		success : function(data) {
			var obj={
					pageId: $("#pageId").val()
			}
			parent.callbackFun("views/system/backStage.action","searchMainPage");
			parent.callbackFun("CarSysNation/getConfigPageDetail.action","saveDetail",obj);
			$.messager.progress('close');
			var result = JSON.parse(data);
			if(result.success){
				msgWarn("保存成功!");
			}
			console.log(data);
		},
		error: function(data){
			
		}
	});		
	};

 //下载附件
 function downloadFile(){
	var attId = $("#attId").val();
	if(attId==null||attId==""){
		msgError("没有附件下载")
	}else{
		
		window.open("comCAtt/downloadFile.action?id="+attId);
	}
}
 
//查询附件
function queryFile(userId){
		  $.ajax({
	    		url : 'comCAtt/getComCAttMapInfoList.action',
	    		method : 'post',
	    		data : {
	    			tblName : "SYS_C_USER",
	    			tblKey:userId
	    		},
	    		success : function(result) {
	    			var json = JSON.parse(result);
	    			if(!$.isEmptyObject(json)){
	    				var fileName = json[0].fileName;
		    			$("#attId").val(json[0].attId);
		    			$("#uploadFile").textbox("setValue",fileName);
		    			
	    			}	    			
	    		},
	    		failure : function() {
	    		msgError("操作失败！")
	    		}
	    	});
		
}

function closeCurrentWin() {
	parent.closewin();
}