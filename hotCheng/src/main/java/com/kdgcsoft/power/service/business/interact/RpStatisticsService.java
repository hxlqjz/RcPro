package com.kdgcsoft.power.service.business.interact;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.interact.RpStatisticsDao;
import com.kdgcsoft.power.entity.business.interact.RpStatistics;
import com.kdgcsoft.power.entity.business.interact.RpStore;
import com.kdgcsoft.power.entity.business.interact.RpUser;
import com.kdgcsoft.power.service.fw.base.BaseService;

@Component
@Transactional
public class RpStatisticsService  extends BaseService{
	
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private RpStatisticsDao rpStatisticsDao;
	@Autowired
	private RpUserService rpUserService;
	@Autowired
	private RpStoreService rpStoreService;
	@Transactional(readOnly = true)
    public List<Map<String, Object>> getList (String qrNo,String startTime, String endTime, PageRequest pageRequest){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("qrNo", qrNo);
    	para.put("startTime", startTime);
    	para.put("endTime", endTime);
		return bsh.getMapList("business.interact.rpStatistics.getList",para);
    }
	@Transactional(readOnly = true)
    public List<Map<String, Object>> getTjList (String wechat,String startTime, String endTime, PageRequest pageRequest){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("wechat", wechat);
    	para.put("startTime", startTime);
    	para.put("endTime", endTime);
		return bsh.getMapList("business.interact.rpStatistics.getTjList",para);
    }
	@Transactional(readOnly = true)
    public RpStatistics getInfo (Long id){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("id", id);
		return bsh.getEntity("business.interact.rpStatistics.getInfo", para, RpStatistics.class);
    }
	
	@Transactional(readOnly = true)
    public void insert(RpStatistics entity){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("qrCode", entity.getQrCode());
    	para.put("qrNo", entity.getQrNo());
    	para.put("productModel", entity.getProductModel());
    	
    	/*para.put("presaleArea", entity.getPresaleArea());
    	para.put("qrCode", entity.getQrCode());
    	para.put("qrNo", entity.getQrNo());
    	para.put("productModel", entity.getProductModel());
    	para.put("activiateStatus", entity.getActiviateStatus());
    	para.put("scanTime", entity.getScanTime());
    	para.put("qualityStart", entity.getQualityStart());
    	para.put("qualityEnd", entity.getQualityEnd());
    	para.put("cashTime", entity.getCashTime());
    	para.put("scanWechat", entity.getScanWechat());
    	para.put("provice", entity.getProvice());
    	para.put("storeName", entity.getStoreName());
    	para.put("wechatName", entity.getWechatName());
    	
    	para.put("wechatTel", entity.getWechatTel());
    	para.put("wechatInfo", entity.getWechatInfo());
    	para.put("queryPower", entity.getQueryPower());
    	para.put("rolePower", entity.getRolePower());
    	para.put("plateNumber", entity.getPlateNumber());
    	para.put("carTel", entity.getCarTel());*/
    	
		bsh.updateOrDelete("business.interact.rpStatistics.insert",para);
    }
	
	@Transactional(readOnly = true)
    public void update(RpStatistics entity){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("qrCode", entity.getQrCode());
    	para.put("qrNo", entity.getQrNo());
    	para.put("productModel", entity.getProductModel());
    	/*
    	para.put("presaleArea", entity.getPresaleArea());
    	para.put("qrCode", entity.getQrCode());
    	para.put("qrNo", entity.getQrNo());
    	para.put("productModel", entity.getProductModel());
    	para.put("activiateStatus", entity.getActiviateStatus());
    	para.put("scanTime", entity.getScanTime());
    	para.put("qualityStart", entity.getQualityStart());
    	para.put("qualityEnd", entity.getQualityEnd());
    	para.put("cashTime", entity.getCashTime());
    	para.put("scanWechat", entity.getScanWechat());
    	para.put("provice", entity.getProvice());
    	para.put("storeName", entity.getStoreName());
    	para.put("wechatName", entity.getWechatName());
    	
    	para.put("wechatTel", entity.getWechatTel());
    	para.put("wechatInfo", entity.getWechatInfo());
    	para.put("queryPower", entity.getQueryPower());
    	para.put("rolePower", entity.getRolePower());
    	para.put("plateNumber", entity.getPlateNumber());
    	para.put("carTel", entity.getCarTel());*/
    	para.put("id", entity.getId());
		bsh.updateOrDelete("business.interact.rpStatistics.update",para);
    }
	@Transactional(readOnly = true)
	 public void activate(Long start, Long end, String presaleArea){
	    	Map<String,Object> para = new HashMap<String,Object>();
	    	para.put("start", start);
	    	para.put("end", end);
	    	para.put("presaleArea", presaleArea);
	    	
			bsh.updateOrDelete("business.interact.rpStatistics.activate",para);
	}
	@Transactional(readOnly = true)
	 public Long getActivateCount(Long start, Long end){
	    	Map<String,Object> para = new HashMap<String,Object>();
	    	para.put("start", start);
	    	para.put("end", end);
	    	return bsh.getLongValue("business.interact.rpStatistics.getActivateCount", para);
	}
	
	@Transactional(readOnly = true)
    public void delete(Long id){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("id", id);
    	bsh.updateOrDelete("business.interact.rpStatistics.delete", para);
    }
	
	@Transactional(readOnly = true)
    public RpStatistics checkQr (String qrCode, String qrNo){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("qrCode", qrCode);
    	para.put("qrNo", qrNo);
    	return bsh.getEntity("business.interact.rpStatistics.checkQr", para, RpStatistics.class);
    }
	@Transactional(readOnly = true)
    public List<Map<String, Object>> getExportList (String activiateStatus,String productModel,String startTime, String endTime){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("activiateStatus", activiateStatus);
    	para.put("productModel", productModel);
    	para.put("startTime", startTime);
    	para.put("endTime", endTime);
		return bsh.getMapList("business.interact.rpStatistics.getList",para);
    }
	
	//微信使用
	@Transactional(readOnly = true)
	public Long getActiveByCode(String qrCode){
		Map<String,Object> para = new HashMap<String, Object>();
		para.put("qrCode", qrCode);
		return bsh.getLongValue("business.interact.rpStatistics.getActiveByCode", para);
	}
	
	@Transactional(readOnly = true)
    public void scanToBack(RpStatistics entity){
    	Map<String,Object> para = new HashMap<String,Object>();
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(new Date());//设置起时间
    	cal.add(Calendar.YEAR, 1);//增加一年
    	cal.add(Calendar.MONTH, 6);//增加一个月  
    	
    	//返现时间应该是返现成功后更新10
    	Calendar cal2 = Calendar.getInstance();
    	cal2.setTime(new Date());//设置起时间
    	cal2.add(Calendar.MINUTE, 10);
    	
    	RpUser u = rpUserService.getInfoByWechat(entity.getScanWechat());
    	RpStore s = rpStoreService.getInfoByIdCode(u.getIdCode());
    	
    	para.put("qrCode", entity.getQrCode());
    	para.put("plateNumber", entity.getPlateNumber());
    	para.put("carTel", entity.getCarTel());
    	para.put("scanWechat", entity.getScanWechat());
    	para.put("nickName",u.getNickName());
    	para.put("activiateStatus", 2);
    	para.put("scanTime", new Date());
    	para.put("qualityStart", new Date());
    	para.put("qualityEnd", cal.getTime());
    	para.put("cashTime", cal2.getTime());
    	para.put("wechatName", u.getUserName());
    	para.put("wechatTel", u.getTel());
    	para.put("wechatInfo", u.getInfo());
    	para.put("queryPower", u.getQueryPower());
    	para.put("rolePower", u.getRolePower());
    	
    	para.put("province", s.getProvince());
    	para.put("storeName", s.getStoreName());
    	
    	
		bsh.updateOrDelete("business.interact.rpStatistics.scanToBack",para);
    }
	
	
	@Transactional(readOnly = true)
    public RpStatistics getInfoByQp(String qrCode, String scanWechat){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	RpUser u = rpUserService.getInfoByWechat(scanWechat);
    	para.put("qrCode", qrCode);
    	para.put("queryPower", u.getQueryPower());
		return bsh.getEntity("business.interact.rpStatistics.getInfoByQp", para, RpStatistics.class);
    }
	
	@Transactional(readOnly = true)
    public Map<String, Object> getListByRole(String start,String end,String scanWechat, String chooseWechat,String province,String storeName){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	//根据微信号查找他的角色权限
    	RpUser u = rpUserService.getInfoByWechat(scanWechat);
    	RpStore s = rpStoreService.getInfoByIdCode(u.getIdCode());
    	//chooseWechat是权限2时选择的单个的用户
    	para.put("startTime", start);
    	para.put("endTime", end);
    	Map<String,Object> map = new HashMap<String, Object>();
    	Long rolePower = u.getRolePower();
    	if(rolePower == 1){
    		para.put("province", province);
        	para.put("storeName", storeName);
        	List<Map<String, Object>> list = bsh.getMapList("business.interact.rpStatistics.role_power1_query",para);
        	map.put("list", list);
        	Long count = 0l;
        	for(int i=0;i<list.size();i++){
        		count += Long.parseLong(list.get(i).get("count").toString());
        	}
        	map.put("count", count);
        	map.put("sum", count*5);
    		return map;
    	}else if(rolePower == 2){
        	para.put("wechatNo", chooseWechat);
        	para.put("province", s.getProvince());
        	para.put("storeName", s.getStoreName());
        	List<Map<String, Object>> list = bsh.getMapList("business.interact.rpStatistics.role_power2_query",para);
        	map.put("list", list);
        	Long count = 0l;
        	for(int i=0;i<list.size();i++){
        		count += Long.parseLong(list.get(i).get("count").toString());
        	}
        	map.put("count", count);
        	map.put("sum", 0);
        	return map;
    	}else if(rolePower == 3){
    		para.put("wechatNo", scanWechat);
    		List<Map<String, Object>> list = bsh.getMapList("business.interact.rpStatistics.role_power3_query",para);
    		Long sum = bsh.getLongValue("business.interact.rpStatistics.role_power3_sum", para);
    		map.put("list", list);
    		Long count = 0l;
        	for(int i=0;i<list.size();i++){
        		count += Long.parseLong(list.get(i).get("count").toString());
        	}
        	map.put("count", count);
    		map.put("sum", sum);
    		return map;
    	}else{
    		return map;
    	}
		
    }
	
	@Transactional(readOnly = true)
    public List<Map<String, Object>> getProvince(){
		return bsh.getMapList("business.interact.rpStatistics.getProvince", null);
    }
	@Transactional(readOnly = true)
    public List<Map<String, Object>> getStore(String province){
		Map<String,Object> para = new HashMap<String,Object>();	
		para.put("province", province);
		return bsh.getMapList("business.interact.rpStatistics.getStore", para);
    }
}
