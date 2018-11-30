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
<meta http-equiv="X-UA-Compatible" content="IE=9;IE=10;IE=edge;chrome=1" />
<title>热骋 </title>
<jsp:include page="/view/common/jsp/common.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="static/css/fuping/skin.css"/>
<link rel="shortcut icon" href="static/favicon.ico" type="image/x-icon" />
<style>


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

	<div id="topDiv" data-options="region:'north',border:false" style="height: 90px;">
		<div class="top">
		<div class="logo">&nbsp;</div>
		  <div class="smallTools"> 
		  <span id="userInfo"><%=principal.getUserName()%>，您好！</span>
		  <div >
			  <a title="刷新系统" href="javascript:location.reload();">刷新系统</a> 
			  <a title="修改密码" href="javascript:openPwdWin();">修改密码</a> 
			  <a title="注销" href="javascript:toIndex();">注销</a> 
		  </div>
		  </div>
		</div>

	</div>	


	<div id="west" region="west" title="系统导航" style="width:240px;" style=" overflow-y:hidden;">
	
		<div id="menu" class="easyui-accordion"  data-options ="fit:true,border:false">
			<div title="电池管理" style="overflow-x: auto; overflow-y: auto" data-options="iconCls:'icon'">
				<div title="扫码人员管理" style="overflow-x: auto; overflow-y: auto"
					data-options="iconCls:'icon'">
               			<div class="J2" >                    
							<ul id="nav47" class="easyui-tree" onClick="openMenu('扫码人员管理','view/business/rpUserList.jsp')">
								扫码人员管理
							</ul>  
						</div>
				</div>
				<div title="门店管理" style="overflow-x: auto; overflow-y: auto"
					data-options="iconCls:'icon'">
               			<div class="J2" >                    
							<ul id="nav47" class="easyui-tree" onClick="openMenu('门店管理','view/business/rpStoreList.jsp')">
								门店管理
							</ul>  
						</div>
				</div>
				<div title="用户管理" style="overflow-x: auto; overflow-y: auto"
					data-options="iconCls:'icon'">
               			<div class="J2" >                    
							<ul id="nav47" class="easyui-tree" data-options="iconCls:'icon-save'" onClick="openMenu('电池数据管理','view/business/rpStatisticsList.jsp')">
								电池数据管理
							</ul>  
						</div>
				</div>
				<div title="电池销售统计" style="overflow-x: auto; overflow-y: auto"
					data-options="iconCls:'icon'">
               			<div class="J2" >                    
							<ul id="nav47" class="easyui-tree" onClick="openMenu('电池销售统计','view/business/rpStatisticsTj.jsp')">
								电池销售统计
							</ul>  
						</div>
				</div>
				<div title="制动器管理" style="overflow-x: auto; overflow-y: auto"
					data-options="iconCls:'icon'">
               			<div class="J2" >                    
							<ul id="nav47" class="easyui-tree" onClick="openMenu('制动器管理','view/business/rcBfdBrakeList.jsp')">
								制动器管理 
							</ul>  
						</div>
				</div>
			</div>
			<%-- <c:forEach var="menu" items="${fileList}">
			
				<div title=${menu.text} style="overflow-x: auto; overflow-y: auto"
					data-options="iconCls:'icon'">
               <div class="J2" >                    
                      
						<ul id="nav47" class="easyui-tree"
							data-options="
	          		  url:'system/findChildByMenuCode.action?menuCode=${menu.code}',
	          		  method:'post',
	          		  animate:true,
	          		  onClick: function(node){
	          		    if(node.state=='closed'){
                             $(this).tree('expand',node.target);
                        }else if(node.state=='open'){
            	             $(this).tree('collapse',node.target);
                        }
			
			           if(!$(this).tree('isLeaf', node.target)){
				         return ;
			           }
	          		  clickMenu(node)},
	                  onBeforeExpand:function(node,param){$(this).tree('options').url = 'system/findChildByMenuCode.action?menuCode=' + node.code;} ">
						</ul>  
						
					</div>
</div>

			</c:forEach> --%>
	 </div> 

	</div>
	
		<div id="mainPanle" region="center" style=" overflow-y:hidden;">  
        <div id="tabs" class="easyui-tabs"  fit="true" border="false">  
            <div title="首页">
                  <iframe frameborder="0" src="view/homepage/first.jsp" width="100%" height="90%"></iframe>
            </div>  
        </div>  
    </div>  
    <div id="mm" class="easyui-menu" style="width: 100px;" display = "none">
     <div name="6">刷新</div>
     <div name="1">关闭</div>
     <div name="3">关闭其他</div>
     <div name="2">全部关闭</div>
     <div class="menu-sep"></div>
     <div name="4">关闭右侧</div>
     <div name="5">关闭左侧</div>
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


$(document).ready(function(){
	homeFlag = true;
	$("#topDiv").height(90);
});

var iframe_max_id = "";

/**
 * 用来记录弹框的层数
 */
var currentIndexArray = new Array();

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
	iframe_max_id = "layui-layer-iframe"+layer.index;
	currentIndexArray.push(layer.index);
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
 			content: content,
 			zIndex : layer.zIndex
 		});
}
function closewin(){
   	layer.close(currentIndexArray[currentIndexArray.length - 1]);
   	currentIndexArray.pop();
}

/**
 * windowUrl 页面url
 * callback 方法名 字符串
 * obj参数 Object对象
 */
function callbackFun(windowUrl, callback, obj){
	$('iframe', window.parent.document).each(function(){
		var src = this.src;
		var indexNum = src.indexOf("?");
		if(indexNum > 0){
			src = src.substr(0, indexNum);
		}
		if(src.indexOf(windowUrl) != -1){
			var fn = "$(window.parent.document).contents().find(\"#" + this.id + "\")[0].contentWindow."+ callback;
			eval(fn)(obj);
		}
	});
}

var homeFlag = true;

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
			north.panel('resize',{height: 90});
			max_flag = true;
		}else{
			var north = $("#layoutDiv").layout("panel","north");
			north.panel('resize',{height: 90});
			max_flag = false;
		}
	}else{
		var north = $("#layoutDiv").layout("panel","north");
		north.panel('resize',{height: 90});
	}
});


$('#tabs').tabs({
    onContextMenu:function(e, title,index){
        e.preventDefault();
        if(index>0){
            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY
            }).data("tabTitle", title);
        }
    }
});
//右键菜单click
$("#mm").menu({
    onClick : function (item) {
        closeTab(this, item.name);
    }
});
var iframeId = "";
function closeTab(menu, type) {
    var allTabs = $("#tabs").tabs('tabs');
    var allTabtitle = [];
    $.each(allTabs, function (i, n) {
        var opt = $(n).panel('options');
        if (opt.closable)
            allTabtitle.push(opt.title);
    });
    var curTabTitle = $(menu).data("tabTitle");
    var curTabIndex = $("#tabs").tabs("getTabIndex", $("#tabs").tabs("getTab", curTabTitle));
    switch (type) {
        case '1'://关闭当前
            $("#tabs").tabs("close", curTabIndex);
            return false;
            break;
        case '2'://全部关闭
            for (var i = 0; i < allTabtitle.length; i++) {
                $('#tabs').tabs('close', allTabtitle[i]);
            }
            break;
        case '3'://除此之外全部关闭
            for (var i = 0; i < allTabtitle.length; i++) {
                if (curTabTitle != allTabtitle[i])
                    $('#tabs').tabs('close', allTabtitle[i]);
            }
            $('#tabs').tabs('select', curTabTitle);
            break;
        case '4'://当前侧面右边
            for (var i = curTabIndex; i < allTabtitle.length; i++) {
                $('#tabs').tabs('close', allTabtitle[i]);
            }
            $('#tabs').tabs('select', curTabTitle);
            break;
        case '5': //当前侧面左边
            for (var i = 0; i < curTabIndex - 1; i++) {
                $('#tabs').tabs('close', allTabtitle[i]);
            }
            $('#tabs').tabs('select', curTabTitle);
            break;
        case '6': //刷新
      	  var currTab = $('#tabs').tabs('getSelected');
      	  var url = $(currTab.panel('options').content).attr('src');
      	  $('#tabs').tabs('update', {
      	      tab : currTab,
      	      options : {
      	       content : '<iframe id="'+iframeId+'" frameborder="0"  src="'+url+'" width="100%" height="100%"></iframe>'
      	      }
      	     });
            break;
    }

}
		function clickMenu(node){
			if(node.state=="open"){
				if(node.openway=="R"){
					//window.open(node.url);
				}else{
					if($("#tabs").tabs("exists",node.text)){
					       $("#tabs").tabs("select",node.text); 
				    } else{
				    	iframeId = node.code;
						$('#tabs').tabs('add',{
							id:node.id,
							title: node.text,
							content: '<iframe id="'+ node.code +'" frameborder="0"  src="'+node.url+'" width="100%" height="100%"></iframe>',
							closable: true
						});
				    }
				}
			}
		}
		function openMenu(name,url){
			if($("#tabs").tabs("exists",name)){
			       $("#tabs").tabs("select",name); 
		    } else{
		    	//iframeId = node.code;
				$('#tabs').tabs('add',{
					//id:node.id,
					title: name,
					content: '<iframe  frameborder="0"  src="'+url+'" width="100%" height="100%"></iframe>',
					closable: true 
				});
		    }
		}

</script>
</body>
</html>