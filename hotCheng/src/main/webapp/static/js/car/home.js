$(function(){
	applyMeetData()
});

function applyMeetData() {
	var url =basUrl+"/interact/getImportantNewList.action";
	var param = {				
	};

	$.ajax({
		url : url,
		data : {
			id : param
		},
		dataType: 'json',
		success : function(data) {
			//集团要闻
			var jtywList ;
				if(data.length<3){
					jtywList = new Array(data.length);
				}else{
					jtywList = new Array(3);
				}
				for(var i=0;i<jtywList.length;i++){
					jtywList[i]=data[i]
				}
				
				document.getElementById("jtywlist").innerHTML =template("jtyw123", {
					list3: jtywList
				});
		},
		error:function(data){
			alert("err:"+JSON.stringify(data));
		}
	});
};


