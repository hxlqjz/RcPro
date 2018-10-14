package com.kdgcsoft.power.entity.business.interact;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name="car_sys_nation")
public class CarSysNation  implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long sysId;
	private String carBrand;
	private String carNation;
	private String content;
	private String alphabet;
	private int orderNo;

	private int height;

	private int width;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="car_sys_nation")
	@TableGenerator(name="car_sys_nation",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "car_sys_nation",allocationSize = 1)
	@Column(name ="sysId",nullable=false,precision=15,scale=0,length=22)
	public Long getSysId() {
		return sysId;
	}
	public void setSysId(Long sysId) {
		this.sysId = sysId;
	}
	@Column(name="carBrand")
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	@Column(name="carNation")
	public String getCarNation() {
		return carNation;
	}
	public void setCarNation(String carNation) {
		this.carNation = carNation;
	}
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="alphabet")
	public String getAlphabet() {
		return alphabet;
	}
	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}
	@Column(name="orderNo")
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	@Transient
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@Transient
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	
	

}
