package com.kdgcsoft.power.common.security.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 登录令牌
 * 
 * @author BridgeBai
 */
public class FwAuthenticationToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 5898441540965086534L;
	/** 登录方式*/
	public enum ModeEnum {
		 /** 密码方式 */
		 password,
		 /** UKey网关 */
		 agent;
		 
		 public static ModeEnum valueOf(int ordinal) {
		        if (ordinal < 0 || ordinal >= values().length) {
		            throw new IndexOutOfBoundsException("Invalid ordinal");
		        }
		        return values()[ordinal];
		   }
	}
	
	/** 验证码ID */
	private String captchaId;

	/** 验证码 */
	private String captcha;
	
	/** 登录单位ID*/
	private Long companyId;
	/** 登录方式*/
	private ModeEnum loginMode;
	/**
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param captchaId
	 *            验证码ID
	 * @param captcha
	 *            验证码
	 * @param rememberMe
	 *            记住我
	 * @param String orgId 
	 *            登录单位ID
	 */
	public FwAuthenticationToken(String username, String password, String captchaId, String captcha, boolean rememberMe, Long companyId,ModeEnum loginMode) {
		super(username, password, rememberMe);
		this.captchaId = captchaId;
		this.captcha = captcha;
		this.companyId=companyId;
		this.loginMode=loginMode;
	}

	/**
	 * 获取验证码ID
	 * 
	 * @return 验证码ID
	 */
	public String getCaptchaId() {
		return captchaId;
	}

	/**
	 * 设置验证码ID
	 * 
	 * @param captchaId
	 *            验证码ID
	 */
	public void setCaptchaId(String captchaId) {
		this.captchaId = captchaId;
	}

	/**
	 * 获取验证码
	 * 
	 * @return 验证码
	 */
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * 设置验证码
	 * 
	 * @param captcha
	 *            验证码
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	/**
	 * 获取登录单位ID
	 * 
	 * @return 单位ID
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 设置单位ID
	 * 
	 * @param orgId
	 *            单位ID
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public ModeEnum getLoginMode() {
		return loginMode;
	}

	public void setLoginMode(ModeEnum loginMode) {
		this.loginMode = loginMode;
	}

	
}