package org.hannes.scoundrel.rs.entity.sync;

import org.hannes.scoundrel.rs.entity.Character;
import org.hannes.scoundrel.rs.entity.EntityManager;

public interface Synchronizer<T extends Character> {

	/**
	 * 
	 * @param manager
	 */
	public abstract void synchronize(EntityManager<T> manager);

}