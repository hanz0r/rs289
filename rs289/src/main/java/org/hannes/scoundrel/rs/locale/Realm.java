package org.hannes.scoundrel.rs.locale;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hannes.scoundrel.rs.event.LoginEvent;
import org.hannes.scoundrel.rs.util.LoginResponse;
import org.hannes.scoundrel.rs.util.LoginResponse.Result;
import org.hannes.scoundrel.rse.Player;
import org.hannes.scoundrel.rse.player.Credentials;
import org.hannes.scoundrel.rse.queue.EntityQueue;

/**
 * A realm is a "world" the player can log in to.
 * 
 * @author Red
 *
 */
@ApplicationScoped
public class Realm {

	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Realm.class);
	
	/**
	 * The player manager for this realm
	 */
	@org.hannes.scoundrel.rse.qualifier.Player
	@Inject private EntityQueue<Player> players;
	
	/**
	 * The persistence entity manager
	 */
	@Inject private EntityManager entity_manager;

	/**
	 * Catches the login event and will validate the player's login attempt
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void onLoginEvent(@Observes LoginEvent event) throws IOException {
		TypedQuery<Credentials> query = entity_manager.createQuery("SELECT c FROM Credentials c WHERE c.username= :username AND c.password= :password", Credentials.class)
				.setParameter("username", event.getUsername()).setParameter("password", event.getPassword());
		
		/*
		 * Try and get the credentials
		 */
		Credentials credentials = query.getResultList().isEmpty() ? null : query.getSingleResult();
		
		/*
		 * Validate the player's login attempt
		 */
		if (credentials != null && credentials.validate(event.getUsername(), event.getPassword())) {
			/*
			 * If the player object is null, the realm is full
			 */
			if (players.hasRemaining()) {
				/*
				 * Write the OK
				 */
				event.getSession().channel().write(new LoginResponse(Result.OK));
				
				/*
				 * Register the player
				 */
				players.register(credentials.getPlayer());
				
				/*
				 * Tell the server admin the player has logged in
				 */
				logger.info(event.getUsername() + " has logged in");
			}
			else {
				/*
				 * The player's credentials are invalid. Tell the client to dismiss the login
				 */
				event.getSession().channel().write(new LoginResponse(Result.SERVER_FULL));
				
				/*
				 * Close the channel
				 */
				event.getSession().close();
				
				/*
				 * Tell the server admin the player has logged in
				 */
				logger.info(event.getUsername() + " did not register. Realm full.");
			}
		}
		else {
			/*
			 * The player's credentials are invalid. Tell the client to dismiss the login
			 */
			event.getSession().channel().write(new LoginResponse(Result.INVALID_CREDENTIALS));
			
			/*
			 * Close the channel
			 */
			event.getSession().close();
			
			/*
			 * Tell the server admin the player couldn't complete the login
			 */
			logger.info("Invalid password entered for " + event.getUsername());
		}
	}

}