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
@Table(name = "HR_C_DEPT")
@SuppressWarnings("serial")
public class HrCDept extends BaseDelEntity implements java.io.Serializable {
	/** deptId  机构/部门ID  appCode */
	private Long deptId;
	/** orgCode  隶属机构   */
	private String orgCode;
	/** pdeptCode  上级机构/部门编码   */
	private String pdeptCode;
	/** deptCode  机构/部门编码   */
	private String deptCode;
	/** deptName  中文名称   */
	private String deptName;
	/** enName  英文名称   */
	private String enName;
	/** deptAlias  简称   */
	private String deptAlias;
	/** industryCat  所属行业   */
	private String industryCat;
	/** website  网址   */
	private String website;
	/** province  省/直辖市   */
	private String province;
	/** city  城市   */
	private String city;
	/** zones  区县   */
	private String zones;
	/** mgr  分管领导   */
	private String mgr;
	/** linkman  联系人   */
	private String linkman;
	/** tel  联系电话   */
	private String tel;
	/** fax  传真号码   */
	private String fax;
	/** mail  电子邮件   */
	private String mail;
	/** addr  通信地址   */
	private String addr;
	/** postalcode  邮政编码   */
	private String postalcode;
	/** deptCat  分类属性   */
	private String deptCat;
	/** isOrg  是否机构   */
	private String isOrg;
	/** isVirtual  虚拟组织   */
	private String isVirtual;
	/** isOutsourcing  外委单位   */
	private String isOutsourcing;
	/** rmk  说明描述   */
	private String rmk;
	/** ordBy  排序   */
	private Long ordBy;
	
	private String flag;
	
	/**
	 *方法: 取得  机构/部门ID deptId Long
	 *@return: Long   字典[appCode]
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="HR_C_DEPT")
	@TableGenerator(name="HR_C_DEPT",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "HR_C_DEPT",allocationSize = 1)
	@Column(name ="DEPT_ID",nullable=false,precision=15,scale=0,length=22)
	public Long getDeptId(){
		return this.deptId;
	}

	/**
	 *方法: 设置  机构/部门ID  deptId Long 字典[appCode]
	 *@param: Long  
	 */
	public void setDeptId(Long deptId){
		this.deptId = deptId;
	}
	/**
	 *方法: 取得  隶属机构 orgCode String
	 *@return: String   字典[]
	 */
	@Column(name ="ORG_CODE",nullable=true,length=32)
	public String getOrgCode(){
		return this.orgCode;
	}

	/**
	 *方法: 设置  隶属机构  orgCode String 字典[]
	 *@param: String  
	 */
	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}
	/**
	 *方法: 取得  上级机构/部门编码 pdeptCode String
	 *@return: String   字典[]
	 */
	@Column(name ="PDEPT_CODE",nullable=true,length=32)
	public String getPdeptCode(){
		return this.pdeptCode;
	}

	/**
	 *方法: 设置  上级机构/部门编码  pdeptCode String 字典[]
	 *@param: String  
	 */
	public void setPdeptCode(String pdeptCode){
		this.pdeptCode = pdeptCode;
	}
	/**
	 *方法: 取得  机构/部门编码 deptCode String
	 *@return: String   字典[]
	 */
	@Column(name ="DEPT_CODE",nullable=true,length=32)
	public String getDeptCode(){
		return this.deptCode;
	}

	/**
	 *方法: 设置  机构/部门编码  deptCode String 字典[]
	 *@param: String  
	 */
	public void setDeptCode(String deptCode){
		this.deptCode = deptCode;
	}
	/**
	 *方法: 取得  中文名称 deptName String
	 *@return: String   字典[]
	 */
	@Column(name ="DEPT_NAME",nullable=true,length=128)
	public String getDeptName(){
		return this.deptName;
	}

	/**
	 *方法: 设置  中文名称  deptName String 字典[]
	 *@param: String  
	 */
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}
	/**
	 *方法: 取得  英文名称 enName String
	 *@return: String   字典[]
	 */
	@Column(name ="EN_NAME",nullable=true,length=128)
	public String getEnName(){
		return this.enName;
	}

	/**
	 *方法: 设置  英文名称  enName String 字典[]
	 *@param: String  
	 */
	public void setEnName(String enName){
		this.enName = enName;
	}
	/**
	 *方法: 取得  简称 deptAlias String
	 *@return: String   字典[]
	 */
	@Column(name ="DEPT_ALIAS",nullable=true,length=64)
	public String getDeptAlias(){
		return this.deptAlias;
	}

	/**
	 *方法: 设置  简称  deptAlias String 字典[]
	 *@param: String  
	 */
	public void setDeptAlias(String deptAlias){
		this.deptAlias = deptAlias;
	}
	/**
	 *方法: 取得  所属行业 industryCat String
	 *@return: String   字典[]
	 */
	@Column(name ="INDUSTRY_CAT",nullable=true,length=32)
	public String getIndustryCat(){
		return this.industryCat;
	}

	/**
	 *方法: 设置  所属行业  industryCat String 字典[]
	 *@param: String  
	 */
	public void setIndustryCat(String industryCat){
		this.industryCat = industryCat;
	}
	/**
	 *方法: 取得  网址 website String
	 *@return: String   字典[]
	 */
	@Column(name ="WEBSITE",nullable=true,length=128)
	public String getWebsite(){
		return this.website;
	}

	/**
	 *方法: 设置  网址  website String 字典[]
	 *@param: String  
	 */
	public void setWebsite(String website){
		this.website = website;
	}
	/**
	 *方法: 取得  省/直辖市 province String
	 *@return: String   字典[]
	 */
	@Column(name ="PROVINCE",nullable=true,length=32)
	public String getProvince(){
		return this.province;
	}

	/**
	 *方法: 设置  省/直辖市  province String 字典[]
	 *@param: String  
	 */
	public void setProvince(String province){
		this.province = province;
	}
	/**
	 *方法: 取得  城市 city String
	 *@return: String   字典[]
	 */
	@Column(name ="CITY",nullable=true,length=32)
	public String getCity(){
		return this.city;
	}

	/**
	 *方法: 设置  城市  city String 字典[]
	 *@param: String  
	 */
	public void setCity(String city){
		this.city = city;
	}
	/**
	 *方法: 取得  区县 zones String
	 *@return: String   字典[]
	 */
	@Column(name ="ZONES",nullable=true,length=32)
	public String getZones(){
		return this.zones;
	}

	/**
	 *方法: 设置  区县  zones String 字典[]
	 *@param: String  
	 */
	public void setZones(String zones){
		this.zones = zones;
	}
	/**
	 *方法: 取得  分管领导 mgr String
	 *@return: String   字典[]
	 */
	@Column(name ="MGR",nullable=true,length=32)
	public String getMgr(){
		return this.mgr;
	}

	/**
	 *方法: 设置  分管领导  mgr String 字典[]
	 *@param: String  
	 */
	public void setMgr(String mgr){
		this.mgr = mgr;
	}
	/**
	 *方法: 取得  联系人 linkman String
	 *@return: String   字典[]
	 */
	@Column(name ="LINKMAN",nullable=true,length=32)
	public String getLinkman(){
		return this.linkman;
	}

	/**
	 *方法: 设置  联系人  linkman String 字典[]
	 *@param: String  
	 */
	public void setLinkman(String linkman){
		this.linkman = linkman;
	}
	/**
	 *方法: 取得  联系电话 tel String
	 *@return: String   字典[]
	 */
	@Column(name ="TEL",nullable=true,length=16)
	public String getTel(){
		return this.tel;
	}

	/**
	 *方法: 设置  联系电话  tel String 字典[]
	 *@param: String  
	 */
	public void setTel(String tel){
		this.tel = tel;
	}
	/**
	 *方法: 取得  传真号码 fax String
	 *@return: String   字典[]
	 */
	@Column(name ="FAX",nullable=true,length=16)
	public String getFax(){
		return this.fax;
	}

	/**
	 *方法: 设置  传真号码  fax String 字典[]
	 *@param: String  
	 */
	public void setFax(String fax){
		this.fax = fax;
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
	 *方法: 取得  通信地址 addr String
	 *@return: String   字典[]
	 */
	@Column(name ="ADDR",nullable=true,length=128)
	public String getAddr(){
		return this.addr;
	}

	/**
	 *方法: 设置  通信地址  addr String 字典[]
	 *@param: String  
	 */
	public void setAddr(String addr){
		this.addr = addr;
	}
	/**
	 *方法: 取得  邮政编码 postalcode String
	 *@return: String   字典[]
	 */
	@Column(name ="POSTALCODE",nullable=true,length=16)
	public String getPostalcode(){
		return this.postalcode;
	}

	/**
	 *方法: 设置  邮政编码  postalcode String 字典[]
	 *@param: String  
	 */
	public void setPostalcode(String postalcode){
		this.postalcode = postalcode;
	}
	/**
	 *方法: 取得  分类属性 deptCat String
	 *@return: String   字典[]
	 */
	@Column(name ="DEPT_CAT",nullable=true,length=16)
	public String getDeptCat(){
		return this.deptCat;
	}

	/**
	 *方法: 设置  分类属性  deptCat String 字典[]
	 *@param: String  
	 */
	public void setDeptCat(String deptCat){
		this.deptCat = deptCat;
	}
	/**
	 *方法: 取得  是否机构 isOrg String
	 *@return: String   字典[]
	 */
	@Column(name ="IS_ORG",nullable=true,length=1)
	public String getIsOrg(){
		return this.isOrg;
	}

	/**
	 *方法: 设置  是否机构  isOrg String 字典[]
	 *@param: String  
	 */
	public void setIsOrg(String isOrg){
		this.isOrg = isOrg;
	}
	/**
	 *方法: 取得  虚拟组织 isVirtual String
	 *@return: String   字典[]
	 */
	@Column(name ="IS_VIRTUAL",nullable=true,length=1)
	public String getIsVirtual(){
		return this.isVirtual;
	}

	/**
	 *方法: 设置  虚拟组织  isVirtual String 字典[]
	 *@param: String  
	 */
	public void setIsVirtual(String isVirtual){
		this.isVirtual = isVirtual;
	}
	/**
	 *方法: 取得  外委单位 isOutsourcing String
	 *@return: String   字典[]
	 */
	@Column(name ="IS_OUTSOURCING",nullable=true,length=1)
	public String getIsOutsourcing(){
		return this.isOutsourcing;
	}

	/**
	 *方法: 设置  外委单位  isOutsourcing String 字典[]
	 *@param: String  
	 */
	public void setIsOutsourcing(String isOutsourcing){
		this.isOutsourcing = isOutsourcing;
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
