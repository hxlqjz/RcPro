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
@Table(name="my_snap")
public class MySnap implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long snapId;
	private Date createTime;
	private String snapTitle;
	private String snapContent;
	private String typeCode;
	private String typeName;
	private String isUse;
	private String create_by;
	private String sendMan;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="my_snap")
	@TableGenerator(name="my_snap",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "my_snap",allocationSize = 1)
	@Column(name ="snap_id",nullable=false,precision=15,scale=0,length=22)	
	public Long getSnapId() {
		return snapId;
	}
	public void setSnapId(Long snapId) {
		this.snapId = snapId;
	}
	
	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="snap_title")
	public String getSnapTitle() {
		return snapTitle;
	}
	public void setSnapTitle(String snapTitle) {
		this.snapTitle = snapTitle;
	}
	public String getSnapContent() {
		return snapContent;
	}
	@Column(name="snap_content")
	public void setSnapContent(String snapContent) {
		this.snapContent = snapContent;
	}
	@Column(name="type_code")
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	@Column(name="type_name")
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Column(name="is_use")
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	@Column(name="create_by")
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	@Column(name="send_man")
	public String getSendMan() {
		return sendMan;
	}
	public void setSendMan(String sendMan) {
		this.sendMan = sendMan;
	}
	




}
