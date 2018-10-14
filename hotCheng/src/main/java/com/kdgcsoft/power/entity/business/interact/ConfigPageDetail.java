package com.kdgcsoft.power.entity.business.interact;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="config_page_detail")
public class ConfigPageDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long detailId;
	private Date createTime;
	private Long pageId;
	private String pageUrl;
	private String pageTitle;
	private String isUse;
	private String hType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="config_page_detail")
	@TableGenerator(name="config_page_detail",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "config_page_detail",allocationSize = 1)
	@Column(name ="detail_id",nullable=false,precision=15,scale=0,length=22)	
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="page_id")
	public Long getPageId() {
		return pageId;
	}
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
	@Column(name="page_url")
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	@Column(name="page_title")
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	@Column(name="is_use")
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	@Column(name="h_type")
	public String gethType() {
		return hType;
	}
	public void sethType(String hType) {
		this.hType = hType;
	}
	
	


}
