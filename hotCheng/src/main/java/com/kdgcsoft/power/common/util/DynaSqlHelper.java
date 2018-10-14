package com.kdgcsoft.power.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于辅助动态拼SQL的工具类，简化代码，并可设置自动在子句间添加空格。
 * 拼SQL过程采用StringBuffer并优化了内存使用。
 *
 * @author hling
 *
 */
public class DynaSqlHelper {

	private static final Logger logger = LoggerFactory.getLogger(DynaSqlHelper.class);

	// --------------------------处理动态SQL子句的工具---------------------------------------//
	private static final int PARAM = 0;
	private static final int SQL = 1;

	private List<Object[]> dynaParams = new ArrayList<Object[]>();
	private boolean isAutoAddSpace;
	private int sqlLen = 0;

	/**
	 * 创建一个用于处理动态SQL子句的辅助工具对象。在处理大量动态子句时用该工具辅助生成子句和参数能简化代码。
	 * 
	 * @return 工具对象
	 */
	public static DynaSqlHelper create() {
		return new DynaSqlHelper(true);
	}

	/**
	 * 创建一个SQL参数和子句的辅助处理器。
	 * 
	 * @param autoAddSpace
	 *            是否自动在子句之间添加空格。默认为true：添加空格。
	 * @return 处理器对象。
	 */
	public static DynaSqlHelper create(boolean autoAddSpace) {
		return new DynaSqlHelper(autoAddSpace);
	}

	public DynaSqlHelper() {
		isAutoAddSpace = true;
	}

	public DynaSqlHelper(boolean autoAddSpace) {
		isAutoAddSpace = autoAddSpace;
	}

	/**
	 * 根据条件，决定是否把SQL子句加入最后的SQL中。
	 * 如果SQL子句中有"?"字符，则把condition作为SQL的参数保存，否则视为该子句不需要参数
	 * @param condition 判断条件：<p>
	 *      如果是字符串型，当为空时不加入SQL <p>
	 *      如果是Booean型，当为False时不加入SQL <p>
	 *      如果以上都不是，则为null时不加入SQL
	 * @param sqlSegment SQL子句
	 * @return DynaSQL实例
	 */
	public DynaSqlHelper add(Object condition, String sqlSegment) {
		if (sqlSegment.contains("?")) {
			// 如果SQL子句中有？，则把条件对象本身作为参数使用
			return  add(condition, sqlSegment, new Object[]{condition});
		} else {
			// 如果SQL子句中没有？，则视为不需要参数
			return  add(condition, sqlSegment, new Object[]{null});
		}
		
	}

	/**
	 * 根据条件，决定是否把SQL子句加入最后的SQL中，并使用param作为参数
	 * @param condition 判断条件：<p>
	 *      如果是字符串型，当为空时不加入SQL <p>
	 *      如果是Booean型，当为False时不加入SQL <p>
	 *      如果以上都不是，则为null时不加入SQL
	 * @param sqlSegment SQL子句
	 * @param param SQL子句的参数
	 * @return DynaSQL实例
	 */
	public DynaSqlHelper add(Object condition, String sqlSegment, Object param) {
		return  add(condition, sqlSegment, new Object[]{param});
	}

	/**
	 * 根据条件，决定是否把SQL子句加入最后的SQL中，并使用params数组作为参数
	 * 主要用于处理一些特殊情况，例如做全文检索时，用来判断的变量与最终传递给SQL的变量不同，
	 * 后者会加上%，并随着匹配模式不同，可能需要多个变量。而子句是否有效的判断条件始终只基于一个变量。
	 * @param condition 判断条件：<p>
	 *      如果是字符串型，当为空时不加入SQL <p>
	 *      如果是Booean型，当为False时不加入SQL <p>
	 *      如果以上都不是，则为null时不加入SQL
	 * @param sqlSegment
	 *            SQL子句
	 * @param params
	 *            作为SQL语句参数的对象列表。
	 * @return
	 */
	public DynaSqlHelper add(Object condition, String sqlSegment, Object... params) {
		if (condition == null || sqlSegment == null) {
			// 条件为null，则不添加动态参数
			return this;
		}

		// 条件为空字符串，也视为不输出sql子句
		if (condition instanceof String) {
			if (((String) condition).isEmpty()) {
				return this;
			}
		} else if (condition instanceof Boolean) {
			if (!(Boolean) condition) {
				return this;
			}
		}

		// SQL子句中的问号数量，代表该子句所需要的参数数量
		int paramNeed = StringUtils.countMatches(sqlSegment, "?");
		int paramHave = 0;

		if (params.length == 0) {
			// 如果没有参数列表，则视为不需要参数
			Object[] arr = new Object[2];
			arr[PARAM] = null;
			arr[SQL] = sqlSegment;
			dynaParams.add(arr);
			// +1是考虑自动添加一个空格作为子句的间隔
			sqlLen += sqlSegment.length() + (isAutoAddSpace ? 1 : 0);
			paramHave = 0;
		} else {
			// 把参数列表中的参数加入到动态参数中。此时的condition仅作为判断条件使用。
			// 一个子句有多个参数
			for (int i = 0; i < params.length; i++) {
				Object[] arr = new Object[2];
				arr[PARAM] = params[i];
				if (params[i] != null) {
					// 实际有效的参数计数。用于检查与SQL子句中的?是否相符
					paramHave++;
				}
				// SQL子句只添加一次
				if (i == 0) {
					arr[SQL] = sqlSegment;
					sqlLen += sqlSegment.length() + (isAutoAddSpace ? 1 : 0);
				}
				dynaParams.add(arr);
			}
		}

		if (paramNeed != paramHave) {
			logger.warn("SQL子句中的\"？\"数量与传入的参数数量不符，有可能存在错误。SQL子句为{}", sqlSegment);
		}
		return this;
	}

	/**
	 * 输出所有SQL子句的拼接字符串，并把需要的参数对象写入参数列表中
	 * 
	 * @param outParams
	 *            传入的参数列表对象，不能为null。
	 * @return 各个SQL子句拼接后的字符串。根据create()时的设定，子句之间可以自动添加一个空格。
	 */
	public StringBuffer build(final List<Object> outParams) {

		if (dynaParams == null) {
			return new StringBuffer(1);
		}

		// 根据预先计算好的长度一次性分配正好大小的内存，减少内存分配次数
		StringBuffer buffer = new StringBuffer(sqlLen + 1);
		// 添加首个空格
		if (isAutoAddSpace) {
			buffer.append(" ");
		}

		for (Object[] arr : dynaParams) {
			if (arr[PARAM] != null) {
				outParams.add(arr[PARAM]);
			}
			if (arr[SQL] != null) {
				// 末尾自动添加一个空格，减少程序员忘记添加空格时出错的可能性
				// 理论上有可能在特殊的拼sql过程中导致错误，例如sql中的字符串常量也是拼出来的时候，
				// 会导致拼出来的字符串常量中多了个空格。因此需要在初始化时由程序员指定是否自动添加空格
				buffer.append(arr[SQL]);
				if (isAutoAddSpace) {
					// 每个子句后添加空格
					buffer.append(" ");
				}
			}
		}

		return buffer;
	}

	public static void main(String[] args) {
		// 测试动态SQL子句辅助工具类。在处理大量动态子句时能简化代码。
		List<Object> params = new ArrayList<Object>();

		String sql = "select * from t where 1=1";
		System.out.println("固定SQL为：\t" + sql);
		String paramA = "aaa";
		String paramB = null;
		String paramC = "ccc";
		String paramD = "ddd";

		params.clear();
		sql += DynaSqlHelper.create()
				// 判断条件与子句需要的参数不一样。下例中判断条件是一个字符串，而实际参数为两个加了%后的字符串
				.add(paramA, "and (t.aaa like ? or t.aaa like ?)", "%" + paramA + "%", "%" + paramA.toUpperCase() + "%")
				// SQL中有问号，判断条件会成为最终使用的参数
				.add(paramB, "and t.bbb = ?")
				// 判断条件中没有问号，则不传递参数
				.add(paramC, "and t.c > 1")
				// 判断条件为null，子句被忽略
				.add(paramC, "and t.ccc = ? ")
				// 有判断条件，但SQL子句不需要参数
				.add(paramD, "and t.type = 4 ", new Object[] { null }).build(params);
		System.out.println("添加子句后的SQL为：\t" + sql);
		System.out.println("参数列表为：\t" + params);
	}

}
