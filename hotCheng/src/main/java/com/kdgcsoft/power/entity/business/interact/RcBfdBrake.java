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
 * 制动器查询
 * @author lenovo
 *
 */
@Entity
@Table(name="rc_bfd_brake")
public class RcBfdBrake  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String wheel;
	private String bfd;
	private String bfCode;
	private String bfMemo;
	private String bdsCode;
	private String bdsMemo;
	private String dCode;
	private String fmsiCode;
	private String fldm1;
	private String fldm2;
	private String trw1;
	private String trw2;
	private String oe1;
	private String oe2;
	private String oe3;
	private String oe4;
	private String oe5;
	
	private String imgSrc;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="rc_bfd_brake")
	@TableGenerator(name="rc_bfd_brake",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "rc_bfd_brake",allocationSize = 1)
	@Column(name ="ID",nullable=false,precision=15,scale=0,length=22)	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="wheel")
	public String getWheel() {
		return wheel;
	}
	@Column(name="bfd")
	public String getBfd() {
		return bfd;
	}
	@Column(name="bf_code")
	public String getBfCode() {
		return bfCode;
	}
	@Column(name="bf_memo")
	public String getBfMemo() {
		return bfMemo;
	}
	@Column(name="bds_code")
	public String getBdsCode() {
		return bdsCode;
	}
	@Column(name="bds_memo")
	public String getBdsMemo() {
		return bdsMemo;
	}
	@Column(name="d_code")
	public String getdCode() {
		return dCode;
	}
	@Column(name="fmsi_code")
	public String getFmsiCode() {
		return fmsiCode;
	}
	@Column(name="fldm1")
	public String getFldm1() {
		return fldm1;
	}
	@Column(name="fldm2")
	public String getFldm2() {
		return fldm2;
	}
	@Column(name="trw1")
	public String getTrw1() {
		return trw1;
	}
	@Column(name="trw2")
	public String getTrw2() {
		return trw2;
	}
	@Column(name="oe1")
	public String getOe1() {
		return oe1;
	}
	@Column(name="oe2")
	public String getOe2() {
		return oe2;
	}
	@Column(name="oe3")
	public String getOe3() {
		return oe3;
	}
	@Column(name="oe4")
	public String getOe4() {
		return oe4;
	}
	@Column(name="oe5")
	public String getOe5() {
		return oe5;
	}
	
	@Column(name="img_src")
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public void setWheel(String wheel) {
		this.wheel = wheel;
	}
	public void setBfd(String bfd) {
		this.bfd = bfd;
	}
	public void setBfCode(String bfCode) {
		this.bfCode = bfCode;
	}
	public void setBfMemo(String bfMemo) {
		this.bfMemo = bfMemo;
	}
	public void setBdsCode(String bdsCode) {
		this.bdsCode = bdsCode;
	}
	public void setBdsMemo(String bdsMemo) {
		this.bdsMemo = bdsMemo;
	}
	public void setdCode(String dCode) {
		this.dCode = dCode;
	}
	public void setFmsiCode(String fmsiCode) {
		this.fmsiCode = fmsiCode;
	}
	public void setFldm1(String fldm1) {
		this.fldm1 = fldm1;
	}
	public void setFldm2(String fldm2) {
		this.fldm2 = fldm2;
	}
	public void setTrw1(String trw1) {
		this.trw1 = trw1;
	}
	public void setTrw2(String trw2) {
		this.trw2 = trw2;
	}
	public void setOe1(String oe1) {
		this.oe1 = oe1;
	}
	public void setOe2(String oe2) {
		this.oe2 = oe2;
	}
	public void setOe3(String oe3) {
		this.oe3 = oe3;
	}
	public void setOe4(String oe4) {
		this.oe4 = oe4;
	}
	public void setOe5(String oe5) {
		this.oe5 = oe5;
	}
	

}
