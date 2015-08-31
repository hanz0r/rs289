package org.hannes.rs.net.netty.codec;

import java.nio.ByteBuffer;
import java.util.List;

import org.apache.log4j.Logger;
import org.hannes.rs.net.Session;
import org.hannes.rs.net.Session.State;
import org.hannes.rs.net.message.Header;
import org.hannes.rs.net.message.Message;
import org.hannes.rs.net.message.Header.MetaData;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

/**
 * 
 * @author Red
 *
 */
public class GameCodec extends MessageToMessageCodec<ByteBuf, Message> {
	
	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GameCodec.class);

	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
		ByteBuffer buffer = msg.getHeader().prepare();
		
		/*
		 * debug buffer
		 */
		logger.debug("prepared buffer: " + buffer);
		
		/*
		 * Put the message's payload
		 */
		buffer.put(msg.getPayload());
		
		/*
		 * Write the bytebuffer
		 */
		out.add(Unpooled.copiedBuffer(buffer));
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		Session session = ctx.attr(Session.ATTRIBUTE_KEY).get();
	
		/*
		 * The opcode of the message
		 */
		int opcode = msg.readByte() & 0xFF;
		
		/*
		 * The length of the message
		 */
		int length = session.getState().equals(State.DISCOVERED) ? msg.readableBytes() : msg.readableBytes();
		
		/*
		 * Testing
		 */
		Header header = new Header(length, opcode, MetaData.RAW);

		/*
		 * Testing 2
		 */
		out.add(new Message(header, msg.nioBuffer()));
	}

}