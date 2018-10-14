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
@Table(name="car_style")
public class CarStyle  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long lyId;
	private String carBrand;
	private String carSys;
	private String carForm;
	private String carOutput;
	private String carYear;
	private String marketYear;
	private String saleName;
	private String fatory;
	private String chassis;
	private String outputStand;
	private String carClass;
	private String nation;
	private String nationImpClass;
	private String engineType;
	private String cylinderVolume;
	private String gasForm;
	private String gasType;
	private String gasGrade;
	private String maxHorsepower;
	private String maxPower;
	private String cylinderArrayform;
	private String cylinderNum;
	private String percylinderNum;
	private String shiftMemo;
	private String gearsNum;
	private String prefixBrakingType;
	private String suffixBrakingType;
	private String assistanceType;
	private String engineSite;
	private String driveForm;
	private String wheelbase;
	private String preffixTyreScale;
	private String suffixTyreScale;
	private String hubMaterial;
	private String elecWindow;
	private String wholeWindow;
	private String xenonLamp;
	private String prefixFoglamp;
	private String suffixBrush;
	private String airCondition;
	private String autoAircondition;
	private String goodA;
	private String goodB;
	private String goodC;
	private String goodD;
	private String goodE;
	private String goodF;
	private String goodG;
	private String goodH;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="car_style")
	@TableGenerator(name="car_style",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "car_style",allocationSize = 1)
	@Column(name ="lyId",nullable=false,precision=15,scale=0,length=22)
	public Long getLyId() {
		return lyId;
	}
	public void setLyId(Long lyId) {
		this.lyId = lyId;
	}
	@Column(name="car_brand")
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	@Column(name="car_sys")
	public String getCarSys() {
		return carSys;
	}
	public void setCarSys(String carSys) {
		this.carSys = carSys;
	}
	@Column(name="car_form")
	public String getCarForm() {
		return carForm;
	}
	public void setCarForm(String carForm) {
		this.carForm = carForm;
	}
	@Column(name="car_output")
	public String getCarOutput() {
		return carOutput;
	}
	public void setCarOutput(String carOutput) {
		this.carOutput = carOutput;
	}
	@Column(name="car_year")
	public String getCarYear() {
		return carYear;
	}
	public void setCarYear(String carYear) {
		this.carYear = carYear;
	}
	@Column(name="market_year")
	public String getMarketYear() {
		return marketYear;
	}
	public void setMarketYear(String marketYear) {
		this.marketYear = marketYear;
	}
	@Column(name="sale_name")
	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	@Column(name="fatory")
	public String getFatory() {
		return fatory;
	}
	public void setFatory(String fatory) {
		this.fatory = fatory;
	}
	@Column(name="chassis")
	public String getChassis() {
		return chassis;
	}
	public void setChassis(String chassis) {
		this.chassis = chassis;
	}
	@Column(name="output_stand")
	public String getOutputStand() {
		return outputStand;
	}
	public void setOutputStand(String outputStand) {
		this.outputStand = outputStand;
	}
	@Column(name="car_class")
	public String getCarClass() {
		return carClass;
	}
	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}
	@Column(name="nation")
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	@Column(name="nation_imp_class")
	public String getNationImpClass() {
		return nationImpClass;
	}
	public void setNationImpClass(String nationImpClass) {
		this.nationImpClass = nationImpClass;
	}
	@Column(name="engine_type")
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	@Column(name="cylinder_volume")
	public String getCylinderVolume() {
		return cylinderVolume;
	}
	public void setCylinderVolume(String cylinderVolume) {
		this.cylinderVolume = cylinderVolume;
	}
	@Column(name="gas_form")
	public String getGasForm() {
		return gasForm;
	}
	public void setGasForm(String gasForm) {
		this.gasForm = gasForm;
	}
	@Column(name="gas_type")
	public String getGasType() {
		return gasType;
	}
	public void setGasType(String gasType) {
		this.gasType = gasType;
	}
	@Column(name="gas_grade")
	public String getGasGrade() {
		return gasGrade;
	}
	public void setGasGrade(String gasGrade) {
		this.gasGrade = gasGrade;
	}
	@Column(name="max_horsepower")
	public String getMaxHorsepower() {
		return maxHorsepower;
	}
	public void setMaxHorsepower(String maxHorsepower) {
		this.maxHorsepower = maxHorsepower;
	}
	@Column(name="max_power")
	public String getMaxPower() {
		return maxPower;
	}
	public void setMaxPower(String maxPower) {
		this.maxPower = maxPower;
	}
	@Column(name="cylinder_arrayform")
	public String getCylinderArrayform() {
		return cylinderArrayform;
	}
	public void setCylinderArrayform(String cylinderArrayform) {
		this.cylinderArrayform = cylinderArrayform;
	}
	@Column(name="cylinder_num")
	public String getCylinderNum() {
		return cylinderNum;
	}
	public void setCylinderNum(String cylinderNum) {
		this.cylinderNum = cylinderNum;
	}
	@Column(name="percylinder_num")
	public String getPercylinderNum() {
		return percylinderNum;
	}
	public void setPercylinderNum(String percylinderNum) {
		this.percylinderNum = percylinderNum;
	}
	@Column(name="shift_memo")
	public String getShiftMemo() {
		return shiftMemo;
	}
	public void setShiftMemo(String shiftMemo) {
		this.shiftMemo = shiftMemo;
	}
	@Column(name="gears_num")
	public String getGearsNum() {
		return gearsNum;
	}
	public void setGearsNum(String gearsNum) {
		this.gearsNum = gearsNum;
	}
	@Column(name="prefix_braking_type")
	public String getPrefixBrakingType() {
		return prefixBrakingType;
	}
	public void setPrefixBrakingType(String prefixBrakingType) {
		this.prefixBrakingType = prefixBrakingType;
	}
	@Column(name="suffix_braking_type")
	public String getSuffixBrakingType() {
		return suffixBrakingType;
	}
	public void setSuffixBrakingType(String suffixBrakingType) {
		this.suffixBrakingType = suffixBrakingType;
	}
	@Column(name="assistance_type")
	public String getAssistanceType() {
		return assistanceType;
	}
	public void setAssistanceType(String assistanceType) {
		this.assistanceType = assistanceType;
	}
	@Column(name="engine_site")
	public String getEngineSite() {
		return engineSite;
	}
	public void setEngineSite(String engineSite) {
		this.engineSite = engineSite;
	}
	@Column(name="drive_form")
	public String getDriveForm() {
		return driveForm;
	}
	public void setDriveForm(String driveForm) {
		this.driveForm = driveForm;
	}
	@Column(name="wheelbase")
	public String getWheelbase() {
		return wheelbase;
	}
	public void setWheelbase(String wheelbase) {
		this.wheelbase = wheelbase;
	}
	@Column(name="preffix_tyre_scale")
	public String getPreffixTyreScale() {
		return preffixTyreScale;
	}
	public void setPreffixTyreScale(String preffixTyreScale) {
		this.preffixTyreScale = preffixTyreScale;
	}
	@Column(name="suffix_tyre_scale")
	public String getSuffixTyreScale() {
		return suffixTyreScale;
	}
	public void setSuffixTyreScale(String suffixTyreScale) {
		this.suffixTyreScale = suffixTyreScale;
	}
	@Column(name="hub_material")
	public String getHubMaterial() {
		return hubMaterial;
	}
	public void setHubMaterial(String hubMaterial) {
		this.hubMaterial = hubMaterial;
	}
	@Column(name="elec_window")
	public String getElecWindow() {
		return elecWindow;
	}
	public void setElecWindow(String elecWindow) {
		this.elecWindow = elecWindow;
	}
	@Column(name="whole_window")
	public String getWholeWindow() {
		return wholeWindow;
	}
	public void setWholeWindow(String wholeWindow) {
		this.wholeWindow = wholeWindow;
	}
	@Column(name="xenon_lamp")
	public String getXenonLamp() {
		return xenonLamp;
	}
	public void setXenonLamp(String xenonLamp) {
		this.xenonLamp = xenonLamp;
	}
	@Column(name="prefix_foglamp")
	public String getPrefixFoglamp() {
		return prefixFoglamp;
	}
	public void setPrefixFoglamp(String prefixFoglamp) {
		this.prefixFoglamp = prefixFoglamp;
	}
	@Column(name="suffix_brush")
	public String getSuffixBrush() {
		return suffixBrush;
	}
	public void setSuffixBrush(String suffixBrush) {
		this.suffixBrush = suffixBrush;
	}
	@Column(name="air_condition")
	public String getAirCondition() {
		return airCondition;
	}
	public void setAirCondition(String airCondition) {
		this.airCondition = airCondition;
	}
	@Column(name="auto_aircondition")
	public String getAutoAircondition() {
		return autoAircondition;
	}
	public void setAutoAircondition(String autoAircondition) {
		this.autoAircondition = autoAircondition;
	}
	@Column(name="good_a")
	public String getGoodA() {
		return goodA;
	}
	public void setGoodA(String goodA) {
		this.goodA = goodA;
	}
	@Column(name="good_b")
	public String getGoodB() {
		return goodB;
	}
	public void setGoodB(String goodB) {
		this.goodB = goodB;
	}
	@Column(name="good_c")
	public String getGoodC() {
		return goodC;
	}
	public void setGoodC(String goodC) {
		this.goodC = goodC;
	}
	@Column(name="good_d")
	public String getGoodD() {
		return goodD;
	}

	public void setGoodD(String goodD) {
		this.goodD = goodD;
	}
	@Column(name="good_e")
	public String getGoodE() {
		return goodE;
	}

	public void setGoodE(String goodE) {
		this.goodE = goodE;
	}
	@Column(name="good_f")
	public String getGoodF() {
		return goodF;
	}
	
	public void setGoodF(String goodF) {
		this.goodF = goodF;
	}
	@Column(name="good_g")
	public String getGoodG() {
		return goodG;
	}
	public void setGoodG(String goodG) {
		this.goodG = goodG;
	}
	@Column(name="good_h")
	public String getGoodH() {
		return goodH;
	}
	public void setGoodH(String goodH) {
		this.goodH = goodH;
	}
	

	

}
