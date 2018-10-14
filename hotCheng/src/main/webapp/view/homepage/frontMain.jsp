<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.kdgcsoft.power.common.bean.Principal"%> 
<%@page import="org.apache.shiro.SecurityUtils"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; 
	Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
%>

<!doctype html>
<html style="overflow: hidden;">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<title>电力部ERP开发基础框架 </title>
<jsp:include page="/WEB-INF/views/common/jsp/common.jsp"></jsp:include>
<link rel="shortcut icon" href="static/favicon.ico" type="image/x-icon" />
<style>
a,img{border:0; text-decoration:none; color:#333}
body{font:17px Arial, Microsoft YaHei;}
.space{ height:15px; width:100%}
a:hover{color:#0000ED}

.top{ width:100%; height:64px; background:#D56C06 url(static/images/homepage/topBg.jpg) no-repeat;position:relative; border-bottom:2px solid #9F0000}
.smallTools{color:#666; font-size:16px; width:300px; float:right; text-align: right;padding-right:20px;padding-top:20px}
.smallTools span{color: #fff;}
.smallTools a{ color:#666; margin-right:2px}
.smallTools a:hover{ text-decoration:underline; color:#0466c2}
.smallTools img{ margin:0 2px; vertical-align:bottom}
.nav{ height:47px; width:100%; border-bottom:#333 1px solid; margin-top: 5px;}
.nav a{color:#FF6600; font-weight:bold; font-size:18px; line-height:45px; text-decoration:none; text-align:center; margin-left: 15px}
.nav a:hover{ border:#FF6600 1px solid}
.nav span:hover{ color: #FF6600}

.l-btn-text { display: inline-block;vertical-align: middle;width: auto;line-height: 45px;font-size: 18px;padding:6px 12px;text-align:center;}
.l-btn:hover { background: none; color: #FF6600; border: #FF6600 1px solid; filter: none;}
.l-btn {background: none;border: none;}
.menu-text,.menu-text span{display: inline-block;color:#FF6600;font-size: 16px;font-weight:bold;}
.menu a{font-size:16px;color:#FF6600;}

.selectedNav {
	background-color: #f1c72f;
	padding: 0	
}
.selectedNav span{color: #fff }

.divTop {
	top: 120px ! important
}

.divTopNew {
	top: 155px ! important
}

/* 加border:#ffffff 1px solid;让选中的时候多出来一个边框的时候不会有变动 */
.nav a{border:#ffffff 1px solid;}

/* 加上这个样式就去除了选中menubutton的动态效果 */
.nav .l-btn-plain{
	padding: 0px;
}
</style>
</head>

<body>
	<div id="layoutDiv" class="easyui-layout"  style="width:100%;height:100%;">
	<div id="alterPassWord" class="easyui-window" title="修改密码" style="width:380px;height:260px;display: none;" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true,closed:true,resizable:false,draggable:false">
			<form id="alterPwdForm" action="post">
				<table width="90%" border="0" cellspacing="10" style="margin: 5 auto">
				  	<tr style="margin:10px 0;">
				  		<td width="30%" align="right">旧密码：</td>
				  		<td width="70%"><input id="loginPwd" type="password" name="pswd" class="easyui-textbox" style="width: 100%" /></td>
				  	</tr>
				  	<tr style="margin:10px 0;">
				  		<td align="right">新密码：</td>
				  		<td><input id="newPwd" name="newPwd" type="password" class="easyui-textbox" style="width: 100%" /></td>
				  	</tr>
				  	<tr style="margin:10px 0;">
				  		<td align="right">密码确认：</td>
				  		<td><input id="newPwdConfirm" type="password" class="easyui-textbox" style="width: 100%" /></td>
				  	</tr>
				  	<tr style="margin:10px 0;">
				  		<td align="center" colspan="2">
				  			<a href="javascript:savePwd()" class="easyui-linkbutton" data-options="iconCls:'icon-save',split:true" style="height: 30px;">确定</a>
				  			<a href="javascript:closePwdWin()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',split:true" style="height: 30px;">取消</a>
				  		</td>
				  	</tr>
				  </table>
			</form>
	</div>

	<div id="topDiv" data-options="region:'north',border:false" style="height: 155px;">
		<div class="top">
		  <div class="smallTools"> 
		  <span id="userInfo"><%=principal.getUserName()%>，您好！</span>
		  <a title="刷新系统" href="javascript:location.reload();"><img src="static/images/homepage/3-0.png" width="19" height="17" alt=""/></a> 
		  <a title="修改密码" href="javascript:openPwdWin();"><img src="static/images/homepage/2-0.png" width="19" height="17" alt=""/></a> 
		  <a title="注销" href="javascript:toIndex();"><img src="static/images/homepage/1-0.png" width="19" height="17" alt=""/></a> 
		  </div>
		</div>
		<div class="nav">
			<a class="easyui-linkbutton" id="homeNvl0" data-options="height: 40" href="#" onclick="getPagePath(this, 0)" target = "Main">首页</a>
			
			<c:forEach items="${fileList}" var="record0">
				<c:choose>
					<c:when test="${record0.state == 'closed'}">
						<a class="easyui-menubutton" data-options="menu:'#menuId0${record0.id}',hasDownArrow:false,height: 40">${record0.text}</a>
						<div id="menuId0${record0.id}" class="menu" style="display: none;">
							<c:choose>
								<c:when test="${record0.state == 'closed'}">
									<c:forEach items="${record0.children}" var="record1">
										<c:choose>
											<c:when test="${record1.state == 'closed'}">
												<div>
											        <span>${record1.text}</span>
											        <div>
											        	<c:forEach items="${record1.children}" var="record2">
											        		<c:choose>
																<c:when test="${empty record2.url}">
																	<div><a href="javascript:;" target = "Main">${record2.text}</a></div>
																</c:when>
																<c:otherwise>
																	<c:choose>
																		<c:when test="${record2.openway == 'R'}">
																			<c:choose>
																				<c:when test="${record2.business == 'singleLogin'}">
																					<div><a id="file${record2.id}" onclick="openhds('${record2.url}');">${record2.text}</a></div>
																				</c:when>
																				<c:otherwise>
																					<div><a href="${record2.url}"  id="file${record2.id}" target = "_blank">${record2.text}</a></div>
																				</c:otherwise>
																			</c:choose>
																		</c:when>
																		<c:otherwise>
																			<div><a href="${record2.url}" id="file${record2.id}" onclick="getPagePath(this,${record2.id})" target = "Main">${record2.text}</a></div>
																		</c:otherwise>
																	</c:choose>
																</c:otherwise>
															</c:choose>
											        	</c:forEach>
											        </div>
											    </div>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${empty record1.url}">
														<div><a href="javascript:;" target = "Main">${record1.text}</a></div>
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${record1.openway == 'R'}">
																	<c:choose>
																		<c:when test="${record1.business == 'singleLogin'}">
																			<div><a id="file${record1.id}" onclick="openhds('${record1.url}');">${record1.text}</a></div>
																		</c:when>
																		<c:otherwise>
																			<div><a href="${record1.url}" id="file${record1.id}" target = "_blank">${record1.text}</a></div>
																		</c:otherwise>
																	</c:choose>
															</c:when>
															<c:otherwise>
																<div><a href="${record1.url}" id="file${record1.id}" onclick="getPagePath(this,${record1.id})" target = "Main">${record1.text}</a></div>
															</c:otherwise>
														</c:choose>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
							</c:choose>
						</div>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${empty record0.url}">
								<%-- <a class="easyui-menubutton" data-options="hasDownArrow:false,height: 40">${record0.text}</a> --%>
								<div><a href="javascript:;" target = "Main">${record0.text}</a></div>
							</c:when>
							<c:otherwise>
								<a class="easyui-linkbutton" id="homeNvl${record0.id}" data-options="height: 40" href="${record0.url}" onclick="getPagePath(this,${record0.id})" target = "Main">${record0.text}</a>
								<%-- <div><a href="${record0.url}" id="file${record0.id}" onclick="getPagePath(this,${record0.id})" target = "Main">${record0.text}</a></div> --%>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		
		</div>
		<div id="currentPathDiv" style="height: 35px;padding-left: 25px;line-height: 35px;font-size: 14px;display: none" >
			<img alt="" src="static/images/location.png">
			当前位置：<span id="pagePath" style='color: #ff6600;'></span>
		</div>
	</div>	
	
	<div id="centerDiv" style=" overflow-y:hidden" data-options="region:'center',border:false">
		<iframe name="Main" frameborder="0" scroll="no" width="100%" height="100%"></iframe>
	</div>
</div>

<script>

var userInfo = {
   userId : "<%=principal.getUserId()%>", 	  
   userCode : "<%=principal.getUserCode()%>", 	   
   userName : "<%=principal.getUserName()%>"   
};

/* $("#userInfo").html(userInfo.userName+"，您好！"); */

var ctx = "<%=path%>" + "/";

//页面宽度和高度
var pageWidth = 0;
var pageHeight = 0;
window.onload = function(){
	pageWidth = document.body.clientWidth - 20;
	pageHeight = document.body.clientHeight;
};

// 退格跳转首页处理
$(document).keydown(function (e) {
    var doPrevent;
    if (e.keyCode == 8) {
        var d = e.srcElement || e.target;
        if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') {
            doPrevent = d.readOnly || d.disabled;
        }
        else
            doPrevent = true;
    }
    else
        doPrevent = false;

    if (doPrevent)
        e.preventDefault();
});

$("#homeNvl0").addClass("selectedNav");

$(document).ready(function(){
	homeFlag = true;
	$("#topDiv").height(120);
	var centerHeight = $("#centerDiv").height();
	$("#centerDiv").parent().addClass("divTop");
	$("#centerDiv").height(centerHeight + 35);
});

var iframe_max_id = "";
var currentIndex = "";

function openwin(url,width,height,title){
	if(width==""){
		width=pageWidth-60+"px";	
	}
	if(height==""){
		height=pageHeight-20+"px";	
	}
	layer.open({
        type: 2
        ,title: title
        ,area: [width, height]
        ,shade: 0.1
        ,maxmin: true
        ,offset: 'auto'
        ,content: url
        ,btn2: function(){
          layer.closeAll();
        },
        success : function(layero){ 
        	layer.setTop(layero); 
        },
        zIndex: layer.zIndex
    });
	currentIndex = layer.index;
	iframe_max_id = "layui-layer-iframe"+layer.index;
}
//异常窗口专用
function openExceptionWin(content){
	layer.open({
 			type: 1,
 			title : '异常信息',
 			closeBtn: 1, //不显示关闭按钮
 			anim: 2,
 		    maxmin: true,
 			area : ["880px", "500px"],
 			shadeClose: true, //开启遮罩关闭
 			content: content
 		});
}
function closewin(){
   	layer.close(currentIndex);
   	currentIndex = currentIndex - 1;
}

/**
 * windowUrl 页面url
 * callback 方法名 字符串
 * obj参数 Object对象
 */
function callbackFun(windowUrl, callback, obj){
	$('iframe', window.parent.document).each(function(){
		if(this.src.indexOf(windowUrl) != -1){
			var fn = "$(window.parent.document).contents().find(\"#" + this.id + "\")[0].contentWindow."+ callback;
			eval(fn)(obj);
		}
	});
}

var homeFlag = true;
function getPagePath(self, pageId){
	
	$(".easyui-linkbutton").removeClass("selectedNav");
	$(".easyui-menubutton").removeClass("selectedNav");
	
	if($(self).attr("id").indexOf("homeNvl") != -1){
		$(self).addClass("selectedNav");
	}
	
	
	var menuId = $("#file" + pageId).parents(".menu").attr("id");
	if( typeof menuId == "undefined" && pageId != null){
		var obj = $("#file" + pageId).parents(".menu");
		var i = 0;
		while(typeof obj.prev().attr("id") == "undefined" && i<40){
			obj = obj.prev();
			i ++;
		}
		menuId = obj.prev().attr("id");
	}
	
	$(".easyui-menubutton").each(function(){
	    var options = $(this).data("options");
	    var json = eval("({"+ options +"})");
	    var menubutton_menuId = json.menu.replace("#", "");
	    if(menubutton_menuId == menuId)
	    	$(this).addClass("selectedNav");
	    else
	    	$(this).removeClass("selectedNav");
	});
	
	if(pageId == null || pageId == ""){
		$("#pagePath").html("");
		$("#currentPathDiv").hide();
		
		if(!homeFlag){
			var north = $("#layoutDiv").layout("panel","north");
			north.panel('resize',{height: 120});
			var centerHeight = $("#centerDiv").height();
			$("#centerDiv").parent().removeClass("divTopNew");
			$("#centerDiv").parent().addClass("divTop");
			$("#centerDiv").height(centerHeight + 35);
			homeFlag = true;
		}
	}else{
		if(homeFlag){
			var north = $("#layoutDiv").layout("panel","north");
			north.panel('resize',{height: 155});
			var centerHeight = $("#centerDiv").height();
			$("#centerDiv").parent().removeClass("divTop");
			$("#centerDiv").parent().addClass("divTopNew");
			$("#centerDiv").height(centerHeight - 35);
			homeFlag = false;
		}
		
		$("#currentPathDiv").show();
		$.ajax({
			url : 'SysCMenu/getMenuPath.action',
			data : {
				menuId : pageId
			},
			success : function(data){
				$("#pagePath").html(data);
			}
		})
	}
}

function toIndex(){
	layer.confirm('确定要退出吗？', {
  		btn: ['确定 ','取消'] //按钮
	}, function(){
		//确定
		window.location = ctx+"system/logout.action";
	}, function(){
		//取消
	});
}

function openPwdWin(){
	$("#alterPassWord").window("open");
	$("#alterPwdForm").form('reset');
}

function closePwdWin(){
	$("#alterPassWord").window("close");
}

function savePwd(){
	$("#alterPwdForm").form('submit', {
		url : 'system/modifyPwd.action',
		onSubmit : function(data){
			if($("#newPwd").textbox('getValue') == "" || $("#newPwdConfirm").textbox('getValue') == ""){
				layer.alert('新密码/确认密码不能为空!', {
				  skin: 'layer-ext-moon'
				});
				return false;
			}
			if($("#newPwd").textbox('getValue') != $("#newPwdConfirm").textbox('getValue')){
				layer.alert('新密码/确认密码不一致!', {
				  skin: 'layer-ext-moon'
				});
				return false;
			}
		},
		success : function(data){
			var jsonData = eval("("+ data +")");
			if(jsonData.success){
				msgSuccess("密码修改成功,下次登录请使用新密码!");
				$('#alterPassWord').window("close");
			}else{
	    		msgInfo(jsonData.msg);
			}
		},
		onLoadError : function(){
			msgError("修改密码出现错误，请联系管理员！");
			return false;
		}
	});
}

var max_flag = false;
$(window).resize(function(){
	if(homeFlag){
		if(!max_flag){
			var north = $("#layoutDiv").layout("panel","north");
			north.panel('resize',{height: 120});
			var centerHeight = $("#centerDiv").height();
			$("#centerDiv").height(centerHeight + 35);
			max_flag = true;
		}else{
			var north = $("#layoutDiv").layout("panel","north");
			north.panel('resize',{height: 120});
			var centerHeight = $("#centerDiv").height();
			$("#centerDiv").height(centerHeight - 35);
			max_flag = false;
		}
	}else{
		var north = $("#layoutDiv").layout("panel","north");
		north.panel('resize',{height: 155});
	}
});
</script>
</body>
</html>