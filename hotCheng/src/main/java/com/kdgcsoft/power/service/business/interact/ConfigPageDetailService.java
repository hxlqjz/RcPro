package com.kdgcsoft.power.service.business.interact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.interact.ConfigPageDetailDao;
import com.kdgcsoft.power.entity.business.interact.ConfigPageDetail;
import com.kdgcsoft.power.entity.business.interact.ConfigPageMain;
import com.kdgcsoft.power.service.fw.base.BaseService;

/**   
 * @Title: Service
 * @Description: 配置明细页面
 * @date 2017-07-08
 * @version V1.0   
 *
 */
@Component
@Transactional
public class ConfigPageDetailService extends BaseService{
	@Autowired
	private BeetlSQLHelper bsh;
	
	@Autowired
	private ConfigPageDetailDao detailDao;
	
	public ConfigPageDetail saveDetail(ConfigPageDetail e){
		 e = detailDao.save(e);
		 return e;
	};
	
	// 根据ID查找实体bean
		public ConfigPageDetail findConfigDetail(Long id) {
			ConfigPageDetail entity = detailDao.findOne(id);
			return entity;
		}
		
		public void delete(ConfigPageDetail e){
			detailDao.delete(e);
		};
		
		@Transactional(readOnly = true)
	    public List<Map<String, Object>> searchDetailPageList ( PageRequest pageRequest,String pageId){
	    	Map<String,Object> para = new HashMap<String,Object>();	    
	    	para.put("pageId", pageId);
			return bsh.getMapList("fw.system.configPage.findPageDetailList",para);
	    }


		@Transactional(readOnly = true)
	    public List<ConfigPageDetail> findByPageId (Long pageId){
			return detailDao.findByPageId(pageId);
	    }
}
