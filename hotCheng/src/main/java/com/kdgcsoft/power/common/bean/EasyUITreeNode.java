package com.kdgcsoft.power.common.bean;

import java.io.Serializable;
import java.util.List;

public class EasyUITreeNode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;//id 节点的VALUE
	private String code;// 节点的code
	private String text;// 节点显示的文字
	private String url; //地址路径  
	private String iconCls;//节点的图标
	private String checked;//表示该节点是否被选中
	private String state; //节点状态，'open'(叶节点) 或 'closed'(非叶节点)，默认：'open'。
	private String business;
	private String openway;//打开方式
	private List<EasyUITreeNode> children;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getOpenway() {
		return openway;
	}
	public void setOpenway(String openway) {
		this.openway = openway;
	}
	public List<EasyUITreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<EasyUITreeNode> children) {
		this.children = children;
	}

	
	
}