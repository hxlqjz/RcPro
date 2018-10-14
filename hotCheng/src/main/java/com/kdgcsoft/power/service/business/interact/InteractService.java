package com.kdgcsoft.power.service.business.interact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hp.hpl.sparta.ParseException;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.entity.business.interact.PpJPublisthNews;
import com.kdgcsoft.power.service.fw.base.BaseService;

/**   
 * @Title: Service
 * @Description: 交流互动的代码
 * @date 2017-07-08
 * @version V1.0   
 *
 */
@Component
@Transactional
public class InteractService extends BaseService{
	@Autowired
	private BeetlSQLHelper bsh;
	

	public List<Map<String,Object>> getImportantNewList(Long empId) throws ParseException {
		List<PpJPublisthNews> returnList=new ArrayList<PpJPublisthNews>();		
		Map<String,Object> para = new HashMap<String,Object>();	
		para.put("toPraiseBy", empId);
		 List<Map<String,Object>> ls=bsh.getMapList("business.interact.interactSql.findNewsList", para);	
		return ls;
	}
	
	public Map<String,Object> findPublishInfoById(String newsId){	
		Map<String,Object> para = new HashMap<String,Object>();	
		para.put("newsId", newsId);
		 Map<String,Object> rec=bsh.getMap("business.interact.interactSql.findTheNewsDetail", para);	
		return rec;
	};
	
	public List<Map<String,Object>> getAllPortalCommentsByNewsId(String newsId,String empId){	
		Map<String,Object> para = new HashMap<String,Object>();	
		para.put("newsId", newsId);
		para.put("empId", empId);
	   List<Map<String,Object>> rec=bsh.getMapList("business.interact.interactSql.findAllComments", para);	
		return rec;
	};
	
}
