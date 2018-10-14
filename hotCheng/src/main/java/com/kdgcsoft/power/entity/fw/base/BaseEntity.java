package com.kdgcsoft.power.entity.fw.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public class BaseEntity {

	/** createBy  创建人   */
	protected String createBy;
	/** createTime  创建时间   */
	protected Date createTime;
	/** modifyBy  修改人   */
	protected String modifyBy;
	/** modifyTime  修改时间   */
	protected Date modifyTime;
	
	public BaseEntity() {
		super();
	}

	/**
	 *方法: 取得  创建人 createBy String
	 *@return: String  
	 */
	@Column(name = "CREATE_BY", nullable = true, length = 32)
	public String getCreateBy() {
		return this.createBy;
	}

	/**
	 *方法: 设置  创建人  createBy String
	 *@param: String  
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 *方法: 取得  创建时间 createTime Date
	 *@return: Date 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", nullable = true, length = 20)
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 *方法: 设置  创建时间  createTime Date
	 *@param: Date  
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 *方法: 取得  修改人 modifyBy String
	 *@return: String 
	 */
	@Column(name = "MODIFY_BY", nullable = true, length = 32)
	public String getModifyBy() {
		return this.modifyBy;
	}

	/**
	 *方法: 设置  修改人  modifyBy String
	 *@param: String  
	 */
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	/**
	 *方法: 取得  修改时间 modifyTime Date
	 *@return: Date 
	 */
	@Version
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_TIME", nullable = true, length = 20)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	/**
	 *方法: 设置  修改时间  modifyTime Date 
	 *@param: Date  
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}