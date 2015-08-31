package org.hannes.scoundrel.rs.util;

import java.nio.ByteBuffer;

public class ByteBufferUtil {

	public static String readString(ByteBuffer buffer) {
		StringBuilder builder = new StringBuilder();
		for (byte b = buffer.get(); b != 10 && buffer.hasRemaining(); b = buffer.get()) {
			builder.append((char) b);
		}
		return builder.toString();
	}

}