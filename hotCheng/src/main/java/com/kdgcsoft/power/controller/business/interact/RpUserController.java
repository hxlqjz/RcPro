package com.kdgcsoft.power.controller.business.interact;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.kdgcsoft.power.entity.business.interact.RpReg;
import com.kdgcsoft.power.entity.business.interact.RpStore;
import com.kdgcsoft.power.entity.business.interact.RpUser;
import com.kdgcsoft.power.service.business.interact.RpRegService;
import com.kdgcsoft.power.service.business.interact.RpStoreService;
import com.kdgcsoft.power.service.business.interact.RpUserService;
import com.xiaoleilu.hutool.util.CollectionUtil;

import net.sf.ehcache.CacheManager;
/**
 * 门店
 * @author lenovo
 *
 */ 
@Controller
@RequestMapping("/user")
public class RpUserController extends BaseController{
	   private static CacheManager cacheManager= CacheManager.create();  
	
		@Autowired
		private RpUserService rpUserService;
		@Autowired
		private SQLManager sqlManager ;
		
		@Autowired
		private RpStoreService rpStoreService;
		@Autowired
		private RpRegService rpRegService;
 	
 	/**
 	 * 查询用户信息
 	 * @param request
 	 * @param name模糊匹配用户名和微信号 	idCode识别码
 	 * @return
 	 */
 	@RequestMapping(value = "/getList",method = RequestMethod.POST)
 	@ResponseBody
 	public Object getList(HttpServletRequest request, Integer page, Integer rows){
 		String name = request.getParameter("name");
 		String idCode = request.getParameter("idCode");
 		PageRequest pageRequest = new PageRequest(page , rows);
		PageObject<Map<String,Object>> result=new PageObject<Map<String,Object>> ();	
		
		if(StringUtil.isEmpty(name)){
			name = null;
 		}
 		if(StringUtil.isEmpty(idCode)){
 			idCode = null;
 		}
 		List<Map<String,Object>> ls = rpUserService.getList(name,idCode,pageRequest);
 		List<Map<String,Object>>rs= CollectionUtil.sub(ls, (page-1)*rows, page*rows);
		result.setTotalCount((long)ls.size());	
		result.setList(rs);
		return getPageModel(result);
 	};
 	/**
 	 * 根据id查找用户信息
 	 * @param request
 	 * @param id
 	 * @return
 	 */
 	@RequestMapping(value = "/getInfo")
 	@ResponseBody
 	public Object getInfo(HttpServletRequest request){
 		String id=request.getParameter("id");
 		RpUser user=rpUserService.getInfo(Long.parseLong(id));
 		Map<String,Object> maps = new HashMap<String,Object>();
 		maps.put("res", user);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
 	};
 	
 	/**
 	 * 删除信息
 	 * @param request
 	 * @param id 主键
 	 * @return
 	 * @throws ConflictException
 	 * @throws IOException
 	 */
 	@RequestMapping(value = "/del")
	@ResponseBody
	public JsonMsg saveSysCUser(HttpServletRequest request) throws ConflictException, IOException{
 		String id=request.getParameter("id");
 		rpUserService.delete(Long.parseLong(id));
		return new JsonMsg(true,SystemConstants.MSG_DELETE_SUCCESS,null);
	}
 /**
  * 新增或修改
  * @param request
  * @param entity
  * @return
  */
 	@RequestMapping("/save")
 	@ResponseBody
	public JsonMsg save(HttpServletRequest request,RpUser entity){
		Long id = entity.getId();
		if( id != null ){
			rpUserService.update(entity);
		}else{
			rpUserService.insert(entity);
		}
		
		return new JsonMsg(true,SystemConstants.MSG_SAVE_SUCCESS,null);
	}
 	
 	/**
 	 * 微信端使用的接口
 	 */
 	
 	/**
 	 * 根据微信号查询用户信息
 	 * @param wechatNo
 	 * @return
 	 */
 	@RequestMapping("/getInfoByWechat")
 	@ResponseBody
 	public RpUser getInfoByWechat(String wechatNo){
 		if( wechatNo != null ){
 			RpUser en=rpUserService.getInfoByWechat(wechatNo);
			return  en;
		}else{
			return null;
		}
		
 	}
 	
 	@RequestMapping(value = "/loadInfo")
	public ModelAndView loadInfo(String id, HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		RpUser entity = new RpUser();
		String type = "add";
		if(!StringUtil.isEmpty(id)){//修改
			type = "edit";
			entity = rpUserService.getInfo(Long.parseLong(id));	
		}
		model.addObject("entity", entity);
		model.addObject("type", type);
		model.setViewName("view/business/rpUserForm.jsp");
		return model;
	}
 	/**微信注册用户
 	 * 用户输入识别码，判断识别码是否存在
 	 * 不存在则提示没有该数据
 	 * 存在，则往用户表中添加数据，微信号，识别号，queryPower和rolePower从门店表中带过来
 	 * @param request
 	 * @param entity
 	 * @return
 	 */
 	@RequestMapping("/reg")
 	@ResponseBody
	public JsonMsg reg(HttpServletRequest request){
 		String wechatNo = request.getParameter("wechatNo");
 		String nickName = request.getParameter("nickName");
 		String idCode = request.getParameter("idCode");
 		RpStore s = rpStoreService.getInfoByIdCode(idCode);
 		if(s != null){
 			rpUserService.reg(wechatNo, nickName, idCode);
 			return new JsonMsg(true,"注册成功",null);
 		}else{
 			RpReg entity = rpRegService.getInfo(wechatNo);
 			if(entity == null){
 				RpReg r = new RpReg();
 				r.setWechatNo(wechatNo);
 				rpRegService.insert(r);
 			}else{
 				rpRegService.update(entity);
 			}
 			entity = rpRegService.getInfo(wechatNo);
 			return new JsonMsg(false,"请查询后输入识别码",entity);
 		}
		
	}
 	@RequestMapping("/checkNum")
 	@ResponseBody
	public Object checkNum(HttpServletRequest request){
 		String wechatNo = request.getParameter("wechatNo");
 		RpReg entity = rpRegService.getInfo(wechatNo);
 		return entity;
		
	}
 	@RequestMapping("/getUsersByStore")
 	@ResponseBody
 	public Object getUsersByStore(HttpServletRequest request){
 		String wechatNo = request.getParameter("wechatNo");
 		if(StringUtil.isEmpty(wechatNo)){
 			wechatNo = null;
 		}
 		List<RpUser> list = rpUserService.getUsersByStore(wechatNo);
 		return list;
 	}
 	
}
