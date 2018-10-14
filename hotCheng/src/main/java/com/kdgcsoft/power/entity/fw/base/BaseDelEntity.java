package com.kdgcsoft.power.entity.fw.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.kdgcsoft.power.common.bean.SystemConstants;

@MappedSuperclass
public class BaseDelEntity extends BaseEntity{

	/** isUse  是否使用   */
	protected String isUse=SystemConstants.IS_USE_Y;
	
	public BaseDelEntity() {
		super();
	}

	/**
	 *方法: 取得  是否使用 isUse String
	 *@return: String  
	 */
	@Column(name ="IS_USE",nullable=true,length=1)
	public String getIsUse() {
		return this.isUse;
	}

	/**
	 *方法: 设置  是否使用  isUse String
	 *@param: String  
	 */
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

}