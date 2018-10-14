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
@Table(name = "COM_C_DICT_CAT")
@SuppressWarnings("serial")
public class ComCDictCat extends BaseDelEntity implements java.io.Serializable {
	/** catId  分类ID   */
	private Long catId;
	/** catCode  分类编码   */
	private String catCode;
	/** catName  名称   */
	private String catName;
	/** catTyp  类型   */
	private String catTyp;
	/** dataAttr  属性   */
	private String dataAttr;
	/** rmk  描述   */
	private String rmk;
	/** ordBy  排序   */
	private Long ordBy;
	
	private String flag;
	
	/**
	 *方法: 取得  分类ID catId Long
	 *@return: Long   字典[]
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="COM_C_DICT_CAT")
	@TableGenerator(name="COM_C_DICT_CAT",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "COM_C_DICT_CAT",allocationSize = 1)
	@Column(name ="CAT_ID",nullable=false,precision=15,scale=0,length=22)
	public Long getCatId(){
		return this.catId;
	}

	/**
	 *方法: 设置  分类ID  catId Long 字典[]
	 *@param: Long  
	 */
	public void setCatId(Long catId){
		this.catId = catId;
	}
	/**
	 *方法: 取得  分类编码 catCode String
	 *@return: String   字典[]
	 */
	@Column(name ="CAT_CODE",nullable=true,length=32)
	public String getCatCode(){
		return this.catCode;
	}

	/**
	 *方法: 设置  分类编码  catCode String 字典[]
	 *@param: String  
	 */
	public void setCatCode(String catCode){
		this.catCode = catCode;
	}
	/**
	 *方法: 取得  名称 catName String
	 *@return: String   字典[]
	 */
	@Column(name ="CAT_NAME",nullable=true,length=128)
	public String getCatName(){
		return this.catName;
	}

	/**
	 *方法: 设置  名称  catName String 字典[]
	 *@param: String  
	 */
	public void setCatName(String catName){
		this.catName = catName;
	}
	/**
	 *方法: 取得  类型 catTyp String
	 *@return: String   字典[]
	 */
	@Column(name ="CAT_TYP",nullable=true,length=16)
	public String getCatTyp(){
		return this.catTyp;
	}

	/**
	 *方法: 设置  类型  catTyp String 字典[]
	 *@param: String  
	 */
	public void setCatTyp(String catTyp){
		this.catTyp = catTyp;
	}
	/**
	 *方法: 取得  属性 dataAttr String
	 *@return: String   字典[]
	 */
	@Column(name ="DATA_ATTR",nullable=true,length=16)
	public String getDataAttr(){
		return this.dataAttr;
	}

	/**
	 *方法: 设置  属性  dataAttr String 字典[]
	 *@param: String  
	 */
	public void setDataAttr(String dataAttr){
		this.dataAttr = dataAttr;
	}
	/**
	 *方法: 取得  描述 rmk String
	 *@return: String   字典[]
	 */
	@Column(name ="RMK",nullable=true,length=256)
	public String getRmk(){
		return this.rmk;
	}

	/**
	 *方法: 设置  描述  rmk String 字典[]
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
