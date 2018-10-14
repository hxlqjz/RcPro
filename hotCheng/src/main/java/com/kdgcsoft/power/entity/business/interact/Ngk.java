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
@Table(name="ngk")
public class Ngk implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ngkBrand;
	private String ngkType;
	private String outputAmount;
	private String marketYear;
	private String engineType;
	private String sparkPlug;
	private String stockNum;
	private String ixSprakPlug;
	private String stockNumOther;
	private String gearNum;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="ngk")
	@TableGenerator(name="ngk",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "ngk",allocationSize = 1)
	@Column(name ="ngkBrand",nullable=false,precision=15,scale=0,length=22)	
	public String getNgkBrand() {
		return ngkBrand;
	}
	public void setNgkBrand(String ngkBrand) {
		this.ngkBrand = ngkBrand;
	}
	
	@Column(name="ngk_type")
	public String getNgkType() {
		return ngkType;
	}
	public void setNgkType(String ngkType) {
		this.ngkType = ngkType;
	}
	
	@Column(name="output_amount")
	public String getOutputAmount() {
		return outputAmount;
	}
	public void setOutputAmount(String outputAmount) {
		this.outputAmount = outputAmount;
	}
	
	@Column(name="market_year")
	public String getMarketYear() {
		return marketYear;
	}
	public void setMarketYear(String marketYear) {
		this.marketYear = marketYear;
	}
	
	@Column(name="engine_type")
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	
	@Column(name="spark_plug")
	public String getSparkPlug() {
		return sparkPlug;
	}
	public void setSparkPlug(String sparkPlug) {
		this.sparkPlug = sparkPlug;
	}
	
	@Column(name="stock_num")
	public String getStockNum() {
		return stockNum;
	}
	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}
	
	@Column(name="ix_sprak_plug")
	public String getIxSprakPlug() {
		return ixSprakPlug;
	}
	public void setIxSprakPlug(String ixSprakPlug) {
		this.ixSprakPlug = ixSprakPlug;
	}
	
	@Column(name="stock_num_other")
	public String getStockNumOther() {
		return stockNumOther;
	}
	public void setStockNumOther(String stockNumOther) {
		this.stockNumOther = stockNumOther;
	}
	
	@Column(name="gear_num")
	public String getGearNum() {
		return gearNum;
	}
	public void setGearNum(String gearNum) {
		this.gearNum = gearNum;
	}
	

}
