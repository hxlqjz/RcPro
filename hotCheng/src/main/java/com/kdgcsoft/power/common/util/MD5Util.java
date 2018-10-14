package com.kdgcsoft.power.common.util;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Util {
	public static final Logger logger = LoggerFactory.getLogger(MD5Util.class);
	
	public static final String GetMD5Str16(String str) {
		String result = GetMD5Str32(str);
		if (result != null) {
			return result.substring(8, 24);
		} else {
			return null;
		}
	}

    /**
     * MD5加密
     * @param str
     * @return
     */
	private static final String GetMD5Str32(String str) {
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = str.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char[] hexBuf = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				hexBuf[k++] = hexDigits[byte0 >>> 4 & 0xF];
				hexBuf[k++] = hexDigits[byte0 & 0xF];
			}
			return new String(hexBuf);
		} catch (Exception localException) {
			logger.info("加密失败",localException);
		}
		// FIXME 应该抛出异常，不应该返回null
		return null;
	}
	public static final String getSaltMd5(String str,String salt){
		String first = GetMD5Str32(str + salt);
		if (first != null) {
			return GetMD5Str32(first);
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(getSaltMd5("123","gcadmin"));
	}
}