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

import com.kdgcsoft.power.entity.fw.annotation.Code;
import com.kdgcsoft.power.entity.fw.base.BaseDelEntity;

@Entity
@Table(name = "SYS_C_USER")
@SuppressWarnings("serial")
public class SysCUser extends BaseDelEntity implements java.io.Serializable {
	/** userId  用户ID   */
	private Long userId;
	/** userCode  用户帐号   */
	private String userCode;
	/** userName  用户名称   */
	private String userName;
	/** pswd  密码   */
	private String pswd;
	/** userAcnt  自定义帐号   */
	private String userAcnt;
	/** thm  主题皮肤   */
	private String thm;
	/** tel  电话   */
	private String tel;
	/** mobile  手机   */
	private String mobile;
	/** mail  电子邮件   */
	private String mail;
	/** isLongterm  长期有效   */
	private String isLongterm;
	/** enableTime  启用时间   */
	private Date validTime;
	/** invalidTime  失效时间   */
	private Date invalidTime;
	/** userTyp  帐号类型   */
	private String userTyp;
	/** rmk  说明描述   */
	private String rmk;
	/** ordBy  排序   */
	private Long ordBy;
	
	private String flag;
	
	private String userProfession;
	
    /**	deptName 所属部门*/	
	private String deptName;
	private String deptCode;
	
	@Column(name ="DEPT_CODE",nullable=true,length=32)
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 *方法: 取得  所属部门 deptName String
	 *@return: String 
	 */
	@Column(name ="DEPT_NAME",nullable=true,length=32)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 *方法: 取得  用户ID userId Long
	 *@return: Long   字典[]
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="SYS_C_USER")
	@TableGenerator(name="SYS_C_USER",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "SYS_C_USER",allocationSize = 1)
	@Column(name ="USER_ID",nullable=false,precision=15,scale=0,length=22)
	public Long getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置  用户ID  userId Long 字典[]
	 *@param: Long  
	 */
	public void setUserId(Long userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得  用户帐号 userCode String
	 *@return: String   字典[]
	 */
	@Code
	@Column(name ="USER_CODE",nullable=true,length=32)
	public String getUserCode(){
		return this.userCode;
	}

	/**
	 *方法: 设置  用户帐号  userCode String 字典[]
	 *@param: String  
	 */
	public void setUserCode(String userCode){
		this.userCode = userCode;
	}
	/**
	 *方法: 取得  用户名称 userName String
	 *@return: String   字典[]
	 */
	@Column(name ="USER_NAME",nullable=true,length=32)
	public String getUserName(){
		return this.userName;
	}

	/**
	 *方法: 设置  用户名称  userName String 字典[]
	 *@param: String  
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
	/**
	 *方法: 取得  密码 pswd String
	 *@return: String   字典[]
	 */
	@Column(name ="PSWD",nullable=true,length=32)
	public String getPswd(){
		return this.pswd;
	}

	/**
	 *方法: 设置  密码  pswd String 字典[]
	 *@param: String  
	 */
	public void setPswd(String pswd){
		this.pswd = pswd;
	}
	/**
	 *方法: 取得  自定义帐号 userAcnt String
	 *@return: String   字典[]
	 */
	@Column(name ="USER_ACNT",nullable=true,length=32)
	public String getUserAcnt(){
		return this.userAcnt;
	}

	/**
	 *方法: 设置  自定义帐号  userAcnt String 字典[]
	 *@param: String  
	 */
	public void setUserAcnt(String userAcnt){
		this.userAcnt = userAcnt;
	}
	/**
	 *方法: 取得  专业 userProfession String
	 *@return: String 
	 */
	@Column(name ="USER_PROFESSION",nullable=true,length=32)
	public String getUserProfession(){
		return this.userProfession;
	}

	/**
	 *方法: 设置  专业 userProfession String
	 *@param: String  
	 */
	public void setUserProfession(String userProfession){
		this.userProfession = userProfession;
	}
	/**
	 *方法: 取得  主题皮肤 thm String
	 *@return: String   字典[]
	 */
	@Column(name ="THM",nullable=true,length=64)
	public String getThm(){
		return this.thm;
	}

	/**
	 *方法: 设置  主题皮肤  thm String 字典[]
	 *@param: String  
	 */
	public void setThm(String thm){
		this.thm = thm;
	}
	/**
	 *方法: 取得  电话 tel String
	 *@return: String   字典[]
	 */
	@Column(name ="TEL",nullable=true,length=16)
	public String getTel(){
		return this.tel;
	}

	/**
	 *方法: 设置  电话  tel String 字典[]
	 *@param: String  
	 */
	public void setTel(String tel){
		this.tel = tel;
	}
	/**
	 *方法: 取得  手机 mobile String
	 *@return: String   字典[]
	 */
	@Column(name ="MOBILE",nullable=true,length=16)
	public String getMobile(){
		return this.mobile;
	}

	/**
	 *方法: 设置  手机  mobile String 字典[]
	 *@param: String  
	 */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	/**
	 *方法: 取得  电子邮件 mail String
	 *@return: String   字典[]
	 */
	@Column(name ="MAIL",nullable=true,length=64)
	public String getMail(){
		return this.mail;
	}

	/**
	 *方法: 设置  电子邮件  mail String 字典[]
	 *@param: String  
	 */
	public void setMail(String mail){
		this.mail = mail;
	}
	/**
	 *方法: 取得  长期有效 isLongterm String
	 *@return: String   字典[]
	 */
	@Column(name ="IS_LONGTERM",nullable=true,length=1)
	public String getIsLongterm(){
		return this.isLongterm;
	}

	/**
	 *方法: 设置  长期有效  isLongterm String 字典[]
	 *@param: String  
	 */
	public void setIsLongterm(String isLongterm){
		this.isLongterm = isLongterm;
	}
	/**
	 *方法: 取得  启用时间 enableTime Date
	 *@return: Date   字典[]
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="VALID_TIME",nullable=true,length=7)
	public Date getValidTime(){
		return this.validTime;
	}

	/**
	 *方法: 设置  启用时间  enableTime Date 字典[]
	 *@param: Date  
	 */
	public void setValidTime(Date validTime){
		this.validTime = validTime;
	}
	/**
	 *方法: 取得  失效时间 invalidTime Date
	 *@return: Date   字典[]
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="INVALID_TIME",nullable=true,length=7)
	public Date getInvalidTime(){
		return this.invalidTime;
	}

	/**
	 *方法: 设置  失效时间  invalidTime Date 字典[]
	 *@param: Date  
	 */
	public void setInvalidTime(Date invalidTime){
		this.invalidTime = invalidTime;
	}
	
	/**
	 *方法: 取得  帐号类型 userTyp String
	 *@return: String   字典[]
	 */
	@Column(name ="USER_TYP",nullable=true,length=16)
	public String getUserTyp(){
		return this.userTyp;
	}

	/**
	 *方法: 设置  帐号类型  userTyp String 字典[]
	 *@param: String  
	 */
	public void setUserTyp(String userTyp){
		this.userTyp = userTyp;
	}
	/**
	 *方法: 取得  说明描述 rmk String
	 *@return: String   字典[]
	 */
	@Column(name ="RMK",nullable=true,length=256)
	public String getRmk(){
		return this.rmk;
	}

	/**
	 *方法: 设置  说明描述  rmk String 字典[]
	 *@param: String  
	 */
	public void setRmk(String rmk){
		this.rmk = rmk;
	}
	/**
	 *方法: 取得  排序 ordBy Long
	 *@return: Long   字典[]
	 */
	@Column(name ="ORD_BY",nullable=true,precision=15,scale=0,length=22)
	public Long getOrdBy(){
		return this.ordBy;
	}

	/**
	 *方法: 设置  排序  ordBy Long 字典[]
	 *@param: Long  
	 */
	public void setOrdBy(Long ordBy){
		this.ordBy = ordBy;
	}
	
	@Column(name ="FLAG",nullable=true,length=1)
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
