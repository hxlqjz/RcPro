package com.kdgcsoft.power.dao.fw.system;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgcsoft.power.entity.fw.system.HrCDept;


/**   
 * @Title: Dao
 * @Description: 代码生成工具自动生成
 * @date 2017-07-07
 * @version V1.0   
 *
 */
public interface HrCDeptDao extends JpaRepository<HrCDept, Long>{
//###Start 自动生成代码  实体类属性进行查找  如需要 可取消注释 或进行简单修改
//public List<HrCDept> findByDeptId(Long deptId);
//public List<HrCDept> findByOrgCode(String orgCode);
//public List<HrCDept> findByPdeptCode(String pdeptCode);
public List<HrCDept> findByDeptCode(String deptCode);
//public List<HrCDept> findByDeptName(String deptName);
//public List<HrCDept> findByEnName(String enName);
//public List<HrCDept> findByDeptAlias(String deptAlias);
//public List<HrCDept> findByIndustryCat(String industryCat);
//public List<HrCDept> findByWebsite(String website);
//public List<HrCDept> findByProvince(String province);
//public List<HrCDept> findByCity(String city);
//public List<HrCDept> findByZones(String zones);
//public List<HrCDept> findByMgr(String mgr);
//public List<HrCDept> findByLinkman(String linkman);
//public List<HrCDept> findByTel(String tel);
//public List<HrCDept> findByFax(String fax);
//public List<HrCDept> findByMail(String mail);
//public List<HrCDept> findByAddr(String addr);
//public List<HrCDept> findByPostalcode(String postalcode);
//public List<HrCDept> findByDeptCat(String deptCat);
//public List<HrCDept> findByIsOrg(String isOrg);
//public List<HrCDept> findByIsVirtual(String isVirtual);
//public List<HrCDept> findByIsOutsourcing(String isOutsourcing);
//public List<HrCDept> findByRemark(String remark);
//public List<HrCDept> findByIsUse(String isUse);
//public List<HrCDept> findByOrdBy(Long ordBy);
//public List<HrCDept> findByCreateBy(String createBy);
//public List<HrCDept> findByCreateTime(Date createTime);
//public List<HrCDept> findByModifyBy(String modifyBy);
//public List<HrCDept> findByModifyTime(Date modifyTime);

}
