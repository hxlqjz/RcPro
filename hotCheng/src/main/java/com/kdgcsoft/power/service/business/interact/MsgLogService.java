package com.kdgcsoft.power.service.business.interact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.interact.MsgLogDao;
import com.kdgcsoft.power.entity.business.interact.MsgLog;
import com.kdgcsoft.power.service.fw.base.BaseService;

import antlr.StringUtils;

/**   
 * @Title: Service
 * @Description: 消息日志的service
 * @date 2017-07-08
 * @version V1.0   
 *
 */
@Component
@Transactional
public class MsgLogService extends BaseService{
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private MsgLogDao mlDao;
	
	public List<Map<String,Object>> getMsgLogList(String userCode){
		Map<String,Object> para = new HashMap<String,Object>();	
		if(StringUtil.isNotEmpty(userCode)){
			para.put("userCode", userCode);			
		}		
		 List<Map<String,Object>> ls=bsh.getMapList("business.interact.msgLogSql.findMsgLogList", para);
		 return ls;
		
	}
	
	public void saveMsgLog(MsgLog e){
		mlDao.save(e);
	}; 
	
	
	public MsgLog findMsgLogByUsercode(String userCode,String type){
		MsgLog e=new MsgLog();
		List<MsgLog> ls=mlDao.findByUserCodeAndType(userCode,type);
		if(ls.size()>0){
			e=ls.get(0);
		}
		return e;
	};
	
	public void deleteMsgLogByUsercode(String userCode,String type){
		MsgLog e=new MsgLog();
		List<MsgLog> ls=mlDao.findByUserCodeAndType(userCode,type);
		if(ls.size()>0){
			e=ls.get(0);
			mlDao.delete(e);
		}		
	};

}
