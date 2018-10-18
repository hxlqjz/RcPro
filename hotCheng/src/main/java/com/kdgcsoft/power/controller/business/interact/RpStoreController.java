package com.kdgcsoft.power.controller.business.interact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.CacheManager;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.entity.business.interact.RpStore;
import com.kdgcsoft.power.service.business.interact.RpStoreService;

/**
 * 微信端使用的接口
 * 
 * @author lenovo
 * 
 */
@Controller
@RequestMapping("/store")
public class RpStoreController extends BaseController {
	private static CacheManager cacheManager = CacheManager.create();

	@Autowired
	private RpStoreService rpStoreService;
	@Autowired
	private SQLManager sqlManager;

	/**
	 * 根据idCode查找门店表信息
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getInfoByIdCode")
	@ResponseBody
	public Object getInfoByIdCode(HttpServletRequest request) {
		String idCode = request.getParameter("idCode");
		RpStore store = rpStoreService.getInfoByIdCode(idCode);
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("res", store);
		return new JsonMsg(true, SystemConstants.MSG_SUCCESS, maps);
	};

	/**
	 * 获取所有门店标识码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getIdCodeList")
	@ResponseBody
	public Object getIdCodeList(HttpServletRequest request) {
		List<RpStore> list = rpStoreService.getIdCodeList();
		return list;
	}
}
