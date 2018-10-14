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
 * 用户注册输入错误注册码
 * @author lenovo
 *
 */
@Entity
@Table(name="rp_reg")
public class RpReg implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String wechatNo;
	private Date time;
	private Long num;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="rp_reg")
	@TableGenerator(name="rp_reg",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "rp_reg",allocationSize = 1)
	@Column(name ="ID",nullable=false,precision=15,scale=0,length=22)	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="WECHAT_NO")
	public String getWechatNo() {
		return wechatNo;
	}
	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}
	@Column(name="TIME")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Column(name="NUM")
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	
	

}
