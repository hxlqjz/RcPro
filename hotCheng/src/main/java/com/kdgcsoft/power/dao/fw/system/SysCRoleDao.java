package com.kdgcsoft.power.dao.fw.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgcsoft.power.entity.fw.system.SysCRole;


/**   
 * @Title: Dao
 * @Description: 代码生成工具自动生成
 * @date 2017-06-29
 * @version V1.0   
 *
 */
public interface SysCRoleDao extends JpaRepository<SysCRole, Long>{
//###Start 自动生成代码  实体类属性进行查找  如需要 可取消注释 或进行简单修改
//public List<SysCRole> findByRoleId(Long roleId);
//public List<SysCRole> findByRoleTypCode(String roleTypCode);
public List<SysCRole> findByRoleCode(String roleCode);
//public List<SysCRole> findByRoleName(String roleName);
//public List<SysCRole> findByOrgCode(String orgCode);
//public List<SysCRole> findByIsUsable(String isUsable);
//public List<SysCRole> findByIsPerm(String isPerm);
//public List<SysCRole> findByRemark(String remark);
//public List<SysCRole> findByIsUse(String isUse);
//public List<SysCRole> findByOrdBy(Long ordBy);
//public List<SysCRole> findByCreateBy(String createBy);
//public List<SysCRole> findByCreateTime(Date createTime);
//public List<SysCRole> findByModifyBy(String modifyBy);
//public List<SysCRole> findByModifyTime(Date modifyTime);

}
