package com.kdgcsoft.power.common.util;

import java.util.HashMap;
import java.util.Map;

public class DBPropertyUtil {
	static char[] a_z = "abcdefghijklmnopqrstwvuxyz".toCharArray();
	static char[] A_Z = "abcdefghijklmnopqrstwvuxyz".toUpperCase().toCharArray();
	
	// 列名转换前后的映射缓存。因只增不减，不需要考虑多线程
	private static Map<String, String> namesCache = new HashMap<String, String>();

	public static void main(String[] args) {
		System.out.println(columnToProperty2("a_bb_cc"));
	}
	
	/**
	 * 表明转换为实体名
	 */
	public static String tblToEntity(String tablename) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (tablename == null || tablename.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!tablename.contains("_")) {
			// 不含下划线，仅将首字母小写
			return tablename.substring(0, 1).toUpperCase()+ tablename.substring(1);
		} else {
			// 用下划线将原始字符串分割
			String[] columns = tablename.split("_");
			for (String columnSplit : columns) {
				// 跳过原始字符串中开头、结尾的下换线或双重下划线
				if (columnSplit.isEmpty()) {
					continue;
				}
				// 处理真正的驼峰片段
				
			// 其他的驼峰片段，首字母大写
			result.append(columnSplit.substring(0, 1).toUpperCase()).append(columnSplit.substring(1).toLowerCase());
			}
			return result.toString();
		}

	}
	
	
	/**
	 * 数据库字段名（下划线分隔）转换为Class的属性名（驼峰，首字母小写）
	 */
	public static String columnToProperty2(String column) {
		// 快速检查
		if (column == null || column.isEmpty()) {
			// 没必要转换
			return "";
		}

		if (namesCache.containsKey(column)) {
			return namesCache.get(column);
		}

		String result = null;
		if (!column.contains("_")) {
			// 不含下划线，仅将首字母小写
			result = column.toLowerCase();
		} else {
			StringBuilder buf = new StringBuilder();
			// 用下划线将原始字符串分割
			String[] columns = column.split("_");
			for (String columnSplit : columns) {
				// 跳过原始字符串中开头、结尾的下换线或双重下划线
				if (columnSplit.isEmpty()) {
					continue;
				}
				// 处理真正的驼峰片段
				if (buf.length() == 0) {
					// 第一个驼峰片段，全部字母都小写
					buf.append(columnSplit.toLowerCase());
				} else {
					// 其他的驼峰片段，首字母大写
					buf.append(columnSplit.substring(0, 1).toUpperCase())
							.append(columnSplit.substring(1).toLowerCase());
				}
			}
			result = buf.toString();
		}
		
		// 缓存转换后的列名
		namesCache.put(column, result);
		 
		return result;
	}
	
	/**
	 * 功能：获得列的数据类型
	 * @param sqlType
	 * @return
	 */
	public static String sqlType2JavaType(String sqlType) {
		
		if("binary_double".equalsIgnoreCase(sqlType)){
			return "Double";
		}else if("binary_float".equalsIgnoreCase(sqlType)){
			return "float";
		}else if("blob".equalsIgnoreCase(sqlType)){
			return "byte[]";
		}else if("clob".equalsIgnoreCase(sqlType)){
			return "byte[]";
		}else if("char".equalsIgnoreCase(sqlType) || "nvarchar2".equalsIgnoreCase(sqlType) 
				|| "varchar2".equalsIgnoreCase(sqlType)){
			return "String";
		}else if("date".equalsIgnoreCase(sqlType) || "timestamp".equalsIgnoreCase(sqlType)
				 || "timestamp with local time zone".equalsIgnoreCase(sqlType) 
				 || "timestamp with time zone".equalsIgnoreCase(sqlType)){
			return "Date";
		}else if("number".equalsIgnoreCase(sqlType)){
			return "Long";
		}
		
		return "String";
	}

}