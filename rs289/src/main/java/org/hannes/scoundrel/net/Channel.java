package org.hannes.scoundrel.net;

import java.io.IOException;

/**
 * 
 * @author Red
 */
public interface Channel {

	/**
	 * Writes an object
	 * 
	 * @param object
	 */
	public abstract void write(Object object) throws IOException;
	
	/**
	 * Reads an object
	 * 
	 * @param object
	 */
	public abstract void read(Object object) throws IOException;
	
	/**
	 * The IOHandler
	 * 
	 * @return
	 */
	public abstract IOHandler getIOHandler();

}