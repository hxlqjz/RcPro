
function getFirstFolderList() {
	var url = baseUrl + "/configPage/findMainPageBySuperior.action";
	var param = {
	dicTypeCode:"mainPage"
	};
	console.log("url"+url);		
	mui.ajax(url, {
		data: param,	
		dataType: "json",
		async:false,
		type: "post",
		timeout: 6000,
		success: function(data) {
			console.log("data================="+JSON.stringify(data));
			var len =data.dicLs.length;
			var controls = document.getElementById("segmentedControls");
			var contents = document.getElementById("segmentedControlContents");
		    var html = [];
			for (var i=0; i < len; i++) {
				html.push('<a class="mui-control-item" data-index="' + i + '" href="#' + data.dicLs[i].dictCode + '">' + data.dicLs[i].dictName + '</a>');
			}
			controls.innerHTML = html.join('');
			html = [];
			for (var i = 0; i < len; i++) {
				var dicCode=data.dicLs[i].dictCode;
				html.push('<div id="' + dicCode + '" class="mui-slider-item"><ul class="mui-table-view mui-grid-view mui-grid-9">');
				var  sData=data[dicCode];
				var leng = sData.length;
				for (j = 0; j < leng; j++) {
					var li='<li pageUrl="'+ sData[j].pageUrl +'" pageId="'+ sData[j].pageId +'"   pageType="'
					+ sData[j].pageType +'"  pageTitle="'+ sData[j].pageTitle +'" class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a open-type="common" id="applycar">'
					+'<div class="homeIcon"><img src="'+ sData[j].picLs[0].content +'"/><span class="mui-icon"><span class="mui-badge" id="1" style="display: none;">'
					+'</span></span></div><div class="mui-media-body">'+ sData[j].pageTitle +'</div></a></li>';						
					html.push(li);
				}
				html.push('</ul></div>');
			}
			controls.querySelector('.mui-control-item').classList.add('mui-active');
			contents.innerHTML = html.join('');
            controls.querySelector('.mui-control-item').classList.add('mui-active');

		},
		error: function(xhr, type, errorThrown) {
			//	plus.nativeUI.closeWaiting();
				if(xhr.status == 403) {
					mui.toast('会话超时，请重新登录！');
					plus.runtime.restart();
				}				
			}
	})
};

		
			
			
	getFirstFolderList();
	(function() {
				var controlsElem = document.getElementById("segmentedControls");
				var contentsElem = document.getElementById("segmentedControlContents");
				var controlListElem = controlsElem.querySelectorAll('.mui-control-item');
				//var contentListElem = contentsElem.querySelectorAll('.mui-control-content');
				var contentListElem = contentsElem.querySelectorAll('.mui-slider-item');
				var controlWrapperElem = controlsElem.parentNode;
				var controlWrapperHeight = controlWrapperElem.offsetHeight;
				var controlMaxScroll = controlWrapperElem.scrollHeight - controlWrapperHeight;//左侧类别最大可滚动高度
				var maxScroll = contentsElem.scrollHeight - contentsElem.offsetHeight;//右侧内容最大可滚动高度
				var controlHeight = controlListElem[0].offsetHeight;//左侧类别每一项的高度
				var controlTops = []; //存储control的scrollTop值
				var contentTops = [0]; //存储content的scrollTop值
				var length = contentListElem.length;
				for (var i = 0; i < length; i++) {
					controlTops.push(controlListElem[i].offsetTop + controlHeight);
				}
				for (var i = 1; i < length; i++) {
					var offsetTop = contentListElem[i].offsetTop;
					if (offsetTop + 100 >= maxScroll) {
						var height = Math.max(offsetTop + 100 - maxScroll, 100);
						var totalHeight = 0;
						var heights = [];
						for (var j = i; j < length; j++) {
							var offsetHeight = contentListElem[j].offsetHeight;
							totalHeight += offsetHeight;
							heights.push(totalHeight);
						}
						for (var m = 0, len = heights.length; m < len; m++) {
							contentTops.push(parseInt(maxScroll - (height - heights[m] / totalHeight * height)));
						}
						break;
					} else {
						contentTops.push(parseInt(offsetTop));
					}
				}
				contentsElem.addEventListener('scroll', function() {
					var scrollTop = contentsElem.scrollTop;
					for (var i = 0; i < length; i++) {
						var offsetTop = contentTops[i];
						var offset = Math.abs(offsetTop - scrollTop);
//						console.log("i:"+i+",scrollTop:"+scrollTop+",offsetTop:"+offsetTop+",offset:"+offset);
						if (scrollTop < offsetTop) {
							if (scrollTop >= maxScroll) {
								onScroll(length - 1);
							} else {
								onScroll(i - 1);
							}
							break;
						} else if (offset < 20) {
							onScroll(i);
							break;
						}else if(scrollTop >= maxScroll){
							onScroll(length - 1);
							break;
						}
					}
				});
				var lastIndex = 0;
				//监听content滚动
				var onScroll = function(index) {
					if (lastIndex !== index) {
						lastIndex = index;
						var lastActiveElem = controlsElem.querySelector('.mui-active');
						lastActiveElem && (lastActiveElem.classList.remove('mui-active'));
						var currentElem = controlsElem.querySelector('.mui-control-item:nth-child(' + (index + 1) + ')');
						currentElem.classList.add('mui-active');
						//简单处理左侧分类滚动，要么滚动到底，要么滚动到顶
						var controlScrollTop = controlWrapperElem.scrollTop;
						if (controlScrollTop + controlWrapperHeight < controlTops[index]) {
							controlWrapperElem.scrollTop = controlMaxScroll;
						} else if (controlScrollTop > controlTops[index] - controlHeight) {
							controlWrapperElem.scrollTop = 0;
						}
					}
				};
				//滚动到指定content
				var scrollTo = function(index) {
					contentsElem.scrollTop = contentTops[index];
				};
				mui(controlsElem).on('tap', '.mui-control-item', function(e) {
					scrollTo(this.getAttribute('data-index'));
					return false;
				});
			})();