package org.hannes.rs.net.message;

import java.nio.ByteBuffer;

public class Message {

	/**
	 * This message's header
	 */
	private final Header header;
	
	/**
	 * This message's payload
	 */
	private final ByteBuffer payload;

	public Message(Header header, ByteBuffer payload) {
		this.header = header;
		this.payload = payload;
	}

	public Header getHeader() {
		return header;
	}

	public ByteBuffer getPayload() {
		return payload;
	}

	@Override
	public String toString() {
		return "Message [header=" + header + ", payload=" + payload + "]";
	}

}