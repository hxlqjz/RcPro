
/**
 * 改写ajax的complete，统一处理ajax请求的异常
 */
var ajaxBack = $.ajax;
$.ajax = function(setting){
	setting.complete = function(event,request, settings){
		if(event.status != 200){
			if(event.status == 0){
				layer.alert('网络出现问题或服务已关闭！');
				return false;
			}else if(event.status == 403 || event.statusText == 'Forbidden'){
				layer.alert('登录过期，请重新登录！', {
				  closeBtn: 0
				}, function(){
					window.top.location = ctx+"system/logout.action";
				});
				return false;
			} else if(event.status == 520){
				layer.alert('编码不能重复！');
				return false;
			} else if(event.status == 521){
				layer.alert('您没有操作权限！');
				return false;
			} else if (event.status == 523) {
				layer.alert('数据已更新，请刷新数据后再修改保存！');
				return false;
			} else{
				layer.confirm("系统出现异常！", {
	  	   			btn: ['确定','查看异常'] //按钮
	  	   		}, function(){
	  	   			layer.close(layer.index);
	  	   		}, function(){
	  	   			parent.openExceptionWin(event.responseText);
	  	   		});
				
				return false;
			}
		}
		return true;
	}
	ajaxBack(setting);
};


function ajaxSubmit(target, options){
	var opts = $.data(target, 'form').options;
	$.extend(opts, options||{});
	
	var param = $.extend({}, opts.queryParams);
	if (opts.onSubmit.call(target, param) == false){return;}

	var input = $(target).find('.textbox-text:focus');
	input.triggerHandler('blur');
	input.focus();

	var disabledFields = null;	// the fields to be disabled
	if (opts.dirty){
		var ff = [];	// all the dirty fields
		$.map(opts.dirtyFields, function(f){
			if ($(f).hasClass('textbox-f')){
				$(f).next().find('.textbox-value').each(function(){
					ff.push(this);
				});
			} else {
				ff.push(f);
			}
		});
		disabledFields = $(target).find('input[name]:enabled,textarea[name]:enabled,select[name]:enabled').filter(function(){
			return $.inArray(this, ff) == -1;
		});
		disabledFields.attr('disabled', 'disabled');
	}

	if (opts.ajax){
		if (opts.iframe){
			submitIframe(target, param);
		} else {
			if (window.FormData !== undefined){
				submitXhr(target, param);
			} else {
				submitIframe(target, param);
			}
		}
	} else {
		$(target).submit();
	}

	if (opts.dirty){
		if(disabledFields!=null){
			disabledFields.removeAttr('disabled');
		}
	}
}

/**
 * 将easyui的form表单提交默认设置为ajax方式
 */
$.fn.form.defaults.iframe = false;
function submitIframe(target, param){
	var opts = $.data(target, 'form').options;
	var frameId = 'easyui_frame_' + (new Date().getTime());
	var frame = $('<iframe id='+frameId+' name='+frameId+'></iframe>').appendTo('body');
	frame.attr('src', window.ActiveXObject ? 'javascript:false' : 'about:blank');
	frame.css({
		position:'absolute',
		top:-1000,
		left:-1000
	});
	frame.bind('load', cb);
	
	submit(param);
	
	function submit(param){
		var form = $(target);
		if (opts.url){
			form.attr('action', opts.url);
		}
		var t = form.attr('target'), a = form.attr('action');
		form.attr('target', frameId);
		var paramFields = $();
		try {
			for(var n in param){
				var field = $('<input type="hidden" name="' + n + '">').val(param[n]).appendTo(form);
				paramFields = paramFields.add(field);
			}
			checkState();
			form[0].submit();
		} finally {
			form.attr('action', a);
			t ? form.attr('target', t) : form.removeAttr('target');
			paramFields.remove();
		}
	}
	
	function checkState(){
		var f = $('#'+frameId);
		if (!f.length){return}
		try{
			var s = f.contents()[0].readyState;
			if (s && s.toLowerCase() == 'uninitialized'){
				setTimeout(checkState, 100);
			}
		} catch(e){
			cb();
		}
	}
	
	var checkCount = 10;
	function cb(){
		var f = $('#'+frameId);
		if (!f.length){return}
		f.unbind();
		var data = '';
		try{
			var body = f.contents().find('body');
			data = body.html();
			if (data == ''){
				if (--checkCount){
					setTimeout(cb, 100);
					return;
				}
			}
			var ta = body.find('>textarea');
			if (ta.length){
				data = ta.val();
			} else {
				var pre = body.find('>pre');
				if (pre.length){
					data = pre.html();
				}
			}
		} catch(e){
		}
		opts.success.call(target, data);
		setTimeout(function(){
			f.unbind();
			f.remove();
		}, 100);
	}
}

function submitXhr(target, param){
	var opts = $.data(target, 'form').options;
	var formData = new FormData($(target)[0]);
	for(var name in param){
		formData.append(name, param[name]);
	}
	$.ajax({
		url: opts.url,
		type: 'post',
		xhr: function(){
			var xhr = $.ajaxSettings.xhr();
			if (xhr.upload) {
				xhr.upload.addEventListener('progress', function(e){
					if (e.lengthComputable) {
						var total = e.total;
						var position = e.loaded || e.position;
						var percent = Math.ceil(position * 100 / total);
						opts.onProgress.call(target, percent);
					}
				}, false);
			}
			return xhr;
		},
		data: formData,
		dataType: 'html',
		cache: false,
		contentType: false,
		processData: false,
		complete: function(res){
			opts.success.call(target, res.responseText);
		},
		success : opts.success,
		error : opts.error
	});
}

$.extend($.fn.form.methods, {
	submit: function(jq, options){
		return jq.each(function(){
			ajaxSubmit(this, options);
		});
	}
});