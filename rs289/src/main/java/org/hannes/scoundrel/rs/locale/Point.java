package org.hannes.scoundrel.rs.locale;

/**
 * Represents a single point in the world. This class is immutable by design
 * 
 * @author Red
 */
public class Point {

	/**
	 * The spawn point of the game (TODO: load from cfg/xml)
	 */
	public static final Point ORIGIN = new Point(3200, 3200, 0);

	/**
	 * The coordinate for this point
	 */
	private final int x, y, z;

	public Point(int x, int y) {
		this(x, y, 0);
	}

	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Distance in manhattan distance
	 * 
	 * @param other
	 * @return
	 */
	public int distance(Point other) {
		return (x - other.x) + (y - other.y);
	}

	/**
	 * Returns a new point with the values of this point offset by a given x and y
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Point offset(int x, int y) {
		return offset(x, y, 0);
	}

	/**
	 * Returns a new point with the values of this point offset by a given x, y and z
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Point offset(int x, int y, int z) {
		return new Point(this.x + x, this.y + y, this.z + z);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

}