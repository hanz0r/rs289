package org.hannes.rs.util;

public class Holder<T> {

	private T field;

	public Holder() {
		
	}

	public Holder(T field) {
		this.set(field);
	}

	/**
	 * @return the field
	 */
	public T get() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void set(T field) {
		this.field = field;
	}

}