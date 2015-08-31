package org.hannes.scoundrel.event.transform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hannes.scoundrel.net.Session.State;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TransformState {

	/**
	 * The state at which the transformer will transform the message
	 * 
	 * @return
	 */
	public State value() default State.UNKNOWN;

}