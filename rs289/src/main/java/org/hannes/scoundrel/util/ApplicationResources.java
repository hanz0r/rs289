package org.hannes.scoundrel.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class ApplicationResources {

	/**
	 * The entity manager factory
	 */
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");

	/**
	 * Creates a new entity manager when an injection call has been made
	 * 
	 * @return
	 */
	@Produces public EntityManager produceManager() {
		return factory.createEntityManager();
	}

	/**
	 * Disposes the entitymanager. Closes the entity manager factory, the entity manager and commits the current active transaction
	 * 
	 * @param manager
	 */
	public void destroy(@Disposes EntityManager manager) {
		manager.getTransaction().commit();
		manager.close();
	}

}