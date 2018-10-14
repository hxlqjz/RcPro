package com.kdgcsoft.power.controller.business.interact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdgcsoft.power.common.bean.ConflictException;
import com.kdgcsoft.power.common.bean.JsonMsg;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.controller.fw.base.BaseController;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.service.business.interact.CarStyleSublistService;

/**   
 * @Title: Controller
 * @Description: 根据品牌查询车型
 * @date 2018年8月28日08:05:04
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/carStyleSublist")
public class CarStyleSublistController  extends BaseController {

	@Autowired
	private BeetlSQLHelper bsh;
	
	@Autowired
	private CarStyleSublistService carStyleSublistService;
	
		
	/**
	 * 根据品牌查询车型
	 */
	@RequestMapping(value="/findCarFormList",method = RequestMethod.POST)
	@ResponseBody
	  public JsonMsg findCarFormList(String carBrand){		  
		  List<Map<String, Object>> ls= carStyleSublistService.findCarFormList(carBrand);
		  List<Map<String, Object>> rec=new ArrayList<Map<String, Object>>();
		  List<String> carFormLs=new ArrayList<String>();//每个厂家对应的车型
		  Map<String, Object> en=new HashMap<String, Object>();		
		  for(int i=0;i<ls.size();i++){
			  Map<String, Object> map=ls.get(i);
			   if(!en.containsKey("fatory")){
				   en.put("fatory", map.get("fatory"));
			    }
			   if(en.get("fatory").equals(map.get("fatory"))){		
				   if(!carFormLs.contains(map.get("carForm").toString())){
					   carFormLs.add(map.get("carForm").toString()); 
				   }					 
					  en.put("carFormLs", carFormLs);
			   }else{				   
				    rec.add(en);
				    en=new HashMap<String, Object>();	
				    carFormLs=new ArrayList<String>();			
				     if(!carFormLs.contains(map.get("carForm").toString())){
						   carFormLs.add(map.get("carForm").toString()); 
					   }
				    en.put("carFormLs", carFormLs);				  	  				  				  
			   }
			   if(i==ls.size()-1){
				   rec.add(en);
				}			  
		  }
		  
		 
		  
			JsonMsg msg=new JsonMsg(true, "操作成功！",rec);			
			return msg;
	  }
	
	/**
	 * @title:查询排量数据
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findCarOutputList",method = RequestMethod.POST)
	@ResponseBody
	public JsonMsg findCarOutputList(HttpServletRequest request) throws ConflictException, IOException{
		Map<String,Object> maps = new HashMap<String,Object>();
		String carBrand=request.getParameter("carBrand");
		String carForm=request.getParameter("carForm");
		 List<Map<String,Object>> ls=carStyleSublistService.findCarOutputList(carBrand,carForm);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title:查询年款数据
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findCarYearList",method = RequestMethod.POST)
	@ResponseBody
	public JsonMsg findCarYearList(HttpServletRequest request) throws ConflictException, IOException{
		Map<String,Object> maps = new HashMap<String,Object>();
		String carBrand=request.getParameter("carBrand");
		String carForm=request.getParameter("carForm");
		String outputTl=request.getParameter("outputTl");
		 List<Map<String,Object>> ls=carStyleSublistService.findCarYearList(carBrand,carForm,outputTl);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title:查询平台数据
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findChassisList",method = RequestMethod.POST)
	@ResponseBody
	public JsonMsg findChassisList(HttpServletRequest request) throws ConflictException, IOException{
		Map<String,Object> maps = new HashMap<String,Object>();
		String carBrand=request.getParameter("carBrand");
		String carForm=request.getParameter("carForm");
		String outputTl=request.getParameter("outputTl");
		String carYear=request.getParameter("carYear");
		 List<Map<String,Object>> ls=carStyleSublistService.findChassisList(carBrand,carForm,outputTl,carYear);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title:查询发动机型号数据
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findEngineTypeList",method = RequestMethod.POST)
	@ResponseBody
	public JsonMsg findEngineTypeList(HttpServletRequest request) throws ConflictException, IOException{
		Map<String,Object> maps = new HashMap<String,Object>();
		String carBrand=request.getParameter("carBrand");
		String carForm=request.getParameter("carForm");
		String outputTl=request.getParameter("outputTl");
		String carYear=request.getParameter("carYear");
		String chassis=request.getParameter("chassis");
		 List<Map<String,Object>> ls=carStyleSublistService.findEngineTypeList(carBrand,carForm,outputTl,carYear,chassis);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	

	/**
	 * @title:查询变速箱数据
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findShiftMemoList",method = RequestMethod.POST)
	@ResponseBody
	public JsonMsg findShiftMemoList(HttpServletRequest request) throws ConflictException, IOException{
		Map<String,Object> maps = new HashMap<String,Object>();
		String carBrand=request.getParameter("carBrand");
		String carForm=request.getParameter("carForm");
		String outputTl=request.getParameter("outputTl");
		String carYear=request.getParameter("carYear");
		String chassis=request.getParameter("chassis");
		String engineType=request.getParameter("engineType");
		 List<Map<String,Object>> ls=carStyleSublistService.findShiftMemoList(carBrand,carForm,outputTl,carYear,chassis,engineType);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title:查询轮毂规格数据
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findPreffixTyreScaleList",method = RequestMethod.POST)
	@ResponseBody
	public JsonMsg findPreffixTyreScaleList(HttpServletRequest request) throws ConflictException, IOException{
		Map<String,Object> maps = new HashMap<String,Object>();
		String carBrand=request.getParameter("carBrand");
		String carForm=request.getParameter("carForm");
		String outputTl=request.getParameter("outputTl");
		String carYear=request.getParameter("carYear");
		String chassis=request.getParameter("chassis");
		String engineType=request.getParameter("engineType");
		String shiftMemo=request.getParameter("shiftMemo");
		 List<Map<String,Object>> ls=carStyleSublistService.findPreffixTyreScaleList(carBrand,carForm,outputTl,carYear,chassis,engineType,shiftMemo);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title:查询销售名称数据
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findSaleNameList",method = RequestMethod.POST)
	@ResponseBody
	public JsonMsg findSaleNameList(HttpServletRequest request) throws ConflictException, IOException{
		Map<String,Object> maps = new HashMap<String,Object>();
		String carBrand=request.getParameter("carBrand");
		String carForm=request.getParameter("carForm");
		String outputTl=request.getParameter("outputTl");
		String carYear=request.getParameter("carYear");
		String chassis=request.getParameter("chassis");
		String engineType=request.getParameter("engineType");
		String shiftMemo=request.getParameter("shiftMemo");
		String preffixTyreScale=request.getParameter("preffixTyreScale");
		 List<Map<String,Object>> ls=carStyleSublistService.findSaleNameList(carBrand,carForm,outputTl,carYear,chassis,engineType,shiftMemo,preffixTyreScale);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title:查询生成年份数据
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findProduceYearList",method = RequestMethod.POST)
	@ResponseBody
	public JsonMsg findProduceYearList(HttpServletRequest request) throws ConflictException, IOException{
		Map<String,Object> maps = new HashMap<String,Object>();
		String carBrand=request.getParameter("carBrand");
		String carForm=request.getParameter("carForm");
		String outputTl=request.getParameter("outputTl");
		String carYear=request.getParameter("carYear");
		String chassis=request.getParameter("chassis");
		String engineType=request.getParameter("engineType");
		String shiftMemo=request.getParameter("shiftMemo");
		String preffixTyreScale=request.getParameter("preffixTyreScale");
		String saleName=request.getParameter("saleName");
		 List<Map<String,Object>> ls=carStyleSublistService.findProduceYearList(carBrand,carForm,outputTl,carYear,chassis,engineType,shiftMemo,preffixTyreScale,saleName);
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title:根据条件检索产品
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findProducesByOpt",method = RequestMethod.POST)
	@ResponseBody
	public JsonMsg findProducesByOpt(HttpServletRequest request) throws ConflictException, IOException{
		Map<String,Object> maps = new HashMap<String,Object>();
		String carBrand=request.getParameter("carBrand");
		String carForm=request.getParameter("carForm");
		String fatory=request.getParameter("fatory");
		String outputTl=request.getParameter("outputTl");
		String carYear=request.getParameter("carYear");
		String chassis=request.getParameter("chassis");
		String engineType=request.getParameter("engineType");
		String shiftMemo=request.getParameter("shiftMemo");
		String preffixTyreScale=request.getParameter("preffixTyreScale");
		String saleName=request.getParameter("saleName");
		String produceYear=request.getParameter("produceYear");
		 List<Map<String,Object>> ls=carStyleSublistService.findProducesByOpt(carBrand,carForm,fatory,outputTl,carYear,chassis,engineType,shiftMemo,preffixTyreScale,saleName,produceYear);		 
		 maps.put("res", ls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title:根据条件检索产品
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findTheProducts",method = RequestMethod.POST)
	@ResponseBody
	public JsonMsg findTheProducts(HttpServletRequest request) throws ConflictException, IOException{
		Map<String,Object> maps = new HashMap<String,Object>();
		String carBrand=request.getParameter("carBrand");
		String carForm=request.getParameter("carForm");
		String fatory=request.getParameter("fatory");
		String outputTl=request.getParameter("outputTl");
		String carYear=request.getParameter("carYear");
		String chassis=request.getParameter("chassis");
		String engineType=request.getParameter("engineType");
		String shiftMemo=request.getParameter("shiftMemo");
		String preffixTyreScale=request.getParameter("preffixTyreScale");
		String saleName=request.getParameter("saleName");
		String produceYear=request.getParameter("produceYear");
		 List<Map<String,Object>> preBrakels=carStyleSublistService.findPreBrakeByOpt(carBrand,carForm,fatory,outputTl,carYear,chassis,engineType,shiftMemo,preffixTyreScale,saleName,produceYear);
		 List<Map<String,Object>> sufBrakels=carStyleSublistService.findSufBrakeByOpt(carBrand,carForm,fatory,outputTl,carYear,chassis,engineType,shiftMemo,preffixTyreScale,saleName,produceYear);
		 List<Map<String,Object>> pre_bf_Bls=new ArrayList<Map<String,Object>>();
		 List<Map<String,Object>> suf_bf_Bls=new ArrayList<Map<String,Object>>();
		 List<Map<String,Object>> pre_bds_Bls=new ArrayList<Map<String,Object>>();
		 List<Map<String,Object>> suf_bds_Bls=new ArrayList<Map<String,Object>>();
		 //前制动
		 for(Map<String,Object> map:preBrakels){			
			
			 if(map.get("fBfBrake1")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBfBrake", map.get("fBfBrake1"));
				 pre_bf_Bls.add(en);				 
			 }
			 if(map.get("fBfBrake2")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBfBrake", map.get("fBfBrake2"));
				 pre_bf_Bls.add(en);				 
			 }
			 if(map.get("fBfBrake3")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBfBrake", map.get("fBfBrake3"));
				 pre_bf_Bls.add(en);				 
			 }
			 if(map.get("fBfBrake4")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBfBrake", map.get("fBfBrake4"));
				 pre_bf_Bls.add(en);				 
			 }
			 if(map.get("fBfBrake5")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBfBrake", map.get("fBfBrake5"));
				 pre_bf_Bls.add(en);				 
			 }
			 if(map.get("fBdsBrake1")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBdsBrake", map.get("fBdsBrake1"));
				 pre_bds_Bls.add(en);				 
			 }
			 if(map.get("fBdsBrake2")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBdsBrake", map.get("fBdsBrake2"));
				 pre_bds_Bls.add(en);				 
			 }
			 if(map.get("fBdsBrake3")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBdsBrake", map.get("fBdsBrake3"));
				 pre_bds_Bls.add(en);				 
			 }
			 
		 }
		 
		 //后制动
		 for(Map<String,Object> map:sufBrakels){			
			
			 if(map.get("rBfBrake1")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBfBrake", map.get("rBfBrake1"));
				 suf_bf_Bls.add(en);				 
			 }
			 if(map.get("rBfBrake2")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBfBrake", map.get("rBfBrake2"));
				 suf_bf_Bls.add(en);				 
			 }
			 if(map.get("rBfBrake3")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBfBrake", map.get("rBfBrake3"));
				 suf_bf_Bls.add(en);				 
			 }
			 if(map.get("rBfBrake4")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBfBrake", map.get("rBfBrake4"));
				 suf_bf_Bls.add(en);				 
			 }
			 if(map.get("rBfBrake5")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBfBrake", map.get("rBfBrake5"));
				 suf_bf_Bls.add(en);				 
			 }
			 if(map.get("rBdsBrake1")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBdsBrake", map.get("rBdsBrake1"));
				 suf_bds_Bls.add(en);				 
			 }
			 if(map.get("rBdsBrake2")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBdsBrake", map.get("rBdsBrake2"));
				 suf_bds_Bls.add(en);				 
			 }
			 if(map.get("rBdsBrake3")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBdsBrake", map.get("rBdsBrake3"));
				 suf_bds_Bls.add(en);				 
			 }
			 
		 }
		 
		 List<Map<String,Object>> oils=carStyleSublistService.findOilsByOpt(carBrand,carForm,fatory,outputTl,carYear,chassis,engineType,shiftMemo,preffixTyreScale,saleName,produceYear);
	     List<String>	oilArr=new ArrayList<String>();	
		 List<Map<String,Object>> rhls=new ArrayList<Map<String,Object>>();
		 List<Map<String,Object>> bsxls=new ArrayList<Map<String,Object>>();
		
		 //油品
		 for(Map<String,Object> map:oils){			
			
			 if(map.get("avia1")!=null &&  StringUtils.isNoneBlank(map.get("avia1").toString()) && !oilArr.contains(map.get("avia1").toString()) ){
				 oilArr.add(map.get("avia1").toString());
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("avia", map.get("avia1"));
				 en.put("oilFill", map.get("oilFill"));//机油保养加注量
				 en.put("jybz", map.get("jybz"));//机油备注
				 rhls.add(en);				 
			 }
			 if(map.get("avia2")!=null && StringUtils.isNoneBlank(map.get("avia2").toString()) && !oilArr.contains(map.get("avia2").toString())){
				 oilArr.add(map.get("avia2").toString());
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("avia", map.get("avia2"));
				 en.put("oilFill", map.get("oilFill"));//机油保养加注量
				 en.put("jybz", map.get("jybz"));//机油备注
				 rhls.add(en);				 
			 }
			 if(map.get("avia3")!=null && StringUtils.isNoneBlank(map.get("avia3").toString()) && !oilArr.contains(map.get("avia3").toString()) ){
				 oilArr.add(map.get("avia3").toString());
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("avia", map.get("avia3"));
				 en.put("oilFill", map.get("oilFill"));//机油保养加注量
				 en.put("jybz", map.get("jybz"));//机油备注
				 rhls.add(en);				 
			 }
			 if(map.get("avia4")!=null &&  StringUtils.isNoneBlank(map.get("avia4").toString()) && !oilArr.contains(map.get("avia4").toString())){
				 oilArr.add(map.get("avia4").toString());
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("avia", map.get("avia4"));
				 en.put("oilFill", map.get("oilFill"));//机油保养加注量
				 en.put("jybz", map.get("jybz"));//机油备注
				 rhls.add(en);				 
			 }
			
			 if(map.get("aviaBsxypXh")!=null &&  StringUtils.isNoneBlank(map.get("aviaBsxypXh").toString()) && !oilArr.contains(map.get("aviaBsxypXh").toString())){
				 oilArr.add(map.get("aviaBsxypXh").toString());
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("aviaBsxypXh", map.get("aviaBsxypXh"));//AVIA变速箱油品型号
				 en.put("bsxbz", map.get("bsxbz"));//变速箱备注
				 bsxls.add(en);				 
			 }
			 			 
		 }
		 
		 List<Map<String,Object>> batteryls=carStyleSublistService.findBatterysByOpt(carBrand,carForm,fatory,outputTl,carYear,chassis,engineType,shiftMemo,preffixTyreScale,saleName,produceYear);
		 List<Map<String,Object>> dcls=new ArrayList<Map<String,Object>>();
		 //电池
		 for(Map<String,Object> map:batteryls){						
			 if(map.get("batteryProduct1")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("batteryProduct", map.get("batteryProduct1"));
				 en.put("batteryRemark", map.get("batteryRemark"));//电池备注			
				 dcls.add(en);				 
			 }
			 if(map.get("batteryProduct2")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("batteryProduct", map.get("batteryProduct2"));
				 en.put("batteryRemark", map.get("batteryRemark"));//电池备注							
				 dcls.add(en);				 
			 }			 			 
		 }
		 List<Map<String,Object>> sparkls=carStyleSublistService.findSparksByOpt(carBrand,carForm,fatory,outputTl,carYear,chassis,engineType,shiftMemo,preffixTyreScale,saleName,produceYear);
		 List<Map<String,Object>> hhsls=new ArrayList<Map<String,Object>>();
		 //电池
		 for(Map<String,Object> map:sparkls){						
			 if(map.get("hhsProduct1")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("hhsProduct", map.get("hhsProduct1"));
				 en.put("hhsBz", map.get("hhsBz"));//火花塞备注			
				 hhsls.add(en);				 
			 }
			 if(map.get("hhsProduct2")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("hhsProduct", map.get("hhsProduct2"));
				 en.put("hhsBz", map.get("hhsBz"));//火花塞备注							
				 hhsls.add(en);				 
			 }			 			 
		 }
		 
		 //热骋产品
		 List<Map<String,Object>> rcls=carStyleSublistService.findRcByOpt(carBrand,carForm,fatory,outputTl,carYear,chassis,engineType,shiftMemo,preffixTyreScale,saleName,produceYear);
		 maps.put("rcls", rcls.subList(0, 1));
		 maps.put("hhsls", hhsls);
		 maps.put("dcls", dcls);
		 maps.put("rhls", rhls);
		 maps.put("bsxls", bsxls);				 
		 maps.put("pre_bf_Bls", pre_bf_Bls);
		 maps.put("pre_bds_Bls", pre_bds_Bls);
		 maps.put("suf_bf_Bls", suf_bf_Bls);
		 maps.put("suf_bds_Bls", suf_bds_Bls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	
	/**
	 * @title:根据条件检索产品
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findTheProductsByIds",method = RequestMethod.POST)
	@ResponseBody
	public JsonMsg findTheProductsByIds(HttpServletRequest request) throws ConflictException, IOException{
		Map<String,Object> maps = new HashMap<String,Object>();
		String lyIds=request.getParameter("lyIds");		
		 List<Map<String,Object>> preBrakels=carStyleSublistService.findPreBrakeByIds(lyIds);
		 List<Map<String,Object>> sufBrakels=carStyleSublistService.findSufBrakeByIds(lyIds);
		 List<Map<String,Object>> pre_bf_Bls=new ArrayList<Map<String,Object>>();
		 List<Map<String,Object>> suf_bf_Bls=new ArrayList<Map<String,Object>>();
		 List<Map<String,Object>> pre_bds_Bls=new ArrayList<Map<String,Object>>();
		 List<Map<String,Object>> suf_bds_Bls=new ArrayList<Map<String,Object>>();
		 //前制动
		 for(Map<String,Object> map:preBrakels){			
			
			 if(map.get("fBfBrake1")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBfBrake", map.get("fBfBrake1"));
				 pre_bf_Bls.add(en);				 
			 }
			 if(map.get("fBfBrake2")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBfBrake", map.get("fBfBrake2"));
				 pre_bf_Bls.add(en);				 
			 }
			 if(map.get("fBfBrake3")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBfBrake", map.get("fBfBrake3"));
				 pre_bf_Bls.add(en);				 
			 }
			 if(map.get("fBfBrake4")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBfBrake", map.get("fBfBrake4"));
				 pre_bf_Bls.add(en);				 
			 }
			 if(map.get("fBfBrake5")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBfBrake", map.get("fBfBrake5"));
				 pre_bf_Bls.add(en);				 
			 }
			 if(map.get("fBdsBrake1")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBdsBrake", map.get("fBdsBrake1"));
				 pre_bds_Bls.add(en);				 
			 }
			 if(map.get("fBdsBrake2")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBdsBrake", map.get("fBdsBrake2"));
				 pre_bds_Bls.add(en);				 
			 }
			 if(map.get("fBdsBrake3")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("fBdsBrake", map.get("fBdsBrake3"));
				 pre_bds_Bls.add(en);				 
			 }
			 
		 }
		 
		 //后制动
		 for(Map<String,Object> map:sufBrakels){			
			
			 if(map.get("rBfBrake1")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBfBrake", map.get("rBfBrake1"));
				 suf_bf_Bls.add(en);				 
			 }
			 if(map.get("rBfBrake2")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBfBrake", map.get("rBfBrake2"));
				 suf_bf_Bls.add(en);				 
			 }
			 if(map.get("rBfBrake3")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBfBrake", map.get("rBfBrake3"));
				 suf_bf_Bls.add(en);				 
			 }
			 if(map.get("rBfBrake4")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBfBrake", map.get("rBfBrake4"));
				 suf_bf_Bls.add(en);				 
			 }
			 if(map.get("rBfBrake5")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBfBrake", map.get("rBfBrake5"));
				 suf_bf_Bls.add(en);				 
			 }
			 if(map.get("rBdsBrake1")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBdsBrake", map.get("rBdsBrake1"));
				 suf_bds_Bls.add(en);				 
			 }
			 if(map.get("rBdsBrake2")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBdsBrake", map.get("rBdsBrake2"));
				 suf_bds_Bls.add(en);				 
			 }
			 if(map.get("rBdsBrake3")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("rBdsBrake", map.get("rBdsBrake3"));
				 suf_bds_Bls.add(en);				 
			 }
			 
		 }
		 
		 List<Map<String,Object>> oils=carStyleSublistService.findOilsByIds(lyIds);
		 List<String>	oilArr=new ArrayList<String>();	
		 List<Map<String,Object>> rhls=new ArrayList<Map<String,Object>>();
		 List<Map<String,Object>> bsxls=new ArrayList<Map<String,Object>>();
		
		 //油品
		 for(Map<String,Object> map:oils){			
				
			 if(map.get("avia1")!=null &&  StringUtils.isNoneBlank(map.get("avia1").toString()) && !oilArr.contains(map.get("avia1").toString()) ){
				 oilArr.add(map.get("avia1").toString());
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("avia", map.get("avia1"));
				 en.put("oilFill", map.get("oilFill"));//机油保养加注量
				 en.put("jybz", map.get("jybz"));//机油备注
				 rhls.add(en);				 
			 }
			 if(map.get("avia2")!=null && StringUtils.isNoneBlank(map.get("avia2").toString()) && !oilArr.contains(map.get("avia2").toString())){
				 oilArr.add(map.get("avia2").toString());
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("avia", map.get("avia2"));
				 en.put("oilFill", map.get("oilFill"));//机油保养加注量
				 en.put("jybz", map.get("jybz"));//机油备注
				 rhls.add(en);				 
			 }
			 if(map.get("avia3")!=null && StringUtils.isNoneBlank(map.get("avia3").toString()) && !oilArr.contains(map.get("avia3").toString()) ){
				 oilArr.add(map.get("avia3").toString());
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("avia", map.get("avia3"));
				 en.put("oilFill", map.get("oilFill"));//机油保养加注量
				 en.put("jybz", map.get("jybz"));//机油备注
				 rhls.add(en);				 
			 }
			 if(map.get("avia4")!=null &&  StringUtils.isNoneBlank(map.get("avia4").toString()) && !oilArr.contains(map.get("avia4").toString())){
				 oilArr.add(map.get("avia4").toString());
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("avia", map.get("avia4"));
				 en.put("oilFill", map.get("oilFill"));//机油保养加注量
				 en.put("jybz", map.get("jybz"));//机油备注
				 rhls.add(en);				 
			 }
			
			 if(map.get("aviaBsxypXh")!=null &&  StringUtils.isNoneBlank(map.get("aviaBsxypXh").toString()) && !oilArr.contains(map.get("aviaBsxypXh").toString())){
				 oilArr.add(map.get("aviaBsxypXh").toString());
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("aviaBsxypXh", map.get("aviaBsxypXh"));//AVIA变速箱油品型号
				 en.put("bsxbz", map.get("bsxbz"));//变速箱备注
				 bsxls.add(en);				 
			 }
			 			 
		 }
		 List<Map<String,Object>> batteryls=carStyleSublistService.findBatterysByIds(lyIds);
		 List<Map<String,Object>> dcls=new ArrayList<Map<String,Object>>();
		 //电池
		 for(Map<String,Object> map:batteryls){						
			 if(map.get("batteryProduct1")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("batteryProduct", map.get("batteryProduct1"));
				 en.put("batteryRemark", map.get("batteryRemark"));//电池备注			
				 dcls.add(en);				 
			 }
			 if(map.get("batteryProduct2")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("batteryProduct", map.get("batteryProduct2"));
				 en.put("batteryRemark", map.get("batteryRemark"));//电池备注							
				 dcls.add(en);				 
			 }			 			 
		 }
		 List<Map<String,Object>> sparkls=carStyleSublistService.findSparksByIds(lyIds);
		 List<Map<String,Object>> hhsls=new ArrayList<Map<String,Object>>();
		 //电池
		 for(Map<String,Object> map:sparkls){						
			 if(map.get("hhsProduct1")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("hhsProduct", map.get("hhsProduct1"));
				 en.put("hhsBz", map.get("hhsBz"));//火花塞备注			
				 hhsls.add(en);				 
			 }
			 if(map.get("hhsProduct2")!=null){
				 Map<String,Object>  en=new HashMap<String,Object>(); 
				 en.put("lyId", map.get("lyId"));
				 en.put("hhsProduct", map.get("hhsProduct2"));
				 en.put("hhsBz", map.get("hhsBz"));//火花塞备注							
				 hhsls.add(en);				 
			 }			 			 
		 }
		 
		 maps.put("hhsls", hhsls);
		 maps.put("dcls", dcls);		 
		 maps.put("rhls", rhls);
		 maps.put("bsxls", bsxls);				 
		 maps.put("pre_bf_Bls", pre_bf_Bls);
		 maps.put("pre_bds_Bls", pre_bds_Bls);
		 maps.put("suf_bf_Bls", suf_bf_Bls);
		 maps.put("suf_bds_Bls", suf_bds_Bls);
		return new JsonMsg(true,SystemConstants.MSG_SUCCESS,maps);
	}
	

	
	/**
	 * @title:根据条件检索产品
	 * @throws ConflictException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findCarbrandFatoryFormLs",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String,Object> findCarbrandFatoryFormLs(HttpServletRequest request){
		Map<String,Object> map= new HashMap<String,Object>();
		 List<Map<String,Object>> ls=carStyleSublistService.findCarbrandFatoryFormLs();
		 map.put("ls", ls);
		return map; 
		
	};
}

	

