package org.hannes.scoundrel.rs.event;

import org.hannes.scoundrel.net.Session;

public class LoginEvent {

	/**
	 * The session that initated the login event
	 */
	private final Session session;

	/**
	 * The entered username
	 */
	private final String username;
	
	/**
	 * The entered password
	 */
	private final String password;

	/**
	 * The server key
	 */
	private final long server_key;
	
	/**
	 * The client key
	 */
	private final long client_key;

	public LoginEvent(Session session, String username, String password, long server_key, long client_key) {
		this.session = session;
		this.username = username;
		this.password = password;
		this.server_key = server_key;
		this.client_key = client_key;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public long getServerKey() {
		return server_key;
	}

	public long getClientKey() {
		return client_key;
	}

	public Session getSession() {
		return session;
	}

}