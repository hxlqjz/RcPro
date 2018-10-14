package com.kdgcsoft.power.common.security.shiro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kdgcsoft.power.common.ResponseData;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.common.security.shiro.FwAuthenticationToken.ModeEnum;
import com.kdgcsoft.power.common.util.AjaxUtil;
import com.kdgcsoft.power.common.util.JSONUtil;
import com.kdgcsoft.power.common.util.MD5Util;

public class FwFormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FwFormAuthenticationFilter.class);
	/** 默认"验证ID"参数名称 */
	private static final String DEFAULT_CAPTCHA_ID_PARAM = "captchaId";
	/** 默认"验证码"参数名称 */
	private static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	/** 默认"组织机构ID"参数名称 */
	private static final String DEFAULT_COMPANY_ID_PARAM = "companyId";
	/** 默认"登录方式"参数名称 */
	private static final String DEFAULT_LOGINMODE_PARAM = "loginMode";

	/** "验证ID"参数名称 */
	private String captchaIdParam = DEFAULT_CAPTCHA_ID_PARAM;
	/** "验证码"参数名称 */
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	/** "组织机构"参数名称 */
	private String companyIdParam = DEFAULT_COMPANY_ID_PARAM;
	/** "登录方式"参数名称 */
	private String loginModeParam = DEFAULT_LOGINMODE_PARAM;

	@Override
	protected org.apache.shiro.authc.AuthenticationToken createToken(ServletRequest servletRequest,
			ServletResponse servletResponse) {
		setUsernameParam("workCode");
		
		String username = getUsername(servletRequest);
		String password = getPassword(servletRequest);
		boolean rememberMe = isRememberMe(servletRequest);
		String captchaId = getCaptchaId(servletRequest);
		String captcha = getCaptcha(servletRequest);
		Long companyId = getCompanyId(servletRequest);
		ModeEnum loginMode = getLoginMode(servletRequest);
		return new FwAuthenticationToken(username, MD5Util.getSaltMd5(password,SystemConstants.PASS_SALT), captchaId, captcha, rememberMe, companyId, loginMode);
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest servletRequest,
			ServletResponse servletResponse) throws Exception {
		if (AjaxUtil.isAjaxRequest((HttpServletRequest) servletRequest)) {// 是ajax请求
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println(JSONUtil.toJSon(ResponseData.SUCCESS_NO_DATA));
			out.flush();
			out.close();
			return false;
		}
		return super.onLoginSuccess(token, subject, servletRequest, servletResponse);
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
			ServletRequest servletRequest, ServletResponse servletResponse) {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		if (AjaxUtil.isAjaxRequest(request)) { // 是ajax请求
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				String message = e.getClass().getSimpleName();
				if ("IncorrectCredentialsException".equals(message)) {
					out.println(JSONUtil.toJSon(ResponseData.error("密码错误")));
				} else if ("UnknownAccountException".equals(message)) {
					out.println(JSONUtil.toJSon(ResponseData.error("账号不存在")));
				} else if ("LockedAccountException".equals(message)) {
					out.println(JSONUtil.toJSon(ResponseData.error("账号被锁定")));
				} else {
					out.println(JSONUtil.toJSon(ResponseData.error("未知错误")));
				}
				out.flush();
				out.close();
			} catch (IOException ioe) {
				LOGGER.error(ioe.getMessage());
			}
			return false;
		}
		return super.onLoginFailure(token, e, request, response);

	}

	@Override
	protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
//		if (AjaxUtil.isAjaxRequest((HttpServletRequest) request)) {// 是ajax请求
//			response.setCharacterEncoding("UTF-8");
//			response.addHeader("loginStatus", "accessDenied");
//			response.sendError(HttpServletResponse.SC_FORBIDDEN);
//			return false;
//		}
		return super.onAccessDenied(request, response);
	}

	/**
	 * 获取验证ID
	 * 
	 * @param servletRequest
	 *            ServletRequest
	 * @return 验证ID
	 */
	protected String getCaptchaId(ServletRequest servletRequest) {
		String captchaId = WebUtils.getCleanParam(servletRequest, captchaIdParam);
		if (captchaId == null) {
			captchaId = ((HttpServletRequest) servletRequest).getSession().getId();
		}
		return captchaId;
	}

	/**
	 * 获取验证码
	 * 
	 * @param servletRequest
	 *            ServletRequest
	 * @return 验证码
	 */
	protected String getCaptcha(ServletRequest servletRequest) {
		return WebUtils.getCleanParam(servletRequest, captchaParam);
	}

	/**
	 * 获取组织机构ID
	 * 
	 * @param servletRequest
	 * @return
	 */
	protected Long getCompanyId(ServletRequest servletRequest) {
		String companyId = WebUtils.getCleanParam(servletRequest, companyIdParam);
		return StringUtils.isNotBlank(companyId) ? Long.valueOf(companyId) : null;
	}

	/**
	 * 获取登录方式
	 * 
	 * @param servletRequest
	 * @return
	 */
	protected ModeEnum getLoginMode(ServletRequest servletRequest) {
		String loginMode = WebUtils.getCleanParam(servletRequest, loginModeParam);
		return StringUtils.isNotBlank(loginMode) ? ModeEnum.valueOf(Integer.valueOf(loginMode)) : ModeEnum.password;
	}

	public String getCaptchaIdParam() {
		return captchaIdParam;
	}

	public void setCaptchaIdParam(String captchaIdParam) {
		this.captchaIdParam = captchaIdParam;
	}

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	public String getCompanyIdParam() {
		return companyIdParam;
	}

	public void setCompanyIdParam(String companyIdParam) {
		this.companyIdParam = companyIdParam;
	}

}
