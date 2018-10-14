/**
 * app相关配置
 * 
 **/
(function($, app) {
   localStorage.setItem("app.server.dataRePc","192.168.30.44:8080");
	// 服务端地址配置
	app.server = {
		dataRePc: "http://" + localStorage.getItem("app.server.dataRePc") + "/",	
		defaultPortalDataRePc:  "10.227.93.8"
	};

	app.init = function() {
		var ip = app.server;		
		if (localStorage.getItem("app.server.dataRePc") == null) {
			localStorage.setItem("app.server.dataRePc", ip.defaultPortalDataRePc);
		}
		app.server.dataRePc = "http://" + localStorage.getItem("app.server.dataRePc") + "/";
	};

	/**
	 * 从服务端获取升级信息
	 * @param {Object} callback
	 */
	app.getAppVersionFromServer = function(callback) {
		mui.post(app.server.portalweb + 'portal-web/static/app/update.json', {}, function(data) {
			if(callback && typeof callback == "function") {
				callback(data);
			}
		}, 'json');
	}

	/**
	 * 升级初始化
	 */
	app.updateInit = function() {

		var os = plus.os.name,
			appid = plus.runtime.appid,
			version = '',
			s_version = '';

		/**
		 * 获取APP客户端版本号
		 * 	注意：
		 * 		1、plus.runtime.version —— 打包后才能生效
		 * 	   	2、WidgetInfo.version   —— manifest.json中的版本号，无需打包即生效
		 * 
		 */
		plus.runtime.getProperty(appid, function(wgtinfo) {
			version = wgtinfo.version;
			// 从服务端获取升级信息
			app.getAppVersionFromServer(function(data) {
				if(data.appid == appid && data[os] != undefined) {
					s_version = data[os].version;
					// 判断客户端版本跟服务端返回的版本
					if(app.updateCheck(version, s_version)) {
						// 应用升级更新
						app.update(data[os]);
					}
				}
			});
		});

	};

	/**
	 * 判断版本号是否相同
	 * @param {Object} o_version：已安装的客户端版本号
	 * @param {Object} n_version：从服务端获取的升级信息中的版本号
	 */
	app.updateCheck = function(o_version, n_version) {

		var cv, sv;

		if(!o_version || !n_version || o_version == "" || n_version == "") {
			return false;
		}
		cv = o_version.split(".", 4);
		sv = n_version.split(".", 4);
		for(var i = 0; i < cv.length && i < sv.length; i++) {
			var so = cv[i],
				no = parseInt(so),
				sn = sv[i],
				nn = parseInt(sn);
			if(nn > no || sn.length > so.length) {
				return true;
			} else if(nn < no) {
				return false;
			}
		}
		if(sv.length > cv.length && 0 == sv.indexOf(cv)) {
			return true;
		}
	};

	/**
	 * APP升级更新
	 * @param {Object} updateObj
	 */
	app.update = function(updateObj) {

		plus.nativeUI.confirm(updateObj.note, function(event) {
			if(0 == event.index) {
				plus.nativeUI.showWaiting("更新数据中...");
				plus.downloader.createDownload(updateObj.url, {}, function(d, status) {
					if(status == 200) {
						var path = d.filename;
						plus.runtime.install(path, {}, function() {
							plus.nativeUI.closeWaiting();
							plus.nativeUI.alert("应用资源更新完成！", function() {
								plus.runtime.restart();
							});
						}, function(e) {
							plus.nativeUI.closeWaiting();
							plus.nativeUI.alert("安装更新文件失败[" + e.code + "]：" + e.message);
						});
					} else {
						plus.nativeUI.alert("数据更新失败！");
					}
					plus.nativeUI.closeWaiting();
				}).start();
			}
		}, updateObj.title, ["立即更新", "取　　消"]);

	};

}(mui, window.app = {}));