package org.hannes.scoundrel.rs.entity.sync;

import java.nio.ByteBuffer;

import org.hannes.scoundrel.rs.entity.Character;
import org.hannes.scoundrel.rs.entity.EntityManager;

public interface Synchronizer<T extends Character> {

	/**
	 * 
	 * @param manager
	 */
	public abstract ByteBuffer synchronize(EntityManager<T> manager);

}