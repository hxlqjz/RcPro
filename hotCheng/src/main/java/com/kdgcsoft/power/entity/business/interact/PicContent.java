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
@Table(name="pic_content")
public class PicContent implements Serializable{
	/**
	 * 将图片变成base64字符串保存到后台
	 */
	private static final long serialVersionUID = 1L;
	private Long picId;
	private Date createTime;
	private String tableName;
	private String tabKey;
	private String content;
	private String isUse;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="pic_content")
	@TableGenerator(name="pic_content",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "pic_content",allocationSize = 1)
	@Column(name ="pic_id",nullable=false,precision=15,scale=0,length=22)	
	public Long getPicId() {
		return picId;
	}
	public void setPicId(Long picId) {
		this.picId = picId;
	}
	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="table_name")
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Column(name="tab_key")
	public String getTabKey() {
		return tabKey;
	}
	public void setTabKey(String tabKey) {
		this.tabKey = tabKey;
	}
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="is_use")
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}


}
