package org.hannes.scoundrel.rs.entity.sync;

import java.nio.ByteBuffer;

public interface Synchronizer {

	/**
	 * 
	 * @param manager
	 */
	public abstract ByteBuffer synchronize();

}