package org.hannes.scoundrel.event.transform;

import org.hannes.scoundrel.event.Event;

/**
 * Transforms something into an event
 * 
 * @author Red
 *
 */
public interface Transformer {

	/**
	 * 
	 * @param context
	 * @return
	 */
	public abstract Event transform(TransformationContext context);

}