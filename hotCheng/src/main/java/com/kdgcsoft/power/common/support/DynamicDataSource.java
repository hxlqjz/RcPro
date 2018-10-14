package com.kdgcsoft.power.common.support;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * 切换数据源,这一步必须在进入业务层之前切换。
 * DynamicDataSource.setCustomerType(DynamicDataSource.DATA_SOURCE_A);
 * @author BridgeBai
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
            contextHolder.set(customerType);
    }

    public static String getCustomerType() {
            return contextHolder.get();
    }

    public static void clearCustomerType() {
            contextHolder.remove();
    }

	@Override
	protected Object determineCurrentLookupKey() {
		return getCustomerType();
	}

}
