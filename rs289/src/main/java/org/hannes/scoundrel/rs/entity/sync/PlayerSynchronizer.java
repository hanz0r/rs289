package org.hannes.scoundrel.rs.entity.sync;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

import org.hannes.scoundrel.rs.entity.EntityManager;
import org.hannes.scoundrel.rs.entity.Player;
import org.hannes.scoundrel.rs.entity.render.RenderingContext;

@ApplicationScoped
public class PlayerSynchronizer implements Synchronizer<Player> {

	@Override
	public final ByteBuffer synchronize(EntityManager<Player> manager) {
		
		return null;
	}

	private final ByteBuffer render(RenderingContext context) {
		ByteBuffer buffer = ByteBuffer.allocate(1024 * 80);
		
		/*
		 * Prepare the 
		 */
		
		/*
		 * 
		 */
		
		/*
		 * 
		 */
		return (ByteBuffer) buffer.flip();
	}

}