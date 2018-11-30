package com.kdgcsoft.power.entity.business.interact;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="hb_copy")
public class HbCopy implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String pp;
	private String cj;
	private String cx;
	private String pl;
	private String nk;
	private String scplx;
	private String d1;
	private String d2;
	private String d3;
	private String d4;
	private String d5;
	private String d6;
	private String d7;
	private String t1;
	private String t2;
	private String fl1;
	private String fl2;
	private String o1;
	private String o2;
	private String o3;
	private String c1;
	private String c2;
	private String c3;
	private String c4;
	private String c5;
	
	private String bf1;
	private String bf2;
	private String bf3;
	private String bf4;
	private String bf5;
	
	private String bds1;
	private String bds2;
	private String bds3;
	private String bds4;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="hb_copy")
	@TableGenerator(name="hb_copy",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "hb_copy",allocationSize = 1)
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
	@Column(name="d1")
	public String getD1() {
		return d1;
	}
	@Column(name="d2")
	public String getD2() {
		return d2;
	}
	@Column(name="d3")
	public String getD3() {
		return d3;
	}
	@Column(name="d4")
	public String getD4() {
		return d4;
	}
	@Column(name="d5")
	public String getD5() {
		return d5;
	}
	@Column(name="d6")
	public String getD6() {
		return d6;
	}
	@Column(name="d7")
	public String getD7() {
		return d7;
	}
	@Column(name="t1")
	public String getT1() {
		return t1;
	}
	@Column(name="t2")
	public String getT2() {
		return t2;
	}
	@Column(name="fl1")
	public String getFl1() {
		return fl1;
	}
	@Column(name="fl2")
	public String getFl2() {
		return fl2;
	}
	@Column(name="o1")
	public String getO1() {
		return o1;
	}
	@Column(name="o2")
	public String getO2() {
		return o2;
	}
	@Column(name="o3")
	public String getO3() {
		return o3;
	}
	@Column(name="c1")
	public String getC1() {
		return c1;
	}
	@Column(name="c2")
	public String getC2() {
		return c2;
	}
	@Column(name="c3")
	public String getC3() {
		return c3;
	}
	@Column(name="c4")
	public String getC4() {
		return c4;
	}
	@Column(name="c5")
	public String getC5() {
		return c5;
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
	public void setD1(String d1) {
		this.d1 = d1;
	}
	public void setD2(String d2) {
		this.d2 = d2;
	}
	public void setD3(String d3) {
		this.d3 = d3;
	}
	public void setD4(String d4) {
		this.d4 = d4;
	}
	public void setD5(String d5) {
		this.d5 = d5;
	}
	public void setD6(String d6) {
		this.d6 = d6;
	}
	public void setD7(String d7) {
		this.d7 = d7;
	}
	public void setT1(String t1) {
		this.t1 = t1;
	}
	public void setT2(String t2) {
		this.t2 = t2;
	}
	public void setFl1(String fl1) {
		this.fl1 = fl1;
	}
	public void setFl2(String fl2) {
		this.fl2 = fl2;
	}
	public void setO1(String o1) {
		this.o1 = o1;
	}
	public void setO2(String o2) {
		this.o2 = o2;
	}
	public void setO3(String o3) {
		this.o3 = o3;
	}
	public void setC1(String c1) {
		this.c1 = c1;
	}
	public void setC2(String c2) {
		this.c2 = c2;
	}
	public void setC3(String c3) {
		this.c3 = c3;
	}
	public void setC4(String c4) {
		this.c4 = c4;
	}
	public void setC5(String c5) {
		this.c5 = c5;
	}
	
	@Column(name="bf1")
	public String getBf1() {
		return bf1;
	}
	@Column(name="bf2")
	public String getBf2() {
		return bf2;
	}
	@Column(name="bf3")
	public String getBf3() {
		return bf3;
	}
	@Column(name="bf4")
	public String getBf4() {
		return bf4;
	}
	@Column(name="bf5")
	public String getBf5() {
		return bf5;
	}
	@Column(name="bds1")
	public String getBds1() {
		return bds1;
	}
	@Column(name="bds2")
	public String getBds2() {
		return bds2;
	}
	@Column(name="bds3")
	public String getBds3() {
		return bds3;
	}
	@Column(name="bds4")
	public String getBds4() {
		return bds4;
	}
	public void setBf1(String bf1) {
		this.bf1 = bf1;
	}
	public void setBf2(String bf2) {
		this.bf2 = bf2;
	}
	public void setBf3(String bf3) {
		this.bf3 = bf3;
	}
	public void setBf4(String bf4) {
		this.bf4 = bf4;
	}
	public void setBf5(String bf5) {
		this.bf5 = bf5;
	}
	public void setBds1(String bds1) {
		this.bds1 = bds1;
	}
	public void setBds2(String bds2) {
		this.bds2 = bds2;
	}
	public void setBds3(String bds3) {
		this.bds3 = bds3;
	}
	public void setBds4(String bds4) {
		this.bds4 = bds4;
	}

}
