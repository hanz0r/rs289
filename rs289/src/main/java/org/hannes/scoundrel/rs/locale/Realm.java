package org.hannes.scoundrel.rs.locale;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hannes.scoundrel.rs.entity.EntityManager;
import org.hannes.scoundrel.rs.entity.Player;
import org.hannes.scoundrel.rs.event.LoginEvent;
import org.hannes.scoundrel.rs.util.Credentials;
import org.hannes.scoundrel.rs.util.LoginResponse;
import org.hannes.scoundrel.rs.util.LoginResponse.Result;

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
	@Inject private EntityManager<Player> player_manager;
	
	/**
	 * The player instance
	 */
	@Inject private Instance<Player> player_instance;
	
	/**
	 * The persistence entity manager
	 */
	@Inject private javax.persistence.EntityManager entity_manager;

	/**
	 * Produces a new player if there is room on the realm
	 * 
	 * @return
	 */
	@Produces public Player createPlayer() {
		if (player_manager.capacity() == player_manager.size()) {
			return null;
		}
		return new Player(player_manager.allocateIndex(), this);
	}

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
			Player player = player_instance.get();
			
			/*
			 * If the player object is null, the realm is full
			 */
			if (player != null) {
				/*
				 * Set the player's name to t
				 */
				player.setUsername(event.getUsername());
				// TODO: load the player's data
				
				/*
				 * Write the OK
				 */
				event.getSession().channel().write(new LoginResponse(Result.OK));
				
				/*
				 * Register the player
				 */
				player_manager.register(player);
				
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