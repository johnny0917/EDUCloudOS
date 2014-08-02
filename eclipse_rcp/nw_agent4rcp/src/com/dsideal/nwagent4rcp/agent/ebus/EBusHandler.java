package com.dsideal.nwagent4rcp.agent.ebus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解到特定的Handler类上面。通过扫描，能够发现注解后的处理类
 * 
 * @author johnny
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EBusHandler {
}
