package org.hannes.scoundrel.net.netty;

import java.io.IOException;

import org.hannes.scoundrel.net.Channel;
import org.hannes.scoundrel.net.IOHandler;
import org.hannes.scoundrel.net.Session;
import org.hannes.scoundrel.net.context.IOContext;
import org.hannes.scoundrel.net.message.Message;
import org.hannes.scoundrel.util.Serializable;

public class NettyChannel implements Channel {

	/**
	 * The session for this channel
	 */
	private final Session session;

	/**
	 * the io handler
	 */
	private final IOHandler handler;
	
	/**
	 * The Netty channel
	 */
	private final io.netty.channel.Channel nettyChannel;

	public NettyChannel(Session session, IOHandler handler, io.netty.channel.Channel nettyChannel) {
		this.session = session;
		this.handler = handler;
		this.nettyChannel = nettyChannel;
	}

	@Override
	public void write(Object object) throws IOException {
		nettyChannel.writeAndFlush(object instanceof Serializable ? ((Serializable) object).serialize(session) : object);
	}

	@Override
	public void read(Object object) throws IOException {
		if (object instanceof Message) {
			handler.fireRead(new IOContext(session, (Message) object, handler));
		}
	}

	@Override
	public IOHandler getIOHandler() {
		return handler;
	}

}