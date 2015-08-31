package org.hannes.rs.event.handler.context;

import java.io.IOException;

import org.hannes.rs.event.Event;
import org.hannes.rs.net.Session;
import org.hannes.rs.util.Attributes;

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