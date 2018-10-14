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
@Table(name="config_page_main")
public class ConfigPageMain implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pageId;
	private Date createTime;
	private String pageSuperior;
	private String pageTitle;
	private String pageType;
	private String pageUrl;
	private String isUse;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="config_page_main")
	@TableGenerator(name="config_page_main",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "config_page_main",allocationSize = 1)
	@Column(name ="page_id",nullable=false,precision=15,scale=0,length=22)	
	public Long getPageId() {
		return pageId;
	}
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="page_superior")
	public String getPageSuperior() {
		return pageSuperior;
	}
	public void setPageSuperior(String pageSuperior) {
		this.pageSuperior = pageSuperior;
	}
	@Column(name="page_title")
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	@Column(name="page_type")
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	@Column(name="is_use")
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	@Column(name="page_url")
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	

}
