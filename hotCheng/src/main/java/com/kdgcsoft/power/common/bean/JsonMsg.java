package com.kdgcsoft.power.common.bean;


/*******************************************************************************
 * 功能说明:JSON格式消息对象模型
 * 
 * 2013-05-02 上午11:00 BridgeBai 创建文件
 * 
 ******************************************************************************/
public class JsonMsg {
	
	/*****  常用返回语句   *******/
	
	public static final JsonMsg SUCCESS = new JsonMsg(SystemConstants.MSG_SUCCESS);
	public static final JsonMsg SAVE_SUCCESS = new JsonMsg(SystemConstants.MSG_SAVE_SUCCESS);
	public static final JsonMsg DELETE_SUCCESS = new JsonMsg(SystemConstants.MSG_DELETE_SUCCESS);
	public static final JsonMsg REPORT_SUCCESS = new JsonMsg(SystemConstants.MSG_REPORT_SUCCESS);
	public static final JsonMsg APPROVE_SUCCESS = new JsonMsg(SystemConstants.MSG_APPROVE_SUCCESS);
	
	
	//是否成功
	private boolean success;
	//消息
	private String msg;
	//数据
	private Object data;
	//总数
	private Long total;
	
	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public JsonMsg(){
		this.success = true;
	}
	
	public JsonMsg(String msg){
		this.success = true;
		this.msg = msg;
	}
	
	public JsonMsg(Object data){
		this.success = true;
		this.data = data;
	}
	
	public JsonMsg(String msg,Object data){
		this.success = true;
		this.msg = msg;
		this.data = data;
	}
	
	public JsonMsg(boolean success,String msg){
		this.success = success;
		this.msg = msg;
	}
	
	public JsonMsg(boolean success,String msg,Object data){
		this.success = success;
		this.msg = msg;
		this.data = data;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	
	
}
