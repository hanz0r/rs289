package org.hannes.rs.net.netty.codec;

import java.nio.ByteBuffer;
import java.util.List;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class ByteBufferEncoder extends MessageToMessageEncoder<ByteBuffer> {

	@Override
	protected void encode(ChannelHandlerContext ctx, ByteBuffer msg, List<Object> out) throws Exception {
		out.add(Unpooled.wrappedBuffer((ByteBuffer) msg.flip()));
	}

}