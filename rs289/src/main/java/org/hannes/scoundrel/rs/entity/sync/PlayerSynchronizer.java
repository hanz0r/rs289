package org.hannes.scoundrel.rs.entity.sync;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Queue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.hannes.scoundrel.rs.entity.EntityManager;
import org.hannes.scoundrel.rs.entity.Player;

@ApplicationScoped
public class PlayerSynchronizer implements Synchronizer {

	/**
	 * The player entity manager
	 */
	@Inject private EntityManager<Player> manager;

	@Override
	public final ByteBuffer synchronize() {
		for (Iterator<Player> iterator = manager.iterator(); iterator.hasNext(); ) {
			Player player = iterator.next();
			
			/*
			 * 
			 */
			if (player == null) {
				continue;
			}
			
			/*
			 * 
			 */
			Queue<Player> surrounding_players = manager.getSurroundingEntities(player);
			
			
		}
		return null;
	}

	/**
	 * 
	 * @author user104
	 *
	 */
	public static enum RenderingHint {

		/**
		 * Render the entity's appearance
		 */
		APPEARANCE(0x1),
		
		/**
		 * Make the entity perform an animation
		 */
		ANIMATION(0x2),
		
		/**
		 * Indicate the entity needs to be rotated toward a target entity
		 */
		FACE_ENTITY(0x4),
		
		/**
		 * Indicate the primary hitmarker needs to be rendered
		 */
		HIT(0x10),
		
		/**
		 * Indicate the entity needs to be rotated towards a target location
		 */
		FACE_COORDINATE(0x20),
		
		/**
		 * Indicate the renderer needs to draw the entity's chat
		 */
		CHAT(0x40),
		
		/**
		 * Indicate the renderer needs to draw a visual effect
		 */
		GRAPHICS(0x100),
		
		/**
		 * Indicate the secondary hitmarker needs to be rendered
		 */
		HIT_2(0x400);
		
		/**
		 * 
		 */
		private final int identifier;

		private RenderingHint(int identifier) {
			this.identifier = identifier;
		}

		public int getIdentifier() {
			return identifier;
		}

	}

}