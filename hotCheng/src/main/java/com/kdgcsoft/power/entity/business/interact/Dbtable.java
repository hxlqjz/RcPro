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
@Table(name="dbtable")
public class Dbtable implements Serializable{
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
	private String c11;
	private String c12;
	private String c13;
	private String c14;
	
	private String c21;
	private String c22;
	private String c23;
	private String c24;
	
	private String c31;
	private String c32;
	private String c33;
	private String c34;
	
	private String c41;
	private String c42;
	private String c43;
	private String c44;
	
	private String bf1;
	private String bf2;
	private String bf3;
	private String bf4;
	private String bf5;
	
	private String bds1;
	private String bds2;
	private String bds3;
	private String bds4;
	
	private String chang1;
	private String chang2;
	private String chang3;
	private String chang4;
	
	private String zh1;
	private String zh2;
	private String zh3;
	private String zh4;
	
	private String cy;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="dbtable")
	@TableGenerator(name="dbtable",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "dbtable",allocationSize = 1)
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
	
	
	@Column(name="c11")
	public String getC11() {
		return c11;
	}
	@Column(name="c12")
	public String getC12() {
		return c12;
	}
	@Column(name="c13")
	public String getC13() {
		return c13;
	}
	@Column(name="c14")
	public String getC14() {
		return c14;
	}
	@Column(name="c21")
	public String getC21() {
		return c21;
	}
	@Column(name="c22")
	public String getC22() {
		return c22;
	}
	@Column(name="c23")
	public String getC23() {
		return c23;
	}
	@Column(name="c24")
	public String getC24() {
		return c24;
	}
	@Column(name="c31")
	public String getC31() {
		return c31;
	}
	@Column(name="c32")
	public String getC32() {
		return c32;
	}
	@Column(name="c33")
	public String getC33() {
		return c33;
	}
	@Column(name="c34")
	public String getC34() {
		return c34;
	}
	@Column(name="c41")
	public String getC41() {
		return c41;
	}
	@Column(name="c42")
	public String getC42() {
		return c42;
	}
	@Column(name="c43")
	public String getC43() {
		return c43;
	}
	@Column(name="c44")
	public String getC44() {
		return c44;
	}
	@Column(name="chang1")
	public String getChang1() {
		return chang1;
	}
	@Column(name="chang2")
	public String getChang2() {
		return chang2;
	}
	@Column(name="chang3")
	public String getChang3() {
		return chang3;
	}
	@Column(name="chang4")
	public String getChang4() {
		return chang4;
	}
	@Column(name="zh1")
	public String getZh1() {
		return zh1;
	}
	@Column(name="zh2")
	public String getZh2() {
		return zh2;
	}
	@Column(name="zh3")
	public String getZh3() {
		return zh3;
	}
	@Column(name="zh4")
	public String getZh4() {
		return zh4;
	}
	@Column(name="cy")
	public String getCy() {
		return cy;
	}
	public void setChang1(String chang1) {
		this.chang1 = chang1;
	}
	public void setChang2(String chang2) {
		this.chang2 = chang2;
	}
	public void setChang3(String chang3) {
		this.chang3 = chang3;
	}
	public void setChang4(String chang4) {
		this.chang4 = chang4;
	}
	public void setZh1(String zh1) {
		this.zh1 = zh1;
	}
	public void setZh2(String zh2) {
		this.zh2 = zh2;
	}
	public void setZh3(String zh3) {
		this.zh3 = zh3;
	}
	public void setZh4(String zh4) {
		this.zh4 = zh4;
	}
	public void setCy(String cy) {
		this.cy = cy;
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
	
	public void setC11(String c11) {
		this.c11 = c11;
	}
	public void setC12(String c12) {
		this.c12 = c12;
	}
	public void setC13(String c13) {
		this.c13 = c13;
	}
	public void setC14(String c14) {
		this.c14 = c14;
	}
	public void setC21(String c21) {
		this.c21 = c21;
	}
	public void setC22(String c22) {
		this.c22 = c22;
	}
	public void setC23(String c23) {
		this.c23 = c23;
	}
	public void setC24(String c24) {
		this.c24 = c24;
	}
	public void setC31(String c31) {
		this.c31 = c31;
	}
	public void setC32(String c32) {
		this.c32 = c32;
	}
	public void setC33(String c33) {
		this.c33 = c33;
	}
	public void setC34(String c34) {
		this.c34 = c34;
	}
	public void setC41(String c41) {
		this.c41 = c41;
	}
	public void setC42(String c42) {
		this.c42 = c42;
	}
	public void setC43(String c43) {
		this.c43 = c43;
	}
	public void setC44(String c44) {
		this.c44 = c44;
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
