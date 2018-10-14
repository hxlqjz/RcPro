<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<shiro:authenticated>
<%
 response.sendRedirect(basePath + "system/toFrontMain.action");
%>
</shiro:authenticated>
<!doctype html>
<html>
<head>
<base href="<%=basePath%>"/>
<meta charset="UTF-8">
<title>合肥通后台管理 </title>

<script src="static/thirdparty/jquery-1.9.1.min.js"></script>
<script src="static/thirdparty/layui/layui.js" charset="utf-8"></script>


<link rel="stylesheet" type="text/css" href="static/loginStyle/css/login2.css">
<script src="static/loginStyle/js/loginLayout.js"></script>
<style>
  .child {
            width:150px;
            height:150px;
            margin:auto;
            position:absolute;/*设定水平和垂直偏移父元素的50%，
再根据实际长度将子元素上左挪回一半大小*/
            left:100%;
            top:20%;
        }
#com-quick-QRcode {
    cursor: pointer;
    border: 0;
    bottom: 82px;
    height: 150px;
    width: 150px;
    margin: 0;
    padding: 0;    
    right: 10px;
    border-radius: 0;
    line-height: 16px;
    color: #fff;
    background-image: url(static/android.png);
}
</style>
<script type="text/javascript"> 

layui.use('layer', function(){ //独立版的layer无需执行这一句
	  var $ = layui.jquery;
	  layer = layui.layer; //独立版的layer无需执行这一句
});


document.onkeydown=function(e) {   
	  var ev =  window.event|| e;  
      if (ev.keyCode == 13) {  
    	  login();
		  }
};
window.onload = function(){
	//登录错误
	var errMsg = "${errMsg}";	
    if (errMsg != "") {
    	if("${errInfo}" != null && "${errInfo}" != ""){
    		layer.confirm(errMsg, {
    	   		  btn: ['确定','查看异常'] //按钮
    	   		}, function(){
    	   			layer.close(layer.index);
    	   		}, function(){
    	   			layer.open({
    	   			  type: 1,
    	   			  title : '异常信息',
    	   			  closeBtn: 1, //不显示关闭按钮
    	   			  anim: 2,
    	   			  area : ["800px", "550px"],
    	   			  shadeClose: true, //开启遮罩关闭
    	   			  content: "${errInfo}"
    	   			});
    	   		});
    	}else{
    		layer.alert(errMsg, {
   			  closeBtn: 0
   			});
    	}
    	
		document.getElementById('workCode').focus();
	}
	//设置cookie
	var cookies = document.cookie.replace(/\s+/g,""); 
	var cookieArr = cookies.split(";");
	for(var i = 0; i < cookieArr.length; i++){
		var cookie = cookieArr[i].split("=");
		if(cookie[0] == "UN"){
			document.getElementById("workCode").value = cookie[1];
		}
		if(cookie[0] == "PWD"){
			document.getElementById("password").value = cookie[1];
		}
	}
   
}


function login(){
	var workerCode = document.getElementById("workCode").value;
	var loginPwd = document.getElementById("password").value;
	if (workerCode == "请输入用户名" || loginPwd == "请输入密码" || !workerCode || !loginPwd) {
		alert("用户名或密码不能为空!");
		return;
	}
	document.getElementById('form1').submit();
}

$(function(){
	$("#workCode,#password").focus(function(){
		if(this.value == this.tip){
			$(this).val("");
			$(this).removeClass("CoCo_InputDefault");
		}
	});
	
	$("#workCode,#password").blur(function(){
		if(this.value == ""){
		 	$(this).val(this.tip);
			$(this).addClass("CoCo_InputDefault");
		}
	});
	
	/*这是一个自定义的函数,作用是设置class1类为隐藏,class2类显示*/  
    function  showDiv(class1,class2){  
      $(class1).css("display","none");  
      $(class2).css("display","block");  

    }  
/*给右下角的图标设置点击事件*/  
  //$('.code').click(function(){  
  //    showDiv(".choice1",".choice2");  
  //})  

  //$('.computer').click(function(){  
  //    showDiv(".choice2",".choice1");  
  //}) 
})

</script>
</head>

<body>

<div class="Login">
  <div class="Head">
    <div class="Box">
      <div class="Logo"></div>
      <div class="SystemName"></div>
      <div class="Tools">2017年8月4日&nbsp;&nbsp;欢迎登录&nbsp;&nbsp;管理系统&nbsp;&nbsp;帮助&nbsp;&nbsp;<a href="#" onClick="changeSkin(skin1);">样式</a></div>
    </div>
  </div>
  <div class="Center">
    <div class="Box">
     	<div class="guang">&nbsp;</div>
      <div class="FlashBox"> &nbsp; </div>
      <div class="InputBox">
        <!-- 账号密码登录 -->
        <div class="choice1">
         <!-- <div class="code"></div> -->
          <form id="form1" name="loginForm" action="system/userLogin.action" method="post" class="login-form" method="post">
            <div class="textTitle">&nbsp;</div>
            <div class="Title Item1">用户名</div>
            <div class="Content Item2">
              <input type="text" id="workCode" name="workCode" value=""  placeholder="请输入账号"/>
            </div>
            <div class="Title Item1">密码</div>
            <div class="Content Item3">
              <input type="password" id="password" name="password" value=""   placeholder="请输入密码"/>
            </div>
            
            <div class="btn submit" onClick="login();">登&nbsp;&nbsp;录</div>
            
          </form>
        </div>
       
           <!-- 二维码登录 -->
        <div class="choice2" style="display:none">
          <div class="computer"></div>
          <form>
            <div  class="textTitle">手机客户端扫码下载</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tbody>
    <tr>
      <td align="center"><div class="ewm"><img src="static/loginStyle/images/Skin2/ewm1.png" width="104" height="92" alt=""/>Android版</div>
            <div class="ewm"><img src="static/loginStyle/images/Skin2/ewm2.png" width="104" height="92" alt=""/>ios版</div></td>
    </tr>
  </tbody>
</table>
            
           
          </form>
        </div>
        
      </div>
      <div class="child">
   
    <image src="static/android.png"/>
    <!-- 	 <a id="com-quick-QRcode" class="QRcodebg1" title="扫描二维码" style="top:153px"><span class="closeQR"></span></a> -->
    </div>
    </div>
    
    
  </div>
    <div class="Foot">
    <div class="Box"> <span class="TextC"></span> <span class="TextR">版权所有 数据资源局</span></div>
  </div>

</body>
</html>
