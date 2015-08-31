package org.hannes.scoundrel.net;

import org.hannes.scoundrel.util.Attributes;
import org.hannes.scoundrel.util.Holder;

import io.netty.util.AttributeKey;

/**
 * Represents a session
 * 
 * @author Red
 */
public interface Session {
	
	/**
	 * The session attribute key
	 */
	public static final AttributeKey<Session> ATTRIBUTE_KEY = AttributeKey.valueOf("session");

	/**
	 * Registers a channel to the session
	 * 
	 * @param channel
	 */
	public abstract void register(Channel channel);

	/**
	 * Gets the channel for this session that handles input and output
	 * 
	 * @return
	 */
	public abstract Channel channel();

	/**
	 * Gets the attributes that store the unmanaged information about the session
	 * 
	 * @return
	 */
	public abstract Attributes attributes();
	
	/**
	 * Closes the session
	 */
	public abstract void close();
	
	/**
	 * The holder for the state 
	 * 
	 * @return
	 */
	public abstract Holder<State> state();
	
	/**
	 * @param state the state
	 */
	default void setState(State state) {
		state().set(state);
	}

	/**
	 * @return the state
	 */
	default State getState() {
		return state().get();
	}

	/**
	 * Indicates the state the session is currently in 
	 * 
	 * @author Red
	 */
	public static enum State {
		
		/**
		 * The connection has recently connected
		 */
		DISCOVERED,
		
		/**
		 * The connection has passed the login sequence and is actively being used
		 */
		ACTIVE,
		
		/**
		 * When the state of the connection is unknown
		 */
		UNKNOWN;
	}

}