package org.hannes.scoundrel.rs.entity.render;

import java.nio.ByteBuffer;

public interface Attribute {

	/**
	 * 
	 * 
	 * @param buffer
	 */
	public abstract void serialize(ByteBuffer buffer);
	
	/**
	 * The mask id
	 * 
	 * @return
	 */
	public abstract int mask();

}