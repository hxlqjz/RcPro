package com.kdgcsoft.power.service.business.interact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.interact.CarStyleDao;
import com.kdgcsoft.power.service.fw.base.BaseService;

/**   
 * @Title: Service
 * @Description: 车型库数据
 * @date 2017-07-08
 * @version V1.0   
 *
 */
@Component
@Transactional
public class CarStyleService  extends BaseService{
	
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private CarStyleDao carStyleDao;
	
	//品牌条件
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findCarBrandList (){
    	Map<String,Object> para = new HashMap<String,Object>();	    
		return bsh.getMapList("business.interact.carStyle.findCarBrandList",para);
    }
	//车系条件
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findCarSysList (String carBrand){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
		return bsh.getMapList("business.interact.carStyle.findfatoryList",para);
    }
	//厂家条件
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findFactorList (String carBrand){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
		return bsh.getMapList("business.interact.carStyle.findFactorList",para);
    }
	//车型条件
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findCarFormList (String carBrand,String fatory){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("fatory", fatory);
		return bsh.getMapList("business.interact.carStyle.findCarFormList",para);
    }
	//排量条件
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findCarOutputList (String carBrand,String fatory,String carForm){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("fatory", fatory);
    	para.put("carForm", carForm);
		return bsh.getMapList("business.interact.carStyle.findCarOutputList",para);
    }
	//年款条件
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findCarYearList (String carBrand,String fatory,String carForm,String carOutput){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("fatory", fatory);
    	para.put("carForm", carForm);
    	para.put("carOutput", carOutput);
		return bsh.getMapList("business.interact.carStyle.findCarYearList",para);
    }
	//上市年份条件
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findMarketYearList (String carBrand,String fatory,String carForm,String carOutput,String carYear,String saleName){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("fatory", fatory);
    	para.put("carForm", carForm);
    	para.put("carOutput", carOutput);
    	para.put("carYear", carYear);
    	para.put("saleName", saleName);
		return bsh.getMapList("business.interact.carStyle.findMarketYearList",para);
    }

	//销售名称条件
	@Transactional(readOnly = true)
    public List<Map<String, Object>> findSaleNameList (String carBrand,String fatory,String carForm,
    		String carOutput,String carYear ){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("fatory", fatory);
    	para.put("carForm", carForm);
    	para.put("carOutput", carOutput);
    	para.put("carYear", carYear);
    	//para.put("marketYear", marketYear);
		return bsh.getMapList("business.interact.carStyle.findSaleNameList",para);
    }
	//根据各个条件结果查询检索结果
	@Transactional(readOnly = true)
    public List<Map<String, Object>> queryCarList (String carBrand,String fatory,String carForm,
    		String carOutput,String carYear ,String produceYear,String saleName){
    	Map<String,Object> para = new HashMap<String,Object>();	 
    	para.put("carBrand", carBrand);
    	para.put("fatory", fatory);
    	para.put("carForm", carForm);
    	para.put("carOutput", carOutput);
    	para.put("carYear", carYear);
    	para.put("produceYear", produceYear);
    	para.put("saleName", saleName);
		return bsh.getMapList("business.interact.carStyle.queryCarList",para);
    }
	
	//根据各个条件结果查询检索结果
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> queryCarGoodByid (String lyId){
	    	Map<String,Object> para = new HashMap<String,Object>();	 
	    	para.put("lyId", lyId);
			return bsh.getMapList("business.interact.carStyle.findCarGoodById",para);
	    }
		
		//根据各个条件结果查询检索结果
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> queryCarGoodByids (String[] ids){
	    	Map<String,Object> para = new HashMap<String,Object>();	 
	    	para.put("ids", ids);
			return bsh.getMapList("business.interact.carStyle.findCarGoodByIds",para);
	    }
		public void updateCarStyledata(String lyId,String shiftMemo){
			Map<String,Object> para = new HashMap<String,Object>();	 
	    	para.put("lyId", lyId);
	    	para.put("shiftMemo", shiftMemo);
	    	bsh.updateOrDelete("business.interact.carStyle.updateCarStyleData",para);
			
		}
		@Transactional(readOnly = true)
		public List<Map<String,Object>> findVinRecordList(String lyId){
			Map<String,Object> para = new HashMap<String,Object>();	 
			para.put("lyId", lyId);
	      return	bsh.getMapList("business.interact.vinCodeRecord.findVinRecordList",para);		 
		}
		
		public void insertVinRecordList(String lyId,String transmissionModel,String vinCode,String createDate){
			Map<String,Object> para = new HashMap<String,Object>();	 
			para.put("lyId", lyId);
			para.put("transmissionModel", transmissionModel);
			para.put("vinCode", vinCode);
			para.put("createDate", createDate);			
	      	bsh.updateOrDelete("business.interact.vinCodeRecord.insertVinRecordLs",para);		 
		}
}
