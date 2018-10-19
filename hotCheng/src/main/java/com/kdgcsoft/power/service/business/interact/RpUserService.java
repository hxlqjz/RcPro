package com.kdgcsoft.power.service.business.interact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.interact.RpUserDao;
import com.kdgcsoft.power.entity.business.interact.RpStore;
import com.kdgcsoft.power.entity.business.interact.RpUser;
import com.kdgcsoft.power.service.fw.base.BaseService;

@Component
@Transactional
public class RpUserService  extends BaseService{
	
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private RpUserDao rpUserDao;
	@Autowired
	private RpStoreService rpStoreService;
	@Transactional(readOnly = true)
    public List<Map<String, Object>> getList (String name,String idCode, PageRequest pageRequest){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("name", name);
    	para.put("idCode", idCode);
		return bsh.getMapList("business.interact.rpUser.getList",para);
    }
	
	@Transactional(readOnly = true)
    public RpUser getInfo (Long id){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("id", id);
		return bsh.getEntity("business.interact.rpUser.getInfo", para, RpUser.class);
    }
	
	@Transactional(readOnly = true)
    public void insert(RpUser entity){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("wechatNo", entity.getWechatNo());
    	para.put("idCode", entity.getIdCode());
    	para.put("nickName", entity.getNickName());
    	para.put("userName", entity.getUserName());
    	para.put("tel", entity.getTel());
    	para.put("info", entity.getInfo());
    	para.put("queryPower", entity.getQueryPower());
    	para.put("rolePower", entity.getRolePower());
    	para.put("isBlack", entity.getIsBlack());
		bsh.updateOrDelete("business.interact.rpUser.insert",para);
    }
	
	@Transactional(readOnly = true)
    public void update(RpUser entity){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("wechatNo", entity.getWechatNo());
    	para.put("idCode", entity.getIdCode());
    	para.put("nickName", entity.getNickName());
    	para.put("userName", entity.getUserName());
    	para.put("tel", entity.getTel());
    	para.put("info", entity.getInfo());
    	para.put("queryPower", entity.getQueryPower());
    	para.put("rolePower", entity.getRolePower());
    	para.put("isBlack", entity.getIsBlack());
    	para.put("id", entity.getId());
		bsh.updateOrDelete("business.interact.rpUser.update",para);
    }
	
	@Transactional(readOnly = true)
    public void delete(Long id){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("id", id);
    	bsh.updateOrDelete("business.interact.rpUser.delete", para);
    }
	@Transactional(readOnly = true)
    public RpUser getInfoByWechat(String wechatNo){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("wechatNo", wechatNo);
    	RpUser en=bsh.getEntity("business.interact.rpUser.getInfoByWechat", para, RpUser.class);
		return en;
    }
	
	@Transactional(readOnly = true)
    public void reg(String wechatNo, String nickName, String idCode,String userName,String tel,String info){
    	Map<String,Object> para = new HashMap<String,Object>();
    	
    	RpStore s = rpStoreService.getInfoByIdCode(idCode);
    	para.put("wechatNo", wechatNo);
    	para.put("idCode", idCode);
    	para.put("nickName", nickName);
    	para.put("userName", userName);
    	para.put("tel", tel);
    	para.put("info", info);
    	para.put("queryPower", s.getQueryPower());
    	para.put("rolePower", s.getRolePower());
    	para.put("isBlack", 0);
		bsh.updateOrDelete("business.interact.rpUser.reg",para);
    }
	
	@Transactional(readOnly = true)
	public List<RpUser> getUsersByStore(String wechatNo){
		Map<String, Object> para = new HashMap<String, Object>();
		RpUser u = getInfoByWechat(wechatNo);
		RpStore s = rpStoreService.getInfoByIdCode(u.getIdCode());
		//根据当前用户的省份和门店进行查询
		para.put("province", s.getProvince());
		para.put("storeName", s.getStoreName());
		//根据省份和门店，进行反向查询门店标识码
		return bsh.getList("business.interact.rpUser.getUsersByStore", para, RpUser.class);
	}
	
	@Transactional(readOnly = true)
    public List<Map<String, Object>> getExportList (String name,String idCode){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("name", name);
    	para.put("idCode", idCode);
		return bsh.getMapList("business.interact.rpUser.getList",para);
    }
}
