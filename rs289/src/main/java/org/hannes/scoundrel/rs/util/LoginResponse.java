package org.hannes.scoundrel.rs.util;

import java.nio.ByteBuffer;

import org.hannes.scoundrel.net.Serializable;
import org.hannes.scoundrel.net.Session;

public class LoginResponse implements Serializable {

	/**
	 * The result of the login procedure
	 */
	private final Result result;

	/**
	 * Defines the player type
	 */
	private final PlayerType playerType;

	/**
	 * Indicates the player needs to be observed
	 */
	private final boolean observe;

	public LoginResponse(Result loginResult, PlayerType playerType, boolean observe) {
		this.result = loginResult;
		this.playerType = playerType;
		this.observe = observe;
	}

	public LoginResponse(Result result) {
		this (result, PlayerType.BASIC, false);
	}

	@Override
	public ByteBuffer serialize(Session session) {
		ByteBuffer buffer = ByteBuffer.allocate(3);
		buffer.put((byte) result.ordinal());
		if (result == Result.OK) {
			buffer.put((byte) playerType.ordinal());
			buffer.put((byte) (observe ? 1 : 0));
		}
		return buffer;
	}

	public Result getLoginResult() {
		return result;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public boolean isObserve() {
		return observe;
	}

	/**
	 * Result of the login procedure. Names nicked from Graham
	 * 
	 * @author Red
	 */
	public static enum Result {

		/**
		 * Exchange data login status.
		 */
		EXCHANGE_DATA,

		/**
		 * Delay for 2 seconds login status.
		 */
		DELAY,

		/**
		 * OK login status.
		 */
		OK,

		/**
		 * Invalid credentials login status.
		 */
		INVALID_CREDENTIALS,

		/**
		 * Account disabled login status.
		 */
		ACCOUNT_DISABLED,

		/**
		 * Account online login status.
		 */
		ACCOUNT_ONLINE,

		/**
		 * Game updated login status.
		 */
		GAME_UPDATED,

		/**
		 * Server full login status.
		 */
		SERVER_FULL,

		/**
		 * Login server offline login status.
		 */
		LOGIN_SERVER_OFFLINE,

		/**
		 * Too many connections login status.
		 */
		TOO_MANY_CONNECTIONS,

		/**
		 * Bad session id login status.
		 */
		BAD_SESSION_ID,

		/**
		 * Login server rejected session login status.
		 */
		LOGIN_SERVER_REJECTED_SESSION,

		/**
		 * Members account required login status.
		 */
		MEMBERS_ACCOUNT_REQUIRED,

		/**
		 * Could not complete login status.
		 */
		COULD_NOT_COMPLETE,

		/**
		 * Server updating login status.
		 */
		UPDATING,

		/**
		 * Reconnection OK login status.
		 */
		RECONNECTION_OK,

		/**
		 * Too many login attempts login status.
		 */
		TOO_MANY_LOGINS,

		/**
		 * Standing in members area on free world status.
		 */
		IN_MEMBERS_AREA;
	}

}