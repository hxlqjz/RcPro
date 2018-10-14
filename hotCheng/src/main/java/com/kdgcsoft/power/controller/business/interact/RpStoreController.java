package com.kdgcsoft.power.controller.business.interact;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.CacheManager;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kdgcsoft.power.common.bean.ConflictException;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.entity.business.interact.RpStore;
import com.kdgcsoft.power.service.business.interact.RpStoreService;
import com.xiaoleilu.hutool.util.CollectionUtil;
/**
 * 门店
 * @author lenovo
 * 
 */
@Controller
@RequestMapping("/store")
public class RpStoreController extends BaseController{
	   private static CacheManager cacheManager= CacheManager.create();  
	
		@Autowired
		private RpStoreService rpStoreService;
		@Autowired
		private SQLManager sqlManager ;
 	
 	/**
 	 * 查找门店表信息
 	 * @param request
 	 * @param storename门店名 	idCode识别码
 	 * @return
 	 */
 	@RequestMapping(value = "/getList",method = RequestMethod.POST)
 	@ResponseBody
 	public Object getStoreList(HttpServletRequest request, Integer page, Integer rows){
 		if(page==null){
			page=1;	
		}
		if(rows==null){
			rows=20;	
		}
		PageRequest pageRequest = new PageRequest(page , rows);
		PageObject<Map<String,Object>> result=new PageObject<Map<String,Object>> ();	
 		String storeName = request.getParameter("storeName");
 		String idCode = request.getParameter("idCode");
 		
 		if(StringUtil.isEmpty(storeName)){
 			storeName = null;
 		}
 		if(StringUtil.isEmpty(idCode)){
 			idCode = null;
 		}
 		List<Map<String,Object>> ls = rpStoreService.getList(storeName,idCode,pageRequest);
 		List<Map<String,Object>>rs= CollectionUtil.sub(ls, (page-1)*rows, page*rows);
		result.setTotalCount((long)ls.size());	
		result.setList(rs);
		return getPageModel(result);
 	};
 	/**
 	 * 根据id查找门店表信息
 	 * @param request
 	 * @param id
 	 * @return
 	 */
 	@RequestMapping(value = "/getInfo")
 	@ResponseBody
 	public Object getInfo(HttpServletRequest request){
 		String id = request.getParameter("id");
 		RpStore store = rpStoreService.getInfo(Long.parseLong(id));
 		Map<String,Object> maps = new HashMap<String,Object>();
 		maps.put("res", store);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
 	};
 	/**
 	 * 根据idCode查找门店表信息
 	 * @param request
 	 * @param id
 	 * @return
 	 */
 	@RequestMapping(value = "/getInfoByIdCode")
 	@ResponseBody
 	public Object getInfoByIdCode(HttpServletRequest request){
 		String idCode = request.getParameter("idCode");
 		RpStore store = rpStoreService.getInfoByIdCode(idCode);
 		Map<String,Object> maps = new HashMap<String,Object>();
 		maps.put("res", store);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
 	};
 	
 	/**
 	 * 删除门店信息
 	 * @param request
 	 * @param id 主键
 	 * @return
 	 * @throws ConflictException
 	 * @throws IOException
 	 */
 	@RequestMapping(value = "/del")
	@ResponseBody
	public JsonMsg saveSysCUser(HttpServletRequest request) throws ConflictException, IOException{
 		String id = request.getParameter("id");
 		rpStoreService.delete(Long.parseLong(id));
		return new JsonMsg(true,SystemConstants.MSG_DELETE_SUCCESS,null);
	}
 /**
  * 新增或修改门店信息
  * @param request
  * @param entity
  * @return
  */
 	@RequestMapping("/save")
 	@ResponseBody
	public JsonMsg save(HttpServletRequest request,RpStore entity){
		Long id = entity.getId();
		if( id != null ){
			rpStoreService.update(entity);
		}else{
			rpStoreService.insert(entity);
		}
		
		return new JsonMsg(true,SystemConstants.MSG_SAVE_SUCCESS,null);
	}
 	
 	@RequestMapping(value = "/loadInfo")
	public ModelAndView loadInfo(String id, HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		RpStore entity = new RpStore();
		if(!StringUtil.isEmpty(id)){//修改
			entity = rpStoreService.getInfo(Long.parseLong(id));	
		}
		model.addObject("entity", entity);
		model.setViewName("view/business/rpStoreForm.jsp");
		return model;
	}
}
