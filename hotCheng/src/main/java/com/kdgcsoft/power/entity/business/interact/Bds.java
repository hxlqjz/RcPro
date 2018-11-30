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
@Table(name="bds")
public class Bds implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String pp;
	private String cj;
	private String cx;
	private String pl;
	private String nk;
	private String scplx;
	private String bm;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="bds")
	@TableGenerator(name="bds",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "bds",allocationSize = 1)
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
	@Column(name="bm")
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}

}
