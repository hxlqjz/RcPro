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
@Table(name = "COM_C_DICT")
@SuppressWarnings("serial")
public class ComCDict extends BaseDelEntity implements java.io.Serializable {
	/** dictId  字典ID   */
	private Long dictId;
	/** catCode  分类   */
	private String catCode;
	/** pdictCode  上级编码   */
	private String pdictCode;
	/** dictCode  字典编码   */
	private String dictCode;
	/** dictName  中文名称   */
	private String dictName;
	/** dictNameEn  英文名称   */
	private String dictNameEn;
	/** mnemonic  助记码   */
	private String mnemonic;
	/** fullName  全称   */
	private String fullName;
	/** rmk  描述   */
	private String rmk;
	/** url  URL   */
	private String url;
	/** srcCode  源码   */
	private String srcCode;
	/** ordBy  排序   */
	private Long ordBy;
	
	private String flag;
	
	/**
	 *方法: 取得  字典ID dictId Long
	 *@return: Long   字典[]
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="COM_C_DICT")
	@TableGenerator(name="COM_C_DICT",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "COM_C_DICT",allocationSize = 1)
	@Column(name ="DICT_ID",nullable=false,precision=15,scale=0,length=22)
	public Long getDictId(){
		return this.dictId;
	}

	/**
	 *方法: 设置  字典ID  dictId Long 字典[]
	 *@param: Long  
	 */
	public void setDictId(Long dictId){
		this.dictId = dictId;
	}
	/**
	 *方法: 取得  分类 catCode String
	 *@return: String   字典[]
	 */
	@Column(name ="CAT_CODE",nullable=true,length=32)
	public String getCatCode(){
		return this.catCode;
	}

	/**
	 *方法: 设置  分类  catCode String 字典[]
	 *@param: String  
	 */
	public void setCatCode(String catCode){
		this.catCode = catCode;
	}
	/**
	 *方法: 取得  上级编码 pdictCode String
	 *@return: String   字典[]
	 */
	@Column(name ="PDICT_CODE",nullable=true,length=32)
	public String getPdictCode(){
		return this.pdictCode;
	}

	/**
	 *方法: 设置  上级编码  pdictCode String 字典[]
	 *@param: String  
	 */
	public void setPdictCode(String pdictCode){
		this.pdictCode = pdictCode;
	}
	/**
	 *方法: 取得  字典编码 dictCode String
	 *@return: String   字典[]
	 */
	@Column(name ="DICT_CODE",nullable=true,length=32)
	public String getDictCode(){
		return this.dictCode;
	}

	/**
	 *方法: 设置  字典编码  dictCode String 字典[]
	 *@param: String  
	 */
	public void setDictCode(String dictCode){
		this.dictCode = dictCode;
	}
	/**
	 *方法: 取得  中文名称 dictName String
	 *@return: String   字典[]
	 */
	@Column(name ="DICT_NAME",nullable=true,length=128)
	public String getDictName(){
		return this.dictName;
	}

	/**
	 *方法: 设置  中文名称  dictName String 字典[]
	 *@param: String  
	 */
	public void setDictName(String dictName){
		this.dictName = dictName;
	}
	/**
	 *方法: 取得  英文名称 dictNameEn String
	 *@return: String   字典[]
	 */
	@Column(name ="DICT_NAME_EN",nullable=true,length=128)
	public String getDictNameEn(){
		return this.dictNameEn;
	}

	/**
	 *方法: 设置  英文名称  dictNameEn String 字典[]
	 *@param: String  
	 */
	public void setDictNameEn(String dictNameEn){
		this.dictNameEn = dictNameEn;
	}
	/**
	 *方法: 取得  助记码 mnemonic String
	 *@return: String   字典[]
	 */
	@Column(name ="MNEMONIC",nullable=true,length=128)
	public String getMnemonic(){
		return this.mnemonic;
	}

	/**
	 *方法: 设置  助记码  mnemonic String 字典[]
	 *@param: String  
	 */
	public void setMnemonic(String mnemonic){
		this.mnemonic = mnemonic;
	}
	/**
	 *方法: 取得  全称 fullName String
	 *@return: String   字典[]
	 */
	@Column(name ="FULL_NAME",nullable=true,length=128)
	public String getFullName(){
		return this.fullName;
	}

	/**
	 *方法: 设置  全称  fullName String 字典[]
	 *@param: String  
	 */
	public void setFullName(String fullName){
		this.fullName = fullName;
	}
	/**
	 *方法: 取得  描述 rmk String
	 *@return: String   字典[]
	 */
	@Column(name ="RMK",nullable=true,length=1024)
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
	 *方法: 取得  URL url String
	 *@return: String   字典[]
	 */
	@Column(name ="URL",nullable=true,length=256)
	public String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置  URL  url String 字典[]
	 *@param: String  
	 */
	public void setUrl(String url){
		this.url = url;
	}
	/**
	 *方法: 取得  源码 srcCode String
	 *@return: String   字典[]
	 */
	@Column(name ="SRC_CODE",nullable=true,length=4000)
	public String getSrcCode(){
		return this.srcCode;
	}

	/**
	 *方法: 设置  源码  srcCode String 字典[]
	 *@param: String  
	 */
	public void setSrcCode(String srcCode){
		this.srcCode = srcCode;
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
