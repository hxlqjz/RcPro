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
 * 用户表
 * @author lenovo
 *
 */
@Entity
@Table(name="rp_user")
public class RpUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String wechatNo;
	private String idCode;
	private String userName;
	private String tel;
	private String info;
	private Long queryPower;
	private Long rolePower;
	private Long isBlack;
	private String nickName;
	@Column(name="NICK_NAME")
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="rp_user")
	@TableGenerator(name="rp_user",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "rp_user",allocationSize = 1)
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
	@Column(name="ID_CODE")
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	@Column(name="USER_NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="TEL")
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name="INFO")
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Column(name="QUERY_POWER")
	public Long getQueryPower() {
		return queryPower;
	}
	public void setQueryPower(Long queryPower) {
		this.queryPower = queryPower;
	}
	@Column(name="ROLE_POWER")
	public Long getRolePower() {
		return rolePower;
	}
	public void setRolePower(Long rolePower) {
		this.rolePower = rolePower;
	}
	@Column(name="IS_BLACK")
	public Long getIsBlack() {
		return isBlack;
	}
	public void setIsBlack(Long isBlack) {
		this.isBlack = isBlack;
	}
	

}
