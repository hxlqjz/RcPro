package com.kdgcsoft.power.service.fw.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.common.bean.EasyUITreeNode;
import com.kdgcsoft.power.common.bean.Principal;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.fw.system.SysCMenuDao;
import com.kdgcsoft.power.entity.fw.system.SysCMenu;
import com.kdgcsoft.power.service.fw.base.BaseService;

@Service
@Transactional
public class SysCMenuService extends BaseService{
	
	@Autowired
	private SysCMenuDao sysCMenuDao;
	@Autowired
	private BeetlSQLHelper bsh;
	

	public List<EasyUITreeNode> getFileTreeListByRoleIds(String pmenuCode, List<String> roleCodes){
		
		Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
		
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("pmenuCode", pmenuCode);
		paras.put("superAdmin", SystemConstants.SUPER_ADMIN);
		paras.put("userCode", principal.getUserCode());
		paras.put("roleCodesStr", roleCodes);
		
		List<EasyUITreeNode> list = bsh.getList("fw.system.SysCMenu.getFileTreeListByRoleIds", paras, EasyUITreeNode.class);
		
		
		return list;
	}
	
	public String getMenuPath(String menuId){
		
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("menuId", menuId);
		String path = bsh.getStringlValue("fw.system.SysCMenu.getMenuPath", paras);
		
		return path;
	}

	public List<EasyUITreeNode> getSysCMenuEasyUI(String node) {
		
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("pmenuCode", node);
		List<EasyUITreeNode> list = bsh.getList("fw.system.SysCMenu.getSysCMenuEasyUI", paras, EasyUITreeNode.class);
		
		return list;
	}

	public SysCMenu findSysCMenuById(Long id) {
		return sysCMenuDao.findOne(id);
	}

	public SysCMenu saveSysCMenu(SysCMenu entity) {
		return sysCMenuDao.save(entity);
	}

	public void deleteSysCMenu(Long id) {
		SysCMenu entity=sysCMenuDao.findOne(id);
		entity.setIsUse(SystemConstants.IS_USE_N);
		sysCMenuDao.save(entity);
	}
	
	public List<EasyUITreeNode> getSysCMenuTreeByRole(String node, String roleCode) {

		Map<String, Object> paras = new HashMap<>();
		paras.put("roleCode", roleCode);
		paras.put("pmenuCode", node);
		List<EasyUITreeNode> list = bsh.getList("fw.system.SysCMenu.getSysCMenuTreeByRole", paras, EasyUITreeNode.class);
		
		return list;
	}

	
	/**
	 * 根据点击的节点查询子节点
	 * @param menuCode  菜单code
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<EasyUITreeNode> findChildByMenuCode(String menuCode,List<String> roleCodes) {
		if(roleCodes == null || roleCodes.size() == 0){
			return new ArrayList<EasyUITreeNode>();
		}
        Principal principal = (Principal)SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("pmenuCode", menuCode);
		paras.put("superAdmin", SystemConstants.SUPER_ADMIN);
		paras.put("userCode", principal.getUserCode());
		paras.put("roleCodesStr", roleCodes);
		List<EasyUITreeNode> list = bsh.getList("fw.system.SysCMenu.queryChildByCode", paras, EasyUITreeNode.class);
		return list;
	}


}
