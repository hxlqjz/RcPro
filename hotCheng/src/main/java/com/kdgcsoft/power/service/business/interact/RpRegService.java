package com.kdgcsoft.power.service.business.interact;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.interact.RpRegDao;
import com.kdgcsoft.power.entity.business.interact.RpReg;
import com.kdgcsoft.power.entity.business.interact.RpStore;
import com.kdgcsoft.power.entity.business.interact.RpUser;
import com.kdgcsoft.power.service.fw.base.BaseService;

@Component
@Transactional
public class RpRegService  extends BaseService{
	
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private RpRegDao rpRegDao;
	
	@Transactional(readOnly = true)
    public RpReg getInfo(String wechatNo){
    	Map<String,Object> para = new HashMap<String,Object>();	  
    	para.put("wechatNo", wechatNo);
    	para.put("time", getNow());
		return bsh.getEntity("business.interact.rpReg.getInfo", para, RpReg.class);
    }
	
	@Transactional(readOnly = true)
    public void insert(RpReg entity){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("wechatNo", entity.getWechatNo());
    	para.put("time", getNow());
    	para.put("num", 1);
		bsh.updateOrDelete("business.interact.rpReg.insert",para);
    }
	
	@Transactional(readOnly = true)
    public void update(RpReg entity){
    	Map<String,Object> para = new HashMap<String,Object>();
    	para.put("wechatNo", entity.getWechatNo());
    	
    	para.put("time", getNow());
    	para.put("num", 2);
		bsh.updateOrDelete("business.interact.rpReg.update",para);
    }
	
	private Date getNow(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String nowdayTime = dateFormat.format(new Date());
		Date nowDate = new Date();
		try {
			nowDate = dateFormat.parse(nowdayTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return nowDate;
	}
	
}
