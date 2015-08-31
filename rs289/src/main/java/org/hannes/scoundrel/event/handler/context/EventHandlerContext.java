package org.hannes.scoundrel.event.handler.context;

import java.io.IOException;

import org.hannes.scoundrel.event.Event;
import org.hannes.scoundrel.net.Session;
import org.hannes.scoundrel.util.Attributes;

public class EventHandlerContext {

	/**
	 * 
	 */
	private final Event event;
	
	/**
	 * 
	 */
	private final Session session;

	public EventHandlerContext(Event event, Session session) {
		this.event = event;
		this.session = session;
	}

	public <T> T get(String attribute) {
		return event.get(attribute);
	}

	public Attributes attributes() {
		return session.attributes();
	}

	public void write(Object object) throws IOException {
		session.channel().write(object);
	}

	public Event getEvent() {
		return event;
	}

	public Session getSession() {
		return session;
	}

}