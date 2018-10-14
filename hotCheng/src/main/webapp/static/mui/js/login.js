function login() {

	// 登录前，删除应用所有会话期Cookie值
	plus.navigator.removeSessionCookie();
	app.init();

	var _username = document.getElementById("app-username").value,
		_password = document.getElementById("app-password").value;

	if(!_username) {
		mui.toast("请输入用户名...");
		return;
	}

	if(!_password) {
		mui.toast("请输入密码...");
		return;
	}

	var rember = document.getElementById("rember").checked;

	if(rember) {

		localStorage.setItem("app.login.username", _username);
		localStorage.setItem("app.login.password", _password);
		localStorage.setItem("app.login.rember", true);
	} else {

		localStorage.setItem("app.login.username", "");
		localStorage.setItem("app.login.password", "");
		localStorage.setItem("app.login.rember", false);

	}

	plus.nativeUI.showWaiting("正在登录，请稍后...");

	var ajaxUrl = baseUrl+ "/system/userLogin.action" ;
	//var ajaxUrl=baseUrl+"/interact/addCommentsText.action";
	
	var params = {
					workCode:localStorage.getItem("app.login.username"),
					password:localStorage.getItem("app.login.password")
			};
	mui.ajax(ajaxUrl, {
		data: params,
		dataType: "json", //服务器返回json格式数据
		type: 'post',
		timeout: 60000,
		success: function(data) {
			plus.nativeUI.closeWaiting();
					if(data.success){
						insertMsgLog();
						backFun();
						mui.toast("登录成功");
					}else{
						mui.toast("密码错误,请重新输入");
					}
				
		},
				error: function(xhr, type, errorThrown) {
				plus.nativeUI.closeWaiting();
				if(xhr.status == 403) {
					mui.toast('会话超时，请重新登录！');
					plus.runtime.restart();
				}
				if(type == 'timeout'){
					mui.toast('请求超时请重试!');
				}else if(type == 'error'){
					mui.toast('请求错误!');
					console.log(errorThrown+xhr.status);
				}else if(type == 'abort'){
					mui.toast('请求中止!');
				}else if(type == 'parsererror'){
					mui.toast('结果解析失败!');
					console.log(errorThrown);
				}else{
					mui.toast('操作失败，请检查原因!');
				}
			}
	});

}

function go2Main() {
	mui.ajax( baseUrl+ "/app/offlineDownload.action" , {
		data: {id:1},
		dataType: 'json',
		type: 'post',
		timeout: 60000,
		success: function(data) {

		},
		error: function(xhr, type, errorThrown) {}
	});

};

function setting() {
	mui.openWindow({
		id: 'server-setting',
		url: 'server-setting.html',
		show: {
			autoShow: true
		},
		waiting: {
			autoShow: true,
			title: '正在加载...'
		}
	});
}

mui.init({
			beforeback: function(){			
				return true;
			}
		});	
		

mui.plusReady(function() {
	
	//app.updateInit();

	document.getElementById("app-username").value = localStorage.getItem("app.login.username") || "";
	document.getElementById("app-password").value = localStorage.getItem("app.login.password") || "";
	if(localStorage.getItem("app.login.rember") == "true") {
		document.getElementById("rember").checked = true;
	} else {
		document.getElementById("rember").checked = false;
	}

	document.querySelector("#loginBtn").addEventListener("tap", login);
	document.querySelector("#userRegister").addEventListener("tap", function(){
		mui.openWindow({	
							id: "register.html",
							url: "register.html",
							show: {
								autoShow: true
							},
							extras: {
																				
							},
							waiting: {
								autoShow: true, //自动显示等待框，默认为true
								title: '正在加载...'
							}
						});
	});
	//document.querySelector("#settingBtn").addEventListener("tap", setting);

	var settings = JSON.parse(localStorage.getItem("settings") || "{}");
	if(settings.useGestures == true) {
		mui.openWindow({
			id: 'lockerlogin',
			url: 'lockerlogin.html'
		});
	}

});

 var old_back = mui.back;	   
		var backFun=function () {	
			document.activeElement.blur();	
			var all = plus.webview.all();        
            for (var i = 0, len = all.length; i < len; i++) {
                if (all[i].id == 'service_login.html' ) {
                    all[i].close();
                }
                if (all[i].id == 'register.html' ) {
                    all[i].close();
                }
            }
			old_back();					
			var parentWin =plus.webview.getWebviewById('02yyzx.html');    
				parentWin.evalJS('isHadLogin()');	    					
			    plus.webview.currentWebview().close();	
       };