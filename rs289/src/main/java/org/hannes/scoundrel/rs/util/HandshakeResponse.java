package org.hannes.scoundrel.rs.util;

import java.nio.ByteBuffer;

import org.hannes.scoundrel.net.Serializable;
import org.hannes.scoundrel.net.Session;

public class HandshakeResponse implements Serializable {
	
	/**
	 * The initial response (idk lol)
	 */
	private static final byte[] INITIAL_RESPONSE = { 0, 0, 0, 0, 0, 0, 0, 0 };
	
	/**
	 * The server's seed (eww)
	 */
	private final long seed;

	/**
	 * 
	 * @param seed
	 */
	public HandshakeResponse(long seed) {
		this.seed = seed;
	}

	@Override
	public ByteBuffer serialize(Session session) {
		return ByteBuffer.allocate(17).put(INITIAL_RESPONSE).put((byte) 0).putLong(seed);
	}

}