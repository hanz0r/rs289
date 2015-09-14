package org.hannes.scoundrel;


import java.net.InetSocketAddress;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.hannes.scoundrel.net.IOHandler;
import org.hannes.scoundrel.rse.Player;
import org.hannes.scoundrel.rse.player.Credentials;
import org.hannes.scoundrel.util.Initializable;
import org.jboss.weld.environment.se.events.ContainerInitialized;

/**
 * 
 * @author Red
 *
 */
public class App {

	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(App.class);

	/**
	 * The implementation of the IOHandler interface that is currently being used
	 */
	@Inject private IOHandler handler;
	
	/**
	 * The collection of classes that implement initializable
	 */
	@Inject private Instance<Initializable> initializables;
	
	/**
	 * Initializes the application
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void initialize(@Observes ContainerInitialized event) throws Exception {
		logger.debug("using handler: " + handler.getClass());
		
		Credentials credentials = new Credentials("x", "x", "Hanonator");
		credentials.setPlayer(new Player());
		
		/*
		 * Initialize every class implementing initializable pls ignore the ugly
		 */
		initializables.forEach(i -> {try {i.initialize();} catch (Exception ex) {ex.printStackTrace();}});
		
		/*
		 * Bind the server to the socket address
		 */
		handler.bind(new InetSocketAddress(43594));
	}

}