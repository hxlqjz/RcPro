package com.kdgcsoft.power.entity.business.interact;
import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;


@Entity
@Table(name="PP_J_PUBLISTH_NEWS")
public class PpJPublisthNews implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long newsId;
	private Long theColumn;
	private Date createDate;
	private String publishBy;
	private String auditorBy;
	private Clob publishContentClob;
	private String publishStatus;
	private Long publishDept;
	private Date modifyDate;
	private String fileUrl;
	private String imageUrl;
	private String isUse;
	private String lastmodifyBy;
	private String theme;
	private String isCheck;
	private String publishName;
	private String auditorName;
	private String publishDeptName;
	private String theColumnName;
	private String approveStatus;
	private String enterpriseCode;
	private String isPicNews;
	private String picUrl;
	private String isColumn;
	private String isImportantNews;
	private String GroupNewsUrl;
	private String isAccept;
	private String acceptMemo;
	private Long goodCount;
	private String isToPraise;
	private String informationSources;
	@Transient
	public Long getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(Long goodCount) {
		this.goodCount = goodCount;
	}
	@Transient
	public String getIsToPraise() {
		return isToPraise;
	}

	public void setIsToPraise(String isToPraise) {
		this.isToPraise = isToPraise;
	}
	
	@Column(name="IS_ACCEPT")
	public String getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}

	@Column(name="ACCEPT_MEMO")
	public String getAcceptMemo() {
		return acceptMemo;
	}

	public void setAcceptMemo(String acceptMemo) {
		this.acceptMemo = acceptMemo;
	}

	@Column(name="IS_IMPORTANT_NEWS")
	public String getIsImportantNews() {
		return isImportantNews;
	}

	public void setIsImportantNews(String isImportantNews) {
		this.isImportantNews = isImportantNews;
	}
	@Column(name="GROUP_NEWS_URL")
	public String getGroupNewsUrl() {
		return GroupNewsUrl;
	}
	
	public void setGroupNewsUrl(String groupNewsUrl) {
		GroupNewsUrl = groupNewsUrl;
	}

	@Column(name="IS_COLUMN")
	@Transient
	public String getIsColumn(){
		return isColumn;
	}

	public void setIsColumn(String isColumn) {
		this.isColumn = isColumn;
	}

	@Column(name="IS_PIC_NEWS")
	public String getIsPicNews() {
		return isPicNews;
	}

	public void setIsPicNews(String isPicNews) {
		this.isPicNews = isPicNews;
	}
	@Column(name="PIC_URL")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	private String publishContent;
	
	@Column(name="ENTERPRISE_CODE")
	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	@Column(name="APPROVE_STATUS")
	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	@Column(name="THECOLUMN_NAME")
	public String getTheColumnName() {
		return theColumnName;
	}

	public void setTheColumnName(String theColumnName) {
		this.theColumnName = theColumnName;
	}

	@Column(name="PUBLISH_DEPT_NAME")
	public String getPublishDeptName() {
		return publishDeptName;
	}

	public void setPublishDeptName(String publishDeptName) {
		this.publishDeptName = publishDeptName;
	}

	@Column(name="PUBLISH_NAME")
	public String getPublishName() {
		return publishName;
	}

	public void setPublishName(String publishName) {
		this.publishName = publishName;
	}

	@Column(name="AUDITOR_NAME")
	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	@Column(name="IS_CHECK")
	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	@Column(name="THEME")
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator = "PP_J_PUBLISTH_NEWS")
    @TableGenerator(name="PP_J_PUBLISTH_NEWS",allocationSize=50)
	@Column(name="NEWS_ID")
	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	@Column(name="THE_COLUMN")
	public Long getTheColumn() {
		return theColumn;
	}

	public void setTheColumn(Long theColumn) {
		this.theColumn = theColumn;
	}
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="PUBLISH_BY")
	public String getPublishBy() {
		return publishBy;
	}

	public void setPublishBy(String publishBy) {
		this.publishBy = publishBy;
	}
	@Column(name="AUDITOR_BY")
	public String getAuditorBy() {
		return auditorBy;
	}

	public void setAuditorBy(String auditorBy) {
		this.auditorBy = auditorBy;
	}

	@Lob 
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="PUBLISH_CONTENT")
	public Clob getPublishContentClob() {
		return publishContentClob;
	}

	public void setPublishContentClob(Clob publishContentClob) {
		this.publishContentClob = publishContentClob;
	}

	@Column(name="PUBLISH_STATUS")
	public String getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}

	@Column(name="PUBLISH_DEPT")
	public Long getPublishDept() {
		return publishDept;
	}

	public void setPublishDept(Long publishDept) {
		this.publishDept = publishDept;
	}

	@Column(name="MODIFY_DATE")
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Column(name="FILE_URL")
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Column(name="IMAGE_URL")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name="IS_USE")
	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	@Column(name="LASTMODIFY_BY")
	public String getLastmodifyBy() {
		return lastmodifyBy;
	}

	public void setLastmodifyBy(String lastmodifyBy) {
		this.lastmodifyBy = lastmodifyBy;
	}

	@Transient
	public String getPublishContent() {
		return publishContent;
	}

	public void setPublishContent(String publishContent) {
		this.publishContent = publishContent;
	}

	@Column(name="INFORMATION_SOURCES")
	public String getInformationSources() {
		return informationSources;
	}

	public void setInformationSources(String informationSources) {
		this.informationSources = informationSources;
	}
	
}
