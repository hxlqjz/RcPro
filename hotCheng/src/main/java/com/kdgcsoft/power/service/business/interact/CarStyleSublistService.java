package com.kdgcsoft.power.service.business.interact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.service.fw.base.BaseService;

/**   
 * @Title: Service
 * @Description: 品牌对应的数据
 * @date 2018年8月28日08:11:43
 * @version V1.0   
 *
 */
@Component
@Transactional
public class CarStyleSublistService  extends BaseService{
	
	@Autowired
	private BeetlSQLHelper bsh;
   
	//品牌条件
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findCarFormList (String carBrand){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
		return bsh.getMapList("business.interact.carStyleSublist.findCarFormList",para);
    }
	
   //查询排量
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findCarOutputList (String carBrand,String carForm){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("carForm", carForm);
		return bsh.getMapList("business.interact.carStyleSublist.findCarOutputList",para);
    }
	
   //查询排量
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findCarYearList (String carBrand,String carForm,String outputTl){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("carForm", carForm);
    	para.put("outputTl", outputTl);
		return bsh.getMapList("business.interact.carStyleSublist.findCarYearList",para);
    }
	

   //查询平台
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findChassisList (String carBrand,String carForm,String outputTl,String carYear){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("carForm", carForm);
    	para.put("outputTl", outputTl);
    	para.put("carYear", carYear);
    	return bsh.getMapList("business.interact.carStyleSublist.findChassisList",para);
	}
	
   //查询发动机型号
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findEngineTypeList (String carBrand,String carForm,String outputTl,String carYear,String chassis){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("carForm", carForm);
    	para.put("outputTl", outputTl);
    	para.put("carYear", carYear);
    	para.put("chassis", chassis);
    	return bsh.getMapList("business.interact.carStyleSublist.findEngineTypeList",para);
	}
	
	//查询平台
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findShiftMemoList (String carBrand,String carForm,String outputTl,String carYear,String chassis,String engineType){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("carForm", carForm);
    	para.put("outputTl", outputTl);
    	para.put("carYear", carYear);
    	para.put("chassis", chassis);
    	para.put("engineType", engineType);
    	return bsh.getMapList("business.interact.carStyleSublist.findShiftMemoList",para);
	}
	
	//查询轮毂
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findPreffixTyreScaleList (String carBrand,String carForm,String outputTl,String carYear,String chassis,String engineType,
    		String shiftMemo){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("carForm", carForm);
    	para.put("outputTl", outputTl);
    	para.put("carYear", carYear);
    	para.put("chassis", chassis);
    	para.put("engineType", engineType);
    	para.put("shiftMemo", shiftMemo);
    	return bsh.getMapList("business.interact.carStyleSublist.findPreffixTyreScaleList",para);
	}
	
	//查询销售名称
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findSaleNameList (String carBrand,String carForm,String outputTl,String carYear,String chassis,String engineType,
    		String shiftMemo,String preffixTyreScale){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("carForm", carForm);
    	para.put("outputTl", outputTl);
    	para.put("carYear", carYear);
    	para.put("chassis", chassis);
    	para.put("engineType", engineType);
    	para.put("shiftMemo", shiftMemo);
    	para.put("preffixTyreScale", preffixTyreScale);
  
    	return bsh.getMapList("business.interact.carStyleSublist.findSaleNameList",para);
	}
	//查询生成年份
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findProduceYearList (String carBrand,String carForm,String outputTl,String carYear,String chassis,String engineType,
    		String shiftMemo,String preffixTyreScale,String saleName){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("carForm", carForm);
    	para.put("outputTl", outputTl);
    	para.put("carYear", carYear);
    	para.put("chassis", chassis);
    	para.put("engineType", engineType);
    	para.put("shiftMemo", shiftMemo);
    	para.put("preffixTyreScale", preffixTyreScale);    	
    	para.put("saleName", saleName);
  
    	return bsh.getMapList("business.interact.carStyleSublist.findProduceYearList",para);
	}
	
	    //根据条件查询产品
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> findProducesByOpt (String carBrand,String carForm,String fatory,String outputTl,String carYear,String chassis,String engineType,
	    		String shiftMemo,String preffixTyreScale,String saleName,String produceYear){
	    	Map<String,Object> para = new HashMap<String,Object>();	 	    	
	    	para.put("carBrand", carBrand);
	    	para.put("carForm", carForm);
	    	para.put("fatory", fatory);
	    	if(StringUtils.isNoneBlank(outputTl)){
	    		para.put("outputTl", outputTl);
	    	}
	    	if(StringUtils.isNoneBlank(carYear)){
	    		para.put("carYear", carYear);
	    	}
	    	if(StringUtils.isNoneBlank(chassis)){
	    		para.put("chassis", chassis);
	    	}
	    	if(StringUtils.isNoneBlank(engineType)){
	    		para.put("engineType", engineType);
	    	}
	    	if(StringUtils.isNoneBlank(shiftMemo)){
	    		para.put("shiftMemo", shiftMemo);
	    	}
	    	if(StringUtils.isNoneBlank(preffixTyreScale)){
	    		para.put("preffixTyreScale", preffixTyreScale);    	
	    	}
	    	if(StringUtils.isNoneBlank(saleName)){
	    		para.put("saleName", saleName);
	    	}
	    	if(StringUtils.isNoneBlank(produceYear)){
	    		para.put("produceYear", produceYear);
	    	}
	    	return bsh.getMapList("business.interact.carStyleSublist.findProducesByOpt",para);
		}
		
		//根据条件查前制动
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> findPreBrakeByOpt (String carBrand,String carForm,String fatory,String outputTl,String carYear,String chassis,String engineType,
	    		String shiftMemo,String preffixTyreScale,String saleName,String produceYear){
	    	Map<String,Object> para = new HashMap<String,Object>();	 	    	
	    	para.put("carBrand", carBrand);
	    	para.put("carForm", carForm);
	    	para.put("fatory", fatory);
	    	if(StringUtils.isNoneBlank(outputTl)){
	    		para.put("outputTl", outputTl);
	    	}
	    	if(StringUtils.isNoneBlank(carYear)){
	    		para.put("carYear", carYear);
	    	}
	    	if(StringUtils.isNoneBlank(chassis)){
	    		para.put("chassis", chassis);
	    	}
	    	if(StringUtils.isNoneBlank(engineType)){
	    		para.put("engineType", engineType);
	    	}
	    	if(StringUtils.isNoneBlank(shiftMemo)){
	    		para.put("shiftMemo", shiftMemo);
	    	}
	    	if(StringUtils.isNoneBlank(preffixTyreScale)){
	    		para.put("preffixTyreScale", preffixTyreScale);    	
	    	}
	    	if(StringUtils.isNoneBlank(saleName)){
	    		para.put("saleName", saleName);
	    	}
	    	if(StringUtils.isNoneBlank(produceYear)){
	    		para.put("produceYear", produceYear);
	    	}
	    	return bsh.getMapList("business.interact.carStyleSublist.findPreBrakeByOpt",para);
		}
		
		//根据条件查后制动
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> findSufBrakeByOpt (String carBrand,String carForm,String fatory,String outputTl,String carYear,String chassis,String engineType,
	    		String shiftMemo,String preffixTyreScale,String saleName,String produceYear){
	    	Map<String,Object> para = new HashMap<String,Object>();	 	    	
	    	para.put("carBrand", carBrand);
	    	para.put("carForm", carForm);
	    	para.put("fatory", fatory);
	    	if(StringUtils.isNoneBlank(outputTl)){
	    		para.put("outputTl", outputTl);
	    	}
	    	if(StringUtils.isNoneBlank(carYear)){
	    		para.put("carYear", carYear);
	    	}
	    	if(StringUtils.isNoneBlank(chassis)){
	    		para.put("chassis", chassis);
	    	}
	    	if(StringUtils.isNoneBlank(engineType)){
	    		para.put("engineType", engineType);
	    	}
	    	if(StringUtils.isNoneBlank(shiftMemo)){
	    		para.put("shiftMemo", shiftMemo);
	    	}
	    	if(StringUtils.isNoneBlank(preffixTyreScale)){
	    		para.put("preffixTyreScale", preffixTyreScale);    	
	    	}
	    	if(StringUtils.isNoneBlank(saleName)){
	    		para.put("saleName", saleName);
	    	}
	    	if(StringUtils.isNoneBlank(produceYear)){
	    		para.put("produceYear", produceYear);
	    	}
	    	return bsh.getMapList("business.interact.carStyleSublist.findSufBrakeByOpt",para);
		}
		
		//根据条件查油品数据
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> findOilsByOpt (String carBrand,String carForm,String fatory,String outputTl,String carYear,String chassis,String engineType,
	    		String shiftMemo,String preffixTyreScale,String saleName,String produceYear){
	    	Map<String,Object> para = new HashMap<String,Object>();	 	    	
	    	para.put("carBrand", carBrand);
	    	para.put("carForm", carForm);
	    	para.put("fatory", fatory);
	    	if(StringUtils.isNoneBlank(outputTl)){
	    		para.put("outputTl", outputTl);
	    	}
	    	if(StringUtils.isNoneBlank(carYear)){
	    		para.put("carYear", carYear);
	    	}
	    	if(StringUtils.isNoneBlank(chassis)){
	    		para.put("chassis", chassis);
	    	}
	    	if(StringUtils.isNoneBlank(engineType)){
	    		para.put("engineType", engineType);
	    	}
	    	if(StringUtils.isNoneBlank(shiftMemo)){
	    		para.put("shiftMemo", shiftMemo);
	    	}
	    	if(StringUtils.isNoneBlank(preffixTyreScale)){
	    		para.put("preffixTyreScale", preffixTyreScale);    	
	    	}
	    	if(StringUtils.isNoneBlank(saleName)){
	    		para.put("saleName", saleName);
	    	}
	    	if(StringUtils.isNoneBlank(produceYear)){
	    		para.put("produceYear", produceYear);
	    	}
	    	return bsh.getMapList("business.interact.carStyleSublist.findOilsByOpt",para);
		}
		
		//根据条件查电池数据
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> findBatterysByOpt (String carBrand,String carForm,String fatory,String outputTl,String carYear,String chassis,String engineType,
	    		String shiftMemo,String preffixTyreScale,String saleName,String produceYear){
	    	Map<String,Object> para = new HashMap<String,Object>();	 	    	
	    	para.put("carBrand", carBrand);
	    	para.put("carForm", carForm);
	    	para.put("fatory", fatory);
	    	if(StringUtils.isNoneBlank(outputTl)){
	    		para.put("outputTl", outputTl);
	    	}
	    	if(StringUtils.isNoneBlank(carYear)){
	    		para.put("carYear", carYear);
	    	}
	    	if(StringUtils.isNoneBlank(chassis)){
	    		para.put("chassis", chassis);
	    	}
	    	if(StringUtils.isNoneBlank(engineType)){
	    		para.put("engineType", engineType);
	    	}
	    	if(StringUtils.isNoneBlank(shiftMemo)){
	    		para.put("shiftMemo", shiftMemo);
	    	}
	    	if(StringUtils.isNoneBlank(preffixTyreScale)){
	    		para.put("preffixTyreScale", preffixTyreScale);    	
	    	}
	    	if(StringUtils.isNoneBlank(saleName)){
	    		para.put("saleName", saleName);
	    	}
	    	if(StringUtils.isNoneBlank(produceYear)){
	    		para.put("produceYear", produceYear);
	    	}
	    	return bsh.getMapList("business.interact.carStyleSublist.findBatterysByOpt",para);
		}
		
		//根据条件查火花塞数据
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> findSparksByOpt (String carBrand,String carForm,String fatory,String outputTl,String carYear,String chassis,String engineType,
	    		String shiftMemo,String preffixTyreScale,String saleName,String produceYear){
	    	Map<String,Object> para = new HashMap<String,Object>();	 	    	
	    	para.put("carBrand", carBrand);
	    	para.put("carForm", carForm);
	    	para.put("fatory", fatory);
	    	if(StringUtils.isNoneBlank(outputTl)){
	    		para.put("outputTl", outputTl);
	    	}
	    	if(StringUtils.isNoneBlank(carYear)){
	    		para.put("carYear", carYear);
	    	}
	    	if(StringUtils.isNoneBlank(chassis)){
	    		para.put("chassis", chassis);
	    	}
	    	if(StringUtils.isNoneBlank(engineType)){
	    		para.put("engineType", engineType);
	    	}
	    	if(StringUtils.isNoneBlank(shiftMemo)){
	    		para.put("shiftMemo", shiftMemo);
	    	}
	    	if(StringUtils.isNoneBlank(preffixTyreScale)){
	    		para.put("preffixTyreScale", preffixTyreScale);    	
	    	}
	    	if(StringUtils.isNoneBlank(saleName)){
	    		para.put("saleName", saleName);
	    	}
	    	if(StringUtils.isNoneBlank(produceYear)){
	    		para.put("produceYear", produceYear);
	    	}
	    	return bsh.getMapList("business.interact.carStyleSublist.findSparksByOpt",para);
		}
		
		//根据条件查热骋数据
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> findRcByOpt (String carBrand,String carForm,String fatory,String outputTl,String carYear,String chassis,String engineType,
	    		String shiftMemo,String preffixTyreScale,String saleName,String produceYear){
	    	Map<String,Object> para = new HashMap<String,Object>();	 	    	
	    	para.put("carBrand", carBrand);
	    	para.put("carForm", carForm);
	    	para.put("fatory", fatory);
	    	if(StringUtils.isNoneBlank(outputTl)){
	    		para.put("outputTl", outputTl);
	    	}
	    	if(StringUtils.isNoneBlank(carYear)){
	    		para.put("carYear", carYear);
	    	}
	    	if(StringUtils.isNoneBlank(chassis)){
	    		para.put("chassis", chassis);
	    	}
	    	if(StringUtils.isNoneBlank(engineType)){
	    		para.put("engineType", engineType);
	    	}
	    	if(StringUtils.isNoneBlank(shiftMemo)){
	    		para.put("shiftMemo", shiftMemo);
	    	}
	    	if(StringUtils.isNoneBlank(preffixTyreScale)){
	    		para.put("preffixTyreScale", preffixTyreScale);    	
	    	}
	    	if(StringUtils.isNoneBlank(saleName)){
	    		para.put("saleName", saleName);
	    	}
	    	if(StringUtils.isNoneBlank(produceYear)){
	    		para.put("produceYear", produceYear);
	    	}
	    	return bsh.getMapList("business.interact.carStyleSublist.findCarGoodByOpt",para);
		}
		
	    //根据条件查询产品
		@Transactional(readOnly = true)
		  public List<Map<String, Object>> findCarbrandFatoryFormLs (){
			Map<String,Object> para = new HashMap<String,Object>();	 	
			return bsh.getMapList("business.interact.carStyleSublist.findCarBrandFatoryForm",para);				
		}
		
		//根据lyIds查前制动
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> findPreBrakeByIds (String lyIds){
	    	Map<String,Object> para = new HashMap<String,Object>();	 	    	
	    	String[] ids=lyIds.split(",");
	    	para.put("ids", ids);
	    	return bsh.getMapList("business.interact.carStyleSublist.findPreBrakeByIds",para);
		}
		//根据条件查后制动
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> findSufBrakeByIds (String lyIds){
	    	Map<String,Object> para = new HashMap<String,Object>();	 	    	
	    	String[] ids=lyIds.split(",");
	    	para.put("ids", ids);
	    	return bsh.getMapList("business.interact.carStyleSublist.findSufBrakeByIds",para);
		}
		
		//根据lyIds查油品数据
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> findOilsByIds (String lyIds){
	    	Map<String,Object> para = new HashMap<String,Object>();	 	    	
	    	String[] ids=lyIds.split(",");
	    	para.put("ids", ids);
	    	return bsh.getMapList("business.interact.carStyleSublist.findOilsByIds",para);
		}
		//根据lyids查电池数据
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> findBatterysByIds(String lyIds){
	    	Map<String,Object> para = new HashMap<String,Object>();	 	
	    	String[] ids=lyIds.split(",");
	    	para.put("ids", ids);	    	
	    	return bsh.getMapList("business.interact.carStyleSublist.findBatterysByIds",para);
		}
		
		//根据lyids查火花塞数据
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> findSparksByIds (String lyIds){
	    	Map<String,Object> para = new HashMap<String,Object>();	 
	    	String[] ids=lyIds.split(",");
	    	para.put("ids", ids);
	    	return bsh.getMapList("business.interact.carStyleSublist.findSparksByIds",para);
		}

}
