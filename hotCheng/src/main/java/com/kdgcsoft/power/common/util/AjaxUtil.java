package com.kdgcsoft.power.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * ajax请求的工具类
 * 
 * @author BridgeBai
 *
 */
public class AjaxUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxUtil.class);
	/**
	 * 判断当前请求是否是ajax请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		return (requestedWith != null) ? "XMLHttpRequest".equalsIgnoreCase(requestedWith) : false;
	}

	/**
	 * 将请求参数以Map对象形式返回
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		// 把请求中的参数取出
		Map<String, String[]> reqParams = request.getParameterMap();
		Set<Entry<String, String[]>> entries = reqParams.entrySet();
		for (Iterator<Entry<String, String[]>> iterator = entries.iterator(); iterator.hasNext();) {
			Map.Entry<String, String[]> entry = iterator.next();
			String name = (String) entry.getKey();
			String[] value = (String[]) entry.getValue();
			if (value != null) {
				if (value.length == 1) {
					paraMap.put(name, value[0]);
				} else {
					paraMap.put(name, value);
				}
			}
		}
		return paraMap;
	}

	/**
	 * 获取用户客户端IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "本地";
		}
		return ip;
	}

	/**
	 * 获取应用的完整URL地址
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	public static String getAppbaseUrl(HttpServletRequest request, String url) {
		return request.getContextPath() + url;
	}

	/**
	 * 添加cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            有效期(单位: 秒)
	 * @param path
	 *            路径
	 * @param domain
	 *            域
	 * @param secure
	 *            是否启用加密
	 */
	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
			Integer maxAge, String path, String domain, Boolean secure) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			value = URLEncoder.encode(value, "UTF-8");
			Cookie cookie = new Cookie(name, value);
			if (maxAge != null) {
				cookie.setMaxAge(maxAge);
			}
			if (StringUtils.isNotEmpty(path)) {
				cookie.setPath(path);
			}
			if (StringUtils.isNotEmpty(domain)) {
				cookie.setDomain(domain);
			}
			if (secure != null) {
				cookie.setSecure(secure);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			logger.error("保存失败",e);
		}
	}

	public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		addCookie(request, response, name, value, null, "/", null, null);
	}

	/**
	 * 获取cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            cookie名称
	 * @return 若不存在则返回null
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		Assert.notNull(request);
		Assert.hasText(name);
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			try {
				name = URLEncoder.encode(name, "UTF-8");
				for (Cookie cookie : cookies) {
					if (name.equals(cookie.getName())) {
						return URLDecoder.decode(cookie.getValue(), "UTF-8");
					}
				}
			} catch (UnsupportedEncodingException e) {
				logger.error("获取失败",e);
			}
		}
		return null;
	}

	/**
	 * 移除cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            cookie名称
	 * @param path
	 *            路径
	 * @param domain
	 *            域
	 */
	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path,
			String domain) {
		Assert.notNull(request);
		Assert.notNull(response);
		Assert.hasText(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
			Cookie cookie = new Cookie(name, null);
			cookie.setMaxAge(0);
			if (StringUtils.isNotEmpty(path)) {
				cookie.setPath(path);
			}
			if (StringUtils.isNotEmpty(domain)) {
				cookie.setDomain(domain);
			}
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			logger.error("删除失败",e);
		}
	}

}