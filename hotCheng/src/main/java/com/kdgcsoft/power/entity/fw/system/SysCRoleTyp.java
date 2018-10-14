package com.kdgcsoft.power.entity.fw.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.kdgcsoft.power.entity.fw.base.BaseDelEntity;

@Entity
@Table(name = "SYS_C_ROLE_TYP")
@SuppressWarnings("serial")
public class SysCRoleTyp extends BaseDelEntity implements java.io.Serializable {
	/** typId  角色类型ID   */
	private Long typId;
	/** typCode  角色类型编码   */
	private String typCode;
	/** typName  角色类型名称   */
	private String typName;
	/** isAdmin  超级管理员   */
	private String isAdmin;
	/** isUsable  可否使用   */
	private String isUsable;
	/** isPerm  可否授权   */
	private String isPerm;
	/** rmk  说明描述   */
	private String rmk;
	/** ordBy  排序   */
	private Long ordBy;
	
	private String flag;

	
	/**
	 *方法: 取得  角色类型ID typId Long
	 *@return: Long   字典[]
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="SYS_C_ROLE_TYP")
	@TableGenerator(name="SYS_C_ROLE_TYP",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "SYS_C_ROLE_TYP",allocationSize = 1)
	@Column(name ="TYP_ID",nullable=false,precision=15,scale=0,length=22)
	public Long getTypId(){
		return this.typId;
	}

	/**
	 *方法: 设置  角色类型ID  typId Long 字典[]
	 *@param: Long  
	 */
	public void setTypId(Long typId){
		this.typId = typId;
	}
	/**
	 *方法: 取得  角色类型编码 typCode String
	 *@return: String   字典[]
	 */
	@Column(name ="TYP_CODE",nullable=true,length=32)
	public String getTypCode(){
		return this.typCode;
	}

	/**
	 *方法: 设置  角色类型编码  typCode String 字典[]
	 *@param: String  
	 */
	public void setTypCode(String typCode){
		this.typCode = typCode;
	}
	/**
	 *方法: 取得  角色类型名称 typName String
	 *@return: String   字典[]
	 */
	@Column(name ="TYP_NAME",nullable=true,length=128)
	public String getTypName(){
		return this.typName;
	}

	/**
	 *方法: 设置  角色类型名称  typName String 字典[]
	 *@param: String  
	 */
	public void setTypName(String typName){
		this.typName = typName;
	}
	/**
	 *方法: 取得  超级管理员 isAdmin String
	 *@return: String   字典[]
	 */
	@Column(name ="IS_ADMIN",nullable=true,length=1)
	public String getIsAdmin(){
		return this.isAdmin;
	}

	/**
	 *方法: 设置  超级管理员  isAdmin String 字典[]
	 *@param: String  
	 */
	public void setIsAdmin(String isAdmin){
		this.isAdmin = isAdmin;
	}
	/**
	 *方法: 取得  可否使用 isUsable String
	 *@return: String   字典[]
	 */
	@Column(name ="IS_USABLE",nullable=true,length=1)
	public String getIsUsable(){
		return this.isUsable;
	}

	/**
	 *方法: 设置  可否使用  isUsable String 字典[]
	 *@param: String  
	 */
	public void setIsUsable(String isUsable){
		this.isUsable = isUsable;
	}
	/**
	 *方法: 取得  可否授权 isPerm String
	 *@return: String   字典[]
	 */
	@Column(name ="IS_PERM",nullable=true,length=1)
	public String getIsPerm(){
		return this.isPerm;
	}

	/**
	 *方法: 设置  可否授权  isPerm String 字典[]
	 *@param: String  
	 */
	public void setIsPerm(String isPerm){
		this.isPerm = isPerm;
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
