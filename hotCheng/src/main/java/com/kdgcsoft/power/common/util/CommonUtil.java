package com.kdgcsoft.power.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kdgcsoft.power.common.bean.Principal;

public class CommonUtil {
	
	private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	public static String getComCode(HttpServletRequest request,Principal principal){
		String comCode = request.getParameter("comCode");
		if (StringUtil.isEmpty(comCode)) {
			comCode = principal.getOrgCode();
		}
		return comCode;
	}
	
	public static String subStr(String str, String split, int itervel,
			String columnName) {
		StringBuilder returnStr = new StringBuilder("(");
		String[] s = str.split(split);
		String tr = "";
		for (int i = 0; i < s.length; i++) {
			tr += s[i] + ",";
			if ((i + 1) % itervel == 0) {
				tr = tr.substring(0, tr.length() - 1);
				returnStr.append(" " + columnName + " in(" + tr + ") or");
				tr = "";
			}
		}
		if (tr.length() > 0) {
			tr = tr.substring(0, tr.length() - 1);
			returnStr.append(" " + columnName + " in(" + tr + "))");
		} else {
			returnStr = new StringBuilder(returnStr.toString().substring(0, returnStr.length() - 3) + ")");
		}
		return returnStr.toString();
	}
	
	
	
	public static void writeXml(HttpServletResponse response,String outStr) {
		try {
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write( outStr);
			writer.flush();
			writer.close();
		} catch (IOException exc) {
			logger.error("BaseController的writeXml()方法错误!",exc);
		}
	}
	
	public static void writeDoc(HttpServletResponse response, byte[] data) {
		try {
			response.setContentType("text/doc;charset=utf-8");
			PrintWriter writer = response.getWriter();
			for (int i = 0; i < data.length; i++) {
				writer.write(data[i]);
			}
			writer.flush();
			writer.close();
		} catch (IOException exc) {
			logger.error("BaseController的writedoc()方法错误!",exc);		
			}
	}
	
	
	public static void write(HttpServletResponse response,String outStr) {
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(outStr);
			writer.flush();
			writer.close();
		} catch (IOException exc) {
			logger.error("BaseController的write()方法错误!",exc);
		}
	}

}
