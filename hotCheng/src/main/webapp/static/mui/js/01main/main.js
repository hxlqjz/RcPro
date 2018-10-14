// 首页天气展示
//$(function() {
//	$('#weathericon').leoweather({
//		format: ' <i class="icon-{图标}"></i> '
//	});
//
//	$('#chengshi').leoweather({
//		format: '<font color=white>{城市}</font>'
//	});
//	$('#tianqi').leoweather({
//		format: '<font color=white>{天气}</font>' 
//	});
//	$('#fengji').leoweather({
//		format: '<font color=white>{风向}</font>'
//	});
//	$('#qiwen').leoweather({
//		format: '<font size=4 color=white>{气温}℃   </font>'
//	});
//});

var mainWvObj = {
	main: null,
	menu: null,
	showMenu: false
};

mui.init({
	swipeBack: false,
	beforeback: back
});

mui.plusReady(function() {

	// 获取位置信息
	plus.geolocation.getCurrentPosition(translatePoint, function(e) {
		mui.toast("异常:" + e.message);
	});

	mainWvObj.main = plus.webview.currentWebview();

	//setTimeout的目的是等待窗体动画结束后，再执行create webview操作，避免资源竞争，导致窗口动画不流畅；
	setTimeout(function() {
		//侧滑菜单默认隐藏，这样可以节省内存；
		mainWvObj.menu = mui.preload({
			id: 'offcanvas',
			url: '../offcanvas.html',
			styles: {
				left: '-100%',
				zindex: 0,
				width : '70%',
				render: 'always'
			}
		});
	}, 300);

	var subpages = ['01ggcx.html', '02yyzx.html', '03tydb.html', '04jczx.html'];
	var subpage_style = {
		top: '45px',
		bottom: '51px'
	};

	var aniShow = {};

	//创建子页面，首个选项卡页面显示，其它均隐藏；
	for(var i = 0; i < 4; i++) {
		var temp = {};
		var sub = plus.webview.create(subpages[i], subpages[i], subpage_style);
		if(i != 1) {
			sub.hide();
		} else {
			temp[subpages[i]] = "true";
			mui.extend(aniShow, temp);
		}
		mainWvObj.main.append(sub);
	}

	mainWvObj.main.show('slide-in-right', 500, function() {
		plus.nativeUI.closeWaiting();
	});

	//当前激活选项
	var activeTab = subpages[1];
	//选项卡点击事件
	mui('.mui-bar-tab').on('tap', 'a', function(e) {
		var targetTab = this.getAttribute('href');
		if(targetTab == activeTab) {
			return;
		}
		//显示目标选项卡
		//若为iOS平台或非首次显示，则直接显示
		if(mui.os.ios || aniShow[targetTab]) {
			plus.webview.show(targetTab);
		} else {
			//否则，使用fade-in动画，且保存变量
			var temp = {};
			temp[targetTab] = "true";
			mui.extend(aniShow, temp);
			plus.webview.show(targetTab, "slide-in-right", 300);
		}
		//隐藏当前;
		plus.webview.hide(activeTab);
		//更改当前活跃的选项卡
		activeTab = targetTab;
	});

});

document.querySelector('.mui-icon-bars').addEventListener('tap', openMenu);
window.addEventListener('dragright', function(e) {
	e.detail.gesture.preventDefault();
});
window.addEventListener("swiperight", openMenu);
window.addEventListener("swipeleft", closeMenu);
window.addEventListener("menu:swipeleft", closeMenu);

function back() {
	if(mainWvObj.showMenu) {
		//菜单处于显示状态，返回键应该先关闭菜单,阻止主窗口执行mui.back逻辑；
		closeMenu();
		return false;
	} else {
		var btn = ["退出", "取消"];
		mui.confirm('是否要退出应用？', '提示', btn, function(e) {
			if(e.index == 0) {
				plus.runtime.quit();
			}
		});
		return false;
	}
}

mui.menu = function() {
	if(mainWvObj.showMenu) {
		closeMenu();
	} else {
		openMenu();
	}
}

function openMenu() {
	if(!mainWvObj.showMenu) {
		// 侧滑页面出现右移到显示区域  
		mainWvObj.menu.show('none', 0, function() {
			mainWvObj.menu.setStyle({
				left: '0',
				transition: {
					duration: 150
				}
			});
		});
		// 主界面右移  
		mainWvObj.main.show('none', 0, function() {
			mainWvObj.main.setStyle({
				left: '70%',
				top: '0',
				bottom: '0',
				mask: 'rgba(0,0,0,0.5)',
				transition: {
					duration: 300
				}
			});
		});

		// 每次移除遮罩点击事件，避免重复添加监听  
		mainWvObj.main.removeEventListener('maskClick');
		// 点击关闭遮罩时  
		mainWvObj.main.addEventListener('maskClick', function() {
			closeMenu();
		});

		mainWvObj.showMenu = true;
	}
}

function closeMenu() {
	if(mainWvObj.showMenu) {
		// 侧滑界面移出显示区域之外  
		mainWvObj.menu.setStyle({
			left: "-100%",
			transition: {
				duration: 300
			}
		});
		// 主界面移动到最大显示区域  
		mainWvObj.main.setStyle({
			left: '0',
			top: '0',
			bottom: '0',
			mask: 'none',
			transition: {
				duration: 300
			}
		});
		// 隐藏侧滑页面，setTimeout避免竞争资源  
		setTimeout(function() {
			mainWvObj.menu.hide();
		}, 300);

		mainWvObj.showMenu = false;
	}
}

function translatePoint(position) {
	var city = position.address.city.replace("市", "");
	mi.getWeather(city, function(data) {
		if(data) {
			var data = JSON.parse(data);
			if(data.weatherinfo) {
				data = data.weatherinfo;
				document.getElementById("chengshi").innerHTML = data.city;
				document.getElementById("tianqi").innerHTML = data.weather;
				document.getElementById("wendu").innerHTML = data.temp1 + " ~ " + data.temp2;
				var weather = '';
				if (data.weather.indexOf("雨") != -1) {
					weather = '<i class="icon-xiaoyu"></i>';
				} else if (data.weather.indexOf("晴") != -1) {
					weather = '<i class="icon-qing"></i>';
				} else if (data.weather.indexOf("多云") != -1) {
					weather = '<i class="icon-duoyun"></i>';
				} else {
					weather = '<i class="icon-yin"></i>';
				}
				document.getElementById("weathericon").innerHTML = weather;
			}
		}
	});
}