package com.kdgcsoft.power.common.workflow;

/**
 * @Title: WebServiceUtil.java
 * @Package com.bwp.ask.utils
 * @Description: TODO(用一句话描述该文件做什么)  
 * @date 2017年3月16日 下午4:21:59
 * @version V1.0   
 */
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPHeaderElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName: WebServiceUtil
 * 
 * @Description: TODO
 * @author zhang.zilong@ustcinfo.com
 * @date 2017年3月16日
 */
public class WebServiceUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebServiceUtil.class);

	private static volatile Service service;
	private static volatile Call call;

	public static Object execute(String nameSpaceUri, String OperationName, Object[] parameter) throws Exception {
		String wsdlUrl = nameSpaceUri + "?wsdl";
		try {
			service = new Service();
			// 创建调用对象
			call = (Call) service.createCall();
			
			// 调用 远程方法
			call.setOperationName(new QName(nameSpaceUri, OperationName));
			// 设置URL
			call.setTargetEndpointAddress(new URL(wsdlUrl));
			
			try {
				Object ret = call.invoke(parameter);
				return ret;
			} catch (IOException e) {
				LOGGER.error("IO异常",e);
			}
		} catch (Exception e) {
			LOGGER.error("未知错误",e);
		}
		return null;
	}
	
	public static Object execute(String nameSpaceUri, String OperationName, Object[] parameter,Map<String,Object> paramMap) throws Exception {
		String wsdlUrl = nameSpaceUri + "?wsdl";
		try {
			service = new Service();
			// 创建调用对象
			call = (Call) service.createCall();
			
			// 设置事物头信息
			SOAPHeaderElement cpHeader = createSoapHeader(wsdlUrl,paramMap);
			call.addHeader(cpHeader);
			
			// 调用 远程方法
			call.setOperationName(new QName(nameSpaceUri, OperationName));
			// 设置URL
			call.setTargetEndpointAddress(new URL(wsdlUrl));
			
			try {
				Object ret = call.invoke(parameter);
				return ret;
			} catch (IOException e) {
				LOGGER.error("IO异常",e);
			}
		} catch (Exception e) {
			LOGGER.error("未知错误",e);
		}
		return null;
	}

	public static SOAPHeaderElement createSoapHeader(String wsdlUrl,Map<String,Object> paramMap) {
		try {
			// 设置头信息
			SOAPHeaderElement cpHeader = new SOAPHeaderElement(wsdlUrl, "FlowExtendInfo");
			cpHeader.setPrefix("");
			SOAPElement soapElement = null;
			for (String param : paramMap.keySet()) {
				soapElement = cpHeader.addChildElement(param);
				soapElement.addTextNode((paramMap.get(param)).toString());
			}
			return cpHeader ;
		} catch (SOAPException e) {
			LOGGER.error("接口调用失败",e);
		}
		return null;
	}
}
