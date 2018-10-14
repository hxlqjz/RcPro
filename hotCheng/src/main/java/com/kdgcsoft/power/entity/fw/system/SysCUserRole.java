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
@Table(name = "SYS_C_USER_ROLE")
@SuppressWarnings("serial")
public class SysCUserRole extends BaseEntity implements java.io.Serializable {
	/** urId  用户角色ID   */
	private Long urId;
	/** userCode  用户帐号   */
	private String userCode;
	/** roleCode  角色编码   */
	private String roleCode;
	/** ordBy  排序   */
	private Long ordBy;
	
	/**
	 *方法: 取得  用户角色ID urId Long
	 *@return: Long   字典[]
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="SYS_C_USER_ROLE")
	@TableGenerator(name="SYS_C_USER_ROLE",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "SYS_C_USER_ROLE",allocationSize = 1)
	@Column(name ="UR_ID",nullable=false,precision=15,scale=0,length=22)
	public Long getUrId(){
		return this.urId;
	}

	/**
	 *方法: 设置  用户角色ID  urId Long 字典[]
	 *@param: Long  
	 */
	public void setUrId(Long urId){
		this.urId = urId;
	}
	/**
	 *方法: 取得  用户帐号 userCode String
	 *@return: String   字典[]
	 */
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
