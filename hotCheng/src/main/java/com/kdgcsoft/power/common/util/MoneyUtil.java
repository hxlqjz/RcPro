package com.kdgcsoft.power.common.util;

import java.text.DecimalFormat;

/**
 * 金额处理类
 * @author lshua
 *
 */
public class MoneyUtil {
	
	public static final String[] unit = { "仟", "佰", "拾", "万", "仟", "佰", "拾",
			"亿", "仟", "佰", "拾", "万", "仟", "佰", "拾", "元" };
	// 数字表示
	public static final char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆',
			'柒', '捌', '玖' };
	
	
	/**
	 * 小写金额 转 大写金额 
	 * @param _amount
	 * @return
	 */
	public static String number2UpperCase(double _amount) {
		double amount = Math.abs(_amount);
		StringBuffer result = new StringBuffer();
		/*
		 * -------- 整数部分 --------
		 */
		StringBuffer result_intStr = new StringBuffer();
		long num = (long) amount;
		// 转成大写
		String intStr = getUpperCase(num);
		int len = intStr.length();

		String regex_01 = "(零.)+";
		String regex_00 = "(零)+";
		String rep_str = "零";
		char zero = '零';

		if (len <= 16) {
			// ====== 仟万亿 ======
			int index = 0;
			for (int i = 16 - len; i < 16; i++) {
				result_intStr.append(intStr.charAt(index++));
				// 加权值
				result_intStr.append(unit[i]);
			}
			// 加权后，未合并连续零
			String temp_intStr = result_intStr.toString();
			int temp_len = temp_intStr.length();

			result_intStr = new StringBuffer();
			if (len >= 13) {
				// 万亿 ,合并连续的 零X ->
				String str = temp_intStr.substring(0, temp_len - 12 * 2)
						.replaceAll(regex_01, rep_str);
				result_intStr.append(str.charAt(str.length() - 1) == zero ? str
						.substring(0, str.length() - 1) + '万' : str);
			}
			if (len >= 9) {
				// 亿 ,合并连续的 零X
				int start = temp_len - 12 * 2 < 0 ? 0 : temp_len - 12 * 2;
				String str = temp_intStr.substring(start, temp_len - 8 * 2)
						.replaceAll(regex_01, rep_str);
				result_intStr.append(str.charAt(str.length() - 1) == zero ? str
						.substring(0, str.length() - 1) + '亿' : str);
			}
			if (len >= 5) {
				// 万 ,合并连续的 零X
				int start = temp_len - 8 * 2 < 0 ? 0 : temp_len - 8 * 2;
				String str = temp_intStr.substring(start, temp_len - 4 * 2)
						.replaceAll(regex_01, rep_str);
				result_intStr.append(str.charAt(str.length() - 1) == zero ? str
						.substring(0, str.length() - 1) + '万' : str);
			}
			if (len >= 1) {
				// 元 ,合并连续的 零X
				int start = temp_len - 4 * 2 < 0 ? 0 : temp_len - 4 * 2;
				String str = temp_intStr.substring(start, temp_len).replaceAll(
						regex_01, rep_str);
				result_intStr.append(str.charAt(str.length() - 1) == zero ? str
						.substring(0, str.length() - 1) + '元' : str);
			}
			// 最后处理
			String last_intStr = result_intStr.toString().replaceAll(regex_00,
					rep_str);
			if (last_intStr.length() == 1) {
				// 元
				last_intStr = zero + last_intStr;
				result_intStr = new StringBuffer(last_intStr);
			} else {
				// XX亿万玖仟元 -> XX亿零玖仟元
				int w_index = last_intStr.lastIndexOf('万');

				if (len >= 9 && w_index > 0
						&& last_intStr.charAt(w_index - 1) == '亿') {
					last_intStr = last_intStr.substring(0, w_index) + zero
							+ last_intStr.substring(w_index + 1);
					result_intStr = new StringBuffer(last_intStr);
				}
			}
		} else {
			// ======= 超过 仟万亿 =======
			result_intStr.append(intStr);
			result_intStr.append("元");
		}
		// 加入结果
		result.append(result_intStr.toString().replaceAll(regex_00,
				String.valueOf(zero)));
		result.append(" ");

		/*
		 * -------- 小数部分(保留二位小数) --------
		 */
		// 小数部分(保留二位小数) 0.01
		double decimal = Double.parseDouble(new DecimalFormat("0.00")
				.format(amount - (double) num));
		if (decimal > 0d) {
			StringBuffer result_floatStr = new StringBuffer();

			String decimalStr = new DecimalFormat("0.00").format(amount - num);

			len = decimalStr.length();
			decimalStr = decimalStr.substring(2, len > 5 ? 4 : len);

			result_floatStr.append(getUpperCase(Long.parseLong(decimalStr
					.substring(0, 1))));
			result_floatStr.append("角");
			result_floatStr.append(getUpperCase(Long.parseLong(decimalStr
					.substring(1, 2))));
			result_floatStr.append("分");

			// 加入结果
			result.append(result_floatStr);
			
		}
		return result.toString().replace(" ", "") + "整";
	}
	
	
	/**
	 * 数字大写
	 * 
	 * @param num
	 *            <li>保持原来排列输出</li>
	 * @return
	 */
	private static String getUpperCase(long num) {

		return getUpperCase(num, true);
	}

	/**
	 * 数字大写
	 * 
	 * @param num
	 * @param is_original
	 *            是否原序 <li>is_original = true : 保持原来排列输出</li> <li>is_original =
	 *            false : 反序输出</li>
	 * @return
	 */
	private static String getUpperCase(long num, boolean is_original) {
		StringBuffer result = new StringBuffer();
		String temp = null;
		if (is_original) {
			temp = String.valueOf(num);
		} else {
			temp = new StringBuffer(String.valueOf(num)).reverse().toString();
		}
		for (int i = 0; i < temp.length(); i++) {
			char c = temp.charAt(i);
			int index = Integer.parseInt(String.valueOf(c));
			result.append(digit[index]);
		}

		return result.toString();
	}
	
	public static void main(String[] args) {
	}

}
