package com.dsideal.nwagent4rcp.agent.ebus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;

import com.dsideal.nwagent4rcp.agent.InstallVertIOService;

public class ScanEBAnnotaion {
	/**
	 * 根据传入进来的handler对象，获取对象是否被@EBusHandler注解，如果此类被注解，那么需要判断此类中的全局变量
	 * 是否有被@EBusField进行注解。如果注解了，需要添加到Ebus中。
	 * 
	 * @param obj
	 */
	public static void scanEBus(Object obj) {
		if (obj == null) {
			return;
		}
		EventBus ebs = InstallVertIOService.getVertx().eventBus();
		Class className = obj.getClass();
		Annotation anno = className.getDeclaredAnnotation(EBusHandler.class);
		if (anno != null) {
			Field[] fields = className.getDeclaredFields();
			for (Field field : fields) {
				EBusField ebf = (EBusField) field
						.getAnnotation(EBusField.class);
				if (ebf != null) {
					String value = ebf.value();
					try {
						ebs.registerHandler(value,
								(Handler<Message>) field.get(obj));
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}


}
