package org.hannes.scoundrel.net.netty.codec;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class ReflectionEncoder extends MessageToMessageEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
		System.out.println(msg);
	}

}