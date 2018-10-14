function checkSelectOne(grid_id) {
	var rows = $(grid_id).datagrid('getSelections');
	if (rows.length != 1) {
		msgWarn('请先选择一条数据!');
		return false;
	}
	return true;
}
//警告提示
function msgWarn(msg){
	layer.msg("提示："+msg, {
		time : 1500,
		icon : 7,
		shift : 6
	});
}
//普通提示
function msgInfo(msg){
	layer.msg("提示："+msg, {
		time : 1500,
		icon : 6,
		shift : 2
	});
}
//错误提示
function msgError(msg){
	layer.msg("错误："+msg, {
		time : 1500,
		icon : 2,
		shift : 6
	});
}
//成功提示
function msgSuccess(msg){
	layer.msg("提示："+msg, {
		time : 1500,
		icon : 1,
		shift : 2
	});
}
//保存成功提示
function msgSaveSuc(msg,callback){
	layer.alert("提示："+msg, {
		time : 0,
		icon : 1,
		shift : 5,
		shade : [0.3,'#eee'],
		btn:['确定'],
		yes:callback
	});
}
//选择对话框
function msgConfirm(msg,callback){
	layer.alert("提示："+msg, {
		time : 0,
		icon : 3,
		shift : 5,
		shade : [0.3,'#eee'],
		btn:['确定','取消'],
		yes:callback
	});
}
/**
 * 
 * @param grid_id
 *            grid的id
 * @param url
 *            删除的url
 * @param idName
 *            主键列名
 * @param calback
 *            回调函数
 * @returns {Boolean}
 */
function deleteRecord(grid_id, url, idName, calback) {
	var row = $(grid_id).datagrid('getSelections');
	if (row.length == 0) {
		msgWarn('请先选择要删除的数据!');
		return false;
	}
	var idArray = new Array();
	for ( var i = 0; i < row.length; i++) {
		idArray.push(row[i][idName]);
	}
	msgConfirm("确定删除数据吗？", function(btn) {
		if (btn) {
			$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍候....'
			}); // 显示进度条
			$.ajax({
				url : url,
				data : {
					id : idArray.join(",")
				},
				type:'post',
				dataType : 'json',
				success : function(result) {
					if (result.success) {
						$.messager.progress('close'); // 如果提交成功则隐藏进度条
						$(grid_id).datagrid('reload');
						msgSuccess('删除成功');
						if (calback) {
							calback(result);
						}
					} else {
						msgError('删除失败');
					}
				}
			});
		}
		return true;
	});
}

/**
 * 选人弹框
 * @param 参数obj对象，
 * 其中属性pageUrl定义为需要操作的页面url（字符串类型）,
 * 属性callback定义为pageUrl指定页面中需要调用的回调函数名称（字符串类型）
 * @return udObj返回值为对象直接被回调函数callback使用，
 * 其中包含userCode、userName、deptCode、deptId，
 * url 请求的弹出框url
 * title 弹出框标题
 * type （备用字段，类型。例如课题页面区分是组长还是副组长）
 * width  宽度
 * height 高度
 */
function showUserWin(obj){
	var width = "600px" ;
	var height = "500px" ;
	if(obj.width){
		width = obj.width ;
	}
	if(obj.height){
		height = obj.height ;
	}
	var url = obj.url+'?callback='+ obj.callback +'&pageUrl=' + obj.pageUrl+'&type=' + obj.type; 
	parent.openwin(url, width, height, obj.title);
}
/**
 * 判断树的节点有没有被选中
 * @param tree_id
 * @returns {Boolean}
 */
function checkSelectTreeNode(tree_id){
	var selectNode = $(tree_id).tree('getSelected');
	if(selectNode){
		return true;
	} else{
		msgWarn("请选择一个节点！");
		return false;
	}
}
/**
 * 删除一个树节点
 * @param tree_id
 * @param url
 * @param calback
 * @returns {Boolean}
 */
function delTreeNode(tree_id, url, calback){
	var selectNode = $(tree_id).tree('getSelected');
    if(checkSelectTreeNode(tree_id)){
		if(!$(tree_id).tree('isLeaf',selectNode.target)){
			msgWarn("有子节点不能删除!");
			return false;
		}
		msgConfirm("确认删除该节点?",function(r){
	        if(r){
	            $.ajax({
	            	url : url,
	            	method : 'post',
					data : {
						id : selectNode.id
					},
					success : function(data) {
	            		$(tree_id).tree('remove',selectNode.target);
						if (calback) {
							calback(data);
						}
						msgSuccess("删除成功！");
					}
	            });
	    	}
	        return true;
    	});
    }
	return false;
}
/**
 * 工作流上报
 * @param gird_id 列表id
 * @param idName 主键名
 * @param pageUrl 当前页面的URL
 * @param calbackFun 上报完成后回调方法
 * @param processDefName 工作流编码
 * @param busUrl 上报url
 */
function flowReport(gird_id,idName,pageUrl,calbackFun,processDefName,busUrl){
	if (checkSelectOne(gird_id)) {
		var row = $(gird_id).datagrid('getSelected');
		if (row.processinstid != "" && row.processinstid != null) {
			msgWarn("请选择未上报的记录！");
		} else {
			var url = "workflow/openReportWin.action?id="
					+ row[idName]+"&pageUrl="+pageUrl+"&calbackFun="+calbackFun
					+"&busUrl="+busUrl+"&processDefName="+processDefName;
			parent.openwin(url, "600px", "400px", "上报");
		}
	}
}
/**
 * 工作流审批
 * @param gird_id 列表id
 * @param idName id名称
 * @param pageUrl 当前页面url
 * @param calbackFun 审批成功后回调方法
 * @param busUrl 审批URL
 */
function flowApprove(gird_id,idName,pageUrl,calbackFun,busUrl){
	if (checkSelectOne(gird_id)) {
		var row = $(gird_id).datagrid('getSelected');
		if (row.processinstid != "" && row.processinstid != null) {
			var url = "workflow/openApproveWin.action?id="
					+ row[idName]+"&processInstId="+ row.processinstid
					+"&pageUrl="+pageUrl+"&calbackFun="+calbackFun+"&busUrl="+busUrl;
			parent.openwin(url, "600px", "400px", "审核");
		} else {
			msgWarn("请选择已上报的记录！");
		}
	}
}
/**
 * 流程查看
 * @param gird_id
 */
function flowView(gird_id) {
	if (checkSelectOne(gird_id)) {
		var row = $(gird_id).datagrid('getSelected');
		var url = Constants.flowViewUrl+"?processInstId="+ row.processinstid;
		window.open(url);
	}
}
/**
 * 表单保存公用方法
 * @param form_id 表单id
 * @param url 保存URL
 * @param calback 回调方法
 */
function saveRecord(form_id,url,calback,queryParams){
	if(!queryParams){
		queryParams={};
	}
	$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍候....'
	}); // 显示进度条
	$(form_id).form(
			'submit',
			{
				url : url,
				queryParams : queryParams,
				onSubmit : function() {
					var isValid = $(this).form('validate');
					if (!isValid) {
						$.messager.progress('close'); // 如果表单是无效的则隐藏进度条
					}
					return isValid; // 返回false终止表单提交
				},
				method : 'post',
				success : function(data) {
					$.messager.progress('close'); // 如果提交成功则隐藏进度条
					var jsonData = JSON.parse(data);
					calback(jsonData);
				},
				error : function() {
					$.messager.progress('close');
				}
			});
}

/***************************************** 可编辑表格 start ********************************************/
/**
 * 直接在easyui-datagrid的onClickCell事件调用onClickCell方法
 * 注：如有特殊需求可复写onClickCell方法
 */
var editIndex = undefined;
function endEditing(self){
	if (editIndex == undefined){
		return true
	}
	if ($(self).datagrid('validateRow', editIndex)){
		$(self).datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function onClickCell(index, field){
	if (endEditing(this)){
		$(this).datagrid('selectRow', index)
				.datagrid('editCell', {index:index,field:field});
		editIndex = index;
	}
}
/***************************************** end *******************************************/


String.prototype.format = function(args) {  
    var result = this;  
    if (arguments.length > 0) {      
        if (arguments.length == 1 && typeof (args) == "object") {  
            for (var key in args) {  
                if(args[key]!=undefined){  
                    var reg1 = new RegExp("({" + key + "})", "g");  
                    result = result.replace(reg1, args[key]);  
                }  
            }  
        }  
        else {  
            for (var i = 0; i < arguments.length; i++) {  
                if (arguments[i] != undefined) {  
                    var reg = new RegExp("({)" + i + "(})", "g");  
                    result = result.replace(reg, arguments[i]);  
             }  
          }  
       }  
   }  
   return result;  
} 





/************************************************公共js的扩展*****************************/
function parseDate(str) 
{
    return new Date(Date.parse(str.replace(/-/g,"/")));
};
function applyIf(o, c) { 
    if(o) { 
        for(var p in c) { 
            if(o[p]===undefined) { 
                o[p] = c[p]; 
            } 
        } 
    } 
    return o; 
} 

applyIf(Date.prototype, {	
	pattern : function(fmt) {         
	    var o = {         
	    "M+" : this.getMonth()+1, //月份         
	    "d+" : this.getDate(), //日         
	    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
	    "H+" : this.getHours(), //小时         
	    "m+" : this.getMinutes(), //分         
	    "s+" : this.getSeconds(), //秒         
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
	    "S" : this.getMilliseconds() //毫秒         
	    };         
	    var week = {         
	    "0" : "/u65e5",         
	    "1" : "/u4e00",         
	    "2" : "/u4e8c",         
	    "3" : "/u4e09",         
	    "4" : "/u56db",         
	    "5" : "/u4e94",         
	    "6" : "/u516d"        
	    };         
	    if(/(y+)/.test(fmt)){         
	        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
	    }         
	    if(/(E+)/.test(fmt)){         
	        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
	    }         
	    for(var k in o){         
	        if(new RegExp("("+ k +")").test(fmt)){         
	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
	        }         
	    }         
	    return fmt;         
	},
	//获得本周的起始日期 
	getWeekStartDate:function() {
		var day = this.getDate()- this.getDay();
		if(this.getDay()==0){
			day-=7;
		}
		var weekStartDate = new Date(this.getFullYear(), this.getMonth(), day+1); 
		return weekStartDate;
	},
	//获得本周的停止日期 
	getWeekEndDate:function() {
		var day = this.getDate()- this.getDay();
		if(this.getDay()==0){
			day-=7;
		}
		var weekEndDate = new Date(this.getFullYear(), this.getMonth(),day+7); 
		return weekEndDate; 
	},
	//比较日期类型时间的大小
	compareDate : function(endTime) {
		return this - ((typeof endTime == "string") ? parseDate(endTime) : endTime);
	},
	//获取和调用日期相差years的日期
	addYear : function(years) {
		var date = new Date(this);
		date.setFullYear(date.getFullYear() + years);
		if (date.getMonth() < this.getMonth()) 
			date.setDate(0);
		return date;
	},
	//获取和调用日期相差months的日期
	addMonth : function(months) {
		var date = new Date(this);
		date.setMonth(date.getMonth()+months);
		if (date.getDate() < this.getDate())
			date.setDate(0);
		return date;
	},
	//获取和调用日期相差days的日期
	addDate : function(days) {
		var date = new Date(this);
		date.setDate(date.getDate() + days);
		return date;
	},
	monthBetween:function(endDate){
		var num=0;
		var year=endDate.getFullYear()-this.getFullYear();
		num+=year*12;
		var month=endDate.getMonth()-this.getMonth();
		num+=month;
		return num;
	},
	monthFirstDay:function(){
	  return new Date(this.getFullYear(),this.getMonth(),1);
	},
	monthLastDay:function(){
	  return new Date(this.getFullYear(),this.getMonth()+1,0);
	},
	getWeek:function(){
		var week=['日','一','二','三','四','五','六'];
		return week[this.getDay()];
	}
});