package com.kdgcsoft.power.service.business.interact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.interact.NgkDao;
import com.kdgcsoft.power.service.fw.base.BaseService;

/**   
 * @Title: Service
 * @Description: NgkService
 * @date 2017-07-08
 * @version V1.0   
 *
 */
@Component
@Transactional
public class NgkService  extends BaseService{
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private NgkDao ngkDao;

	
	public List<Map<String,Object>> findNgkBrandList(){
		Map<String,Object> para = new HashMap<String,Object>();				
		 List<Map<String,Object>> ls=bsh.getMapList("business.interact.ngk.findNgkBrandList", para);
		 return ls;
		
	}
	public List<Map<String,Object>> findCarFormListByBrand(String brand){
		Map<String,Object> para = new HashMap<String,Object>();		
		para.put("ngkBrand", brand);
		 List<Map<String,Object>> ls=bsh.getMapList("business.interact.ngk.findCarFormListByBrand", para);
		 return ls;
		
	}
	public List<Map<String,Object>> findCarFormListByBrandAndType(String brand,String type){
		Map<String,Object> para = new HashMap<String,Object>();	
		para.put("ngkBrand", brand);
		para.put("ngkType", type);
		 List<Map<String,Object>> ls=bsh.getMapList("business.interact.ngk.findCarFormListByBrandAndType", para);
		 return ls;
		
	}
	public List<Map<String,Object>> findCarListByBrandAndTypeAndOutPut(String brand,String type,String output){
		Map<String,Object> para = new HashMap<String,Object>();	
		para.put("ngkBrand", brand);
		para.put("ngkType", type);
		para.put("outputAmount", output);
		 List<Map<String,Object>> ls=bsh.getMapList("business.interact.ngk.findCarListByBrandAndTypeAndOutPut", para);
		 return ls;
		
	}
	public  List<Map<String, Object>>  findCarListByBrandAndTypeAndOutPutAndYear(String brand,String type,String output,String year){
		Map<String,Object> para = new HashMap<String,Object>();	
		para.put("ngkBrand", brand);
		para.put("ngkType", type);
		para.put("outputAmount", output);	
		para.put("markeyYear", year);	
		 return bsh.getMapList("business.interact.ngk.findCarListByBrandAndTypeAndOutPutAndYear", para);
		 
		
	}
}
