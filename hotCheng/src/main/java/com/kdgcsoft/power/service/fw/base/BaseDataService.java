package com.kdgcsoft.power.service.fw.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdgcsoft.power.dao.fw.base.BeetlSQLHelper;

@Service
@Transactional
public class BaseDataService {
	
	@Autowired
	private BeetlSQLHelper bsh;
	
	public List<?> getDicData(String dicTypeCode){
		
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("catCode", dicTypeCode);
		return bsh.getList("fw.system.ComCDictCat.getDicData", paras, Map.class);
	}
	
	/**
	 * 根据字典类型及父编码获取字典数据
	 * @param catCode 字典类型
	 * @param pDicCode 父编码
	 */
	public List<?> getDicTreeData(String catCode, String pDicCode){
		
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("catCode", catCode);
		paras.put("pdictCode", pDicCode);
		return bsh.getList("fw.system.ComCDictCat.getDicTreeData", paras, Map.class);
	}
	
	public List<?> getDictCatData(){
		
		return bsh.getList("fw.system.ComCDictCat.getDictCatData", null, Map.class);
	}
	
	public Map<String, Object> getComCDictByTypAndCode(String catCode, String dictCode){
		Map<String, Object> paras = new HashMap<String, Object>();
		paras.put("catCode", catCode);
		paras.put("dictCode", dictCode);
		Map<String, Object> map = bsh.getMap("fw.system.ComCDictCat.getComCDictByTypAndCode", paras);
		return map;
	}


}
