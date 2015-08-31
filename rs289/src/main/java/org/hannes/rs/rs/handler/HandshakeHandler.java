package org.hannes.rs.rs.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;

import org.hannes.rs.event.handler.EventHandler;
import org.hannes.rs.event.handler.context.EventHandlerContext;
import org.hannes.rs.event.handler.context.Listens;
import org.hannes.rs.rs.util.HandshakeResponse;

/**
 * Handles the login events which should marked as "undefined"
 * 
 * @author Red
 */
@Listens("handshake")
public class HandshakeHandler implements EventHandler {
	
	/**
	 * The random number generator, used for generating a random server seed
	 */
	private static final Random random = new Random();

	@Override
	public void onReceive(EventHandlerContext ctx) throws IOException {
		ByteBuffer payload = ctx.get("payload");
		
		/*
		 * junk data?
		 */
		@SuppressWarnings("unused")
		int junk$0 = payload.get() & 0xFF;
		
		/*
		 * The server key
		 */
		long server_key = random.nextLong();
		
		/*
		 * Save the server key in the attributes
		 */
		ctx.getSession().attributes().set("server-key", server_key);
		
		/*
		 * Write the login response
		 */
		ctx.write(new HandshakeResponse(server_key));
	}

}