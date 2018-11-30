package com.kdgcsoft.power.service.business.interact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.entity.business.interact.Bds;
import com.kdgcsoft.power.entity.business.interact.Bf;
import com.kdgcsoft.power.entity.business.interact.Cp;
import com.kdgcsoft.power.entity.business.interact.Hb;
import com.kdgcsoft.power.entity.business.interact.Hbtable;
import com.kdgcsoft.power.service.fw.base.BaseService;

@Component
@Transactional
public class HbService  extends BaseService{
	
	@Autowired
	private BeetlSQLHelper bsh;
	
	@Transactional(readOnly = true)
    public List<Hb> getList(){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	
		return bsh.getList("business.interact.hb.getList", para, Hb.class);
    }
	@Transactional(readOnly = true)
    public List<Hbtable> getHbList(){
    	Map<String,Object> para = new HashMap<String,Object>();	  
		return bsh.getList("business.interact.hb.getHbList", para, Hbtable.class);
    }
	
	@Transactional(readOnly = true)
    public void insertDb(Map<String,Object> para){
    	
		bsh.updateOrDelete("business.interact.hb.insertDb", para);
    }
	@Transactional(readOnly = true)
    public void insert(Map<String,Object> para){
    	
		bsh.updateOrDelete("business.interact.hb.insert", para);
    }
	
	@Transactional(readOnly = true)
    public List<Bf> getBf(){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	
		return bsh.getList("business.interact.hb.getBf", para, Bf.class);
    }
	
	@Transactional(readOnly = true)
    public void updateBf(Map<String,Object> para){
    	
		bsh.updateOrDelete("business.interact.hb.updateBf", para);
    }
	
	@Transactional(readOnly = true)
    public List<Bds> getBds(){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	
		return bsh.getList("business.interact.hb.getBds", para, Bds.class);
    }
	
	@Transactional(readOnly = true)
    public void updateBds(Map<String,Object> para){
    	
		bsh.updateOrDelete("business.interact.hb.updateBds", para);
    }
	
	@Transactional(readOnly = true)
    public void insertBds(Map<String,Object> para){
    	
		bsh.updateOrDelete("business.interact.hb.insertBds", para);
    }
	
	@Transactional(readOnly = true)
    public void insertBf(Map<String,Object> para){
    	
		bsh.updateOrDelete("business.interact.hb.insertBf", para);
    }
	@Transactional(readOnly = true)
    public void deleteBds(Map<String,Object> para){
    	
		bsh.updateOrDelete("business.interact.hb.deleteBds", para);
    }
	
	@Transactional(readOnly = true)
    public List<Cp> getBfByBds(Map<String,Object> para){
		return bsh.getList("business.interact.hb.getBfByBds", para, Cp.class);
    }
}
