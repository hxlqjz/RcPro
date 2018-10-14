package com.kdgcsoft.power.service.business.interact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.interact.ConfigPageMainDao;
import com.kdgcsoft.power.entity.business.interact.ConfigPageDetail;
import com.kdgcsoft.power.entity.business.interact.ConfigPageMain;
import com.kdgcsoft.power.service.fw.base.BaseService;

/**   
 * @Title: Service
 * @Description: 配置主页面
 * @date 2017-07-08
 * @version V1.0   
 *
 */
@Component
@Transactional
public class ConfigPageMainService  extends BaseService{
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private ConfigPageMainDao mainDao;
	
	// 根据ID查找实体bean
	public ConfigPageMain findConfigPageMain(Long id) {
		ConfigPageMain entity = mainDao.findOne(id);
		return entity;
	}
	
	public void delete(ConfigPageMain e){
		mainDao.delete(e);
	};
	
	// 根据ID查找实体bean
		public ConfigPageMain saveMainPage(ConfigPageMain e) {
			ConfigPageMain entity = mainDao.save(e);
			return entity;
		}
		

		@Transactional(readOnly = true)
	    public List<Map<String, Object>> searchMainPageList ( PageRequest pageRequest){
	    	Map<String,Object> para = new HashMap<String,Object>();	    
			return bsh.getMapList("fw.system.configPage.findPageMainList",para);
	    }
		
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> findByPageSuperior ( String pageSuperior){	    	    
			Map<String,Object> para = new HashMap<String,Object>();	    
			para.put("pageSuperior", pageSuperior);
			return bsh.getMapList("fw.system.configPage.findMainPageBySup",para);
	    }

}
