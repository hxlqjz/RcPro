

/**********  常用表单操作    ****************/

/**
 * 彻底清空表单
 * @param {} form
 */
function clearForm(form){
	if(form.getForm().getEl()){
		form.getForm().getEl().dom.reset();
	}
}

/************  常用方法  *******************/

/**
 * 时间去除T处理
 * */
function getCurrentDate(value) {
	if (!value)
		return "";
	var reDate = /\d{4}\-\d{2}\-\d{2}/gi;
	var reTime = /\d{2}:\d{2}:\d{2}/gi;
	var strDate = value.match(reDate);
	var strTime = value.match(reTime);
	if (strDate){
		return strTime ? strDate + " " + strTime : strDate;
	}
	return "";
}

/**
 * 格式化成红色
 * @param {} o
 * @return {}
 */
function fontRed(o){
	return "<font color='red'>"+o+"</font>";
}


/**
 * 附件
 * @param {} val
 * @return {}
 */
function renderFile(val){
	if(val){
		return "<a href='"+val+"' target='_blank'><center>查看附件</center></a>";
	}
	return "";
}

/**
 * url,根据参数名获取参数值
 * @param {} psName
 * @return {}
 */
function getParameter(psName){
    var result="";
    var str = self.location.search;
    var pIndex = str.indexOf(psName);
    if(pIndex != -1){
    	result = str.substring(pIndex + psName.length + 1);
    	pIndex = result.indexOf("&");
    	if(pIndex != -1){
    		result = result.substring(0,pIndex);
    	}
    }
    return result;
}

/**
 * url特殊字符串转换
 * @param {} sStr url地址
 * @return {}
 */
function URLencode(sStr){
     return sStr.replace(/\%/g,'%25').replace(/\&/g,'%26').replace(/\+/g, '+').replace(/\"/g,'"').replace(/\'/g, "'").replace(/\//g,'/').replace(/\#/g,'%23');
};
/**
 * 判断对象是否为空
 * @param obj
 */
function isEmpty(obj){
	return !obj&obj!="0";
}
/**
 * Y转换成是N转换成否
 */
function transYN(str){
		if(str=="Y")
			return "是";
	    if(str=="N")
	    	return "否";
	    else 
		return "";
}
//时间转换 后台时间类型转换成字符类型 年月日 时分秒
function dateFormat(v){
	if(v != null){
		return new Date(v).format("yyyy-MM-dd hh:mm:ss");
	} else return "";
}

//时间转换 后台时间类型转换成字符类型 年月日 
function dateFormatYMD(v){
	if(v != null){
		return new Date(v).format("yyyy-MM-dd");
	} else return "";
}

//时间转换 后台时间类型转换成字符类型 年月日 时分
function dateFormatYMDHM(v){
	if(v != null){
		return new Date(v).format("yyyy-MM-dd hh:mm");
	} else return "";
}

/**
 * 数字转换金额大写
 * @param {} num
 * @return {}
 */
function numToCny(num) {
	var strOutput = "";
	var strUnit = '仟佰拾亿仟佰拾万仟佰拾元角分';
	num += "00";
	var intPos = num.indexOf('.');
	if (intPos >= 0)
		num = num.substring(0, intPos) + num.substr(intPos + 1, 2);
	strUnit = strUnit.substr(strUnit.length - num.length);
	for (var i = 0; i < num.length; i++)
		strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(parseInt(num.substr(i, 1)), 1)
				+ strUnit.substr(i, 1);
	return strOutput.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(
			/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元')
			.replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元");
};


/**
 * 数字格式化
 * 精度默认为2
 * 1234.1234  返回1,234.12
 * @param val
 * @param dec
 * @returns {String}
 */
function formatNum(val, dec) {
	var result = "";
	if(isNum(val)){

		//默认精度为2
		if(!dec){
			dec = 2;
		}
		result = Number(val).toFixed(dec);

		//3个为一位
		var tmp = "";
		result = String(result);
		while ((tmp = result.replace(/^(-?[0-9]+)([0-9]{3}.*)$/, '$1,$2')) !== result){
			result = tmp;
		}

	}
	return result;
}


/**
 * 数字四舍五入，默认精度为2
 * @param val
 * @param dec
 * @returns {String}
 */
function fixNum(val,dec) {
	var result = "0.00";
	if(isNum(val)){

		//默认精度为2
		if(!dec){
			dec = 2;
		}
		result = Number(val).toFixed(dec);

	}
	return result;
}

/**
 * 金钱格式化，精度为4位
 * @param val
 * @returns {String}
 */
function moneyFormat(val){
	return formatNum(val,4);
}



/**
 * 字符串转数字
 * @param {} val
 * @return {}
 */
function strToNum(val){
	var result = 0;
	if(isNum(val)){
		result = Number(val);
	}
	return result;
}


/**
 * 字符串转浮点数
 * @param {} val
 * @return {}
 */
function strToFloat(val){
	var result = 0;
	if(isNum(val)){
		result = parseFloat(val);
	}
	return result;
}

/**
 * 字符串转整数
 * @param {} val
 * @return {}
 */
function strToInt(val){
	var result = 0;
	if(isNum(val)){
		result = parseInt(val);
	}
	return result;
}


/**
 * 转字符串
 * @param obj
 * @return {String}
 */
function toString(obj) {
	if (isEmpty(obj)) {
		return "";
	} else {
		return obj;
	}
};

//判断是否为数字
function isNum(str){
	if(!isEmpty(str) && !isNaN(str) && str != "Infinity"){
		return true;
	}
	return false;
}




/********** 日期函数    ******************/
/**
 * 取得两个时间段的间隔的天数
 * @param {} start
 * @param {} end
 * @return {}
 */
function getBetweenDays(start, end) {
	var format = "Y-m-d";
	var time1 = Date.parseDate(start,format);
	var tiem2 = Date.parseDate(end,format);
	var days = (tiem2.getTime() - time1.getTime()) / 86400000;
	return days;
}


/**
 * 比较时间大小
 * @param {字符串或日期} start
 * @param {字符串或日期} end
 * @return {}
 */
function compareDate(start, end){
	var dateStr1 = start;
	var dateStr2 = end;
	if (start instanceof Date) {
		dateStr1 = start.format('Y-m-d');
	}
	if (end instanceof Date) {
		dateStr2 = end.format('Y-m-d');
	}

    return dateStr1 >= dateStr2;
}

/*********** 正则验证   *****************/

//判断是否为正整数
function isPosInt(str){
	var reg = /^[0-9]*[1-9][0-9]*$/;
	return reg.test(str);
}


/************ js扩展  ************/


/*********  String对象扩展  ************/

//替换所有
String.prototype.replaceAll = function(replaceStr, replaceWith) {
    return this.replace(new RegExp(replaceStr, "gm"), replaceWith);
};

//两端去空格函数
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

//获取字符串字节数
String.prototype.byteLen = function() {
	var len = 0;
    for (var i=0;i<this.length;i++) {
        if (this.charCodeAt(i) > 255){
            len += 2;
        }else{
            len++;
        }
    }
    return len;
};

/**
 * 获取date所在月份的第一天。
 * @returns {Date}
 */
Date.prototype.getFirstDateOfMonth = function(){
	return new Date(this.getFullYear(), this.getMonth(), 1);
}
/**
 * 获取date是月中的第几天
 * @returns
 */
Date.prototype.getDaysInMonth = function() {
    var daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    var m = this.getMonth();
    return m == 1 && this.isLeapYear() ? 29 : daysInMonth[m];
}
/**
 * 获取date所在月份的最后一天
 * @returns {Date}
 */
Date.prototype.getLastDateOfMonth = function() {
    return new Date(this.getFullYear(), this.getMonth(), this.getDaysInMonth());
}
/**
 * 重写日期add方法
 * @returns {Date}
 */
Date.prototype.add = function(interval, value){
	var d = this;
	switch(interval) {
	    case 'milli':
	        d.setMilliseconds(this.getMilliseconds() + value);
	        break;
	    case 'second':
	        d.setSeconds(this.getSeconds() + value);
	        break;
	    case 'minute':
	        d.setMinutes(this.getMinutes() + value);
	        break;
	    case 'hour':
	        d.setHours(this.getHours() + value);
	        break;
	    case 'day':
	        d.setDate(this.getDate() + value);
	        break;
	    case 'month':
	        var day = this.getDate();
	        if (day > 28) {
	            day = Math.min(day, this.getFirstDateOfMonth().add('month', value).getLastDateOfMonth().getDate());
	        }
	        d.setDate(day);
	        d.setMonth(this.getMonth() + value);
	        break;
	    case 'year':
	        d.setFullYear(this.getFullYear() + value);
	        break;
	}
	return d;
}