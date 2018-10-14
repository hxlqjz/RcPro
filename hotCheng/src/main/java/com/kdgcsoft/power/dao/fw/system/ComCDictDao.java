package com.kdgcsoft.power.dao.fw.system;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kdgcsoft.power.entity.fw.system.ComCDict;


/**   
 * @Title: Dao
 * @Description: 代码生成工具自动生成
 * @date 2017-07-08
 * @version V1.0   
 *
 */
public interface ComCDictDao extends JpaRepository<ComCDict, Long>{
//###Start 自动生成代码  实体类属性进行查找  如需要 可取消注释 或进行简单修改
	
	public Long countByCatCodeAndIsUse(String catCode, String isUse);
//public List<ComCDict> findByDictId(Long dictId);
	public List<ComCDict> findByCatCodeAndIsUse(String catCode, String isUse);
	
	public List<ComCDict> findByPdictCodeAndIsUse(String pdictCode, String isUse);
	
//public List<ComCDict> findByPdictCode(String pdictCode);
 public List<ComCDict> findByDictCode(String dictCode);
//public List<ComCDict> findByDictName(String dictName);
//public List<ComCDict> findByDictNameEn(String dictNameEn);
//public List<ComCDict> findByMnemonic(String mnemonic);
//public List<ComCDict> findByFullName(String fullName);
//public List<ComCDict> findByRemark(String remark);
//public List<ComCDict> findByUrl(String url);
//public List<ComCDict> findBySrcCode(String srcCode);
//public List<ComCDict> findByIsUse(String isUse);
//public List<ComCDict> findByOrdBy(Long ordBy);
//public List<ComCDict> findByCreateBy(String createBy);
//public List<ComCDict> findByCreateTime(Date createTime);
//public List<ComCDict> findByModifyBy(String modifyBy);
//public List<ComCDict> findByModifyTime(Date modifyTime);

}
