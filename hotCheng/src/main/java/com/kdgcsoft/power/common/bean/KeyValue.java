package com.kdgcsoft.power.common.bean;

import java.io.Serializable;

public class KeyValue implements Serializable {
	private static final long serialVersionUID = 3968382501481381815L;
	private String key;
	private String value;

	public KeyValue() {
	}

	public KeyValue(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}