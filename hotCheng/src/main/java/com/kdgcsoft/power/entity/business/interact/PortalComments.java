package com.kdgcsoft.power.entity.business.interact;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
/**
 * 评论的实体类
 * @author ustcsoft
 *
 */
@Entity
@Table(name="PORTAL_COMMENTS")
public class PortalComments implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long commentsId;
	private Long newsId;
	private String createBy;
	private Date createTime;
	private String isDisplayName;
	private String commentsText;
	private String createByName;
	private String headPic;
	private String commentsNum;
	private String isToPraise;
	@Transient
	public String getIsToPraise() {
		return isToPraise;
	}
	public void setIsToPraise(String isToPraise) {
		this.isToPraise = isToPraise;
	}
	private List<PortalCommentsForUser> list;
	
	@Transient
	public List<PortalCommentsForUser> getList() {
		return list;
	}
	public void setList(List<PortalCommentsForUser> list) {
		this.list = list;
	}
	@Transient
	public String getCommentsNum() {
		return commentsNum;
	}
	public void setCommentsNum(String commentsNum) {
		this.commentsNum = commentsNum;
	}
	@Transient
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	@Transient
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="PORTAL_COMMENTS")
	@TableGenerator(name="PORTAL_COMMENTS",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "PORTAL_COMMENTS",allocationSize = 1)
	@Column(name ="COMMENTS_ID",nullable=false,precision=15,scale=0,length=22)	
	public Long getCommentsId() {
		return commentsId;
	}
	public void setCommentsId(Long commentsId) {
		this.commentsId = commentsId;
	}
	@Column(name="NEWS_ID")
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	@Column(name="CREATE_BY")
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="IS_DISPLAY_NAME")
	public String getIsDisplayName() {
		return isDisplayName;
	}
	public void setIsDisplayName(String isDisplayName) {
		this.isDisplayName = isDisplayName;
	}
	@Column(name="COMMENTS_TEXT")
	public String getCommentsText() {
		return commentsText;
	}
	public void setCommentsText(String commentsText) {
		this.commentsText = commentsText;
	}
	
	
}
