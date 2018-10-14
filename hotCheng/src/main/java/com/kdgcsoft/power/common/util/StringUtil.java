package com.kdgcsoft.power.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 字符串处理类
 * 重要工具类
 * @author lshua
 *
 */
public class StringUtil {
	
	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		return obj == null || "".equals(obj.toString()) || "null".equals(obj) || "NULL".equals(obj) ? true : false;
	}

	
	/**  基本数据类型的转换    ***/
	
	/**
	 * 将空类型转为字符串型
	 * 
	 * @param obj
	 * @return
	 */
	public static String null2String(Object obj) {
		return obj == null || "null".equals(obj) || "NULL".equals(obj) ? "" : obj.toString();
	}

	
	/**
	 * 转为Long类型，空值返回null
	 * 
	 * @param obj
	 * @return
	 */
	public static Long strToLong(Object obj) {
		return isEmpty(obj) ? null : Long.parseLong(obj.toString());
	}

	/**
	 * 转为Int类型
	 * 
	 * @param str
	 * @return
	 */
	public static Integer strToInt(Object obj) {
		return isEmpty(obj) ? null : Integer.parseInt(obj.toString());
	}

	/**
	 * 转为Float类型
	 * 
	 * @param str
	 * @return
	 */
	public static Float strToFloat(Object obj) {
		return isEmpty(obj) ? null : Float.parseFloat(obj.toString());
	}

	/**
	 * 转为Double类型
	 * 
	 * @param str
	 * @return
	 */
	public static Double strToDouble(Object obj) {
		return isEmpty(obj) ? null : Double.parseDouble(obj.toString());
	}
	
	public static Boolean strToBoolean(Object obj){
		return isEmpty(obj) ? false : Boolean.parseBoolean(obj.toString());
	}
	
	public static Character strToChar(Object obj){
		return isEmpty(obj) ? ' ' : (Character)obj;
	}
	
	public static Byte strToByte(Object obj){
		return isEmpty(obj) ? 0 :Byte.parseByte(obj.toString());
	}
	
	public static Short strToShort(Object obj){
		return isEmpty(obj) ? 0 : Short.parseShort(obj.toString());
	}

	/**
	 * 转为long类型
	 * 
	 * @param str
	 * @return
	 */
	public static long str2Long(Object obj) {
		return isEmpty(obj) ? 0 : Long.parseLong(obj.toString());
	}

	/**
	 * 转为int类型
	 * 
	 * @param str
	 * @return
	 */
	public static int str2Int(Object obj) {
		return isEmpty(obj) ? 0 : Integer.parseInt(obj.toString());
	}

	/**
	 * 转为float类型
	 * 
	 * @param str
	 * @return
	 */
	public static float str2Float(Object obj) {
		return isEmpty(obj) ? 0 : Float.parseFloat(obj.toString());
	}

	/**
	 * 转为double类型
	 * 
	 * @param str
	 * @return
	 */
	public static double str2Double(Object obj) {
		return isEmpty(obj) ? 0 : Double.parseDouble(obj.toString());
	}
	
	public static Double str2NullDouble(Object obj) {
		if(isEmpty(obj)){
			return null;
		}else{
			return Double.parseDouble(obj.toString());
		}
	}
	
	public static boolean str2Boolean(Object obj){
		return isEmpty(obj) ? false : Boolean.parseBoolean(obj.toString());
	}
	
	public static char str2Char(Object obj){
		return isEmpty(obj) ? ' ' : (Character)obj;
	}
	
	public static byte str2Byte(Object obj){
		return isEmpty(obj) ? 0 :Byte.parseByte(obj.toString());
	}
	
	public static short str2Short(Object obj){
		return isEmpty(obj) ? 0 : Short.parseShort(obj.toString());
	}

	
	
	
	
	/**
	 * 数组转为 ('a','b')，空返回('') 
	 * 
	 * @param names
	 * @return
	 */
	public static String getStrsplit(String[] names) {
		String rs = "('')";
		if (names != null && names.length > 0) {
			StringBuilder result = new StringBuilder("(");
			for (String string : names) {
				result.append("'" + string.toString() + "',");
			}
			rs = result.substring(0, result.length() - 1) + ")";
		}
		return rs;
	}

	/**
	 * 数组链表转为 ('a','b') ，空返回('')
	 * 
	 * @param names
	 * @return
	 */
	public static String getStrsplit(List<?> names) {
		String rs = "('')";
		if (names != null && !names.isEmpty()) {
			StringBuilder result = new StringBuilder("(");
			for (Object o : names) {
				result.append("'" + o.toString() + "',");
			}
			rs = result.substring(0, result.length() - 1) + ")";
		}
		return rs;
	}


	/**
	 * 
	 * @return 返回调用者的方法名
	 */
	public static String getCurrentMethodName() {
		return new Exception().getStackTrace()[1].getMethodName();
	}
    
	
	public static StringBuffer string2Json(String s) {       
        StringBuffer sb = new StringBuffer ();       
        for (int i=0; i<s.length(); i++) {     
            char c = s.charAt(i);       
            switch (c) {       
            case '\"':       
                sb.append("\\\"");       
                break;       
            case '\\':       
                sb.append("\\\\");       
                break;       
            case '/':       
                sb.append("\\/");       
                break;       
            case '\b':       
                sb.append("\\b");       
                break;       
            case '\f':       
                sb.append("\\f");       
                break;       
            case '\n':       
                sb.append("\\n");       
                break;       
            case '\r':       
                sb.append("\\r");       
                break;       
            case '\t':       
                sb.append("\\t");       
                break;       
            default:       
                sb.append(c);       
            }  
        }  
        return sb;       
    }
	
	
	public static String getLikeStr(String str){
		return getLikeStr(str,"");
	}
	
	public static String getLikeStr(String str,String type){
		if(type.isEmpty()){
			str = "%" + str + "%";
		}else if("L".equals(type)){
			str = "%" + str;
		}else if("R".equals(type)){
			str = str + "%";
		}
		return str;
	}
	

	/**
	 * list转map
	 * @param list
	 * @return
	 */
	public static Map<String, String> listToMap(List<Object[]> list) {
		Map<String, String> map = new HashMap<String, String>();
		for (Object[] obj : list) {
			map.put(null2String(obj[0]), null2String(obj[1]));
		}
		return map;
	}
	
	public static String transYN(String str){
		if("Y".equals(str)){
			return "是";
		}else if("N".equals(str)){
			return "否";
		} else {
			return "";
		}
	}
	

	public static boolean isNotEmpty(Object str) {
		return !StringUtil.isEmpty(str);
	}
	
}
