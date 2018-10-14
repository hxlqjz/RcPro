package com.kdgcsoft.power.common.bean;

import java.util.List;

public class PageModel {
	private Long total;
	private List<?> rows;
	
	public PageModel() {

	}
	
	public PageModel(Long total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
