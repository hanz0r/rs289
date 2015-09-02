package org.hannes.scoundrel.rs.entity;

import java.util.Queue;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface EntityManager<T extends Entity> extends Iterable<T>, Supplier<Stream<T>> {

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
	 * Gets the entities surrounding the given entity (non inclusive)
	 * 
	 * @return
	 */
	public Queue<T> getSurroundingEntities(Entity entity);

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
	 * Allocates a free index
	 * @return
	 */
	public int allocateIndex();

	/**
	 * Clears every entity being managed
	 */
	public void clear();

}