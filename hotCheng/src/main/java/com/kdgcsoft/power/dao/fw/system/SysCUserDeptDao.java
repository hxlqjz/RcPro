package com.kdgcsoft.power.dao.fw.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgcsoft.power.entity.fw.system.SysCUserDept;


/**   
 * @Title: Dao
 * @Description: 代码生成工具自动生成
 * @date 2017-06-27
 * @version V1.0   
 *
 */
public interface SysCUserDeptDao extends JpaRepository<SysCUserDept, Long>{
//###Start 自动生成代码  实体类属性进行查找  如需要 可取消注释 或进行简单修改
//public List<SysCUserDept> findByUdId(Long udId);
//public List<SysCUserDept> findByOrgCode(String orgCode);
//public List<SysCUserDept> findByDeptCode(String deptCode);
public List<SysCUserDept> findByUserCode(String userCode);
//public List<SysCUserDept> findByIsMain(String isMain);
//public List<SysCUserDept> findByOrdBy(Long ordBy);
//public List<SysCUserDept> findByCreateBy(String createBy);
//public List<SysCUserDept> findByCreateTime(Date createTime);
//public List<SysCUserDept> findByModifyBy(String modifyBy);
//public List<SysCUserDept> findByModifyTime(Date modifyTime);

}
