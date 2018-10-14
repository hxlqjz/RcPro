package com.kdgcsoft.power.dao.fw.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgcsoft.power.entity.fw.system.SysCUserRole;


/**   
 * @Title: Dao
 * @Description: 代码生成工具自动生成
 * @date 2017-06-27
 * @version V1.0   
 *
 */
public interface SysCUserRoleDao extends JpaRepository<SysCUserRole, Long>{
//###Start 自动生成代码  实体类属性进行查找  如需要 可取消注释 或进行简单修改
//public List<SysCUserRole> findByUrId(Long urId);
public List<SysCUserRole> findByUserCode(String userCode);
//public List<SysCUserRole> findByRoleCode(String roleCode);
//public List<SysCUserRole> findByOrdBy(Long ordBy);
//public List<SysCUserRole> findByCreateBy(String createBy);
//public List<SysCUserRole> findByCreateTime(Date createTime);
//public List<SysCUserRole> findByModifyBy(String modifyBy);
//public List<SysCUserRole> findByModifyTime(Date modifyTime);

}
