package com.kdgcsoft.power.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author lshua
 * 
 * 检测工具类
 * 
 * 正则表达式基本含义
 * ? 匹配前一项 0、1 次
 * + 匹配前一项 1次或多次
 * * 匹配前一项 0次或多次
 * ^ 以什么开始
 * $ 以什么结束
 */
public class ValidUtil {
	

    /**
     * 判断是否为非负整数
     * @param num
     * @return
     */
    public static boolean isNonInt(String str){
	    return checkData("^[0-9]*$",str);  	
    }
    
    /**
     * 是否为整数
     * @param str
     * @return
     */
    public static boolean isInt(String str){
    	return checkData("^-?\\d+$", str);
    }
    
    /**
     * 是否为数字
     * @param str
     * @return
     */
    public static boolean isNum(String str){
	    return checkData("^-?\\d+.?\\d+$",str);  	
    }
	
	/**
	 * 是否为日期 yyyy-MM-dd格式
	 * @param str
	 * @return
	 */
	 public static boolean isDate(String str){
    	String regEx = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))" +
    			       "|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
    	return checkData(regEx,str);  	
    }
	 
	public static boolean checkData(String regEx,String str){
		Pattern pat = Pattern.compile(regEx);  
    	Matcher mat = pat.matcher(str);
    	return mat.matches();
	} 
	 
	public static void main(String[] args) {
	} 

}
