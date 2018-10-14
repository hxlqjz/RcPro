var pagerOffset = 1;
var pageTotal = 10000;
var index;
var page1;
var row = 10;
var searchTitle = "";

mui.init({
	swipeBack: false,
	keyEventBind: {
		backbutton: true
	},
	pullRefresh: {
		container: '#pullrefresh',
		down: {
			style:'circle',//下拉刷新样式，目前支持原生5+ ‘circle’ 样式
			auto: false,//可选,默认false.首次加载自动上拉刷新一次
			offset:'100px',
			contentrefresh: '正在刷新...',
			callback: pulldownRefresh
		},
		up: {
			contentrefresh: '正在加载...',
			callback: pullupRefresh
		}
	}
});

function searchRecord(page, row, index, keyWord) {
	//plus.nativeUI.showWaiting();
	var url = app.server.portalbusi + "portal-busi/BusiJRecord/searchBusiJRecoed.action";
	var param = {
		keyWord: keyWord,
		page: page,
		rows: row	
	};
	console.log("para"+JSON.stringify(param));
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
				let createTime = getFormatDateByLong(data.data[i].createTime,"yyyy-MM-dd hh:mm:ss");
				data.data[i].createTime = createTime;
				rs.push(data.data[i]);
			}
			console.log("rs=============="+JSON.stringify(rs));
			if(index == "down") {
				document.getElementById("dataList").innerHTML = template("tpl", {
					list: rs
				});
			}  else {
				document.getElementById("dataList").innerHTML = document.getElementById("dataList").innerHTML + template("tpl", {
					list: rs
				});
			}			
			page1 = parseInt(data.msg) ;
			plus.nativeUI.closeWaiting();
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
					console.log(errorThrown+JSON.stringify(xhr.status));
				}else if(type == 'abort'){
					mui.toast('请求中止!');
				}else if(type == 'parsererror'){
					mui.toast('结果解析失败!');
					console.log(errorThrown);
				}else{
					mui.toast('操作失败，请检查原因!');
				}
			}
	})
};

/**
 * 下拉刷新具体业务实现,下拉刷新，直接重新加载这个页面
 */
function pulldownRefresh() {
	setTimeout(function() {
		var keyWord = document.getElementById("search").value ;
		page1 = 1 ;
		searchRecord(1, row, "down", keyWord);	
		pagerOffset = 1;
		mui('#pullrefresh').pullRefresh().scrollTo(0,0,100);
		mui('#pullrefresh').pullRefresh().endPulldownToRefresh();
		mui('#pullrefresh').pullRefresh().refresh(true);
	}, 1500);
}

/**
 * 上拉加载具体业务实现
 */
function pullupRefresh() {
	setTimeout(function() {
		mui('#pullrefresh').pullRefresh().endPullupToRefresh((++page1 > pageTotal)); 
		searchRecord(page1, row, "up", searchTitle);	
	}, 1500);
}