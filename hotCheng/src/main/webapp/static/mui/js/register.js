function register() {
	var _username = document.getElementById("reg-username").value,
		_password = document.getElementById("reg-password").value;
		
	if(!$("input[type='checkbox']").is(':checked')) {
          mui.toast("请选择‘已阅读并同意《合肥通协议》’");
	      return;      	    
       }		
	if(!_username) {
		mui.toast("请输入手机号...");
		return;
	}
	if(!_password) {
		mui.toast("请输入密码...");
		return;
	}

	plus.nativeUI.showWaiting("正在注册，请稍后...");
	var ajaxUrl = baseUrl+ "/SysCUser/registerSaveSysCUser.action" ;
		var mask = mui.createMask();//callback为用户点击蒙版时自动执行的回调；
	    mask.show();//显示遮罩
		$("#register-form").ajaxSubmit({
						type: "post", /*设置表单以post方法提交*/
	                    dataType: "json", /*设置返回值类型为文本*/
						url:ajaxUrl,
						success:function(result,status,xhr){
							plus.nativeUI.closeWaiting();
							if(result.success){
							mui.toast('注册成功!');  
							go2Main();
							}else{
								mui.toast('该用户已存在！');  	
							}							       
							mask.close();//关闭遮罩								
						},error:function(xhr, status, error){
							mui.toast('注册出错!');
							mask.close();//关闭遮罩
						}
					});	
}

function go2Main() {
	var _username = document.getElementById("reg-username").value,
		_password = document.getElementById("reg-password").value;
	var ajaxUrl = baseUrl+ "/system/userLogin.action" ;
	var params = {
					workCode:_username,
					password:_password
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
						//mui.toast("登录成功");
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
};

mui.init({
			beforeback: function(){			
				return true;
			}
		});	
		
mui.plusReady(function() {
	document.querySelector("#registerBtn").addEventListener("tap", register);
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
