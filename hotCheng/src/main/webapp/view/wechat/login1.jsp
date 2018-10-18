<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="format-detection" content="telephone=no">		
		<title></title>
		<base href="/hotCheng/" />
		<script src="static/js/mui.min.js"></script>
	    <script type="text/javascript" src="static/js/mui.picker.min.js" ></script>
	    <link href="static/css/mui.min.css" rel="stylesheet"/>
	    <link rel="stylesheet" href="static/css/mui.picker.min.css" />
	    <link rel="stylesheet" href="static/css/common.css">
		<link rel="stylesheet" href="static/css/smIndex.css">
		<script type="text/javascript" src="static/js/jquery-3.2.1.min.js"></script>
	</head>
	<body>
		<header>
			<img src="static/img/headTitle.png" class="headTitle">
			<div class="headsy"><a href="view/wechat/index.html"></a></div>
		</header>
		<div class="mainBg"></div>
		<!-- -->				
		<div class="taikeContainer">
			<img src="static/img/taike.png" class="taikeLogo"/>
			<div class="containerBox" style="">
				<input type="text" id="code"  autocomplete="off" class="taikeBtn taikeInt" placeholder="请输入识别码" style="height:40px;padding:0;"/>
				<input type="text" id="userName"  autocomplete="off" class="taikeBtn taikeInt" placeholder="姓名" style="height:40px;padding:0;"/>
				<input type="text" id="tel"  autocomplete="off" class="taikeBtn taikeInt" placeholder="电话" style="height:40px;padding:0;"/>
				<button class="taikeBtn" id="check">确&nbsp;&nbsp;定</button>
			</div>
		</div>
		<!--footer -->
		<footer>
			<div class="foot1">
				<span>移动版</span>
				<span>&nbsp;|&nbsp;</span>
				<a href="javascript:;">热骋</a>
			</div>
			<div class="foot2">系统版本<i>RC5861-1.07 .13499s</i></div>

		</footer>
		

		<script>
		mui.init();
		window.addEventListener("pageshow", ready);
		var scanWechat="${openId}";
		var nickName="${nickname}";
		localStorage.setItem("scanWechat",scanWechat);
		
		function ready(){
			//var scanWechat = "ping";
			document.getElementById("check").addEventListener("click", function() {
				var code = $("#code").val();
				var un= $("#userName").val();
				var tel = $("#tel").val();
				if(code==null||code==""){
					mui.toast("请输入识别码");
				}else{
					
					if(un!=null&&un!=""&&tel!=null&&tel!=""){
						mui.ajax("user/checkNum.action", {
							data: {
								wechatNo:scanWechat
							},
							dataType: "json", //服务器返回json格式数据
							type: 'get', //HTTP请求类型 
							timeout: 60000, //超时时间设置为60秒；
							success: function(data) { //服务器返回响应
								if(data!=null && data.num>=2){ 
									mui.toast("今日已操作错误两次，请明日再操作");
								}else{
									 mui.ajax("user/reg.action", {
										data: {
											wechatNo:scanWechat,
											idCode:code,
											nickName:nickName,
											userName:un,
											tel:tel
										},
										dataType: "json", //服务器返回json格式数据
										type: 'get', //HTTP请求类型 
										timeout: 60000, //超时时间设置为60秒；
										success: function(result) { //服务器返回响应
											console.log(result);
											if(result.success == true){
												mui.toast(result.msg);
												window.location.href='view/wechat/chaxun.html';
											}else{  
												mui.toast(result.msg);
											}
										},
										error: function(xhr, type, errorThrown) {
											console.log(errorThrown)
										}
									});
								}
							},
							error: function(xhr, type, errorThrown) {
								console.log(errorThrown)
							}
						}); 
					}else{
						mui.toast("请输入您的信息");
					} 
				}
				
			});
		}
		</script>
		
		
		<script>

			//解决输入框获取焦点时底部弹上来问题
			$(document).ready(function(){			
			    var h=$(window).height();
			    $(window).resize(function() {
			        if($(window).height()<h){
			            $('footer').hide();
			        }
			        if($(window).height()>=h){
			            $('footer').show();
			        }
			    });
				$("input").focus(function(){
			    	$(this).attr("placeholder","")
			    })//input输入框获取焦点时placeholder为空			
			});
		</script>
	

</body></html>