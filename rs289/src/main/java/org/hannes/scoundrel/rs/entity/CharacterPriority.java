package org.hannes.scoundrel.rs.entity;

import java.util.Comparator;

public class CharacterPriority implements Comparator<Character>{

	public static final CharacterPriority COMPARATOR = new CharacterPriority();

	@Override
	public int compare(Character o1, Character o2) {
		return o1.distance(o2);
	}

}