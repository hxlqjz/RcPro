package com.kdgcsoft.power.dao.fw.system;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgcsoft.power.entity.fw.system.SysCPerm;


/**   
 * @Title: Dao
 * @Description: 代码生成工具自动生成
 * @date 2017-06-27
 * @version V1.0   
 *
 */
public interface SysCPermDao extends JpaRepository<SysCPerm, Long>{
//###Start 自动生成代码  实体类属性进行查找  如需要 可取消注释 或进行简单修改
//public List<SysCPerm> findByPermId(Long permId);
	public List<SysCPerm> findByMenuCode(String menuCode);
//public List<SysCPerm> findByPermCode(String permCode);
//public List<SysCPerm> findByPermName(String permName);
//public List<SysCPerm> findByPermTyp(String permTyp);
//public List<SysCPerm> findByRemark(String remark);
//public List<SysCPerm> findByOrdBy(Long ordBy);
//public List<SysCPerm> findByCreateBy(String createBy);
//public List<SysCPerm> findByCreateTime(Date createTime);
//public List<SysCPerm> findByModifyBy(String modifyBy);
//public List<SysCPerm> findByModifyTime(Date modifyTime);

}
