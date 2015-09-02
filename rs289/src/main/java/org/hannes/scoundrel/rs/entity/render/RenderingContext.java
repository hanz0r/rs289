package org.hannes.scoundrel.rs.entity.render;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.hannes.scoundrel.util.Serializable;

/**
 * The rendering context
 * 
 * @author user104
 */
public class RenderingContext implements Iterable<Serializable>, Supplier<Stream<Serializable>> {

	/**
	 * The collection containing the rendering hints
	 */
	private final SortedMap<RenderingHint, Serializable> map;

	/**
	 * Creates the rendering context
	 * 
	 * @param map
	 */
	public RenderingContext(SortedMap<RenderingHint, Serializable> map) {
		this.map = map;
	}

	@Override
	public Stream<Serializable> get() {
		return map.values().stream();
	}

	@Override
	public Iterator<Serializable> iterator() {
		return map.values().iterator();
	}

}