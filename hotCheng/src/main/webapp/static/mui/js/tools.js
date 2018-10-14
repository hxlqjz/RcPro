localStorage.setItem("serverURL","192.168.30.124:8081");
var baseUrl = "http://60.174.249.204:37070/dataRe-pc";
//var baseUrl = "http://172.20.236.2:8080/dataRe-pc";
//var baseImg = "http://172.16.67.193:8082";

//var busiUrl= "http://192.168.30.124:8081/portal-busi/";


function baseAjax(url,params,type,successCallback){
	
 plus.nativeUI.showWaiting()
	mui.ajax(baseUrl + url, {
		data: params,
		dataType: "json", //服务器返回json格式数据
		type: type, //HTTP请求类型
		timeout: 60000, //超时时间设置为60秒；
		success: function(data) { //服务器返回响应
			plus.nativeUI.closeWaiting();
			successCallback(data);
		},
		error: function(xhr, type, errorThrown) {
			console.log("xhr="+xhr);
			console.log("type="+type);
			console.log("errorThrown="+errorThrown);
			//异常处理
			plus.nativeUI.closeWaiting();
			if(xhr.status == 403) {
				mui.toast('会话超时，请重新登录！');
				plus.runtime.restart();
			}
			mui.toast('操作失败，请检查网络状态!');
		}
	});
}

function insertMsgLog(){
	var url="/msgLog/saveMsgLog.action";
		mui.ajax(baseUrl + url, {
		data: {},
		dataType: "json", //服务器返回json格式数据
		type: "post", //HTTP请求类型
		timeout: 60000, //超时时间设置为60秒；
		success: function(data) { //服务器返回响应
			plus.nativeUI.closeWaiting();
		},
		error: function(xhr, type, errorThrown) {			
			//mui.toast('操作失败，请检查网络状态!');
		}
	});
};
