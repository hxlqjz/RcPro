



function getMySnapList() {
	//plus.nativeUI.showWaiting();
	var url = baseUrl + "/MySnap/getMySnapList.action";
	var param = {
	
	};
	console.log("url"+url);
	mui.ajax(url, {
		data: param,	
		dataType: "json",
		type: "post",
		timeout: 6000,
		success: function(data) {
			console.log("data================="+JSON.stringify(data));
			var rs = new Array();
			var len = data.data.length;
			for (let i = 0; i < len; i++) {
				let createTime = getFormatDateByLong(data.data[i].createTime,"yyyy-MM-dd");
				data.data[i].createTime = createTime;
				rs.push(data.data[i]);
			}
			console.log("rs=============="+JSON.stringify(rs));
				document.getElementById("MySnapList").innerHTML = template("mySnap", {
					list: rs
				});			
			plus.nativeUI.closeWaiting();
		},
		error: function(xhr, type, errorThrown) {
				plus.nativeUI.closeWaiting();
				if(xhr.status == 403) {
					mui.toast('会话超时，请重新登录！');
					plus.runtime.restart();
				}				
			}
	})
};

