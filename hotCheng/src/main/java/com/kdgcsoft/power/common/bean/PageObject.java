package com.kdgcsoft.power.common.bean;

import java.io.Serializable;
import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.data.domain.Page;

public class PageObject<T> implements Serializable {
	private static final long serialVersionUID = -6691418477901017313L;
	private Long totalCount;
	private transient  List<T> list;

	public PageObject(){
		
	}
	public PageObject(List<T> list, Long totalCount){
		this.list = list;
		this.totalCount = totalCount;
	}
	public PageObject(PageQuery<T> query){
		this.list = query.getList();
		this.totalCount = query.getTotalRow();
	}
	public PageObject(Page<T> query){
		this.list = query.getContent();
		this.totalCount = Long.parseLong(String.valueOf(query.getTotalElements()));
	}
	public Long getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getList() {
		return this.list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}