package org.hannes.rs.event.transform;

import org.hannes.rs.net.Session;
import org.hannes.rs.net.message.Message;

public class TransformationContext {

	/**
	 * The session
	 */
	private final Session session;
	
	/**
	 * The message
	 */
	private final Message message;

	public TransformationContext(Session session, Message message) {
		this.session = session;
		this.message = message;
	}

	public Session getSession() {
		return session;
	}

	public Message getMessage() {
		return message;
	}

}