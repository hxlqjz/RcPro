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
@Table(name="hb")
public class Hb implements Serializable{
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
	private String c6;
	private String c7;
	private String c8;
	private String c9;
	private String c10;
	private String c11;
	private String c12;
	private String c13;
	private String c14;
	private String c15;
	private String c16;
	private String c17;
	private String c18;
	private String c19;
	private String c20;
	private String c21;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="hb")
	@TableGenerator(name="hb",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "hb",allocationSize = 1)
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
	@Column(name="c6")
	public String getC6() {
		return c6;
	}
	@Column(name="c7")
	public String getC7() {
		return c7;
	}
	@Column(name="c8")
	public String getC8() {
		return c8;
	}
	@Column(name="c9")
	public String getC9() {
		return c9;
	}
	@Column(name="c10")
	public String getC10() {
		return c10;
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
	@Column(name="c15")
	public String getC15() {
		return c15;
	}
	@Column(name="c16")
	public String getC16() {
		return c16;
	}
	@Column(name="c17")
	public String getC17() {
		return c17;
	}
	@Column(name="c18")
	public String getC18() {
		return c18;
	}
	@Column(name="c19")
	public String getC19() {
		return c19;
	}
	@Column(name="c20")
	public String getC20() {
		return c20;
	}
	@Column(name="c21")
	public String getC21() {
		return c21;
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
	public void setC6(String c6) {
		this.c6 = c6;
	}
	public void setC7(String c7) {
		this.c7 = c7;
	}
	public void setC8(String c8) {
		this.c8 = c8;
	}
	public void setC9(String c9) {
		this.c9 = c9;
	}
	public void setC10(String c10) {
		this.c10 = c10;
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
	public void setC15(String c15) {
		this.c15 = c15;
	}
	public void setC16(String c16) {
		this.c16 = c16;
	}
	public void setC17(String c17) {
		this.c17 = c17;
	}
	public void setC18(String c18) {
		this.c18 = c18;
	}
	public void setC19(String c19) {
		this.c19 = c19;
	}
	public void setC20(String c20) {
		this.c20 = c20;
	}
	public void setC21(String c21) {
		this.c21 = c21;
	}
	
	

}
