package com.kdgcsoft.power.entity.fw.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.kdgcsoft.power.entity.fw.annotation.Code;
import com.kdgcsoft.power.entity.fw.base.BaseDelEntity;

@Entity
@Table(name = "SYS_C_ROLE")
@SuppressWarnings("serial")
public class SysCRole extends BaseDelEntity implements java.io.Serializable {
	/** roleId  角色ID   */
	private Long roleId;
	/** roleTypCode  角色类型编码   */
	private String roleTypCode;
	/** roleCode  角色编码   */
	private String roleCode;
	/** roleName  角色名称   */
	private String roleName;
	/** orgCode  所属机构   */
	private String orgCode;
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
	 *方法: 取得  角色ID roleId Long
	 *@return: Long   字典[]
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="SYS_C_ROLE")
	@TableGenerator(name="SYS_C_ROLE",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "SYS_C_ROLE",allocationSize = 1)
	@Column(name ="ROLE_ID",nullable=false,precision=15,scale=0,length=22)
	public Long getRoleId(){
		return this.roleId;
	}

	/**
	 *方法: 设置  角色ID  roleId Long 字典[]
	 *@param: Long  
	 */
	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}
	/**
	 *方法: 取得  角色类型编码 roleTypCode String
	 *@return: String   字典[]
	 */
	@Column(name ="ROLE_TYP_CODE",nullable=true,length=32)
	public String getRoleTypCode(){
		return this.roleTypCode;
	}

	/**
	 *方法: 设置  角色类型编码  roleTypCode String 字典[]
	 *@param: String  
	 */
	public void setRoleTypCode(String roleTypCode){
		this.roleTypCode = roleTypCode;
	}
	/**
	 *方法: 取得  角色编码 roleCode String
	 *@return: String   字典[]
	 */
	@Code
	@Column(name ="ROLE_CODE",nullable=true,length=32)
	public String getRoleCode(){
		return this.roleCode;
	}

	/**
	 *方法: 设置  角色编码  roleCode String 字典[]
	 *@param: String  
	 */
	public void setRoleCode(String roleCode){
		this.roleCode = roleCode;
	}
	/**
	 *方法: 取得  角色名称 roleName String
	 *@return: String   字典[]
	 */
	@Column(name ="ROLE_NAME",nullable=true,length=128)
	public String getRoleName(){
		return this.roleName;
	}

	/**
	 *方法: 设置  角色名称  roleName String 字典[]
	 *@param: String  
	 */
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	/**
	 *方法: 取得  所属机构 orgCode String
	 *@return: String   字典[]
	 */
	@Column(name ="ORG_CODE",nullable=true,length=32)
	public String getOrgCode(){
		return this.orgCode;
	}

	/**
	 *方法: 设置  所属机构  orgCode String 字典[]
	 *@param: String  
	 */
	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
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
