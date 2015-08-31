package org.hannes.scoundrel.rs.entity;

import org.hannes.scoundrel.rs.locale.Realm;

public class Player extends Character {
	
	/**
	 * The id of the player
	 */
	private final int id;

	/**
	 * The realm the player has logged in to
	 */
	private final Realm realm;

	/**
	 * The username of the player
	 */
	private String username;

	public Player(int id, Realm realm) {
		this.id = id;
		this.realm = realm;
	}

	public int getId() {
		return id;
	}

	public Realm getRealm() {
		return realm;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}