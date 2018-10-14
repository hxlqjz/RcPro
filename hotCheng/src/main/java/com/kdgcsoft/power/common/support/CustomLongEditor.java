package com.kdgcsoft.power.common.support;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

public class CustomLongEditor extends PropertyEditorSupport{
	

	
	private final boolean allowEmpty;
	
	public CustomLongEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		}else if("null".equals(text) || "".equals(text)){
			setValue(null);
		}else {			
			setValue(Long.parseLong(text));
		}
	}
}
