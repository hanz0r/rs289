package org.hannes.scoundrel.rs.entity;

import org.hannes.scoundrel.rs.locale.Point;
import org.hannes.scoundrel.util.Attributes;

public abstract class Character implements Entity {

	/**
	 * The character's location
	 */
	private Point point;

	/**
	 * The attributes for this
	 */
	private final Attributes attributes = new Attributes();

	@Override
	public Attributes attributes() {
		return attributes;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

}