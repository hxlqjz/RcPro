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
 * 统计表
 * @author lenovo
 *
 */
@Entity
@Table(name="bds_copy")
public class BdsCopy implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String pp;
	private String cj;
	private String cx;
	private String pl;
	private String nk;
	private String scplx;
	private String bm1;
	private String bm2;
	private String bm3;
	private String bm4;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="bds_copy")
	@TableGenerator(name="bds_copy",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "bds_copy",allocationSize = 1)
	@Column(name ="ID",nullable=false,precision=15,scale=0,length=22)	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="pp")
	public String getPp() {
		return pp;
	}
	@Column(name="cj")
	public String getCj() {
		return cj;
	}
	@Column(name="cx")
	public String getCx() {
		return cx;
	}
	@Column(name="pl")
	public String getPl() {
		return pl;
	}
	@Column(name="nk")
	public String getNk() {
		return nk;
	}
	@Column(name="scplx")
	public String getScplx() {
		return scplx;
	}
	
	public void setPp(String pp) {
		this.pp = pp;
	}
	public void setCj(String cj) {
		this.cj = cj;
	}
	public void setCx(String cx) {
		this.cx = cx;
	}
	public void setPl(String pl) {
		this.pl = pl;
	}
	public void setNk(String nk) {
		this.nk = nk;
	}
	public void setScplx(String scplx) {
		this.scplx = scplx;
	}
	@Column(name="bm1")
	public String getBm1() {
		return bm1;
	}
	public void setBm1(String bm1) {
		this.bm1 = bm1;
	}
	@Column(name="bm2")
	public String getBm2() {
		return bm2;
	}
	@Column(name="bm3")
	public String getBm3() {
		return bm3;
	}
	@Column(name="bm4")
	public String getBm4() {
		return bm4;
	}
	public void setBm2(String bm2) {
		this.bm2 = bm2;
	}
	public void setBm3(String bm3) {
		this.bm3 = bm3;
	}
	public void setBm4(String bm4) {
		this.bm4 = bm4;
	}

}
