package com.kdgcsoft.power.service.business.interact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.dao.interact.PortalCommentsDao;
import com.kdgcsoft.power.entity.business.interact.PortalComments;
import com.kdgcsoft.power.service.fw.base.BaseService;

/**   
 * @Title: Service
 * @Description: 评论列表
 * @date 2017-07-08
 * @version V1.0   
 *
 */
@Component
@Transactional
public class PortalCommentsService extends BaseService{
	@Autowired
	private PortalCommentsDao pcDao;

	public void addCommentsText(PortalComments portalComments) {
		pcDao.save(portalComments);
	}
}
