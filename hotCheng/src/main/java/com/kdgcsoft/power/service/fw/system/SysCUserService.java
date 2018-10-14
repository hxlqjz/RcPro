package com.kdgcsoft.power.service.fw.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.bean.Principal;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.common.util.MD5Util;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.fw.system.SysCUserDao;
import com.kdgcsoft.power.dao.fw.system.SysCUserDeptDao;
import com.kdgcsoft.power.entity.fw.system.SysCUser;
import com.kdgcsoft.power.entity.fw.system.SysCUserDept;
import com.kdgcsoft.power.service.fw.base.BaseService;

/**
 * @Title: Service
 * @Description: 代码生成工具自动生成
 * @date 2017-06-27
 * @version V1.0
 * 
 */
@Component
@Transactional
public class SysCUserService extends BaseService{
	@Autowired
	private BeetlSQLHelper bsh;
	@Autowired
	private SysCUserDao sysCUserDao;
	@Autowired
	private SysCUserDeptDao sysCUserDeptDao;

	// 根据ID查找实体bean
	public SysCUser findSysCUser(Long id) {
		SysCUser entity = sysCUserDao.findOne(id);
		return entity;
	}

	// 持久化实体bean
	public Map<String,Object> saveSysCUser(Map<String, String[]> map, SysCUser entity,
			Class<SysCUser> cls) {
//		String orgCode = map.get("orgCode")[0];
//		String deptCode = map.get("deptCode")[0];
		Map<String,Object> maps=new HashMap<String,Object>();
		if (StringUtil.isEmpty(entity.getUserId())) {
			entity.setPswd(MD5Util.getSaltMd5(entity.getPswd(), SystemConstants.PASS_SALT));
			saveSysCUserDept(entity.getUserCode(), null);
		} else {
			SysCUser oldEntity = sysCUserDao.findOne(entity.getUserId());
			saveSysCUserDept(entity.getUserCode(),
					oldEntity.getUserCode());
		}
		saveEntity(sysCUserDao, map, entity);
		maps.put("userId", entity.getUserId());
		return maps;
	}

	// 删除实体bean
	public void delSysCUser(Long id) {
		delEntity(id, sysCUserDao,null);
	}

	@Transactional(readOnly = true)
	public PageObject<SysCUser> searchSysCUser(String userName, PageRequest pageRequest) {
		if(StringUtil.isEmpty(userName)){
			userName = "";
		}
		Page<SysCUser> query = sysCUserDao.findByUserNameLikeAndIsUseOrderByOrdByAsc("%"+ userName +"%", "Y", pageRequest);
		
		PageObject<SysCUser> obj = new PageObject<>(query);
		return obj;
	}
	
	@Transactional(readOnly = true)
    public List<Map<String, Object>> searchSysCUser2 (String deptCode , String userName , PageRequest pageRequest){
    	Map<String,Object> para = new HashMap<String,Object>();
    	if(!StringUtil.isEmpty(deptCode)){
			para.put("deptCode", deptCode);
		}
    	if(!StringUtil.isEmpty(userName)){
    		para.put("userName", "%"+userName+"%");
    	}
		return bsh.getMapList("fw.system.SysCUser.select",para);
    }

	
	/**
	 * 判断当前用户编码是否已经存在
	 * @param userCode 角色编码
	 * @param userId 用户id（若新增时，传null）
	 * @return 已存在返回true,否则返回false
	 */

	public void updateUserPswd(SysCUser entity,String pass) {
		SysCUser oldEntity = sysCUserDao.findOne(entity.getUserId());
		String ps0= oldEntity.getPswd();
		oldEntity.setPswd(MD5Util.getSaltMd5(pass,SystemConstants.PASS_SALT));
		String ps= oldEntity.getPswd();
		sysCUserDao.save(oldEntity);
	}
	
	@Transactional(readOnly = true)
	public SysCUserDept findSysCUserDeptByUserCode(String userCode){
		List<SysCUserDept> list = sysCUserDeptDao.findByUserCode(userCode);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}else{
			return new SysCUserDept();
		}
	}
	
	public void deleteSysCUserDeptByUserCode(String userCode){
		List<SysCUserDept> entities = sysCUserDeptDao.findByUserCode(userCode);
		sysCUserDeptDao.delete(entities);
	}
	
//	public void saveSysCUserDept(String orgCode, String deptCode, String newUserCode, String oldUserCode){
	public void saveSysCUserDept(String newUserCode, String oldUserCode){

		if(!newUserCode.equals(oldUserCode)){
			//修改了工号  删除旧工号对应机构信息
			deleteSysCUserDeptByUserCode(oldUserCode);
		}
		SysCUserDept entity = findSysCUserDeptByUserCode(newUserCode);
		if(entity == null || entity.getUdId() == null){
			entity = new SysCUserDept();
			entity.setOrdBy(0L);
		}
		entity.setUserCode(newUserCode);
		saveEntity(sysCUserDeptDao, null, entity);
	}
	
	@Transactional(readOnly = true)
	public SysCUser findByUserCodeAndIsUse(String userCode){
		List<SysCUser> list = sysCUserDao.findByUserCodeAndIsUse(userCode, "Y");
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	/**
	 * 通过部门Id查询用户
	 * @param userCode
	 * @param deptId
	 * @param page
	 * @param rows
	 * @return
	 */
	@Transactional(readOnly = true)
    public PageObject<Map<String, Object>> getUserListByDeptCode (String deptCode, String userName, String flag, PageRequest pageRequest){
		String sqlId;
    	Map<String,Object> para = new HashMap<String,Object>();
    	if("0".equals(flag)){
    		sqlId = "fw.system.SysCRole.getUsersByDeptCode";
		}else{
			sqlId = "fw.system.SysCRole.getUserListByDeptCode";
		}
    	if(!StringUtil.isEmpty(deptCode)){
    		para.put("deptCode",deptCode);
		}
    	if(!StringUtil.isEmpty(userName)){
			para.put("userName", "%"+userName+"%");
		}
		return bsh.serachPage(sqlId, para, pageRequest);
    }

    @Transactional(readOnly = true)
	public Map<String, Object> queryIsItemCompany() {
    	Map<String,Object> para = new HashMap<String,Object>();
		Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
		para.put("userCode", principal.getUserCode());
		return bsh.getMap("fw.system.SysCRole.queryIsItemCompany", para);
	}
    
	@Transactional(readOnly = true)
    public SysCUser findByUserCode(String userCode){
		SysCUser en=new SysCUser();
		List<SysCUser> list = sysCUserDao.findByUserCode(userCode);
		if(list.size()>0){
			en=list.get(0);
		}
		return en;
	}
	public SysCUser saveUser(SysCUser e){
		sysCUserDao.save(e);
		return e;
	}
}