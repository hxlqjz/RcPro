<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Untitled Document</title>
<jsp:include page="/view/common/jsp/common.jsp"></jsp:include>
<script type="text/javascript" src="static/js/ajaxfileupload.js"></script>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<body>
	<div class="easyui-panel"  data-options="fit:true" style="">
		<form id="form" name="form"  enctype="multipart/form-data">
		<input type="hidden" value="${entity.id}" name="id" id="id"/>
			<div style="width:100%;height:250px;border:1px dashed #000;">
				<c:if  test="${entity.imgSrc != '' and entity.imgSrc != null}">
					<img src="/hotCheng/pcBfd/tofindPic.action?imgName=${entity.imgSrc}" onclick="dimgloadwin('${entity.imgSrc}')" id="img" style="width:100%;height:250px" >
				</c:if>
				<c:if  test="${entity.imgSrc == '' or entity.imgSrc == null}">
					<img id="img"  style="width:100%;height:250px"/>
				</c:if>
			</div>
			<div style="padding-top:10px;">
				<input accept="image/*"  type="file" id="file" name="file" />
			</div>
			</form>
		<div style="padding-top:10px;"> 
		
		<a href="javascript:;" onclick="uploadImg()" class="btn default">上传</a> 
		<a href="javascript:;" onclick="closeWin()" class="btn default">取消</a>
		</div>
	</div>
	
	<script type="text/javascript">
	$(function(){
		$("input[type='file']").change(function(){
			var file = this.files[0];
			if(window.FileReader){
				var reader = new FileReader();
				reader.readAsDataURL(file);
				reader.onloadend = function(e){
					$("#img").attr("src",e.target.result);
				};
			}
			
		});
			
	});

	function uploadImg(){
		
		$.ajaxFileUpload({
            type:"post",
            url:"pcBfd/uploadImg.action",
            secureuri:false,
            data:{
            	id:$("#id").val()
            },
            dataType: 'text',
            fileElementId:"file",
            cache:false,
            async:false,
            success:function(data,textStatus,jqXHR){
            	console.log(data)
            	data = JSON.parse(data);
               if(data.success == "true"){
            	   alert("上传成功");
               }else{
            	   alert("上传失败");
               }
               closeWin();
            },
            error:function(XMLHttpRequest,textStatus,errorThrown){
                
                }
            });
	}
	function closeWin(){
		parent.$("#datagrid").datagrid('reload', {
			bfd : parent.$("#bfd").val(),
			bCode : parent.$("#bCode").val()});
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    parent.layer.close(index);
	}
	


		
	</script>
</body>
</html>