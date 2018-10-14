package com.kdgcsoft.power.service.business.interact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.interact.MySnapDao;
import com.kdgcsoft.power.entity.business.interact.MySnap;
import com.kdgcsoft.power.service.fw.base.BaseService;

/**   
 * @Title: Service
 * @Description: 我要拍service
 * @date 2017-07-08
 * @version V1.0   
 *
 */
@Component
@Transactional
public class MySnapService extends BaseService{
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private MySnapDao msDao;
	
	//保存我要拍的信息
	public MySnap SaveMySnap(MySnap e){
		e=msDao.save(e);
		return e;
	};
	
	public List<Map<String,Object>> getMySnapList(){
		Map<String,Object> para = new HashMap<String,Object>();		
		 List<Map<String,Object>> ls=bsh.getMapList("business.interact.mySnapSql.findMySnapList", para);
		 return ls;		
	}

}
