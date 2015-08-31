package org.hannes.scoundrel.rs.entity;

public interface EntityManager<T extends Entity> {

	/**
	 * Get the entity for the given id
	 * 
	 * @param id
	 * @return
	 */
	public abstract T get(int id);

	/**
	 * Register the entity
	 * 
	 * @param player
	 */
	public abstract void register(T entity);
	
	/**
	 * Release the entity
	 * 
	 * @param player
	 */
	public abstract void release(T entity);

	/**
	 * Gets the size of the manager
	 * 
	 * @return
	 */
	public int size();

	/**
	 * Gets the capacity of the manager
	 * 
	 * @return
	 */
	public int capacity();

	/**
	 * Clears every entity being managed
	 */
	public void clear();

	/**
	 * Allocates a free index
	 * @return
	 */
	public int allocateIndex();

}