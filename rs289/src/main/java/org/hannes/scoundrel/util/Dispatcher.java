package org.hannes.scoundrel.util;

import org.hannes.scoundrel.net.Session;

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