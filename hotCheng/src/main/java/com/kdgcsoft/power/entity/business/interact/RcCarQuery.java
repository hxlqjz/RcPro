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
 * 车型查询，品牌，厂家，车型
 * @author lenovo
 *
 */
@Entity
@Table(name="rc_car_query")
public class RcCarQuery  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String carBrand;
	private String carFactory;
	private String carModel;
	private String carQuery;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="rc_car_query")
	@TableGenerator(name="rc_car_query",table="TB_GENERATOR", pkColumnName="gen_key", valueColumnName="gen_value", pkColumnValue = "rc_car_query",allocationSize = 1)
	@Column(name ="ID",nullable=false,precision=15,scale=0,length=22)	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="car_brand")
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	@Column(name="car_factory")
	public String getCarFactory() {
		return carFactory;
	}
	public void setCarFactory(String carFactory) {
		this.carFactory = carFactory;
	}
	@Column(name="car_model")
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	@Column(name="car_query")
	public String getCarQuery() {
		return carQuery;
	}
	public void setCarQuery(String carQuery) {
		this.carQuery = carQuery;
	}
	

}
