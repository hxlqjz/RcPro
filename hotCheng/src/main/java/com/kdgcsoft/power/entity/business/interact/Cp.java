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
@Table(name="cp")
public class Cp implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String bf;
	private String bds;
	
	@Column(name ="BF")	
	public String getBf() {
		return bf;
	}
	@Id
	@Column(name ="BDS")	
	public String getBds() {
		return bds;
	}
	public void setBf(String bf) {
		this.bf = bf;
	}
	public void setBds(String bds) {
		this.bds = bds;
	}

	

	

}
