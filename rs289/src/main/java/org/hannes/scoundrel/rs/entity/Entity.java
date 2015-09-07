package org.hannes.scoundrel.rs.entity;

import org.hannes.scoundrel.rs.locale.Point;
import org.hannes.scoundrel.util.Attributes;

public interface Entity {

	/**
	 * Gets the entity's attributes
	 * 
	 * @return
	 */
	public abstract Attributes attributes();

	/**
	 * GEts the entity's location
	 * 
	 * @return
	 */
	public abstract Point getLocation();

	/**
	 * Sets the entity's location
	 * 
	 * @param point
	 */
	public abstract void setLocation(Point point);

	/**
	 * Calculates the distance between this and the given entity's location
	 * 
	 * @param other
	 */
	default int distance(Entity other) {
		return getLocation().distance(other.getLocation());
	}

}