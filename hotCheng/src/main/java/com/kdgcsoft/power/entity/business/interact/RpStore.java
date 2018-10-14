package com.kdgcsoft.power.entity.business.interact;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 门店表
 * @author lenovo
 *
 */
@Entity
@Table(name="rp_store")
public class RpStore implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String idCode;
	private String province;
	private String storeName;
	private Long queryPower;
	private Long rolePower;
	private String marks;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="rp_store")
	@TableGenerator(name="rp_store",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "rp_store",allocationSize = 1)
	@Column(name ="ID",nullable=false,precision=15,scale=0,length=22)	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="ID_CODE")
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	@Column(name="PROVINCE")
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Column(name="STORE_NAME")
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	@Column(name="QUERY_POWER")
	public Long getQueryPower() {
		return queryPower;
	}
	public void setQueryPower(Long queryPower) {
		this.queryPower = queryPower;
	}
	@Column(name="ROLE_POWER")
	public Long getRolePower() {
		return rolePower;
	}
	public void setRolePower(Long rolePower) {
		this.rolePower = rolePower;
	}
	@Column(name="MARKS")
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	
	

}
