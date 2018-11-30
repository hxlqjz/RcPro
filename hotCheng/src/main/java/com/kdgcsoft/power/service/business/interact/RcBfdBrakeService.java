package com.kdgcsoft.power.service.business.interact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.interact.RcBfdBrakeDao;
import com.kdgcsoft.power.entity.business.interact.RcBfdBrake;
import com.kdgcsoft.power.service.fw.base.BaseService;

@Component
@Transactional
public class RcBfdBrakeService  extends BaseService{
	
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private RcBfdBrakeDao rcBfdBrakeDao;
	@Transactional(readOnly = true)
    public List<Map<String, Object>> getList (String bfd,String bCode, PageRequest pageRequest){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("bfd", bfd);
    	para.put("bCode", bCode);
		return bsh.getMapList("business.interact.rcBfdBrake.getList",para);
    }
	
	@Transactional(readOnly = true)
    public RcBfdBrake getInfo (Long id){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("id", id);
		return bsh.getEntity("business.interact.rcBfdBrake.getInfo", para, RcBfdBrake.class);
    }
	
	@Transactional(readOnly = true)
    public void insert(RcBfdBrake entity){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("bfd", entity.getBfd());
    	para.put("wheel", entity.getWheel());
    	para.put("bfCode", entity.getBfCode());
    	para.put("bfMemo", entity.getBfMemo());
    	para.put("bdsCode", entity.getBdsCode());
    	para.put("bdsMemo", entity.getBdsMemo());
    	para.put("dCode", entity.getdCode());
    	para.put("fmsiCode", entity.getFmsiCode());
    	para.put("fldm1", entity.getFldm1());
    	para.put("fldm2", entity.getFldm2());
    	para.put("trw1", entity.getTrw1());
    	para.put("trw2", entity.getTrw2());
    	para.put("oe1", entity.getOe1());
    	para.put("oe2", entity.getOe2());
    	para.put("oe3", entity.getOe3());
    	para.put("oe4", entity.getOe4());
    	para.put("oe5", entity.getOe5());
    	
		bsh.updateOrDelete("business.interact.rcBfdBrake.insert",para);
    }
	
	@Transactional(readOnly = true)
    public void update(RcBfdBrake entity){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("bfd", entity.getBfd());
    	para.put("wheel", entity.getWheel());
    	para.put("bfCode", entity.getBfCode());
    	para.put("bfMemo", entity.getBfMemo());
    	para.put("bdsCode", entity.getBdsCode());
    	para.put("bdsMemo", entity.getBdsMemo());
    	para.put("dCode", entity.getdCode());
    	para.put("fmsiCode", entity.getFmsiCode());
    	para.put("fldm1", entity.getFldm1());
    	para.put("fldm2", entity.getFldm2());
    	para.put("trw1", entity.getTrw1());
    	para.put("trw2", entity.getTrw2());
    	para.put("oe1", entity.getOe1());
    	para.put("oe2", entity.getOe2());
    	para.put("oe3", entity.getOe3());
    	para.put("oe4", entity.getOe4());
    	para.put("oe5", entity.getOe5());
    	para.put("imgSrc", entity.getImgSrc());
    	para.put("id", entity.getId());
		bsh.updateOrDelete("business.interact.rcBfdBrake.update",para);
    }
	
	@Transactional(readOnly = true)
    public void updateImg(Map<String, Object> para){
    	
		bsh.updateOrDelete("business.interact.rcBfdBrake.updateImg",para);
    }
	
	@Transactional(readOnly = true)
    public void delete(Long id){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("id", id);
    	bsh.updateOrDelete("business.interact.rcBfdBrake.delete", para);
    }
	

}
