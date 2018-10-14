package com.kdgcsoft.power.service.fw.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.fw.system.SysCUserRoleDao;
import com.kdgcsoft.power.entity.fw.system.SysCUserRole;
import com.kdgcsoft.power.service.fw.base.BaseService;

@Service
@Transactional
public class SysCUserRoleService extends BaseService{
	
	@Autowired
	private BeetlSQLHelper bsh;
	
	@Autowired
	private SysCUserRoleDao sysCUserRoleDao;
	
	public void save(SysCUserRole entity){
		prepareInsert(entity);
		sysCUserRoleDao.save(entity);
	}
	
	public SysCUserRole findById(Long urId){
		return sysCUserRoleDao.findOne(urId);
	}
	
	public List<SysCUserRole> findByUserCode(String userCode){
		return sysCUserRoleDao.findByUserCode(userCode);
	}
	
	public void delete(Long urId){
		sysCUserRoleDao.delete(urId);
	}
	
	/**
	 * 查询角色列表
	 */
	public PageObject<Map<String,Object>> getRolesList(String roleName, PageRequest pageRequest){
		
		Map<String, Object> paras = new HashMap<>();
		paras.put("roleName", "%" + (roleName == null? "" : roleName) + "%");
		
		PageObject<Map<String,Object>> result = bsh.serachPage("fw.system.SysCUserRole.getRolesList", paras, pageRequest);
		return result;
	}
	
	/**
	 * 查询指定角色已选用户
	 * @param deptCode 部门code
	 * @param roleCode 角色code
	 * @param userName 用户姓名
	 */
	public PageObject<Map<String,Object>> getSelectedUsersList(String deptCode, String roleCode, String userName, PageRequest pageRequest){
		
		Map<String, Object> paras = new HashMap<>();
		paras.put("roleCode", roleCode);
		paras.put("userName", "%" + (userName == null? "" : userName) + "%");
		
		PageObject<Map<String,Object>> result = bsh.serachPage("fw.system.SysCUserRole.getSelectedUsersList", paras, pageRequest);
		return result;
		
	}
	
	/**
	 * 查询指定角色未选用户
	 * @param deptCode 部门code
	 * @param roleCode 角色code
	 * @param userName 用户姓名
	 */
	public PageObject<Map<String,Object>> getUnSelectedUsersList(String deptCode, String roleCode, String userName, PageRequest pageRequest){
		
		Map<String, Object> paras = new HashMap<>();
		paras.put("roleCode", roleCode);
		paras.put("userName", "%" + (userName == null? "" : userName) + "%");
		
		PageObject<Map<String,Object>> result = bsh.serachPage("fw.system.SysCUserRole.getUnSelectedUsersList", paras, pageRequest);
		return result;
		
	}
	
	public PageObject<?> querySelectedRole(String userCode, String roleName, PageRequest pageRequest){
		Map<String,Object> para = new HashMap<String,Object>();
		if(!StringUtil.isEmpty(roleName)){
			para.put("roleName", "%"+roleName+"%");
		}
		para.put("userCode",userCode);
		return bsh.serachPage("fw.system.SysCUserRole.querySelectedRole",para, pageRequest);
	}
	
	public PageObject<?> queryUnselectedRole(String userCode, String roleName, PageRequest pageRequest){
		Map<String,Object> para = new HashMap<String,Object>();
		if(!StringUtil.isEmpty(roleName)){
			para.put("roleName", "%"+roleName+"%");
		}
		para.put("userCode",userCode);
		return bsh.serachPage("fw.system.SysCUserRole.queryUnselectedRole",para, pageRequest);
	}
	
	public Map<String, Object> getUserInfoByUserCode(String userCode){
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("userCode",userCode);
		Map<String, Object> entity = bsh.getMap("fw.system.SysCUserRole.getUserInfoByUserCode", para);
		return entity;
	}

}
