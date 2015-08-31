package org.hannes.rs.net;

import java.io.IOException;
import java.net.SocketAddress;

import org.hannes.rs.net.context.IOContext;

/**
 * 
 * 
 * @author Red
 */
public interface IOHandler {

	/**
	 * 
	 * 
	 * @param address
	 * @throws IOException
	 */
	public abstract void bind(SocketAddress address) throws IOException;
	
	/**
	 * 
	 * @throws IOException
	 */
	public abstract void close() throws IOException;

	/**
	 * Fires a read event
	 * @param event
	 */
	public abstract void fireRead(IOContext event);
	
	/**
	 * Fires a write event
	 * 
	 * @param event
	 */
	public abstract void fireWrite(IOContext event);

}