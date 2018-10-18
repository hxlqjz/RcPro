<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">  
<head>  
<title>电力部ERP开发基础框架 </title>
    <jsp:include page="/view/common/jsp/common.jsp"></jsp:include>
    <link rel="shortcut icon" href="static/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="static/css/skin/skin.css"/>
    <link rel="stylesheet" type="text/css" href="static/css/skin/layout.css"/>
	<style>
	.tabs li a.tabs-inner:hover, .tabs li.tabs-selected a.tabs-inner{
	   background-color:#1f66a4
	}
	.tabs li a.tabs-inner{
	   background-color:#2171B7
	}
	.tabs-header, .tabs-scroller-left, .tabs-scroller-right, .tabs-tool, .tabs,  .tabs li a.tabs-inner,  a.tabs-inner, .tabs-header-bottom , .tabs-header-left , .tabs-header-right {
	    background-color:#2171B7
	}
	.panel-header{
	   background-color:#2171B7;
	   height:19px;
	}
	.tabs-scroller-left, .tabs-scroller-right, .tabs li a.tabs-close{
	background-image:url(static/thirdparty/easyuiThem/themes/insdep/images/tabs_icons1.png)
	}
	</style>
	
</head>



<body class="easyui-layout" style="overflow-y: hidden"  fit="true"   scroll="no">  
<div id="alterPassWord" class="easyui-window" title="修改密码" style="width:350px;height:240px" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true,closed:true,resizable:false,draggable:false">
	<div class="easyui-panel" style="width:100%; text-align: center;padding: 7px 3px;">
		<form id="alterPwdForm" action="post">
			<table width="100%">
			  	<tr>
			  		<td width="30%" align="right">旧密码：</td>
			  		<td width="70%"><input id="loginPwd" type="password" name="loginPwd" class="easyui-textbox" style="width: 100%" /></td>
			  	</tr>
			  	<tr>
			  		<td align="right">新密码：</td>
			  		<td><input id="newPwd" name="newPwd" type="password" class="easyui-textbox" style="width: 100%" /></td>
			  	</tr>
			  	<tr>
			  		<td align="right">密码确认：</td>
			  		<td><input id="newPwdConfirm" type="password" class="easyui-textbox" style="width: 100%" /></td>
			  	</tr>
			  	<tr>
			  		<td></td>
			  		<td>
			  			<a href="javascript:savePwd()" class="easyui-linkbutton" data-options="iconCls:'icon-save',split:true">确定</a>
			  			<a href="javascript:closePwdWin()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',split:true">取消</a>
			  		</td>
			  	</tr>
			  </table>
		</form>
	</div> 
</div>
 <div region="north" style="overflow: hidden; height: 90px;
 background: #fff url(static/COCOSOURCE/layout/theme/skin1/images/top.jpg) left top repeat-x;">  
			<div class="logo"></div>
			<div class="flash"></div>
			<div class="tool">
				欢迎您：${workName} <font>|</font> 在线人数${onlineNum}  <font>|</font>
				<a style="font-size: 14px" href="javascript:openPwdWin()">
				<i class="fonticon icon_user"></i>修改密码</a><font>|</font><a
					style="font-size: 14px" href="javascript:toIndex()"><i
					class="fonticon icon_unlock"></i>注销</a>
			</div>
    </div>
	<div id="west" region="west" title="<font color='#fff'>系统导航</font>" style="width:240px" >
	
		<div id="menu" class="easyui-accordion"  border="false">
			<c:forEach var="menu" items="${menuList}">
			
				<div title=${menu.fileName} style="overflow-x: visible; overflow-y: visible"
					data-options="iconCls:'icon'">
               <div class="J2" style="padding-left:20px">
						<ul id="nav47" class="easyui-tree"
							data-options="
	          		  url:'system/findFilesByWorkerId.action?node=${menu.fileId}',
	          		  method:'post',
	          		  animate:true,
	          		  onClick: function(node){clickMenu(node)},
	                  onBeforeExpand:function(node,param){$(this).tree('options').url = 'system/findFilesByWorkerId.action?node=' + node.id;} ">
						</ul>
					</div>
</div>

			</c:forEach>
		</div>

	</div>
	<div id="mainPanle" region="center" style=" overflow-y:hidden">  
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >  
            <div title="首页" style="overflow-y:hidden">  
                  <iframe frameborder="0" scroll="no" src="views/homepage/home1-4.action" width="100%" height="100%"></iframe>
            </div>  
        </div>  
    </div>  
    <div id="mm" class="easyui-menu" style="width: 120px;">
     <div id="mm-tabclose" name="6">
            刷新</div>
        <div id="Div1" name="1">
            关闭</div>
            <div id="mm-tabcloseother" name="3">
          关闭其他</div>
        <div id="mm-tabcloseall" name="2">
            全部关闭</div>
        <div class="menu-sep">
        </div>
        <div id="mm-tabcloseright" name="4">
            关闭右侧</div>
        <div id="mm-tabcloseleft" name="5">
            关闭左侧</div>
    </div>
  <script type="text/javascript">
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
        	       content : '<iframe  frameborder="0"  src="'+url+'" width="100%" height="100%"></iframe>'
        	      }
        	     });
              break;
      }

  }
		function clickMenu(node){
			if(node.state=="open"){
				if(node.openway=="R"){
					window.open(node.url)
				}else{
					if($("#tabs").tabs("exists",node.text)){
					       $("#tabs").tabs("select",node.text);    
				    } else{
						$('#tabs').tabs('add',{
							id:node.id,
							title: node.text,
							content: '<iframe  frameborder="0"  src="'+node.url+'" width="100%" height="100%"></iframe>',
							closable: true
						});
				    }
				}
			}
		}
		function flowDesign(pageId,title,url){
			$('#tabs').tabs('add',{
				id:pageId,
				title: title,
				content: '<iframe  frameborder="0"  src="'+url+'" width="100%" height="100%"></iframe>',
				closable: true
			});
		}
		function toIndex(){
			$.ajax({ 
				url: "system/loginOut.action", 
				success: function(){
				window.location = ctx+"views/homepage/index.action";
		      }});
			
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
						msgWarn("新密码/确认密码不能为空!");
						return false;
					}
					if($("#newPwd").textbox('getValue') != $("#newPwdConfirm").textbox('getValue')){
						msgWarn("新密码/确认密码不一致!");
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
	</script>
</body>  
</html>  