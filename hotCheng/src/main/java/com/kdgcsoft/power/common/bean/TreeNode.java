package com.kdgcsoft.power.common.bean;

import java.io.Serializable;

public class TreeNode implements Serializable {
	private static final long serialVersionUID = 7162901498284715462L;
	private String id;
	private String text;
	private String code;
	private boolean leaf;
	private String cls;
	private String iconCls;
	private String description;
	private String openType;
	private String href;

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getLeaf() {
		return this.leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getCls() {
		return this.cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOpenType() {
		return this.openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}