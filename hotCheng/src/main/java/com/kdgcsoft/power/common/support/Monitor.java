package com.kdgcsoft.power.common.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法上注解，定义方法操作的模块和方法的描述
 * @author bridgeBai
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Monitor {
  public String actionName();
  public String actionDescr();
  
}
