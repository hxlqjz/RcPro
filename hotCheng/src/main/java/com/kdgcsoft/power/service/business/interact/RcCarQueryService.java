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


@Component
@Transactional
public class RcCarQueryService extends BaseService {

	@Autowired
	private BeetlSQLHelper bsh;

	@Transactional(readOnly = true)
	public List<Map<String, Object>> getAllCarStyle() {
		Map<String, Object> para = new HashMap<String, Object>();
		return bsh.getMapList("business.interact.rcCarQuery.getAllCarStyle",para);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> getOptByStyle(String carBrand, String carModel, String carFactory) {
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("carBrand", carBrand);
		para.put("carModel", carModel);
		para.put("carFactory",carFactory);
		return bsh.getMapList("business.interact.rcCarQuery.getOptByStyle",para);
	}
	
	@Transactional(readOnly = true)
    public List<Map<String, Object>> getCarStyleByKey (String carBrand,String carModel,String carFactory,String pl,
    		String nk,String pt,String fdj,String bsx,String lg,String xsmc,String scnf){
    	Map<String,Object> para = new HashMap<String,Object>();	 	    	
    	para.put("carBrand", carBrand);
    	para.put("carModel", carModel);
    	para.put("carFactory", carFactory);
    	if(StringUtils.isNoneBlank(pl)){
    		para.put("pl", pl);
    	}
    	if(StringUtils.isNoneBlank(nk)){
    		para.put("nk", nk);
    	}
    	if(StringUtils.isNoneBlank(pt)){
    		para.put("pt", pt);
    	}
    	if(StringUtils.isNoneBlank(fdj)){
    		para.put("fdj", fdj);
    	}
    	if(StringUtils.isNoneBlank(bsx)){
    		para.put("bsx", bsx);
    	}
    	if(StringUtils.isNoneBlank(lg)){
    		para.put("lg", lg);    	
    	}
    	if(StringUtils.isNoneBlank(xsmc)){
    		para.put("xsmc", xsmc);
    	}
    	if(StringUtils.isNoneBlank(scnf)){
    		para.put("scnf", scnf);
    	}
    	return bsh.getMapList("business.interact.rcCarQuery.getCarStyleByKey",para);
	}
	
	@Transactional(readOnly = true)
    public List<Map<String, Object>> getFactoryAndModelByBrand (String carBrand){
    	Map<String,Object> para = new HashMap<String,Object>();	 	    	
    	para.put("carBrand", carBrand);
    	return bsh.getMapList("business.interact.rcCarQuery.getFactoryAndModelByBrand",para);
	}
	
	@Transactional(readOnly = true)
    public List<Map<String, Object>> getCarStyleByLyId(String[] ids){
    	Map<String,Object> para = new HashMap<String,Object>();	 	    	
    	para.put("ids", ids);
    	return bsh.getMapList("business.interact.rcCarQuery.getCarStyleByLyId",para);
	}

}
