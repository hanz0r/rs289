package org.hannes.rs.net.netty;

import org.hannes.rs.net.netty.codec.ByteBufferEncoder;
import org.hannes.rs.net.netty.codec.GameCodec;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

	/**
	 * The channel handler
	 */
	private final ChannelHandler handler;

	public NettyChannelInitializer(ChannelHandler handler) {
		this.handler = handler;
	}

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		ChannelPipeline pipeline = channel.pipeline();
		pipeline.addLast("codec", new GameCodec());
		pipeline.addLast("bytebuffer-codec", new ByteBufferEncoder());
		pipeline.addLast("timeout", new IdleStateHandler(30, 0, 0)); // TODO: put in properties file
		pipeline.addLast("handler", handler);
	}

}