$('.button-edit').linkbutton({});
$("#roleOrPerson").textbox({
	editable : false,
	required : false,
	icons : [ {
		iconCls : 'icon-add',
		handler : function(e) {
			var obj = {
				url : "TalCExpertInfo/userForExpert.action",
				callback : "setUDCodesValues",
				pageUrl : "workflow/openApproveWin.action",
				title : "人员信息"
			};
			showUserWin(obj);
		}
	} ]
});

function approveFun() {
	var typeRediao = $('input:radio[name="adminFlag"]:checked').val();
	$.ajax({
		url : busUrl,
		method : 'post',
		data : {
			roleOrPerson : $("#roleOrPerson").val(),
			userCode : $("#userCode").val(),
			processInstId : processInstId,
			typeRediao : typeRediao,
			id : $("#id").val()
		},
		success : function(data) {
			i = 0;// 保存成功后，重置i值
			$.messager.progress('close'); // 如果提交成功则隐藏进度条
			var jsonData = JSON.parse(data);
			if (jsonData.success) {
				parent.callbackFun(pageUrl, calbackFun);
				msgSaveSuc(jsonData.msg, function() {
					closeCurrentWin();
				});
			} else {
				msgInfo(jsonData.msg);
			}
		},
		error : function() {
			$.messager.progress('close');
		}
	});
}

function setUDCodesValues(udObj) {
	$("#roleOrPerson").textbox('setValue', udObj.userName);
	$("#userCode").val(udObj.userCode);
}
function closeCurrentWin() {
	parent.closewin();
}
