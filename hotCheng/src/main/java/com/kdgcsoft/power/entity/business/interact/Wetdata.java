package com.kdgcsoft.power.entity.business.interact;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="wetdata")
public class Wetdata  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String pping;
	private String xche;
	private String lpai;
	private String bban;
	private String xh1;
	private String xh2;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="wetdata")
	@TableGenerator(name="wetdata",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "wetdata",allocationSize = 1)
	@Column(name ="pping",nullable=false,precision=15,scale=0,length=22)	
	public String getPping() {
		return pping;
	}
	public void setPping(String pping) {
		this.pping = pping;
	}
	@Column(name="xche")
	public String getXche() {
		return xche;
	}
	public void setXche(String xche) {
		this.xche = xche;
	}
	@Column(name="lpai")
	public String getLpai() {
		return lpai;
	}
	public void setLpai(String lpai) {
		this.lpai = lpai;
	}
	@Column(name="bban")
	public String getBban() {
		return bban;
	}
	public void setBban(String bban) {
		this.bban = bban;
	}
	@Column(name="xh1")
	public String getXh1() {
		return xh1;
	}
	public void setXh1(String xh1) {
		this.xh1 = xh1;
	}
	@Column(name="xh2")
	public String getXh2() {
		return xh2;
	}
	public void setXh2(String xh2) {
		this.xh2 = xh2;
	}
	
	

}
