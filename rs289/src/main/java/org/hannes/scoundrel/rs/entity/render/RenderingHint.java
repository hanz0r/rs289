package org.hannes.scoundrel.rs.entity.render;

/**
 * 
 * @author user104
 *
 */
public enum RenderingHint {

	/**
	 * Indicates the renderer needs to display an animation
	 */
	ANIMATION(0x100);
	
	private final int mask;

	private RenderingHint(int mask) {
		this.mask = mask;
	}

	public int getMask() {
		return mask;
	}

}