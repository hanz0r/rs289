package org.hannes.rs.util;

import org.hannes.rs.net.Session;

/**
 * 
 * 
 * @author Red
 *
 * @param <T>
 */
@FunctionalInterface
public interface Dispatcher<T> {

	/**
	 * 
	 * @param object
	 */
	public abstract void dispatch(T object, Session session);

}