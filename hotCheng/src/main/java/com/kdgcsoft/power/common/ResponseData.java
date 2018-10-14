package com.kdgcsoft.power.common;

import org.apache.commons.lang3.StringUtils;

/**
 * web请求返回的数据封装对象
 * 
 * @author BridgeBai
 * 
 */
public class ResponseData {

	public static final ResponseData SUCCESS_NO_DATA = new ResponseData(Type.info,"操作成功");
	public static final ResponseData FAILED_NO_DATA = new ResponseData(Type.error,"操作错误");

	/**
	 * ResponseType
	 */
	public enum Type {
		/** 成功 */
		info,
		/** 警告 */
		warn,
		/** 错误 */
		error,
		/**其它*/
		other
	}
	/** 是否执行成功 */
	private boolean success;
	/** 返回信息类型 */
	private Type type;
	/** 消息提示内容*/
	private String content;
	/** 返回的数据*/
	private Object data;

	public ResponseData(Type type, String content) {
		this.type=type;
		this.content=content;
		switch (type)
		{
		  case error:
			  this.success=false;
		      break;
		  default:
			  this.success=true;
		      break;
		}
	}	
	public ResponseData(Type type,String content, Object data) {
		this.type=type;		
		this.data=data;
		switch (type)
		{
		  case error:
			  this.success=false;
			  if(StringUtils.isNotBlank(content)){
				  this.content=content;
			  }else{
				  this.content="操作错误";
			  }
		      break;
		  default:
			  this.success=true;
			  if(StringUtils.isNotBlank(content)){
				  this.content=content;
			  }else{
				  this.content="操作成功";
			  }
		      break;
		}
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}
	/**
	 * 返回成功消息
	 * @param content 消息内容
	 * @return
	 */
	public static ResponseData success(String content) {
		return new ResponseData(Type.info, content);
	}
	
	/**
	 * 返回带数据的成功消息
	 * @param content 消息内容
	 * @param data 返回的数据
	 * @return
	 */
	public static ResponseData success(String content,Object data) {
		return new ResponseData(Type.info, content,data);
	}
	/**
	 * 返回警告消息
	 * @param content 消息内容
	 * @return
	 */
	public static ResponseData warn(String content) {
		return new ResponseData(Type.warn, content);
	}
	
	/**
	 * 返回带数据的警告消息
	 * @param content 消息内容
	 * @param data  返回的数据
	 * @return
	 */
	public static ResponseData warn(String content,Object data) {
		return new ResponseData(Type.warn, content,data);
	}

	/**
	 * 返回错误消息
	 * @param content 消息内容
	 * @return
	 */
	public static ResponseData error(String content) {
		return new ResponseData(Type.error, content);
	}
	
	/**
	 * 返回带数据的错误消息
	 * @param content 消息内容
	 * @param data  返回的数据
	 * @return
	 */
	public static ResponseData error(String content,Object data) {
		return new ResponseData(Type.error, content,data);
	}
	/**
	 * 返回其他消息
	 * @param content 消息内容
	 * @return
	 */
	public static ResponseData other(String content) {
		return new ResponseData(Type.other, content);
	}
	
	/**
	 * 返回带数据的其他消息
	 * @param content 消息内容
	 * @param data  返回的数据
	 * @return
	 */
	public static ResponseData other(String content,Object data) {
		return new ResponseData(Type.other, content,data);
	}
}
