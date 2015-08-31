package org.hannes.scoundrel.rs.handler;

import java.io.IOException;
import java.nio.ByteBuffer;

import javax.inject.Inject;

import org.hannes.scoundrel.event.Event;
import org.hannes.scoundrel.event.handler.EventHandler;
import org.hannes.scoundrel.event.handler.context.EventHandlerContext;
import org.hannes.scoundrel.event.handler.context.Listens;
import org.hannes.scoundrel.net.Session.State;
import org.hannes.scoundrel.rs.event.LoginEvent;
import org.hannes.scoundrel.rs.util.ByteBufferUtil;
import org.hannes.scoundrel.rs.util.LoginResponse;
import org.hannes.scoundrel.rs.util.LoginResponse.Result;

@Listens("login")
public class LoginHandler implements EventHandler {

	@Inject
	private javax.enterprise.event.Event<LoginEvent> event;

	@Override
	@SuppressWarnings("unused")
	public void onReceive(EventHandlerContext ctx) throws IOException {
		ByteBuffer payload = ctx.getEvent().get(Event.PAYLOAD);

		/*
		 * The size of the player credentials chunk. This got rendered useless by the person who fixed the client
		 * to work without the RSA encryption.
		 */
		int size = payload.get();
		
		/*
		 * Read the client version. (release:
		 */
		int release = payload.get();
		int version = payload.getShort();
		
		/*
		 * Check to see if the user's client's version is equal to the server's version
		 */
		if (version != 289 || release != -1) {
			ctx.write(new LoginResponse(Result.GAME_UPDATED));
			return;
		}
		
		/*
		 * Indicates the player's client detail
		 */
		boolean high_detail = payload.get() != 0;
		
		/*
		 * crc of the player's client archives
		 */
		int[] crc = new int[9];
		for (int i = 0; i < crc.length; i++) {
			crc[i] = payload.getInt();
		}
		
		/*
		 * Size of the credential portion of the login packet. This has also been broken by the person
		 * who removed the RSA encryption from the client.
		 */
		int credential_size = payload.get();
		
		/*
		 * Needs to be 10
		 */
		int secure_id = payload.get();
		if (secure_id != 10) {
			ctx.write(new LoginResponse(Result.BAD_SESSION_ID));
			return;
		}
		
		/*
		 * The client and server ISAAC keys. Used for encrypting the opcodes of each packet. These are not used
		 * anymore since ISAAC has been removed from the client.
		 */
		long client_key = payload.getLong();
		long server_key = payload.getLong();
		
		/*
		 * See if the server key is  synchronized with the client
		 */
		if (!ctx.getSession().attributes().get("server-key").equals(server_key)) {
			ctx.write(new LoginResponse(Result.LOGIN_SERVER_REJECTED_SESSION));
			return;
		}
		
		int uuid = payload.getInt();
		
		/*
		 * The username and password
		 */
		String username = ByteBufferUtil.readString(payload);
		String password = ByteBufferUtil.readString(payload);
		
		/*
		 * Set the client's connection state to active
		 */
		ctx.getSession().setState(State.ACTIVE);
		
		/*
		 * Fire a login event
		 */
		event.fire(new LoginEvent(ctx.getSession(), username, password, server_key, client_key));
	}

}