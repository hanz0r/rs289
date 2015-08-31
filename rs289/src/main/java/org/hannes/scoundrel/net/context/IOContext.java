package org.hannes.scoundrel.net.context;

import org.hannes.scoundrel.net.IOHandler;
import org.hannes.scoundrel.net.Session;
import org.hannes.scoundrel.net.message.Message;

public class IOContext {

	/**
	 * The event's session
	 */
	private final Session session;
	
	/**
	 * The event's message
	 */
	private final Message message;
	
	/**
	 * The io handler
	 */
	private final IOHandler handler;

	public IOContext(Session session, Message message, IOHandler handler) {
		this.session = session;
		this.message = message;
		this.handler = handler;
	}

	public Message getMessage() {
		return message;
	}

	public Session getSession() {
		return session;
	}

	/**
	 * @return the handler
	 */
	public IOHandler getHandler() {
		return handler;
	}

}