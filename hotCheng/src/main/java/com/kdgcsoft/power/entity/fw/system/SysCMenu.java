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
@Table(name = "SYS_C_MENU")
@SuppressWarnings("serial")
public class SysCMenu extends BaseDelEntity implements java.io.Serializable {
	/** menuId  菜单ID   */
	private Long menuId;
	/** pmenuCode  父菜单编码   */
	private String pmenuCode;
	/** menuCode  菜单编码   */
	private String menuCode;
	/** menuName  菜单名称   */
	private String menuName;
	/** appCode  所属系统   */
	private String appCode;
	/** isPage  菜单类型   */
	private String isPage;
	/** icon  菜单图标   */
	private String icon;
	/** openTyp  打开方式   */
	private String openTyp;
	/** url  链接地址   */
	private String url;
	/** rmk  说明描述   */
	private String rmk;
	/** ordBy  排序   */
	private Long ordBy;
	
	private String flag;
	
	/**
	 *方法: 取得  菜单ID menuId Long
	 *@return: Long   字典[]
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="SYS_C_MENU")
	@TableGenerator(name="SYS_C_MENU",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "SYS_C_MENU",allocationSize = 1)
	@Column(name ="MENU_ID",nullable=false,precision=15,scale=0,length=22)
	public Long getMenuId(){
		return this.menuId;
	}

	/**
	 *方法: 设置  菜单ID  menuId Long 字典[]
	 *@param: Long  
	 */
	public void setMenuId(Long menuId){
		this.menuId = menuId;
	}
	/**
	 *方法: 取得  父菜单编码 pmenuCode String
	 *@return: String   字典[]
	 */
	@Column(name ="PMENU_CODE",nullable=true,length=32)
	public String getPmenuCode(){
		return this.pmenuCode;
	}

	/**
	 *方法: 设置  父菜单编码  pmenuCode String 字典[]
	 *@param: String  
	 */
	public void setPmenuCode(String pmenuCode){
		this.pmenuCode = pmenuCode;
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
	 *方法: 取得  菜单名称 menuName String
	 *@return: String   字典[]
	 */
	@Column(name ="MENU_NAME",nullable=true,length=128)
	public String getMenuName(){
		return this.menuName;
	}

	/**
	 *方法: 设置  菜单名称  menuName String 字典[]
	 *@param: String  
	 */
	public void setMenuName(String menuName){
		this.menuName = menuName;
	}
	/**
	 *方法: 取得  所属系统 appCode String
	 *@return: String   字典[]
	 */
	@Column(name ="APP_CODE",nullable=true,length=32)
	public String getAppCode(){
		return this.appCode;
	}

	/**
	 *方法: 设置  所属系统  appCode String 字典[]
	 *@param: String  
	 */
	public void setAppCode(String appCode){
		this.appCode = appCode;
	}
	/**
	 *方法: 取得  菜单类型 isPage String
	 *@return: String   字典[]
	 */
	@Column(name ="IS_PAGE",nullable=true,length=1)
	public String getIsPage(){
		return this.isPage;
	}

	/**
	 *方法: 设置  菜单类型  isPage String 字典[]
	 *@param: String  
	 */
	public void setIsPage(String isPage){
		this.isPage = isPage;
	}
	/**
	 *方法: 取得  菜单图标 icon String
	 *@return: String   字典[]
	 */
	@Column(name ="ICON",nullable=true,length=128)
	public String getIcon(){
		return this.icon;
	}

	/**
	 *方法: 设置  菜单图标  icon String 字典[]
	 *@param: String  
	 */
	public void setIcon(String icon){
		this.icon = icon;
	}
	/**
	 *方法: 取得  打开方式 openTyp String
	 *@return: String   字典[]
	 */
	@Column(name ="OPEN_TYP",nullable=true,length=16)
	public String getOpenTyp(){
		return this.openTyp;
	}

	/**
	 *方法: 设置  打开方式  openTyp String 字典[]
	 *@param: String  
	 */
	public void setOpenTyp(String openTyp){
		this.openTyp = openTyp;
	}
	/**
	 *方法: 取得  链接地址 url String
	 *@return: String   字典[]
	 */
	@Column(name ="URL",nullable=true,length=128)
	public String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置  链接地址  url String 字典[]
	 *@param: String  
	 */
	public void setUrl(String url){
		this.url = url;
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
