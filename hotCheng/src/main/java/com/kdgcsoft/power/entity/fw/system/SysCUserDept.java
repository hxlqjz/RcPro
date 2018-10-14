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
@Table(name = "SYS_C_USER_DEPT")
@SuppressWarnings("serial")
public class SysCUserDept extends BaseEntity implements java.io.Serializable {
	/** udId  所属部门ID   */
	private Long udId;
	/** orgCode  机构编码   */
	private String orgCode;
	/** deptCode  部门编码   */
	private String deptCode;
	/** userCode  用户帐号   */
	private String userCode;
	/** isMain  隶属部门   */
	private String isMain;
	/** ordBy  排序   */
	private Long ordBy;
	
	/**
	 *方法: 取得  所属部门ID udId Long
	 *@return: Long   字典[]
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="SYS_C_USER_DEPT")
	@TableGenerator(name="SYS_C_USER_DEPT",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "SYS_C_USER_DEPT",allocationSize = 1)
	@Column(name ="UD_ID",nullable=false,precision=15,scale=0,length=22)
	public Long getUdId(){
		return this.udId;
	}

	/**
	 *方法: 设置  所属部门ID  udId Long 字典[]
	 *@param: Long  
	 */
	public void setUdId(Long udId){
		this.udId = udId;
	}
	/**
	 *方法: 取得  机构编码 orgCode String
	 *@return: String   字典[]
	 */
	@Column(name ="ORG_CODE",nullable=true,length=32)
	public String getOrgCode(){
		return this.orgCode;
	}

	/**
	 *方法: 设置  机构编码  orgCode String 字典[]
	 *@param: String  
	 */
	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}
	/**
	 *方法: 取得  部门编码 deptCode String
	 *@return: String   字典[]
	 */
	@Column(name ="DEPT_CODE",nullable=true,length=32)
	public String getDeptCode(){
		return this.deptCode;
	}

	/**
	 *方法: 设置  部门编码  deptCode String 字典[]
	 *@param: String  
	 */
	public void setDeptCode(String deptCode){
		this.deptCode = deptCode;
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
	 *方法: 取得  隶属部门 isMain String
	 *@return: String   字典[]
	 */
	@Column(name ="IS_MAIN",nullable=true,length=1)
	public String getIsMain(){
		return this.isMain;
	}

	/**
	 *方法: 设置  隶属部门  isMain String 字典[]
	 *@param: String  
	 */
	public void setIsMain(String isMain){
		this.isMain = isMain;
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
