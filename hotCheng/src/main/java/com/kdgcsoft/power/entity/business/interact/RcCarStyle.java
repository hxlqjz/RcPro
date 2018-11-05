package com.kdgcsoft.power.entity.business.interact;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 新的热骋车型目录，包含制动片油，及其他信息
 * @author lenovo
 *
 */
@Entity
@Table(name="rc_car_style")
public class RcCarStyle  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String lyId;
	private String pp;
	private String cj;
	private String cx;
	private String nk;
	private String plms;
	private String pth;
	private String fdjxh;
	private String bsqms;
	private String lggg;
	private String xsmc;
	private String scnf;
	private String tcnf;
	private String pfbz;
	private String jqxs;
	private String rylx;
	private String rybh;
	private String qgs;
	private String dws;
	private String qzdqlx;
	private String hzdqlx;
	private String zllx;
	private String zj;
	private String ltgg;
	private String jssx;
	private String bysx;
	private String rhybyjzl;
	private String rhyAvia1;
	private String rhyAvia2;
	private String rhyAvia3;
	private String rhyAvia4;
	private String bsxyAvia;
	private String bsxybz;
	private String bsxdxjzl;
	private String bsxbyjzl;
	
	private String bfQzd1;
	private String bfQzd2;
	private String bfQzd3;
	private String bfHzd1;
	private String bfHzd2;
	private String bfhzd3;
	
	private String bdsQzd1;
	private String bdsQzd2;
	private String bdsQzd3;
	private String bdsHzd1;
	private String bdsHzd2;
	private String bdsHzd3;
	
	private String nbe1;
	private String nbe2;
	private String lg1;
	private String lg2;
	@Id
	@Column(name ="lyId",nullable=false,precision=15,scale=0,length=22)
	public String getLyId() {
		return lyId;
	}
	public void setLyId(String lyId) {
		this.lyId = lyId;
	}
	@Column(name="pp")
	public String getPp() {
		return pp;
	}
	public void setPp(String pp) {
		this.pp = pp;
	}
	@Column(name="cj")
	public String getCj() {
		return cj;
	}
	public void setCj(String cj) {
		this.cj = cj;
	}
	@Column(name="cx")
	public String getCx() {
		return cx;
	}
	public void setCx(String cx) {
		this.cx = cx;
	}
	@Column(name="nk")
	public String getNk() {
		return nk;
	}
	public void setNk(String nk) {
		this.nk = nk;
	}
	@Column(name="plms")
	public String getPlms() {
		return plms;
	}
	public void setPlms(String plms) {
		this.plms = plms;
	}
	@Column(name="pth")
	public String getPth() {
		return pth;
	}
	public void setPth(String pth) {
		this.pth = pth;
	}
	@Column(name="bsqms")
	public String getBsqms() {
		return bsqms;
	}
	public void setBsqms(String bsqms) {
		this.bsqms = bsqms;
	}
	@Column(name="lggg")
	public String getLggg() {
		return lggg;
	}
	public void setLggg(String lggg) {
		this.lggg = lggg;
	}
	@Column(name="xsmc")
	public String getXsmc() {
		return xsmc;
	}
	public void setXsmc(String xsmc) {
		this.xsmc = xsmc;
	}
	@Column(name="scnf")
	public String getScnf() {
		return scnf;
	}
	public void setScnf(String scnf) {
		this.scnf = scnf;
	}
	@Column(name="tcnf")
	public String getTcnf() {
		return tcnf;
	}
	public void setTcnf(String tcnf) {
		this.tcnf = tcnf;
	}
	@Column(name="pfbz")
	public String getPfbz() {
		return pfbz;
	}
	public void setPfbz(String pfbz) {
		this.pfbz = pfbz;
	}
	@Column(name="jqxs")
	public String getJqxs() {
		return jqxs;
	}
	public void setJqxs(String jqxs) {
		this.jqxs = jqxs;
	}
	@Column(name="rylx")
	public String getRylx() {
		return rylx;
	}
	public void setRylx(String rylx) {
		this.rylx = rylx;
	}
	@Column(name="rybh")
	public String getRybh() {
		return rybh;
	}
	public void setRybh(String rybh) {
		this.rybh = rybh;
	}
	@Column(name="qgs")
	public String getQgs() {
		return qgs;
	}
	public void setQgs(String qgs) {
		this.qgs = qgs;
	}
	@Column(name="dws")
	public String getDws() {
		return dws;
	}
	public void setDws(String dws) {
		this.dws = dws;
	}
	@Column(name="qzdqlx")
	public String getQzdqlx() {
		return qzdqlx;
	}
	public void setQzdqlx(String qzdqlx) {
		this.qzdqlx = qzdqlx;
	}
	@Column(name="hzdqlx")
	public String getHzdqlx() {
		return hzdqlx;
	}
	public void setHzdqlx(String hzdqlx) {
		this.hzdqlx = hzdqlx;
	}
	@Column(name="zllx")
	public String getZllx() {
		return zllx;
	}
	public void setZllx(String zllx) {
		this.zllx = zllx;
	}
	@Column(name="zj")
	public String getZj() {
		return zj;
	}
	public void setZj(String zj) {
		this.zj = zj;
	}
	@Column(name="ltgg")
	public String getLtgg() {
		return ltgg;
	}
	public void setLtgg(String ltgg) {
		this.ltgg = ltgg;
	}
	@Column(name="jssx")
	public String getJssx() {
		return jssx;
	}
	public void setJssx(String jssx) {
		this.jssx = jssx;
	}
	@Column(name="bysx")
	public String getBysx() {
		return bysx;
	}
	public void setBysx(String bysx) {
		this.bysx = bysx;
	}
	@Column(name="rhybyjzl")
	public String getRhybyjzl() {
		return rhybyjzl;
	}
	public void setRhybyjzl(String rhybyjzl) {
		this.rhybyjzl = rhybyjzl;
	}
	@Column(name="rhy_avia_1")
	public String getRhyAvia1() {
		return rhyAvia1;
	}
	public void setRhyAvia1(String rhyAvia1) {
		this.rhyAvia1 = rhyAvia1;
	}
	@Column(name="rhy_avia_2")
	public String getRhyAvia2() {
		return rhyAvia2;
	}
	public void setRhyAvia2(String rhyAvia2) {
		this.rhyAvia2 = rhyAvia2;
	}
	@Column(name="rhy_avia_3")
	public String getRhyAvia3() {
		return rhyAvia3;
	}
	public void setRhyAvia3(String rhyAvia3) {
		this.rhyAvia3 = rhyAvia3;
	}
	@Column(name="rhy_avia_4")
	public String getRhyAvia4() {
		return rhyAvia4;
	}
	public void setRhyAvia4(String rhyAvia4) {
		this.rhyAvia4 = rhyAvia4;
	}
	@Column(name="bsxy_avia")
	public String getBsxyAvia() {
		return bsxyAvia;
	}
	public void setBsxyAvia(String bsxyAvia) {
		this.bsxyAvia = bsxyAvia;
	}
	@Column(name="bsxybz")
	public String getBsxybz() {
		return bsxybz;
	}
	public void setBsxybz(String bsxybz) {
		this.bsxybz = bsxybz;
	}
	@Column(name="bsxdxjzl")
	public String getBsxdxjzl() {
		return bsxdxjzl;
	}
	public void setBsxdxjzl(String bsxdxjzl) {
		this.bsxdxjzl = bsxdxjzl;
	}
	@Column(name="bsxbyjzl")
	public String getBsxbyjzl() {
		return bsxbyjzl;
	}
	public void setBsxbyjzl(String bsxbyjzl) {
		this.bsxbyjzl = bsxbyjzl;
	}
	@Column(name="fdjxh")
	public String getFdjxh() {
		return fdjxh;
	}
	public void setFdjxh(String fdjxh) {
		this.fdjxh = fdjxh;
	}
	
	@Column(name="bf_qzd_1")
	public String getBfQzd1() {
		return bfQzd1;
	}
	public void setBfQzd1(String bfQzd1) {
		this.bfQzd1 = bfQzd1;
	}
	@Column(name="bf_qzd_2")
	public String getBfQzd2() {
		return bfQzd2;
	}
	public void setBfQzd2(String bfQzd2) {
		this.bfQzd2 = bfQzd2;
	}
	@Column(name="bf_qzd_3")
	public String getBfQzd3() {
		return bfQzd3;
	}
	public void setBfQzd3(String bfQzd3) {
		this.bfQzd3 = bfQzd3;
	}
	@Column(name="bf_hzd_1")
	public String getBfHzd1() {
		return bfHzd1;
	}
	public void setBfHzd1(String bfHzd1) {
		this.bfHzd1 = bfHzd1;
	}
	@Column(name="bf_hzd_2")
	public String getBfHzd2() {
		return bfHzd2;
	}
	public void setBfHzd2(String bfHzd2) {
		this.bfHzd2 = bfHzd2;
	}
	@Column(name="bf_hzd_3")
	public String getBfhzd3() {
		return bfhzd3;
	}
	public void setBfhzd3(String bfhzd3) {
		this.bfhzd3 = bfhzd3;
	}
	@Column(name="bds_qzd_1")
	public String getBdsQzd1() {
		return bdsQzd1;
	}
	public void setBdsQzd1(String bdsQzd1) {
		this.bdsQzd1 = bdsQzd1;
	}
	@Column(name="bds_qzd_2")
	public String getBdsQzd2() {
		return bdsQzd2;
	}
	public void setBdsQzd2(String bdsQzd2) {
		this.bdsQzd2 = bdsQzd2;
	}
	@Column(name="bds_qzd_3")
	public String getBdsQzd3() {
		return bdsQzd3;
	}
	public void setBdsQzd3(String bdsQzd3) {
		this.bdsQzd3 = bdsQzd3;
	}
	@Column(name="bds_hzd_1")
	public String getBdsHzd1() {
		return bdsHzd1;
	}
	public void setBdsHzd1(String bdsHzd1) {
		this.bdsHzd1 = bdsHzd1;
	}
	@Column(name="bds_hzd_2")
	public String getBdsHzd2() {
		return bdsHzd2;
	}
	public void setBdsHzd2(String bdsHzd2) {
		this.bdsHzd2 = bdsHzd2;
	}
	@Column(name="bds_hzd_3")
	public String getBdsHzd3() {
		return bdsHzd3;
	}
	public void setBdsHzd3(String bdsHzd3) {
		this.bdsHzd3 = bdsHzd3;
	}
	@Column(name="nbe_1")
	public String getNbe1() {
		return nbe1;
	}
	public void setNbe1(String nbe1) {
		this.nbe1 = nbe1;
	}
	@Column(name="nbe_2")
	public String getNbe2() {
		return nbe2;
	}
	public void setNbe2(String nbe2) {
		this.nbe2 = nbe2;
	}
	@Column(name="lg_1")
	public String getLg1() {
		return lg1;
	}
	public void setLg1(String lg1) {
		this.lg1 = lg1;
	}
	@Column(name="lg_2")
	public String getLg2() {
		return lg2;
	}
	public void setLg2(String lg2) {
		this.lg2 = lg2;
	}

}
