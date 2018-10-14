package com.kdgcsoft.power.service.fw.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.service.fw.base.BaseService;
import com.kdgcsoft.power.entity.fw.system.FwOpmonitor;
import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.fw.system.FwOpmonitorDao;

/**   
 * @Title: Service
 * @Description: 代码生成工具自动生成
 * @date 2017-07-26
 * @version V1.0   
 *
 */
@Component
@Transactional
public class FwOpmonitorService extends BaseService{
	@Autowired
	private FwOpmonitorDao fwOpmonitorDao;
	@Autowired
	private BeetlSQLHelper bsh;
	
	public void save(FwOpmonitor entity){
		fwOpmonitorDao.save(entity);
	}
	
	public PageObject<Map<String, Object>> searchFwOpmonitor(String operateBy, String actionDesc, String startDate, String endDate, PageRequest pageRequest){
		Map<String, Object> paras = new HashMap<>();
		paras.put("startDate", ("".equals(startDate) ? null : startDate));
		paras.put("endDate", ("".equals(endDate) ? null : endDate));
		paras.put("operateBy", "%"+ (operateBy == null ? "" : operateBy) +"%");
		paras.put("actionDesc", "%"+ (actionDesc == null ? "" : actionDesc) +"%");
		
		PageObject<Map<String, Object>> result = bsh.serachPage("fw.system.FwOpmonitor.searchFwOpmonitor", paras, pageRequest);
		return result;
	}
	
}