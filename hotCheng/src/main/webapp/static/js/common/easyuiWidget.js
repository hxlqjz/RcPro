/**
 * 部门下拉树
 * @param id
 * @param options
 * @param queryParams
 */
function deptCombotree(id,options,queryParams){

	if(!options){
		options = {};
	}
	if(!queryParams){
		queryParams={node : Constants.rootNodeCode};
	}
	$("#" + id).combotree({
		url : "HrCDept/getHrCDeptEasyUI.action",
		method : "post",
		animate : true,
		queryParams : queryParams,
		panelHeight : "200",
		onLoadSuccess : function(node, data) {
	     $(this).tree('expandAll');
	     if(options.value){
				$("#" + id).combotree("setValue",options.value);
			}
			if(options.values){
				$("#" + id).combotree("setValues",options.values.split(","));
			}
		},
		onBeforeExpand:function(node,param){
			$(this).tree('options').queryParams = {
				node : node.code
			};               
	    },
	    cascadeCheck : options.cascadeCheck,
		required : options.required,
		multiple : options.multiple
	});
}


/**
 * 部门下拉树 code
 * @param id
 * @param options
 * @param queryParams
 */
function deptCombotreeCode(id,options,queryParams){

	if(!options){
		options = {};
	}
	if(!queryParams){
		queryParams={node : Constants.rootNodeCode};
	}
	$("#" + id).combotree({
		url : "HrCDept/getHrCDeptEasyUI2.action",
		method : "post",
		animate : true,
		queryParams : queryParams,
		panelHeight : "200",
		onLoadSuccess : function(node, data) {
	     $(this).tree('expandAll');
	     if(options.value){
				$("#" + id).combotree("setValue",options.value);
			}
			if(options.values){
				$("#" + id).combotree("setValues",options.values.split(","));
			}
		},
		onBeforeExpand:function(node,param){
			$(this).tree('options').queryParams = {
				node : node.id
			};               
	    },
	    cascadeCheck : options.cascadeCheck,
		required : options.required,
		multiple : options.multiple
	});
}