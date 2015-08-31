package org.hannes.rs.event.handler.context;

import java.awt.Event;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Listens {

	/**
	 * The name of the events that the handler listens to
	 * 
	 * @return
	 */
	public String value() default "undefined";
	
	/**
	 * The class that the handler listens to
	 * 
	 * @return
	 */
	public Class<?> target() default Event.class;

}