package org.hannes.scoundrel.util;

import java.nio.ByteBuffer;

import org.hannes.scoundrel.net.Session;

public interface Serializable {

	/**
	 * Serializes the byte buffer 
	 * 
	 * @return
	 */
	public abstract ByteBuffer serialize(Session session);

}