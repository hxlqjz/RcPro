package com.kdgcsoft.power.service.business.interact;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.interact.RpStoreDao;
import com.kdgcsoft.power.entity.business.interact.RpStore;
import com.kdgcsoft.power.service.fw.base.BaseService;


@Component
@Transactional
public class RpStoreService  extends BaseService{
	
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private RpStoreDao rpStoreDao;
	
	@Transactional(readOnly = true)
    public List<Map<String, Object>> getList (String storeName,String idCode, PageRequest pageRequest){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("storeName", storeName);
    	para.put("idCode", idCode);
		return bsh.getMapList("business.interact.rpStore.getList",para);
    }
	
	@Transactional(readOnly = true)
    public RpStore getInfo(Long id){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("id", id);
		return bsh.getEntity("business.interact.rpStore.getInfo", para, RpStore.class);
    }
	@Transactional(readOnly = true)
    public RpStore getInfoByIdCode(String idCode){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("idCode", idCode);
		return bsh.getEntity("business.interact.rpStore.getInfoByIdCode", para, RpStore.class);
    }
	@Transactional(readOnly = true)
    public void insert(RpStore entity){
    	Map<String, Object> para = new HashMap<String,Object>();;
		para.put("idCode", entity.getIdCode());
		para.put("storeName", entity.getStoreName());
		para.put("province", entity.getProvince());
		para.put("marks", entity.getMarks());
		para.put("queryPower", entity.getQueryPower());
		para.put("rolePower", entity.getRolePower());
		bsh.updateOrDelete("business.interact.rpStore.insert",para);
    }
	
	 
	@Transactional(readOnly = true)
    public void update(RpStore entity){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("id", entity.getId());
		para.put("storeName", entity.getStoreName());
		para.put("province", entity.getProvince());
		para.put("marks", entity.getMarks());
		para.put("queryPower", entity.getQueryPower());
		para.put("rolePower", entity.getRolePower());
		bsh.updateOrDelete("business.interact.rpStore.update",para);
    }
	
	@Transactional(readOnly = true)
    public void delete(Long id){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("id", id);
    	bsh.updateOrDelete("business.interact.rpStore.delete", para);
    }
	
	@Transactional(readOnly = true)
    public List<RpStore> getIdCodeList(){
		return bsh.getList("business.interact.rpStore.getIdCodeList", null, RpStore.class);
    }
}
