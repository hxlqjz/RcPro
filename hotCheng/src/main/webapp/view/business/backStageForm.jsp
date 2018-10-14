<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Untitled Document</title>
<jsp:include page="/view/common/jsp/common.jsp"></jsp:include>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<body>
<div class="easyui-panel" data-options="fit:true">
		<form id="backStage_Form" method="post">
			<table class="CTable" width="95%" border="0" cellpadding="3"
				cellspacing="8" style="margin:auto;table-layout: fixed">
				 <!--主键-->
				<input type="hidden" id="sysId" name="sysId" value="${entity.sysId}" />				
				<tr>
				<td class="CTableTitle1" width="12%"  align="right">品牌:</td>
				<td width="38%">
					<input id="carBrand" name="carBrand" value="${entity.carBrand}" 
						class="easyui-textbox" data-options="required:true" validtype="length[0,255]" 					
                     style="width: 100%;height:30px" >
					</td>
					<td class="CTableTitle1" width="10%"  align="right">国别:</td>
				<td width="40%">
					<input id="carNation" name="carNation" value="${entity.carNation}" 
						class="easyui-textbox" validtype="length[0,255]" 					
                             style="width: 100%;height:30px"  data-options="required:true" >
					</td>
				</tr>
				<tr>
				<td class="CTableTitle1" width="12%"  align="right">字母表:</td>
				<td width="38%">
					<input id="alphabet" name="alphabet" value="${entity.alphabet}" 
						class="easyui-textbox" data-options="required:true" validtype="length[0,255]" 					
                     style="width: 100%;height:30px" >
					</td>
					<td class="CTableTitle1" width="10%"  align="right">排序:</td>
				<td width="40%">
					<input id="orderNo" name="orderNo" value="${entity.orderNo}" 
						class="easyui-numberbox" 					
                             style="width: 100%;height:30px"  data-options="required:true" >
					</td>
				</tr>
					<tr>
				<td class="CTableTitle1" width="12%"  align="right">宽度width:</td>
				<td width="38%">
					<input id="width" name="width" value="70" 
						class="easyui-numberbox" data-options="" validtype="length[0,255]" 					
                     style="width: 100%;height:30px" >
					</td>
					<td class="CTableTitle1" width="10%"  align="right">高度height:</td>
				<td width="40%">
					<input id="height" name="height" value="70" 
						class="easyui-numberbox" 					
                             style="width: 100%;height:30px"  data-options="" >
					</td>
				</tr>
			  <tr>
				<td class="CTableTitle1" style="border-top:0px" width="12%"  align="right">logo:</td>
				<td width="38%"  style="border-top:0px">
	             <input id="uploadFile" name="uploadFile"   type="file" onChange="handleFile(this)"  accept="image/*" capture="camera" >  			
				</td>
				<td id="carTdImg"  style="display:none;">				
					<img id="carShowImg" alt="" src="${entity.content}" style="width:50px;height:50px;">		
				</td>
				</tr>	
				<span style="color:red;"> 排序说明：是对同一个字母的排序</span>	 	 		     
				</table>
			</form> 	
				<table class="CTable" width="95%" border="0" cellpadding="3"
				cellspacing="8" style="margin:auto;table-layout: fixed">							
				<tr>
					<td colspan="4" width="95%" align="center">
						<a  href="javascript:;" onclick="saveFunLocal()" class="btn default" >保存</a> 
						<a  href="javascript:;" onclick="closeCurrentWin()"  class="btn default" >取消</a> 
					</td>
				</tr>
			</table>
	</div>

	<script type="text/javascript" src="static/business/backStageForm.js"></script>
	<script type="text/javascript">
	//文件的处理方法
	function handleFile(f){	
		var reader = new FileReader();
	    var files = f.files;
        for(var k=0;k<files.length;k++){
            if(!/image\/\w+/.test(files[k].type)){
                console.log(files[k].name+"不是图像文件!");
            }else{
                //此处可加入文件上传的代码
                console.log(files[k].name+"文件已上传")
            }
         }
        reader.readAsDataURL(files[0]);
        reader.onload = function(){
       //     img.src = this.result; 
           $("#carShowImg").attr("src",this.result); //图片数据
           $('#carTdImg').css('display','block'); 	
        }
	};
	</script>
</body>
</html>