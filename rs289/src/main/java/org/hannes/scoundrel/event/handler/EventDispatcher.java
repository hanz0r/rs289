package org.hannes.scoundrel.event.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hannes.scoundrel.event.Event;
import org.hannes.scoundrel.event.handler.context.EventHandlerContext;
import org.hannes.scoundrel.event.handler.context.Listens;
import org.hannes.scoundrel.net.Session;
import org.hannes.scoundrel.util.Dispatcher;
import org.hannes.scoundrel.util.Initializable;

@ApplicationScoped
public class EventDispatcher implements Initializable, Dispatcher<Event> {

	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EventDispatcher.class);

	/**
	 * The collection of handlers mapped to what they listen to
	 */
	private final Map<String, List<EventHandler>> map = new HashMap<>();

	/**
	 * The event handlers
	 */
	@Inject private Instance<EventHandler> handlers;

	@Override
	public void initialize() {
		handlers.forEach(h -> { 
			try {
				/*
				 * Get the listens annotation
				 */
				Listens annotation = h.getClass().getDeclaredAnnotation(Listens.class);
				
				/*
				 * Only work with classes that have the listens annotation
				 */
				if (annotation != null) {
					/*
					 * Get the gazorpazorp
					 */
					List<EventHandler> list = map.containsKey(annotation.value()) ? map.get(annotation.value()) : new ArrayList<>();
					
					/*
					 * Glarb the zworf
					 */
					list.add(h);
					
					/*
					 * krombopulos michael
					 */
					map.put(annotation.value(), list);
					
					/*
					 * Info
					 */
					logger.info(h + " - > " + annotation.value());
				} else {
					logger.info("skipping " + h.getClass() + " for not having the correct annotations");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
	}

	@Override
	public void dispatch(Event event, Session session) {
		List<EventHandler> list = map.get(event.getAlias());
		
		/*
		 * If the list doesn't exist, do nothing
		 */
		if (list == null)
			return;
		
		/*
		 * Create the event handler context
		 */
		EventHandlerContext context = new EventHandlerContext(event, session);
		
		/*
		 * Iterate over all of the handlers and call the onReceive method
		 */
		for (Iterator<EventHandler> iterator = list.iterator(); iterator.hasNext();) {
			try {
				iterator.next().onReceive(context);
			} catch (Exception ex) {
				ex.printStackTrace();
				session.close();
			}
		}
	}

}