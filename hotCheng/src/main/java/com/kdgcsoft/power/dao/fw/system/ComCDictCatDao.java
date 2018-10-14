package com.kdgcsoft.power.dao.fw.system;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdgcsoft.power.entity.fw.system.ComCDictCat;


/**   
 * @Title: Dao
 * @Description: 代码生成工具自动生成
 * @date 2017-07-08
 * @version V1.0   
 *
 */
public interface ComCDictCatDao extends JpaRepository<ComCDictCat, Long>{
//###Start 自动生成代码  实体类属性进行查找  如需要 可取消注释 或进行简单修改
//public List<ComCDictCat> findByCatId(Long catId);
public List<ComCDictCat> findByCatCodeAndIsUse(String catCode, String isUse);
//public List<ComCDictCat> findByCatName(String catName);
//public List<ComCDictCat> findByCatTyp(String catTyp);
//public List<ComCDictCat> findByDataAttr(String dataAttr);
//public List<ComCDictCat> findByRemark(String remark);
//public List<ComCDictCat> findByIsUse(String isUse);
//public List<ComCDictCat> findByOrdBy(Long ordBy);
//public List<ComCDictCat> findByCreateBy(String createBy);
//public List<ComCDictCat> findByCreateTime(Date createTime);
//public List<ComCDictCat> findByModifyBy(String modifyBy);
//public List<ComCDictCat> findByModifyTime(Date modifyTime);

}
