//Skin1
var skin1 = { href:"ccSource/css/login1.css",layoutHeight:500,layoutWidth:960,headHeight:60,centerHeight:null,footHeight:50};
//Skin2
var skin2 = { href:"ccSource/css/login2.css",layoutHeight:"100%",layoutWidth:"100%",headHeight:79,centerHeight:null,footHeight:39};

//当前样式表
var currentSkin = skin2;

//加载样式表
$("#LoginCSS").attr("href",currentSkin.href);
$().ready(function(e) {
	//定义样式
	changeSkin(currentSkin);
		
	//加载事件
	LoadEvent();
});
$(window).resize(function() {
    changeSkin(currentSkin);
});


function changeSkin(skin){
	//若样式表未加载，则加载
	if(skin.href != $("#LoginCSS").attr("href")){
		$("#LoginCSS").attr("href",skin.href);
	}
    //高度计算
	$(".Login").width(skin.layoutWidth);
	$(".Login").height(skin.layoutHeight);
	$(".Login").css("top",($(window).height() - skin.layoutHeight)/2+"px");
	$(".Login").css("left",($(window).width() - skin.layoutWidth)/2+"px");
	
	$(".Login .Center").height(skin.layoutHeight - skin.headHeight - skin.footHeight);
	$(".Login .Head").height(skin.headHeight);
	$(".Login .Foot").height(skin.footHeight);
}

function LoadEvent(){
	//登录按钮
	$(".btn.submit").mouseover(function(e){
		$(this).addClass('submitHover');
	}).mouseout(function(e){
		$(this).removeClass('submitHover').removeClass('submitClick');
	}).mousedown(function(e){
		$(this).addClass('submitClick');
	}).mouseup(function(e){
		$(this).removeClass('submitClick');
	});
	
	//
	$(".InputBox input").focus(function(e){
		$(this).parent(".Content").addClass('ContentHover');
	}).focusout(function(e) {
		$(".InputBox .ContentHover").removeClass('ContentHover');
	});
}