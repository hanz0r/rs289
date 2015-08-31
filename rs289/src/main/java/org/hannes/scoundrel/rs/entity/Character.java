package org.hannes.scoundrel.rs.entity;

import org.hannes.scoundrel.rs.locale.Point;

public abstract class Character implements Entity {

	private Point point;

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

}