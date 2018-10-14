package com.kdgcsoft.power.common.bean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统公共配置参数
 */
public class SystemConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemConfig.class);
	
	//上传路径
	public static String UPLOAD_DIR;
	public static String UPLOAD_DIR_REAL;
	
	//工作流消息待办接口地址
    public  static String FLOW_URL;
    
	//配置文件
	static {
		Properties prop = new Properties();
		try {
			prop.load(SystemConfig.class.getResourceAsStream("/profile.properties"));			
			prop.load(SystemConfig.class.getResourceAsStream("/upload.properties"));
			UPLOAD_DIR = prop.getProperty("UPLOAD_DIR");
			UPLOAD_DIR_REAL = prop.getProperty("UPLOAD_DIR_REAL");			
			prop.load(SystemConfig.class.getResourceAsStream("/bwpServer.properties"));
			FLOW_URL = prop.getProperty("flow_url");
			
		} catch (FileNotFoundException e) {
			LOGGER.error("没有找到文件或文件路径不对",e);
		} catch (IOException e) {
			LOGGER.error("IO流异常",e);
		}
	}

}
