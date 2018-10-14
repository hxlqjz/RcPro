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
import javax.persistence.Transient;

@Entity
@Table(name="PORTAL_COMMENTS_FOR_USER")
public class PortalCommentsForUser  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long commentsId;
	private String createBy;
	private Date createDate;
	private String commentsText;
	private String createByName;
	private String replyTo;
	private String replyToName;
	@Transient
	public String getReplyToName() {
		return replyToName;
	}
	public void setReplyToName(String replyToName) {
		this.replyToName = replyToName;
	}
	@Column(name="REPLY_TO")
	public String getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}
	@Transient
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="PORTAL_COMMENTS_FOR_USER")
	@TableGenerator(name="PORTAL_COMMENTS_FOR_USER",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "PORTAL_COMMENTS_FOR_USER",allocationSize = 1)
	@Column(name ="ID",nullable=false,precision=15,scale=0,length=22)	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="COMMENTS_ID")
	public Long getCommentsId() {
		return commentsId;
	}
	public void setCommentsId(Long commentsId) {
		this.commentsId = commentsId;
	}
	@Column(name="CREATE_BY")
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name="COMMENTS_TEXT")
	public String getCommentsText() {
		return commentsText;
	}
	public void setCommentsText(String commentsText) {
		this.commentsText = commentsText;
	}
	

}
