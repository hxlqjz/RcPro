package com.kdgcsoft.power.entity.fw.system;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "FW_OPMONITOR")
@SuppressWarnings("serial")
public class FwOpmonitor implements java.io.Serializable {
	/** id  id   */
	private Long id;
	/** createDate  createDate   */
	private Date createDate;
	/** modifyDate  modifyDate   */
	private Date modifyDate;
	/** opActioname  opActioname   */
	private String opActioname;
	/** opContent  opContent   */
	private String opContent;
	/** opIp  opIp   */
	private String opIp;
	/** opOptime  opOptime   */
	private Date opOptime;
	/** opResult  opResult   */
	private Long opResult;
	/** opTotaltime  opTotaltime   */
	private Long opTotaltime;
	/** opUrl  opUrl   */
	private String opUrl;
	/** opUserid  opUserid   */
	private Long opUserid;
	/** opUsername  opUsername   */
	private String opUsername;
	
	/**
	 *方法: 取得  id id Long
	 *@return: Long   字典[]
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="FW_OPMONITOR")
	@TableGenerator(name="FW_OPMONITOR",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "FW_OPMONITOR",allocationSize = 1)
	@Column(name ="ID",nullable=false,precision=19,scale=0,length=22)
	public Long getId(){
		return this.id;
	}

	/**
	 *方法: 设置  id  id Long 字典[]
	 *@param: Long  
	 */
	public void setId(Long id){
		this.id = id;
	}
	/**
	 *方法: 取得  createDate createDate String
	 *@return: String   字典[]
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="CREATE_DATE",nullable=true,length=7)
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置  createDate  createDate String 字典[]
	 *@param: String  
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得  modifyDate modifyDate String
	 *@return: String   字典[]
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="MODIFY_DATE",nullable=true,length=7)
	public Date getModifyDate(){
		return this.modifyDate;
	}

	/**
	 *方法: 设置  modifyDate  modifyDate String 字典[]
	 *@param: String  
	 */
	public void setModifyDate(Date modifyDate){
		this.modifyDate = modifyDate;
	}
	/**
	 *方法: 取得  opActioname opActioname String
	 *@return: String   字典[]
	 */
	@Column(name ="OP_ACTIONAME",nullable=true,length=510)
	public String getOpActioname(){
		return this.opActioname;
	}

	/**
	 *方法: 设置  opActioname  opActioname String 字典[]
	 *@param: String  
	 */
	public void setOpActioname(String opActioname){
		this.opActioname = opActioname;
	}
	/**
	 *方法: 取得  opContent opContent String
	 *@return: String   字典[]
	 */
	@Column(name ="OP_CONTENT",nullable=true,length=510)
	public String getOpContent(){
		return this.opContent;
	}

	/**
	 *方法: 设置  opContent  opContent String 字典[]
	 *@param: String  
	 */
	public void setOpContent(String opContent){
		this.opContent = opContent;
	}
	/**
	 *方法: 取得  opIp opIp String
	 *@return: String   字典[]
	 */
	@Column(name ="OP_IP",nullable=true,length=510)
	public String getOpIp(){
		return this.opIp;
	}

	/**
	 *方法: 设置  opIp  opIp String 字典[]
	 *@param: String  
	 */
	public void setOpIp(String opIp){
		this.opIp = opIp;
	}
	/**
	 *方法: 取得  opOptime opOptime Date
	 *@return: Date   字典[]
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="OP_OPTIME",nullable=true,length=7)
	public Date getOpOptime(){
		return this.opOptime;
	}

	/**
	 *方法: 设置  opOptime  opOptime Date 字典[]
	 *@param: Date  
	 */
	public void setOpOptime(Date opOptime){
		this.opOptime = opOptime;
	}
	/**
	 *方法: 取得  opResult opResult Long
	 *@return: Long   字典[]
	 */
	@Column(name ="OP_RESULT",nullable=true,precision=1,scale=0,length=22)
	public Long getOpResult(){
		return this.opResult;
	}

	/**
	 *方法: 设置  opResult  opResult Long 字典[]
	 *@param: Long  
	 */
	public void setOpResult(Long opResult){
		this.opResult = opResult;
	}
	/**
	 *方法: 取得  opTotaltime opTotaltime Long
	 *@return: Long   字典[]
	 */
	@Column(name ="OP_TOTALTIME",nullable=true,precision=19,scale=0,length=22)
	public Long getOpTotaltime(){
		return this.opTotaltime;
	}

	/**
	 *方法: 设置  opTotaltime  opTotaltime Long 字典[]
	 *@param: Long  
	 */
	public void setOpTotaltime(Long opTotaltime){
		this.opTotaltime = opTotaltime;
	}
	/**
	 *方法: 取得  opUrl opUrl String
	 *@return: String   字典[]
	 */
	@Column(name ="OP_URL",nullable=true,length=510)
	public String getOpUrl(){
		return this.opUrl;
	}

	/**
	 *方法: 设置  opUrl  opUrl String 字典[]
	 *@param: String  
	 */
	public void setOpUrl(String opUrl){
		this.opUrl = opUrl;
	}
	/**
	 *方法: 取得  opUserid opUserid Long
	 *@return: Long   字典[]
	 */
	@Column(name ="OP_USERID",nullable=true,precision=19,scale=0,length=22)
	public Long getOpUserid(){
		return this.opUserid;
	}

	/**
	 *方法: 设置  opUserid  opUserid Long 字典[]
	 *@param: Long  
	 */
	public void setOpUserid(Long opUserid){
		this.opUserid = opUserid;
	}
	/**
	 *方法: 取得  opUsername opUsername String
	 *@return: String   字典[]
	 */
	@Column(name ="OP_USERNAME",nullable=true,length=510)
	public String getOpUsername(){
		return this.opUsername;
	}

	/**
	 *方法: 设置  opUsername  opUsername String 字典[]
	 *@param: String  
	 */
	public void setOpUsername(String opUsername){
		this.opUsername = opUsername;
	}
}
