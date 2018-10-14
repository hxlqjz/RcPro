package com.kdgcsoft.power.controller.business.interact;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdgcsoft.power.common.bean.ConflictException;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.service.business.interact.CarStyleService;

/**   
 * @Title: Controller
 * @Description: 车型图片上传的后台代码
 * @date 2017-08-21
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/CarStyle")
public class CarStyleController  extends BaseController {
	
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private CarStyleService carStyleService;
	
	
	/**
	 * @title:品牌条件
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findCarBrandList")
	@ResponseBody
	public JsonMsg findCarBrandList(HttpServletRequest request) throws ConflictException, IOException{
		Map<String,Object> maps = new HashMap<String,Object>();
		 List<Map<String,Object>> ls=carStyleService.findCarBrandList();
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title:车系条件
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findfatoryList")
	@ResponseBody
	public JsonMsg findfatoryList(HttpServletRequest request) throws ConflictException, IOException{
		String carBrand=request.getParameter("carBrand");
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("carBrand", carBrand);
		 List<Map<String,Object>> ls=carStyleService.findCarSysList(carBrand);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title:厂家条件
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findFactorList")
	@ResponseBody
	public JsonMsg findFactorList(HttpServletRequest request) throws ConflictException, IOException{
		String carBrand=request.getParameter("carBrand");
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("carBrand", carBrand);
		 List<Map<String,Object>> ls=carStyleService.findFactorList(carBrand);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title:车型条件
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findCarFormList")
	@ResponseBody
	public JsonMsg findCarFormList(HttpServletRequest request) throws ConflictException, IOException{
	String carBrand=request.getParameter("carBrand");
	String fatory=request.getParameter("fatory");
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("carBrand", carBrand);
		maps.put("fatory", fatory);
		 List<Map<String,Object>> ls=carStyleService.findCarFormList(carBrand, fatory);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * #title：排量条件
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findCarOutputList")
	@ResponseBody
	public JsonMsg findCarOutputList(HttpServletRequest request) throws ConflictException, IOException{
		String carBrand=request.getParameter("carBrand");
		String fatory=request.getParameter("fatory");
		String carForm=request.getParameter("carForm");
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("carBrand", carBrand);
		maps.put("fatory", fatory);
		maps.put("carForm", carForm);
		 List<Map<String,Object>> ls=carStyleService.findCarOutputList(carBrand, fatory, carForm);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title：年款条件
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findCarYearList")
	@ResponseBody
	public JsonMsg findCarYearList(HttpServletRequest request) throws ConflictException, IOException{
		String carBrand=request.getParameter("carBrand");
		String fatory=request.getParameter("fatory");
		String carForm=request.getParameter("carForm");
		String carOutput=request.getParameter("carOutput");
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("carBrand", carBrand);
		maps.put("fatory", fatory);
		maps.put("carForm", carForm);
		maps.put("carOutput", carOutput);
		 List<Map<String,Object>> ls=carStyleService.findCarYearList(carBrand, fatory, carForm, carOutput);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title:上市年份条件
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findMarketYearList")
	@ResponseBody
	public JsonMsg findMarketYearList(HttpServletRequest request) throws ConflictException, IOException{
		String carBrand=request.getParameter("carBrand");
		String fatory=request.getParameter("fatory");
		String carForm=request.getParameter("carForm");
		String carOutput=request.getParameter("carOutput");
		String carYear=request.getParameter("carYear");
		String saleName=request.getParameter("saleName");
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("carBrand", carBrand);
		maps.put("fatory", fatory);
		maps.put("carForm", carForm);
		maps.put("carOutput", carOutput);
		maps.put("carYear", carYear);
		maps.put("saleName", saleName);
		 List<Map<String,Object>> ls=carStyleService.findMarketYearList(carBrand, fatory, carForm, carOutput, carYear,saleName);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @tile:销售名称条件
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findSaleNameList")
	@ResponseBody
	public JsonMsg findSaleNameList(HttpServletRequest request) throws ConflictException, IOException{
		String carBrand=request.getParameter("carBrand");
		String fatory=request.getParameter("fatory");
		String carForm=request.getParameter("carForm");
		String carOutput=request.getParameter("carOutput");
		String carYear=request.getParameter("carYear");
	//	String marketYear=request.getParameter("marketYear");
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("carBrand", carBrand);
		maps.put("fatory", fatory);
		maps.put("carForm", carForm);
		maps.put("carOutput", carOutput);
		maps.put("carYear", carYear);
		//maps.put("marketYear", marketYear);
		 List<Map<String,Object>> ls=carStyleService.findSaleNameList(carBrand, fatory, carForm, carOutput, carYear);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * #title：根据各个条件结果查询检索结果
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/queryCarList")
	@ResponseBody
	public JsonMsg queryCarList(HttpServletRequest request) throws ConflictException, IOException{
		String carBrand=request.getParameter("carBrand");
		String fatory=request.getParameter("fatory");
		String carForm=request.getParameter("carForm");
		String carOutput=request.getParameter("carOutput");
		String carYear=request.getParameter("carYear");
		String produceYear=request.getParameter("produceYear");
		String saleName=request.getParameter("saleName");
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("carBrand", carBrand);
		maps.put("fatory", fatory);
		maps.put("carForm", carForm);
		maps.put("carOutput", carOutput);
		maps.put("carYear", carYear);
		maps.put("produceYear", produceYear);
		maps.put("saleName", saleName);
		 List<Map<String,Object>> ls=carStyleService.queryCarList(carBrand, fatory, carForm, carOutput, carYear, produceYear, saleName);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * #title：根据各个条件结果查询检索结果
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/queryCarGoodByid")
	@ResponseBody
	public JsonMsg queryCarGoodByid(HttpServletRequest request) throws ConflictException, IOException{
		String lyId=request.getParameter("lyId");	
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("lyId", lyId);
		 List<Map<String,Object>> ls=carStyleService.queryCarGoodByid(lyId);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}

}
