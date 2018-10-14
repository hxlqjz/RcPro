package com.kdgcsoft.power.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {

		/**
		 * 功能 : 返回array2中在array1中不存在的值。
		
		 * 开发：zwwang 2015-3-22
		
		 * @param array1
		 * @param array2
		 * @return
		 */
		public static List<String> exist(String[] array1,String[] array2){
	        List<String> strs = new ArrayList<String>();
	        for (int i = 0; i < array2.length; i++){
	            boolean exists = Arrays.asList(array1).contains(array2[i]);  
	            if (!exists) {  
	                strs.add(array2[i]);  
	            }  
	        }  
	        return strs;
		}
		/**
		 * 功能 : 返回array2中在array1中不存在的值。
		
		 * 开发：zwwang 2015-3-22
		
		 * @param array1
		 * @param array2
		 * @return
		 */
		public static List<Long> existLong(Long[] array1,Long[] array2){
	        List<Long> strs = new ArrayList<Long>();
	        for (int i = 0; i < array2.length; i++){
	            boolean exists = Arrays.asList(array1).contains(array2[i]);  
	            if (!exists){  
	                strs.add(array2[i]);  
	            }  
	        }  
	        return strs;
		}
}
