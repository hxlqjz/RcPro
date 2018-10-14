package com.kdgcsoft.power.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * sql处理类
 */
public class SqlUtil {
	/**
	 * 获取sql语句from后面的语句
	 * @param sql
	 * @return
	 */
	public static String getCountSql(final String sql){
		String quickSql = removeLastOrderBy(sql);
		return  "select count(1) from (" + quickSql + ") t";
	}
	
	/**
	 * 去掉SQL中最后的Order by子句，用于在某些不需要最终排序的情况下下提高查询性能。
	 * 如果SQL中有多个order by，只去除最后一个。
	 * @param sql SQL语句
	 * @return 去除Order By之后的sql语句
	 */
	public static String removeLastOrderBy(final String sql) {
		int start = StringUtils.lastIndexOfIgnoreCase(sql, "order by ");
		if(start <0){
			return sql;
		}
		int pos = start + "order by ".length();
		int level = 0;
		boolean canRemove = true;
		while(pos < sql.length()) {
			char ch = sql.charAt(pos);
			if (ch == '(') {
				level++;
			}
			if (ch == ')') {
				level--;
			}
			
			if (ch == ':' || ch == '?') {
				// order字段为变量(形如 ?1 或 :name)时，不能去除
				canRemove = false;
				break;
			}
			
			if (level == -1) {
				break;
			}
			
			pos++;
		}
		
		if (canRemove) {
			StringUtils.overlay(sql, null, start, pos);
			String quickSql  = StringUtils.overlay(sql, null, start, pos);
			return quickSql;
		} else {
			return sql;
		}
	}

	public static void main(String[] args) {
		// 测试删除order by子句
		String sql = "select * from a ";
		System.out.println(removeLastOrderBy(sql));

		sql = "select * from a order by a, to_char(b)";
		System.out.println(removeLastOrderBy(sql));
		
		sql = "select count(1) from (select * from a, (select * from b order by ccc) b where a.c1 = b.c2 order by c1, c2, to_char(b.c))";
		System.out.println(removeLastOrderBy(sql));

		sql = "select count(1) from order by ?1, ?2";
		System.out.println(removeLastOrderBy(sql));
		
	}
 
}
