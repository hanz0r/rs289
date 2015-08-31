package org.hannes.scoundrel.event.handler;

import java.io.IOException;

import org.hannes.scoundrel.event.handler.context.EventHandlerContext;

/**
 * 
 * 
 * @author Red
 */
@FunctionalInterface
public interface EventHandler {

	/**
	 * 
	 * 
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public abstract void onReceive(EventHandlerContext ctx) throws IOException;

}