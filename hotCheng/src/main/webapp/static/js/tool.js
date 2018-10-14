var basUrl = "/hotCheng";

function baseAjax(url,params,type,successCallback){
	mui.ajax(basUrl + url, {
		data: params,
		dataType: "json", //服务器返回json格式数据
		type: type, //HTTP请求类型
		timeout: 60000, //超时时间设置为60秒；
		success: function(data) { //服务器返回响应
			successCallback(data);
		},
		error: function(xhr, type, errorThrown) {
			console.log("xhr="+xhr);
			console.log("type="+type);
			console.log("errorThrown="+errorThrown);
			//异常处理
			if(xhr.status == 403) {
				mui.toast('会话超时，请重新登录！');
			}
			mui.toast('操作失败，请检查网络状态!');
		}
	});
}

function setCookie(c_name,value,expiredays)
{
var exdate=new Date()
exdate.setDate(exdate.getDate()+expiredays)
document.cookie=c_name+ "=" +escape(value)+
((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
}

//取回cookie
function getCookie(c_name)
{
if (document.cookie.length>0)
  {
  c_start=document.cookie.indexOf(c_name + "=")
  if (c_start!=-1)
    { 
    c_start=c_start + c_name.length+1 
    c_end=document.cookie.indexOf(";",c_start)
    if (c_end==-1) c_end=document.cookie.length
    return unescape(document.cookie.substring(c_start,c_end))
    } 
  }
return ""
}