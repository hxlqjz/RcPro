package com.kdgcsoft.power.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;

import com.kdgcsoft.power.common.support.CustomDateEditor;


/**
 * 映射工具类
 * 重要工具类 
 * @author hualongsheng
 *
 */
public class ConvertUtil {
	
    public static <T> T mapArrayToBean(Map<String,String[]> map,T entity){
    	if(map == null){
    		return null;
    	} 
    	
    	Map<String, String> newMap = new HashMap<>();
    	for(Entry<String, String[]> entry : map.entrySet()){
    		newMap.put(entry.getKey(), entry.getValue()[0]);
    	}
    	
    	BeanWrapper beanWrapper = new BeanWrapperImpl(entity);
    	//注册日期转换工具
    	beanWrapper.registerCustomEditor(Date.class, new CustomDateEditor(true));
    	
    	PropertyValues pvs = new MutablePropertyValues(newMap);
    	beanWrapper.setPropertyValues(pvs, true, false);
    	
    	return entity;
	}
    
}
