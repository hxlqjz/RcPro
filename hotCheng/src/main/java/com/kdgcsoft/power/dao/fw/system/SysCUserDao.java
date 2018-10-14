package com.kdgcsoft.power.dao.fw.system;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgcsoft.power.entity.fw.system.SysCUser;


/**   
 * @Title: Dao
 * @Description: 代码生成工具自动生成
 * @date 2017-06-27
 * @version V1.0   
 *
 */
public interface SysCUserDao extends JpaRepository<SysCUser, Long>{
//###Start 自动生成代码  实体类属性进行查找  如需要 可取消注释 或进行简单修改
//public List<SysCUser> findByUserId(Long userId);
public List<SysCUser> findByUserCode(String userCode);
public List<SysCUser> findByUserCodeAndIsUse(String userCode, String isUse);
public Page<SysCUser> findByUserNameLikeAndIsUseOrderByOrdByAsc(String userName, String isUse, Pageable pageable);
//public List<SysCUser> findByUserName(String userName);
//public List<SysCUser> findByPswd(String pswd);
//public List<SysCUser> findByUserAcnt(String userAcnt);
//public List<SysCUser> findByThm(String thm);
//public List<SysCUser> findByTel(String tel);
//public List<SysCUser> findByMobile(String mobile);
//public List<SysCUser> findByMail(String mail);
//public List<SysCUser> findByIsLongterm(String isLongterm);
//public List<SysCUser> findByEnableTime(Date enableTime);
//public List<SysCUser> findByInvalidTime(Date invalidTime);
//public List<SysCUser> findByIsUse(String isUse);
//public List<SysCUser> findByUserTyp(String userTyp);
//public List<SysCUser> findByEmpCode(String empCode);
//public List<SysCUser> findByRemark(String remark);
//public List<SysCUser> findByOrdBy(Long ordBy);
//public List<SysCUser> findByCreateBy(String createBy);
//public List<SysCUser> findByCreateTime(Date createTime);
//public List<SysCUser> findByModifyBy(String modifyBy);
//public List<SysCUser> findByModifyTime(Date modifyTime);

}
