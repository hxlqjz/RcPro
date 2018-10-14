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
@Table(name = "SYS_C_PERM")
@SuppressWarnings("serial")
public class SysCPerm extends BaseEntity implements java.io.Serializable {
	/** permId  权限ID   */
	private Long permId;
	/** menuCode  菜单编码   */
	private String menuCode;
	/** permCode  权限编码   */
	private String permCode;
	/** permName  权限名称   */
	private String permName;
	/** permTyp  权限类型   */
	private String permTyp;
	/** rmk  说明描述   */
	private String rmk;
	/** ordBy  排序   */
	private Long ordBy;
	
	/**
	 *方法: 取得  权限ID permId Long
	 *@return: Long   字典[]
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="SYS_C_PERM")
	@TableGenerator(name="SYS_C_PERM",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "SYS_C_PERM",allocationSize = 1)
	@Column(name ="PERM_ID",nullable=false,precision=15,scale=0,length=22)
	public Long getPermId(){
		return this.permId;
	}

	/**
	 *方法: 设置  权限ID  permId Long 字典[]
	 *@param: Long  
	 */
	public void setPermId(Long permId){
		this.permId = permId;
	}
	/**
	 *方法: 取得  菜单编码 menuCode String
	 *@return: String   字典[]
	 */
	@Column(name ="MENU_CODE",nullable=true,length=32)
	public String getMenuCode(){
		return this.menuCode;
	}

	/**
	 *方法: 设置  菜单编码  menuCode String 字典[]
	 *@param: String  
	 */
	public void setMenuCode(String menuCode){
		this.menuCode = menuCode;
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
	 *方法: 取得  权限名称 permName String
	 *@return: String   字典[]
	 */
	@Column(name ="PERM_NAME",nullable=true,length=128)
	public String getPermName(){
		return this.permName;
	}

	/**
	 *方法: 设置  权限名称  permName String 字典[]
	 *@param: String  
	 */
	public void setPermName(String permName){
		this.permName = permName;
	}
	/**
	 *方法: 取得  权限类型 permTyp String
	 *@return: String   字典[]
	 */
	@Column(name ="PERM_TYP",nullable=true,length=32)
	public String getPermTyp(){
		return this.permTyp;
	}

	/**
	 *方法: 设置  权限类型  permTyp String 字典[]
	 *@param: String  
	 */
	public void setPermTyp(String permTyp){
		this.permTyp = permTyp;
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
}
