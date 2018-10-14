package com.kdgcsoft.power.controller.business.interact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdgcsoft.power.common.bean.ConflictException;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.bean.PageModel;
import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.service.business.interact.WetdataService;
import com.ustcinfo.bwp.service.startflow.util.StringUtils;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**   
 * @Title: Controller
 * @Description: ngk de品牌相关的后台代码
 * @date 2017-08-21
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/nbe")
public class NbeController  extends BaseController {
	
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private WetdataService wetdataService;
	
	/**
	 * 保存主页面的数据
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findNgkBrandList")
	@ResponseBody
	public JsonMsg findNgkBrandList(HttpServletRequest request) throws ConflictException, IOException{
		Map<String,Object> maps = new HashMap<String,Object>();
		 List<Map<String,Object>> ls=wetdataService.findNgkBrandList();
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	/**
	 * findCarFormListByBrand
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findCarFormListByBrand")
	@ResponseBody
	public JsonMsg findCarFormListByBrand(HttpServletRequest request) throws ConflictException, IOException{
	String brand=request.getParameter("brand");
		Map<String,Object> maps = new HashMap<String,Object>();
		 List<Map<String,Object>> ls=wetdataService.findCarFormListByBrand(brand);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	/**
	 * findCarFormListByBrandAndType
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findCarFormListByBrandAndType")
	@ResponseBody
	public JsonMsg findCarFormListByBrandAndType(HttpServletRequest request) throws ConflictException, IOException{
		String brand=request.getParameter("brand");
		String type=request.getParameter("type");
		
		Map<String,Object> maps = new HashMap<String,Object>();
		 List<Map<String,Object>> ls=wetdataService.findCarFormListByBrandAndType(brand,type);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	/**
	 * findCarListByBrandAndTypeAndOutPut
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findCarListByBrandAndTypeAndOutPut")
	@ResponseBody
	public JsonMsg findCarListByBrandAndTypeAndOutPut(HttpServletRequest request) throws ConflictException, IOException{
		String brand=request.getParameter("brand");
		String type=request.getParameter("type");
		String output=request.getParameter("output");
		Map<String,Object> maps = new HashMap<String,Object>();
		 List<Map<String,Object>> ls=wetdataService.findCarListByBrandAndTypeAndOutPut(brand,type,output);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	/**
	 * findCarListByBrandAndTypeAndOutPutAndYear
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findCarListByBrandAndTypeAndOutPutAndYear")
	@ResponseBody
	public JsonMsg findCarListByBrandAndTypeAndOutPutAndYear(HttpServletRequest request) throws ConflictException, IOException{
		String brand=request.getParameter("brand");
		String type=request.getParameter("type");
		String output=request.getParameter("output");
		String year=request.getParameter("year");
		Map<String,Object> maps = new HashMap<String,Object>();
		 List<Map<String,Object>> ls=wetdataService.findCarListByBrandAndTypeAndOutPutAndYear(brand,type,output,year);
		 List<Map<String,Object>>  recLs=new ArrayList<Map<String,Object>>(); 
		 for(Map<String,Object> map:ls){
			 Map<String,Object> en=new HashMap<String,Object>();
			 en.put("pping", map.get("pping"));
			 en.put("xche", map.get("xche"));
			 en.put("lpai", map.get("lpai"));
			 en.put("bban", map.get("bban"));
			 en.put("xh", map.get("xh1"));		
			 recLs.add(en);//默认把型号1的数据给recLs
			 if(map.get("xh2")!=null && !StringUtils.isEmpty(map.get("xh2"))){		
				  en=new HashMap<String,Object>();
				 en.put("pping", map.get("pping"));
				 en.put("xche", map.get("xche"));
				 en.put("lpai", map.get("lpai"));
				 en.put("bban", map.get("bban"));	
				 en.put("xh", map.get("xh2"));//
				 recLs.add(en);				 
			 }
		 }
		 maps.put("res", recLs);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * 分页查询数据,带分页和模糊搜索
	 */
	@RequestMapping(value="/searchNgkPageList",method = RequestMethod.POST)
	@ResponseBody
	public PageModel searchNgkPageList(HttpServletRequest request, Integer page, Integer rows){
		if(page==null){
			page=1;	
		}
		if(rows==null){
			rows=20;	
		}
		String brand=request.getParameter("brand");
		String type=request.getParameter("type");
		String output=request.getParameter("output");
		String year=request.getParameter("year");

		PageObject<Map<String,Object>> result=new PageObject<Map<String,Object>> ();		
		List<Map<String,Object>> ls=wetdataService.findCarListByBrandAndTypeAndOutPutAndYear(brand,type,output,year);
	
		
		List<Map<String,Object>>rs= CollectionUtil.sub(ls, (page-1)*rows, page*rows);
		result.setTotalCount((long)ls.size());	
		result.setList(rs);
		return getPageModel(result);
	}


}
