package com.kdgcsoft.power.service.fw.base;

import java.util.List;
import java.util.Map;

import org.hibernate.annotations.common.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdgcsoft.power.service.fw.system.ComCDictCatService;

/**
 * 基于数据字典的键值转换服务类
 * @author hling
 *
 */
@Service
public class DictService {

	private final static Logger logger = LoggerFactory.getLogger(DictService.class);
	
	@Autowired
	private ComCDictCatService comCDictCatService;

	/**
	 * 获取字典值
	 * 
	 * @param dictCatCode
	 *            字典分类编码
	 * @param dictCode
	 *            字典编码
	 * @return
	 */
	public String getDictValue(String dictCatCode, String dictCode) {
		String dictName = "";
		
		Map<String, Object> dictMap = comCDictCatService.findDictMap(dictCatCode);
		if (dictMap.get(dictCode) != null) {
			dictName = dictMap.get(dictCode).toString();
		} else {
			logger.error("getDictValue：未找到对应的字典值！字典分类：{}，字典编码：{}", dictCatCode, dictCode);
		}
		return dictName;
	}

	/**
	 * 根据字典更新List<Map>中的字典名称成员
	 * 
	 * @param list 更新对象Map
	 * @param catCode
	 *            字典分类编码
	 * @param dictCode
	 *            Map中存储字典key的主键名。该主键名+“text"组合是更新对象成员的key。
	 * @return
	 */
	public void updateListWithDict(
			List<Map<String, Object>> list, String catCode, String dictCode) {
		// 返回的目标字段名称为源字段+"text"
		String dictName = dictCode + "text";
		if (StringHelper.isNotEmpty(catCode)
				&& StringHelper.isNotEmpty(dictCode)) {
			for (Map<String, Object> entity : list) {
				// 从列表中查询源字段数据
				Map<String, Object> map = (Map<String, Object>) entity;
				if (map.containsKey(dictCode) && map.get(dictCode) != null) {
					String code = map.get(dictCode).toString();
					String name = getDictValue(catCode, code);
					map.put(dictName, name);
				}
			}
		} else {
			logger.error("updateListWithDict参数不能为空！");
		}
	}

	/**
	 * 根据字典更新List<Map>中的字典名称成员（同时处理多个）
	 * 
	 * @param list 更新对象Map
	 * @param catCode
	 *            字典分类编码集合，用逗号分隔
	 * @param dictCode
	 *            Map中更新目标的主键名，用逗号分隔。每个主键名+“text"组合是更新对象成员的key。
	 * @return
	 */
	public void updateListWithDictMultiple(
			List<Map<String, Object>> list, String catCodes, String dictCodes) {
		if (StringHelper.isNotEmpty(catCodes)
				&& StringHelper.isNotEmpty(dictCodes)) {
			String[] catCodesArray = catCodes.split(",");
			int count1 = catCodesArray.length;
			String[] dictCodesArray = dictCodes.split(",");
			int count2 = dictCodesArray.length;
			if (count1 != count2) {
				logger.error("updateListWithDictMultiple缓存查询：传入的参数数量有误！");
			} else {
				for (int i = 0; i < count1; i++) {
					updateListWithDict(list, catCodesArray[i],	dictCodesArray[i]);
				}
			}
		} else {
			logger.error("updateListWithDictMultiple参数不能为空！\n");
		}
	}

}
