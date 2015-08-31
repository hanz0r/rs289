package org.hannes.scoundrel.rs.util;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Credentials implements Serializable {

	/**
	 * The version id of the serialized object
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id of the player credentials
	 */
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id private long id;

	/**
	 * 
	 */
	private String username;
	
	/**
	 * Password hash
	 */
	private String password;

	public Credentials() {
		// Constructor for Hibernate
	}

	public Credentials(long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean validate(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}

}