/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.kdgcsoft.power.common.util;

import java.security.Principal;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;


/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-12-05
 */
public class UserUtils {

	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static Principal getUser(){
		return 	(Principal)SecurityUtils.getSubject().getSession().getAttribute("principal");
	}
	
	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			if (principal != null){
				return principal;
			}
		}catch (UnavailableSecurityManagerException e) {
			
		}catch (InvalidSessionException e){
			
		}
		return null;
	}

}
