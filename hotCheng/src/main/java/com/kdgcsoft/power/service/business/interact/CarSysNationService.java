package com.kdgcsoft.power.service.business.interact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.interact.CarSysNationDao;
import com.kdgcsoft.power.entity.business.interact.CarSysNation;
import com.kdgcsoft.power.service.fw.base.BaseService;

/**   
 * @Title: Service
 * @Description: 消息日志的service
 * @date 2017-07-08
 * @version V1.0   
 *
 */
@Component
@Transactional
public class CarSysNationService extends BaseService{
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private CarSysNationDao carSysNationDao;
	
	@Transactional(readOnly = true)
    public List<Map<String, Object>> searchMainPageList ( PageRequest pageRequest){
    	Map<String,Object> para = new HashMap<String,Object>();	    
		return bsh.getMapList("business.interact.CarSysNation.findPageCarSysList",para);
    }
	
	// 根据ID查找实体bean
		public CarSysNation findCarSysNationDao(Long id) {
			CarSysNation entity = carSysNationDao.findOne(id);
			return entity;
		}
		
       //保存
		public CarSysNation saveMainPage(CarSysNation e) {
			CarSysNation entity = carSysNationDao.save(e);
			return entity;
		}
		//删除
		public void delete(CarSysNation e){
			carSysNationDao.delete(e);
		};
		
		  public List<Map<String, Object>> findAllCarSysNation (){
		    	Map<String,Object> para = new HashMap<String,Object>();	    
				return bsh.getMapList("business.interact.CarSysNation.findAllCarBrandList",para);
		    }

}
