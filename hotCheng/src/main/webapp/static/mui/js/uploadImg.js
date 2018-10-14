function saveLocalImage(){
			 var doms =$("#F_CKJLBS").children("div");
            for(var i=1;i<doms.length;i++){
            	var paras=doms[i].getElementsByTagName("img");           		 
	            var titleText=paras[0].getAttribute("src");  	             
	        	localImage.push(titleText)
                //保存的本地图片
                localforage.setItem(imageKey, localImage); 
            }
		}
        function showImgDetail (imgId,imgkey,id,src) { 
            var html = "";  
            html +='<div name="picDiv" id="Img'+imgId+imgkey+'"  class="image-item ">';   
            html +='    <img name="pic" data-preview-src=""   data-preview-group="1" '+src+'/>';  
            html +='    <span class="del" onclick="delImg(\''+imgId+'\',\''+imgkey+'\','+id+');">';
            html +='        <div class="fa fa-times-circle"></div>';      
            html +='    </span>';   
            html +='</div>';  
            $("#"+imgkey+"S").append(html);                     
        }  

        function delImg(imgId,imgkey,id){
            var bts = ["是", "否"];  
            plus.nativeUI.confirm("是否删除图片？", function(e) {  
                var i = e.index;  
                if (i == 0) {  
                     $("#Img"+imgId+imgkey).remove();  
                }  
            },"提示", bts);                          
        }  
        
        function delImgFromLocal(itemname,itemvalue,imgId,imgkey,index){ 
            var wa = plus.nativeUI.showWaiting();  
            var left=itemvalue.substr(0,index-1);  
            var right=itemvalue.substring(index,itemvalue.length);  
            var end=right.indexOf("}");  
            right=right.substring(end+1,right.length);  
            var newitem=left+right;  
            plus.storage.setItem(itemname,newitem);   
            $("#Img"+imgId+imgkey).remove();  
            wa.close();  
        }  
        //选取图片的来源，拍照和相册  
        function showActionSheet(conf){
            var divid = conf.id;  
            var actionbuttons=[{title:"拍照"},{title:"相册选取"}];  
            var actionstyle={title:"选择照片",cancel:"取消",buttons:actionbuttons};  
            plus.nativeUI.actionSheet(actionstyle, function(e){  
                if(e.index==1){  
                    getImage(divid);  
                }else if(e.index==2){  
                    galleryImg(divid);  
                }  
            });  
        }  
                
        // 添加文件
		var index=1;
		var imgArray=[];
		function appendFile(p){
			imgArray.push({name:"uploadkey"+index,path:p});
			index++;
		}
        //从相册选择   
        function galleryImg(divid){
    		plus.gallery.pick( function(e){
    			
  			for(var i=0;i<e.files.length;i++){
  				console.log(e.files[i]);
		    	appendFile(e.files[i]);
		    }
    		var zm=0;
    		setTimeout(file,200);
    	    function file(){
    	    	plus.io.resolveLocalFileSystemURL(e.files[zm], function(entry) {
			        compressImage(entry.toLocalURL(),entry.name,divid); 
			    }, function(e) {
				    plus.nativeUI.toast("读取拍照文件错误：" + e.message);
			    });
			    zm++;
			    if(zm<e.files.length){
			    	setTimeout(file,200);
			    }
    	    }   
            }, function ( e ) {
	           console.log( "取消选择图片" );
            },{filename: "_doc/camera/",
               filter:"image",
               multiple:true
            });
         }
        
        //拍照  
        function getImage(divid) {  
            var cmr = plus.camera.getCamera();  
            cmr.captureImage(function(p) {  
                plus.io.resolveLocalFileSystemURL(p, function(entry) {  
                    compressImage(entry.toLocalURL(),entry.name,divid);  
                }, function(e) {  
                    plus.nativeUI.toast("读取拍照文件错误：" + e.message);  
                }); 
                appendFile(p);
            }, function(e) {  
            }, {  
                filename: "_doc/camera/",  
                index: 1  
            });  
        }  
        var localImage = new Array();
        //压缩图片  
        function compressImage(url,filename,divid){	
            var name="_doc/upload/"+divid+"-"+filename;//_doc/upload/F_ZDDZZ-1467602809090.jpg  
            plus.zip.compressImage({  
                    src:url,//src: (String 类型 )压缩转换原始图片的路径  
                    dst:name,//压缩转换目标图片的路径  
                    quality:20,//quality: (Number 类型 )压缩图片的质量.取值范围为1-100  
                    overwrite:true//overwrite: (Boolean 类型 )覆盖生成新文件  
                },  
                function(event) {   
                    var path = name;//压缩转换目标图片的路径  
                    console.log(event.target);
                    saveimage(event.target,divid,filename,path);  
                },function(error) {  
                    plus.nativeUI.toast("压缩图片失败，请稍候再试");  
            });  
        }         
        //保存信息到本地 
        function saveimage(url,divid,name,path){ 
            var  state=0;  
            var wt = plus.nativeUI.showWaiting();  
            name=name.substring(0,name.indexOf("."));//图片名称：1467602809090  
            var id = document.getElementById("ckjl.id").value;  
            var itemname=id+"img-"+divid;//429img-F_ZDDZ  
            var itemvalue=plus.storage.getItem(itemname);  
            if(itemvalue==null){  
                itemvalue="{"+name+","+path+","+url+"}";//{IMG_20160704_112614,_doc/upload/F_ZDDZZ-IMG_20160704_112614.jpg,file:///storage/emulated/0/Android/data/io.dcloud...../doc/upload/F_ZDDZZ-1467602809090.jpg}  
            }else{    
                itemvalue=itemvalue+"{"+name+","+path+","+url+"}";  
            }  
            plus.storage.setItem(itemname, itemvalue);               
            var src = 'src="'+url+'"';              
            showImgDetail(name,divid,id,src);  
            wt.close();  
              
        }               

        function offLineImages(){
        	var divid = $("#F_CKJLB").attr("id");
        	localforage.getItem(imageKey,function(err,value){
        		if(value==null||value.length==0){
        			return;
        		}
        		for(var i=0;i<value.length;i++){
        		 plus.io.resolveLocalFileSystemURL(value[i], function(entry) {  
                    var name=entry.name;
                    var src = 'src="'+entry.toLocalURL()+'"';   
                    var id = document.getElementById("ckjl.id").value;
                    name=name.substring(0,name.indexOf("."));
                    showImgDetail(name,divid,id,src);                   
                }, function(e) {  
                    plus.nativeUI.toast("获取图片错误：" + e.message);  
                }); 
        	}        		
        	});
        }
        
        //上传点检结果及图片
        function uploadimage() {
        	var snapTitle = mui("#snap-title")[0].value ;
           	var snapType = mui("#snap-type")[0].value ;
           	var snapContent = mui("#snap-content")[0].value ;
           console.log("snapTitle="+snapTitle);
           	if (!snapTitle){ 
           		mui.toast("主题不能为空");
           		return false ;
			}
           	if (!snapType){ 
           		mui.toast("分类不能为空");
           		return false ;
			}
           	if (!snapContent){ 
           		mui.toast("内容不能为空");
           		return false ;
			}		
			
			
            var wa = plus.nativeUI.showWaiting();              
             console.log("url="+baseUrl+"/MySnap/saveMySnap.action");
            var task = plus.uploader.createUpload(baseUrl+"/MySnap/saveMySnap.action", {
                method: "POST"
                },  
                function(t, status) {  
                    if (status == 200) {  
                    	wa.close();  
                    //	saveLocalImage();
                    	mui.toast("上传成功")
                        console.log("上传成功"); 
                        backFun();
                    } else {  
                        wa.close();  
                        mui.toast("上传失败")
                        console.log("上传失败");   
                    }  
                }  
            );          
           	
			var params = {
				snapContent : $('#snap-content').val(),
			    snapTitle : $('#snap-title').val(),
	    	    snapType : $('#snap-type').val(),
	    	    snapTypeId : $('#snap-typeId').val()			
			}	
			console.log(JSON.stringify(params))
         	task.addData("params",JSON.stringify(params));
         	console.log("length=========================="+imgArray.length);
            for(var i=0; i<imgArray.length;i++){   
            	var f=imgArray[i];
            	task.addFile(f.path,{key:f.name}); 
            	console.log("name==================="+f.name);
            }  
            task.start();            
        } 
        
		var old_back = mui.back;
		/*var backFun=function () {	
			old_back();
			//隐藏输入法
			document.activeElement.blur() ;	
			plus.webview.currentWebview().close();
			//向父页面传参
			var parentWin =plus.webview.getWebviewById('02yyzx.html');     
			console.log("parentWin="+parentWin);
			parentWin.evalJS('reflashMysnap()');	          		
        }*/