package com.kdgcsoft.power.dao.fw.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgcsoft.power.entity.fw.system.SysCRolePerm;


/**   
 * @Title: Dao
 * @Description: 代码生成工具自动生成
 * @date 2017-06-27
 * @version V1.0   
 *
 */
public interface SysCRolePermDao extends JpaRepository<SysCRolePerm, Long>{
//###Start 自动生成代码  实体类属性进行查找  如需要 可取消注释 或进行简单修改
//public List<SysCRolePerm> findByRpId(Long rpId);
public List<SysCRolePerm> findByRoleCode(String roleCode);
//public List<SysCRolePerm> findByPermCode(String permCode);
//public List<SysCRolePerm> findByIsUsable(String isUsable);
//public List<SysCRolePerm> findByIsPerm(String isPerm);
//public List<SysCRolePerm> findByRemark(String remark);
//public List<SysCRolePerm> findByOrdBy(Long ordBy);
//public List<SysCRolePerm> findByCreateBy(String createBy);
//public List<SysCRolePerm> findByCreateTime(Date createTime);
//public List<SysCRolePerm> findByModifyBy(String modifyBy);
//public List<SysCRolePerm> findByModifyTime(Date modifyTime);

}
