package org.hannes.rs.net.netty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.hannes.rs.net.IOHandler;
import org.hannes.rs.net.Session;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

@Sharable
@ApplicationScoped
public class NettyChannelHandler extends ChannelHandlerAdapter {
	
	/**
	 * The session provider
	 */
	@Inject private Instance<Session> sessions;
	
	/**
	 * The io handler
	 */
	@Inject private IOHandler handler;

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		/*
		 * Create a new session
		 */
		Session session = sessions.get();
		
		/*
		 * Register a new NettyChannel that handles the reading and writing of the messages
		 */
		session.register(new NettyChannel(session, handler, ctx.channel()));
		
		/*
		 * Set the session as a context attribute
		 */
		ctx.attr(Session.ATTRIBUTE_KEY).set(session);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ctx.attr(Session.ATTRIBUTE_KEY).get().channel().read(msg);
	}

}