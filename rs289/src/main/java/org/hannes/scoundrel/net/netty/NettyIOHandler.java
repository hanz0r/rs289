package org.hannes.scoundrel.net.netty;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.hannes.scoundrel.event.handler.EventDispatcher;
import org.hannes.scoundrel.event.transform.TransformState;
import org.hannes.scoundrel.event.transform.TransformationContext;
import org.hannes.scoundrel.event.transform.Transformer;
import org.hannes.scoundrel.net.IOHandler;
import org.hannes.scoundrel.net.Session.State;
import org.hannes.scoundrel.net.context.IOContext;
import org.hannes.scoundrel.net.context.Read;
import org.hannes.scoundrel.net.context.Write;
import org.hannes.scoundrel.util.Initializable;
import org.jboss.weld.exceptions.UnsupportedOperationException;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@ApplicationScoped
public class NettyIOHandler implements IOHandler, Initializable {

	/**
	 * The server bootstrap
	 */
	private final ServerBootstrap bootstrap = new ServerBootstrap();
	
	/**
	 * The transformer map
	 */
	private final Map<State, Transformer> transformers = new HashMap<>();
	
	/**
	 * The netty channel handler implementation
	 */
	@Inject private NettyChannelHandler handler;
	
	/**
	 * The dispatcher for the game events
	 */
	@Inject private EventDispatcher dispatcher;
	
	/**
	 * The collection of transformer instances
	 */
	@Inject private Instance<Transformer> transformer_classes;

	/**
	 * The dispatcher for the IO events
	 */
	@Inject @Read private Event<IOContext> read_events;

	/**
	 * The dispatcher for the IO events
	 */
	@Inject @Write private Event<IOContext> write_events;

	@Override
	public void bind(SocketAddress address) throws IOException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			bootstrap.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new NettyChannelInitializer(handler))
					.option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);

			/*
			 * Bind and start to accept incoming connections.
			 */
			ChannelFuture f = bootstrap.bind(address).sync();

			/*
			 * Wait until the server socket is closed to shut down the server
			 */
			f.channel().closeFuture().sync();
		} catch (InterruptedException ex) {
			throw new IOException("could not start server",ex);
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	@Override
	public void initialize() {
		transformer_classes.forEach(t -> transformers.put(t.getClass().getDeclaredAnnotation(TransformState.class).value(), t));
	}

	public void onRead(@Observes @Read IOContext event) {
		dispatcher.dispatch(transformers.get(event.getSession().state().get()).transform(new TransformationContext(event.getSession(), event.getMessage())), event.getSession());
	}

	public void onWrite(@Observes @Write IOContext event) {
		System.out.println(event);
	}

	@Override
	public void fireRead(IOContext event) {
		read_events.fire(event);
	}

	@Override
	public void fireWrite(IOContext event) {
		write_events.fire(event);
	}

	@Override
	public void close() throws IOException {
		throw new UnsupportedOperationException("not yet implemented");
	}

}