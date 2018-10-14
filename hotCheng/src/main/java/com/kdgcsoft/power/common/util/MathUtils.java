package com.kdgcsoft.power.common.util;

import java.math.BigDecimal;

public final class MathUtils {

	/**
	 * 功能 : 比较两个double类型的数在保留指定小数位数后是否相等
	 * 开发：zwwang 2015-4-26 下午8:59:10
	 * @param double1
	 * @param double2
	 * @param scale double类型的精度
	 * @return
	 */
	public static Boolean compareDouble(double double1,double double2, int scale) {
		BigDecimal data1 = new BigDecimal(String.valueOf(double1));
		BigDecimal data2 = new BigDecimal(String.valueOf(double2));
		return data1.setScale(scale, BigDecimal.ROUND_DOWN).equals(data2.setScale(scale, BigDecimal.ROUND_DOWN));
	}
	
	/**
	 * 功能 : 比较两个String类型的数转换为Double类型保留指定小数位数后是否相等
	 * 开发：zwwang 2015-4-26 下午8:59:10
	 * @param strng1
	 * @param strng2
	 * @param scale double类型的精度
	 * @return
	 */
	public static Boolean compareString(String strng1,String string2, int scale) {
		BigDecimal data1 = new BigDecimal(strng1);
		BigDecimal data2 = new BigDecimal(string2);
		return data1.setScale(scale, BigDecimal.ROUND_DOWN).equals(data2.setScale(scale, BigDecimal.ROUND_DOWN));
	}
	
	
	/**
	 * 功能 : 两数相除
	 * 开发：zwwang 2015-6-18 下午2:58:29
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static String divide(Double v1, Double v2) {
		if(v1 == null || v2 == null || compareString("0.0", v2.toString(), 1)){
			return "0";
		}
		return String.valueOf(v1/v2); 
	}
	
	public static Double convertPrecision(String data, int scale) {
		BigDecimal bg = new BigDecimal(data);
		return bg.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 去除字符串后面的零
	 * @param strData
	 * @return
	 */
	public static BigDecimal stripTrailingZeros(String strData){
		return new BigDecimal(strData).stripTrailingZeros();
	}
	
}
