package com.dsideal.nwagent4rcp.agent.ebus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解到方法上，与@EBusHandler同时使用。在类上没有注解EBusHandler的时候，EBusmethod无效
 * 
 * @author johnny
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EBusField {
	String value();
}
