package com.kdgcsoft.power.common.bean;


/**
 * 流程相关信息bean
 * @author jingao
 *
 */
public class FlowBean {
	
	private Long processinstid;
	private String auditStatus;
	public Long getProcessinstid() {
		return processinstid;
	}
	public void setProcessinstid(Long processinstid) {
		this.processinstid = processinstid;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	

}
