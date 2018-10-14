package com.kdgcsoft.power.controller.fw.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.kdgcsoft.power.common.bean.PageModel;
import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.bean.Principal;
import com.kdgcsoft.power.common.support.CustomDateEditor;
import com.kdgcsoft.power.common.support.CustomLongEditor;
import com.kdgcsoft.power.entity.fw.system.SysCRole;
import com.kdgcsoft.power.service.fw.base.DictService;

/**
 * 所有Controller的基类 
 *
 */     
public abstract class BaseController {

	protected final Logger logger;
	@Autowired
	public CacheManager cacheManager;
	
	@Autowired
	protected DictService dictService;
	
	public BaseController() {
		this.logger=LoggerFactory.getLogger(this.getClass());
	}
	public Principal getEmpSession(){
		return  (Principal)SecurityUtils.getSubject().getPrincipal();
	}
	/**
	 * 获取当前登录人的所有角色
	 * @return
	 */
    public List<String> getRoleCodes(){
    	List<String> roleCodes = new ArrayList<String>();
		Principal principal = getEmpSession();
		List<SysCRole> roles = principal.getRoleList();
		for (SysCRole sysCRole : roles) {
			roleCodes.add(sysCRole.getRoleCode());
		}
		return roleCodes;
    }
	@InitBinder
	public void initBinderBase(WebDataBinder binder) {		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
		binder.registerCustomEditor(Long.class, new CustomLongEditor(true));
	}
	/**
	 * 将原系统PageObject转换成easyui识别的数据
	 * @param <T>
	 * @param obj
	 * @return
	 */
	public  <T> PageModel getPageModel(PageObject<T> obj){
		if(obj!=null && obj.getList()!=null){
			return new PageModel(obj.getTotalCount(), obj.getList());
		}
		return new PageModel(0L, Collections.emptyList());
	}
}
