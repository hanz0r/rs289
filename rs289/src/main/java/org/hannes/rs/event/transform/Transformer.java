package org.hannes.rs.event.transform;

import org.hannes.rs.event.Event;

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