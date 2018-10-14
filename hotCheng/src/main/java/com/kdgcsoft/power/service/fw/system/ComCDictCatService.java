package com.kdgcsoft.power.service.fw.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.common.bean.EasyUITreeNode;
import com.kdgcsoft.power.common.bean.PageObject;
import com.kdgcsoft.power.common.bean.SystemConstants;
import com.kdgcsoft.power.common.cache.CacheNames;
import com.kdgcsoft.power.common.util.StringUtil;
import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;
import com.kdgcsoft.power.dao.fw.system.ComCDictCatDao;
import com.kdgcsoft.power.dao.fw.system.ComCDictDao;
import com.kdgcsoft.power.entity.fw.system.ComCDict;
import com.kdgcsoft.power.entity.fw.system.ComCDictCat;
import com.kdgcsoft.power.service.fw.base.BaseService;

/**   
 * @Title: Service
 * @Description: 代码生成工具自动生成
 * @date 2017-07-08
 * @version V1.0   
 *
 */
@Component
@Transactional
public class ComCDictCatService extends BaseService{
	@Autowired
	private ComCDictCatDao comCDictCatDao;
	@Autowired
	private ComCDictDao comCDictDao;
	@Autowired
	private BeetlSQLHelper bsh;
	
	//根据ID查找实体bean
	public ComCDictCat findComCDictCat(Long id){
		ComCDictCat entity=comCDictCatDao.findOne(id);
		return entity;
	}
	
	//持久化实体bean
	@CacheEvict(value=CacheNames.DICTIONARY, allEntries=true)
	public void saveComCDictCat(Map<String,String[]> map, ComCDictCat entity){
		if(!StringUtil.isEmpty( entity.getCatId())){
			ComCDictCat model = comCDictCatDao.findOne(entity.getCatId());
			if(!model.getCatCode().equals(entity.getCatCode())){
				//当修改字典分类编码时，同时修改字典数据表中对应的分类编码
				updateCatCode(entity.getCatCode(), model.getCatCode()); 
			}
		} 
		saveEntity(comCDictCatDao, map, entity);
	}
	
	
	//删除实体bean
	@CacheEvict(value=CacheNames.DICTIONARY, allEntries=true)
	public void delComCDictCat(Long id){
		ComCDictCat entity=comCDictCatDao.findOne(id);
		entity.setIsUse(SystemConstants.IS_USE_N);
		comCDictCatDao.save(entity);
		
		//删除对应的字典数据
		List<ComCDict> list = comCDictDao.findByCatCodeAndIsUse(entity.getCatCode(), "Y");
		for(ComCDict model : list){
			model.setIsUse(SystemConstants.IS_USE_N);
			comCDictDao.save(model);
		}
	}
	
	@Transactional(readOnly=true)
	public PageObject<Map<String, Object>> searchComCDictCat(String catName, PageRequest pageRequest){
		
		catName = "%" + (catName == null ? "" : catName) + "%";
		
		Map<String, Object> paras = new HashMap<>();
		paras.put("catName", catName);
		
		PageObject<Map<String, Object>> result = bsh.serachPage("fw.system.ComCDictCat.searchComCDictCat", paras, pageRequest);
		return result;
	}
	
	/******************************** 字典数据维护****************************/
	@CacheEvict(value=CacheNames.DICTIONARY, allEntries=true)
	public ComCDict saveComCDict(Map<String,String[]> map, ComCDict entity){
		entity = saveEntity(comCDictDao, map, entity);
		return entity;
	}
	
	@CacheEvict(value=CacheNames.DICTIONARY, allEntries=true)
	public void saveComCDicts(List<ComCDict> entities, String userCode){
		
		for(ComCDict entity : entities){
			if(entity.getDictId() == null){
				entity.setPdictCode("0");
				prepareInsert(entity);
			}else{
				entity.setModifyBy(userCode);
			}
			saveEntity(comCDictDao, null, entity);
		}
	}
	
	@CacheEvict(value=CacheNames.DICTIONARY, allEntries=true)
	public void deleteComCDict(Long dictId){
		ComCDict model = comCDictDao.findOne(dictId);
		model.setIsUse(SystemConstants.IS_USE_N);
		comCDictDao.save(model);
	}
	
	@CacheEvict(value=CacheNames.DICTIONARY, allEntries=true)
	public void deleteComCDictTreeNode(Long dictId){
		ComCDict model = comCDictDao.findOne(dictId);
		model.setIsUse(SystemConstants.IS_USE_N);
		comCDictDao.save(model);
		
		List<ComCDict> list = comCDictDao.findByPdictCodeAndIsUse(model.getDictCode(), "Y");
		for(ComCDict entity : list){
			entity.setIsUse(SystemConstants.IS_USE_N);
			comCDictDao.save(entity);
		}
	}
	
	/**
	 * 当修改字典分类编码时，同时修改字典数据表中对应的分类编码
	 * @param newCatCode 新编码
	 * @param oldCatCode 旧编码
	 */
	@CacheEvict(value=CacheNames.DICTIONARY, allEntries=true)
	public void updateCatCode(String newCatCode, String oldCatCode){
		List<ComCDict> list = comCDictDao.findByCatCodeAndIsUse(oldCatCode, "Y");
		for (ComCDict comCDict : list) {
			comCDict.setCatCode(newCatCode);
			comCDictDao.save(comCDict);
		}
	}
	
	@Transactional(readOnly=true)
	public ComCDict findComCDictById(Long dictId){
		return comCDictDao.findOne(dictId);
	}
	
	@Transactional(readOnly=true)
	public PageObject<Map<String, Object>> getComCDictList(String dictName, String catCode, PageRequest pageRequest){
		dictName = "%" + (dictName == null ? "" : dictName) + "%";
		
		Map<String, Object> paras = new HashMap<>();
		paras.put("dictName", dictName);
		paras.put("catCode", catCode);
		
		PageObject<Map<String, Object>> result = bsh.serachPage("fw.system.ComCDictCat.getComCDictList", paras, pageRequest);
		return result;
	}
	
	@Transactional(readOnly=true)
	@Cacheable(value=CacheNames.DICTIONARY)
	public List<EasyUITreeNode> getComCDictTree(String catCode, String node){
		List<EasyUITreeNode> list = new ArrayList<EasyUITreeNode>();
		if(StringUtil.isEmpty(node)){
			List<ComCDictCat> rootList = comCDictCatDao.findByCatCodeAndIsUse(catCode, "Y");
			if(rootList != null && !rootList.isEmpty()){
				ComCDictCat model = rootList.get(0);
				EasyUITreeNode rootNode = new EasyUITreeNode();
				rootNode.setId(model.getCatId().toString());
				rootNode.setCode("0");
				rootNode.setText(model.getCatName());
				
				Long count = comCDictDao.countByCatCodeAndIsUse(catCode, "Y");
				if(count > 0){
					rootNode.setState("closed");
				}else{
					rootNode.setState("open");
				}
				
				list.add(rootNode);
			}
		}else{
			Map<String, Object> paras = new HashMap<String, Object>();
			paras.put("pdictCode", node);
			paras.put("catCode", catCode);
			list = bsh.getList("fw.system.ComCDictCat.getComCDictTree", paras, EasyUITreeNode.class);
		}
		
		return list;
	}
	
	/**
	 * 获取全部数据字典
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<ComCDictCat> findAllComCDictCat(){
		List<ComCDictCat> dictCatList=new ArrayList<ComCDictCat>();
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("isUse", "Y");
		dictCatList = bsh.getList("fw.system.ComCDictCat.searchComCDictCat", paras, ComCDictCat.class);
		return dictCatList;
	}

	/**
	 * 根据数据字典编码获取对于数据
	 * @param dictCode
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<ComCDict> findAllComCDictByCode(String catCode){
		List<ComCDict> list = comCDictDao.findByCatCodeAndIsUse(catCode, "Y");
		return list;
	}
	
	/**
	 * 根据数据字典编码获取对于数据
	 * @param dictCode
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<ComCDict> findAllComCDictByDictCode(String dictCode){
		List<ComCDict> list = comCDictDao.findByDictCode(dictCode);
		return list;
	}
	
	@Transactional(readOnly=true)
	@Cacheable(value=CacheNames.DICTIONARY, key="#dictCatCode")
	public Map<String ,Object> findDictMap(String dictCatCode) {
		List<ComCDict> dictList = this.findAllComCDictByCode(dictCatCode);
		Map<String ,Object> dictMap = new HashMap<String ,Object>();
		for(ComCDict dict:dictList){
			dictMap.put(dict.getDictCode(), dict.getDictName());
		}
		return dictMap;
	}
}