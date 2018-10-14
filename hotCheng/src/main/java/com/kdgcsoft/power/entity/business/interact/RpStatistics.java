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
@Table(name="rp_statistics")
public class RpStatistics implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String presaleArea;
	private String qrCode;
	private Long qrNo;
	private String productModel;
	private Long activiateStatus;
	private Date scanTime;
	private Date qualityStart;
	private Date qualityEnd;
	private Date cashTime;
	
	private String scanWechat;
	private String province;
	private String storeName;
	
	private String idCode;
	private String wechatName;
	private String wechatTel;
	private String nickName;
	@Column(name="NICK_NAME")
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	private String wechatInfo;
	private Long queryPower;
	private Long rolePower;
	private String plateNumber;
	private String carTel;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="rp_statistics")
	@TableGenerator(name="rp_statistics",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "rp_statistics",allocationSize = 1)
	@Column(name ="ID",nullable=false,precision=15,scale=0,length=22)	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="PRESALE_AREA")
	public String getPresaleArea() {
		return presaleArea; 
	}
	public void setPresaleArea(String presaleArea) {
		this.presaleArea = presaleArea;
	}
	@Column(name="QR_CODE")
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	@Column(name="QR_NO")
	public Long getQrNo() {
		return qrNo;
	}
	public void setQrNo(Long qrNo) {
		this.qrNo = qrNo;
	}
	
	@Column(name="PRODUCT_MODEL")
	public String getProductModel() {
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	@Column(name="ACTIVIATE_STATUS")
	public Long getActiviateStatus() {
		return activiateStatus;
	}
	public void setActiviateStatus(Long activiateStatus) {
		this.activiateStatus = activiateStatus;
	}
	@Column(name="SCAN_TIME")
	public Date getScanTime() {
		return scanTime;
	}
	public void setScanTime(Date scanTime) {
		this.scanTime = scanTime;
	}
	@Column(name="QUALITY_START")
	public Date getQualityStart() {
		return qualityStart;
	}
	public void setQualityStart(Date qualityStart) {
		this.qualityStart = qualityStart;
	}
	@Column(name="QUALITY_END")
	public Date getQualityEnd() {
		return qualityEnd;
	}
	public void setQualityEnd(Date qualityEnd) {
		this.qualityEnd = qualityEnd;
	}
	@Column(name="CASH_TIME")
	public Date getCashTime() {
		return cashTime;
	}
	public void setCashTime(Date cashTime) {
		this.cashTime = cashTime;
	}
	@Column(name="SCAN_WECHAT")
	public String getScanWechat() {
		return scanWechat;
	}
	public void setScanWechat(String scanWechat) {
		this.scanWechat = scanWechat;
	}
	@Column(name="PROVINCE")
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Column(name="STORE_NAME")
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	@Column(name="ID_CODE")
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	@Column(name="WECHAT_NAME")
	public String getWechatName() {
		return wechatName;
	}
	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}
	@Column(name="WECHAT_TEL")
	public String getWechatTel() {
		return wechatTel;
	}
	public void setWechatTel(String wechatTel) {
		this.wechatTel = wechatTel;
	}
	@Column(name="WECHAT_INFO")
	public String getWechatInfo() {
		return wechatInfo;
	}
	public void setWechatInfo(String wechatInfo) {
		this.wechatInfo = wechatInfo;
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
	@Column(name="PLATE_NUMBER")
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	@Column(name="CAR_TEL")
	public String getCarTel() {
		return carTel;
	}
	public void setCarTel(String carTel) {
		this.carTel = carTel;
	}
	
	

}
