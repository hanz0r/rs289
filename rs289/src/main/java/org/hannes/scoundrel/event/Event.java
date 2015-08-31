package org.hannes.scoundrel.event;

import java.nio.ByteBuffer;

import org.hannes.scoundrel.util.Attributes;

public class Event {

	/**
	 * The name of the attribute that stores the payload by default
	 */
	public static final String PAYLOAD = "payload";

	/**
	 * This event's attributes
	 */
	private final Attributes attributes = new Attributes();

	/**
	 * The attribute's alias
	 */
	private final String alias;

	public Event(String alias) {
		this.alias = alias;
	}

	public Event(String alias, ByteBuffer payload) {
		this(alias);
		
		attributes.set(PAYLOAD, payload);
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public String getAlias() {
		return alias;
	}

	public <T> T get(String string) {
		return attributes.get(string);
	}

}