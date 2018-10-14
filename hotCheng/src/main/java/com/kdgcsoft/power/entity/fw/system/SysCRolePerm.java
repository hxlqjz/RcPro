package com.kdgcsoft.power.entity.fw.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.kdgcsoft.power.entity.fw.base.BaseEntity;

@Entity
@Table(name = "SYS_C_ROLE_PERM")
@SuppressWarnings("serial")
public class SysCRolePerm extends BaseEntity implements java.io.Serializable {
	/** rpId  角色权限ID   */
	private Long rpId;
	/** roleCode  角色编码   */
	private String roleCode;
	/** permCode  权限编码   */
	private String permCode;
	/** isUsable  可否使用   */
	private String isUsable;
	/** isPerm  可否授权   */
	private String isPerm;
	/** ordBy  排序   */
	private Long ordBy;
	
	/**
	 *方法: 取得  角色权限ID rpId Long
	 *@return: Long   字典[]
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="SYS_C_ROLE_PERM")
	@TableGenerator(name="SYS_C_ROLE_PERM",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "SYS_C_ROLE_PERM",allocationSize = 1)
	@Column(name ="RP_ID",nullable=false,precision=15,scale=0,length=22)
	public Long getRpId(){
		return this.rpId;
	}

	/**
	 *方法: 设置  角色权限ID  rpId Long 字典[]
	 *@param: Long  
	 */
	public void setRpId(Long rpId){
		this.rpId = rpId;
	}
	/**
	 *方法: 取得  角色编码 roleCode String
	 *@return: String   字典[]
	 */
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
	 *方法: 取得  权限编码 permCode String
	 *@return: String   字典[]
	 */
	@Column(name ="PERM_CODE",nullable=true,length=32)
	public String getPermCode(){
		return this.permCode;
	}

	/**
	 *方法: 设置  权限编码  permCode String 字典[]
	 *@param: String  
	 */
	public void setPermCode(String permCode){
		this.permCode = permCode;
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
}
