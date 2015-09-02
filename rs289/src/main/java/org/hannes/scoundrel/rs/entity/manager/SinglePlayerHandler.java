package org.hannes.scoundrel.rs.entity.manager;

import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Stream;

import org.hannes.scoundrel.rs.entity.EntityManager;
import org.hannes.scoundrel.rs.entity.Player;

public class SinglePlayerHandler implements EntityManager<Player> {

	private Player player;

	@Override
	public Player get(int id) {
		return player;
	}

	@Override
	public void register(Player entity) {
		player = entity;
	}

	@Override
	public void release(Player entity) {
		player = null;
	}

	@Override
	public int size() {
		return player == null ? 0 : 1;
	}

	@Override
	public int capacity() {
		return 1;
	}

	@Override
	public void clear() {
		release(player);
	}

	@Override
	public int allocateIndex() {
		return 1;
	}

	@Override
	public Iterator<Player> iterator() {
		return Collections.singleton(player).iterator();
	}

	@Override
	public Stream<Player> get() {
		return Collections.singleton(player).stream();
	}

}